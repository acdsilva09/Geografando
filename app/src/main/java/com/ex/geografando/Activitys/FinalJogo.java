package com.ex.geografando.Activitys;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.ex.geografando.DAO.CategoriaDAO;
import com.ex.geografando.DAO.ConquistasDAO;
import com.ex.geografando.DAO.PartidaDAO;
import com.ex.geografando.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class FinalJogo extends AppCompatActivity {


    private static TextView textPontos, textEstatistica, textResult;
    private int pontos, pontuacaoMaxima, pontuacaoMedia;

    private ArrayList listaEstatisticasFinalJogo;
    private InterstitialAd mInterstitialAd;

    private AdView mAdView;

    private MediaPlayer player;

    private Button btnVoltar, btnJogarNovamente;

    private boolean catDesbloqueada=false;

    private boolean sons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_final_jogo);
        escondeNavigationBar();
        textPontos = (TextView) findViewById(R.id.textPontos);
        textEstatistica = (TextView) findViewById(R.id.textEstatisticaAleatoria);
        textResult = (TextView) findViewById(R.id.textViewResult);
        listaEstatisticasFinalJogo = preencheArrayEstatisticasFinalJogo();
        textEstatistica.setVisibility(View.INVISIBLE);
        Bundle extras = getIntent().getExtras();
        pontos = extras.getInt("pontos");




        if(pontos==0){

            textResult.setText("VOC?? PERDEU!");
            pontos = 0;
        }

        if(pontos>0){
            textResult.setText("VOC?? VENCEU!");
            mostraEstatistica((Integer) listaEstatisticasFinalJogo.get(0));}


        mostraPontuacao();

        loadAdMob();


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adViewBannerMain);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.admob_interstitial_teste));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());


        mInterstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                startActivity(new Intent(com.ex.geografando.Activitys.FinalJogo.this, MainActivity.class));
                finishAffinity();
            }
        });


        verificaDesbloqueioCategoria();
        verificaConquista();
        if(!catDesbloqueada)
        {
                play();
        }
        verificaDesbloqueioQtdPerguntas();
    }

    private void verificaDesbloqueioQtdPerguntas() {
        CategoriaDAO dao = new CategoriaDAO(this);
        PartidaDAO partidaDAO = new PartidaDAO(this);
        int qtd = dao.selecaoQtdPerguntas();
        int vitoriaPartida = partidaDAO.statusVitoriaUltimaPartida();
        if(vitoriaPartida==1){
            if(qtd==15){
                dao.atualizaQtdHabilitada(2);
            }else if(qtd==30){
                dao.atualizaQtdHabilitada(3);
            }
        }
    }

    private void verificaConquista() {
        ConquistasDAO dao = new ConquistasDAO(this);
        dao.consultaDesbloqueioConquista();
    }

    private void verificaDesbloqueioCategoria() {
        List<Integer> listCategorias = new ArrayList<>(Arrays.asList(6,3,1,2,4,9,5,7,8,10));
        CategoriaDAO dao = new CategoriaDAO(this);
        loop:
        for(int i=0; i<9;i++) {
            boolean categoriaHabilitada = dao.consultaCategoriaHabilitada(listCategorias.get(i+1));

            if(!categoriaHabilitada){
                int qtd = dao.consultaPerguntasCertasCategoria(listCategorias.get(i));
                if(qtd>=50){

                    dao.desbloqueiaCategoria(listCategorias.get(i+1));

                    String categoria = dao.consultaNomeCategoria(listCategorias.get(i+1));
                    categoria = categoria.toUpperCase();
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    AlertDialog alerta = builder.create();
                    View view = LayoutInflater.from(com.ex.geografando.Activitys.FinalJogo.this).inflate(R.layout.nova_categoria,null);
                    TextView textNovaCategoria = (TextView) view.findViewById(R.id.textNovaCategoria1);

                    textNovaCategoria.setText(categoria);

                    alerta.setView(view);
                    alerta.show();
                    catDesbloqueada=true;
                    playCategoriaDesbloqueada();
                }
                break loop;
            }
        }

    }

    private void playCategoriaDesbloqueada() {

        CategoriaDAO categoria_dao = new CategoriaDAO(this);
        if(categoria_dao.consultaSonsHabilitados()) {
            player = MediaPlayer.create(this, R.raw.new_level);
            player.start();
        }
    }


    public void play(){


            CategoriaDAO categoria_dao = new CategoriaDAO(this);



        if(categoria_dao.consultaSonsHabilitados()){

            if (player == null) {

                if (pontos == 0) {
                    player = MediaPlayer.create(this, R.raw.lose);
                } else {
                    player = MediaPlayer.create(this, R.raw.win);
                }
            }
            player.start();
        }
    }


    private void mostraEstatistica(int i) {
        PartidaDAO dao = new PartidaDAO(this);
        int totalPartidasVencidas = dao.consultaTotalPartidasVencidas(1);
        pontuacaoMaxima = dao.consultaMelhorPontuacao(1);

        if(totalPartidasVencidas > 3) {
            if(pontos>=pontuacaoMaxima){
                textEstatistica.setText("NOVO RECORDE!!!");
                textEstatistica.setVisibility(View.VISIBLE);
            }else if (i == 1) {
                pontuacaoMaxima = dao.consultaMelhorPontuacao(1);

                if (pontos < pontuacaoMaxima) {
                    int diferenca = pontuacaoMaxima - pontos;
                    textEstatistica.setText("Sua pontua????o foi " + diferenca + " menor que a pontua????o m??xima");
                    textEstatistica.setVisibility(View.VISIBLE);
                } else if (pontos > pontuacaoMaxima) {
                    int diferenca = pontos - pontuacaoMaxima;
                    textEstatistica.setText("Sua pontua????o foi " + diferenca + " maior que a pontua????o m??xima");
                    textEstatistica.setVisibility(View.VISIBLE);
                }
            } else if (i == 2) {
                pontuacaoMedia = (dao.consultaTotalPontuacao(1) / dao.consultaTotalPartidasConcluidas(1));
                if (pontos < pontuacaoMedia) {
                    int diferenca = pontuacaoMedia - pontos;
                    textEstatistica.setText("Sua pontua????o foi " + diferenca + " menor que a m??dia de pontos");
                    textEstatistica.setVisibility(View.VISIBLE);

                } else if (pontos > pontuacaoMedia) {
                    int diferenca = pontos - pontuacaoMedia;
                    textEstatistica.setText("Sua pontua????o foi " + diferenca + " maior que a m??dia de pontos");
                    textEstatistica.setVisibility(View.VISIBLE);
                }

            }
        }else{
            textEstatistica.setVisibility(View.INVISIBLE);
        }

    }


    public void escondeNavigationBar(){

        this.getWindow().getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);


    }


    //Este m??todo ir?? listar as poss??veis estatistica a serem mostradas na tela finalJogo, sendo
    //Ser?? selecionaa uma estat??stica aleat??ria
    private ArrayList preencheArrayEstatisticasFinalJogo() {
        //1-Diferen??a para a recorde de pontos
        //2-Diferen??a para pontua????o m??dia

        //
        ArrayList list = new ArrayList();
        for(int i=1;i<=2;i++){
            list.add(i);
        }
        Collections.shuffle(list);
        return list;
    }


    public void mostraPontuacao(){
        Locale localeBR = new Locale("pt","BR");
        NumberFormat numberFormat = NumberFormat.getNumberInstance(localeBR);
        textPontos.setText(""+numberFormat.format(pontos));

    }

    public void voltar(View view){

        if(mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }else {
            startActivity(new Intent(com.ex.geografando.Activitys.FinalJogo.this, MainActivity.class));
            finishAffinity();
        }

    }

    public void jogarNovamente(View view){

        PartidaDAO daoPartida = new PartidaDAO(this);
        daoPartida.gravaJogoPendente(0);
        Intent i = new Intent(com.ex.geografando.Activitys.FinalJogo.this, JogoActivity.class);
        startActivity(i);
        finishAffinity();

    }

    public void onBackPressed(){ //Bot??o BACK padr??o do android

        if(mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }else {
            startActivity(new Intent(this, MainActivity.class)); //O efeito ao ser pressionado do bot??o (no caso abre a activity)
            finishAffinity(); //M??todo para matar a activity e n??o deixa-l?? indexada na pilhagem
        }
        return;
    }


    private void loadAdMob() {
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.admob_interstitial_teste));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }



}
