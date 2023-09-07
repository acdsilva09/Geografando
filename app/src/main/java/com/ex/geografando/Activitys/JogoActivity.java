package com.ex.geografando.Activitys;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.ex.geografando.DAO.AlternativaDAO;
import com.ex.geografando.DAO.CategoriaDAO;
import com.ex.geografando.DAO.PartidaDAO;
import com.ex.geografando.DAO.PerguntaDAO;
import com.ex.geografando.Model.Alternativa;
import com.ex.geografando.Model.Pais;
import com.ex.geografando.Model.Partida;
import com.ex.geografando.Model.Pergunta;
import com.ex.geografando.R;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class JogoActivity extends AppCompatActivity {

    private ArrayList<Integer> listaPerguntas = new ArrayList<>();
    private Map<String, Integer> acertosContinentes = new HashMap<String, Integer>();
    private ArrayList<String> listaAlternativasTemp = new ArrayList<>();
    private static TextView enunciado, codPergunta, categoriaPergunta, labelQtdPergunta,textTimer;
    private int pontos=0, coeficientePontos=0, qtdPerguntas=0,codPartida, qtdDicas=3,vidas=3,
            dicasUsadasPergunta=0,flagJogoPendente,timeTotal=31000, alternativasDisponiveis;
    private ImageView bandeira, imageViewVida1,imageViewVida2,imageViewVida3;
    private Pais paisTemporario;
    private Button buttonAlternativa1, buttonAlternativa2,buttonAlternativa3,buttonAlternativa4, buttonAjuda;
    private ImageButton buttonSom;
    private Drawable backgorundButton;
    private ProgressBar barTimer;
    private boolean vibrar, sons, activityOnResume =false, timerRun;
    private InterstitialAd mInterstitialAd;
    private AdView mAdView;
    private CountDownTimer countDownTimer;
    private long timeLeft = 31000;
    private RewardedAd mRewardedAd;
    private MediaPlayer player;
    private final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);

        acertosContinentes.put("África", 0);
        acertosContinentes.put("América", 0);
        acertosContinentes.put("Ásia", 0);
        acertosContinentes.put("Europa", 0);
        acertosContinentes.put("Oceania", 0);
        flagJogoPendente = consultaJogoPendente();
        activityOnResume=true;
        textTimer = (TextView) findViewById(R.id.textViewTimer);
        textTimer.setText("30");
        barTimer = (ProgressBar) findViewById(R.id.barTimer);
        categoriaPergunta = (TextView) findViewById(R.id.textViewCategoriaPergunta);
        buttonAlternativa1 = (Button) findViewById(R.id.buttonAlternativa1);
        buttonAlternativa2 = (Button) findViewById(R.id.buttonAlternativa2);
        buttonAlternativa3 = (Button) findViewById(R.id.buttonAlternativa3);
        buttonAlternativa4 = (Button) findViewById(R.id.buttonAlternativa4);
        enunciado = (TextView) findViewById(R.id.textViewEnunciado);
        codPergunta = (TextView) findViewById(R.id.textViewCodPergunta);
        bandeira = (ImageView) findViewById(R.id.imageViewBandeira);
        labelQtdPergunta = (TextView) findViewById(R.id.textViewQtdPergunta);
        coeficientePontos = quantidadeCategorias();
        qtdPerguntas = quantidadePerguntas();
        backgorundButton = buttonAlternativa1.getBackground();
        buttonAjuda = (Button) findViewById(R.id.buttonAjuda);
        buttonSom = (ImageButton)findViewById(R.id.btnSom);

        imageViewVida1 = (ImageView) findViewById(R.id.imageViewVida1);
        imageViewVida2 = (ImageView) findViewById(R.id.imageViewVida2);
        imageViewVida3 = (ImageView) findViewById(R.id.imageViewVida3);
        vibrar = consultaParametroVibrar();
        sons = consultaParametroSons();
        atualizaBotaoSons();
        verificaJogoIniciar(flagJogoPendente);

        MobileAds.initialize(this, new OnInitializationCompleteListener(){
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus)
            {

            }
        });

        mAdView = findViewById(R.id.adViewBannerJogo);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        loadAdMob();

        onRequestVideoAd();
    }

    private void playRespostaCerta() {
        if(sons) {
            player = MediaPlayer.create(this, R.raw.correct);
            player.start();
        }
    }

    private void atualizaBotaoSons(){
        Resources res = getResources();
        int resourceId;
        if(sons){
            resourceId = res.getIdentifier("som1","drawable", getPackageName());
        }else{
            resourceId = res.getIdentifier("som0","drawable", getPackageName());
        }
        buttonSom.setImageResource(resourceId);
    }
    public void buttonSom(View view){
        CategoriaDAO dao = new CategoriaDAO(this);
        if(sons){
            sons=false;
            dao.atualizaParametroSom(0);
        }else{
            sons=true;
            dao.atualizaParametroSom(1);
        }
        atualizaBotaoSons();
    }

    public void onRequestVideoAd()    {

        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(this, String.valueOf(R.string.admob_reward_ad_activity_jogo),
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        Log.d(TAG, loadAdError.toString());
                        mRewardedAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd ad) {
                        mRewardedAd = ad;
                        Log.d(TAG, "Ad was loaded.");
                    }
                });
    }


    public void onShowRewardedAd(){
        mRewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
            @Override
            public void onAdClicked() {
                // Called when a click is recorded for an ad.
                Log.d(TAG, "Ad was clicked.");
            }

            @Override
            public void onAdDismissedFullScreenContent() {
                // Called when ad is dismissed.
                // Set the ad reference to null so you don't show the ad a second time.
                Log.d(TAG, "Ad dismissed fullscreen content.");
                mRewardedAd = null;
                startTimer();
                onRequestVideoAd();
            }

            @Override
            public void onAdFailedToShowFullScreenContent(AdError adError) {
                // Called when ad fails to show.
                Log.e(TAG, "Ad failed to show fullscreen content.");
                mRewardedAd = null;
            }

            @Override
            public void onAdImpression() {
                // Called when an impression is recorded for an ad.
                Log.d(TAG, "Ad recorded an impression.");
            }

            @Override
            public void onAdShowedFullScreenContent() {
                // Called when ad is shown.
                Log.d(TAG, "Ad showed fullscreen content.");
            }
        });



        if(mRewardedAd== null)
        {
            Toast.makeText( com.ex.geografando.Activitys.JogoActivity.this, "Falha.\nTente Novamente", Toast.LENGTH_SHORT).show();
            onRequestVideoAd();
            startTimer();
            return;
        }else{
            mRewardedAd.show(com.ex.geografando.Activitys.JogoActivity.this, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                    // Handle the reward.
                    limpaAlternativaErrada();
                }
            });
        }


    }

    private boolean consultaParametroVibrar() {
        CategoriaDAO categoria_dao = new CategoriaDAO(this);
        return categoria_dao.consultaVibrarHabilitado();
    }

    private boolean consultaParametroSons() {
        CategoriaDAO categoria_dao = new CategoriaDAO(this);
        return categoria_dao.consultaSonsHabilitados();
    }

    private int consultaJogoPendente() {
        int i=0;
            PartidaDAO daoP = new PartidaDAO(this);
            i=daoP.consultaJogoPendente();
        return i;
    }

    public void verificaJogoIniciar(int flag_jogo_pendente){
        PartidaDAO dao_partida = new PartidaDAO(this);

        if(flag_jogo_pendente == 0){

            codPartida =dao_partida.consultaUltimoCodPartida();
            criaJogo();
            buscaPergunta();

        }else
            if(flag_jogo_pendente==1){


                PartidaDAO daoPartida = new PartidaDAO(this);
                codPartida = (daoPartida.consultaUltimoCodPartida() - 1);
                qtdDicas = daoPartida.consultaAjudasRestantes(codPartida);
                int qtdAltdisponiveis = daoPartida.consultaQtdAlternativasDisponiveis();
                if(qtdAltdisponiveis==0){
                    buttonAjuda.setEnabled(false);

                    buttonAjuda.setBackgroundResource(R.drawable.buttom_help_false);
                }


                listaPerguntas = dao_partida.consultaPerguntasPorPartida(codPartida);
                pontos = dao_partida.consultaPontuacaoPartida(codPartida);
                qtdPerguntas = dao_partida.consultaQuatidadePerguntas(codPartida);
                coeficientePontos = dao_partida.consultaCoeficientePontos(codPartida);
                vidas = dao_partida.consultaVidasRestantes(codPartida);

                int valorCodPergunta =daoPartida.consultaCodPerguntaTemp();
                int valorCategoria = daoPartida.consultaCodCategoriaTemp();

                timeLeft = (dao_partida.consultaTempoRestantePerguntaTemp()*1000);
                timeTotal=(int)timeLeft;
                codPergunta.setText(Integer.toString(valorCodPergunta));
                categoriaPergunta.setText(Integer.toString(valorCategoria));
                listaAlternativasTemp = daoPartida.consultaAlternativasTemp();

                buscaPergunta();

        }

    }

    public void criaJogo(){
        Partida partida = new Partida();
        PartidaDAO daoPartida = new PartidaDAO(this);
        CategoriaDAO daoCategoria = new CategoriaDAO(this);
        partida.setCodPartida(1);
        partida.setNivel(1);
        partida.setCodUsuario(1);
        partida.setPontuacao(0);
        partida.setConcluida(0);
        partida.setCapital(daoCategoria.verificaSelecaoEspecifica(1));
        partida.setBandeira(daoCategoria.verificaSelecaoEspecifica(2));
        partida.setIdioma(daoCategoria.verificaSelecaoEspecifica(3));
        partida.setPopulacao(daoCategoria.verificaSelecaoEspecifica(4));
        partida.setArea(daoCategoria.verificaSelecaoEspecifica(5));
        partida.setContinente(daoCategoria.verificaSelecaoEspecifica(6));
        partida.setMoeda(daoCategoria.verificaSelecaoEspecifica(7));
        partida.setEmblema(daoCategoria.verificaSelecaoEspecifica(8));
        partida.setMapa(daoCategoria.verificaSelecaoEspecifica(9));
        partida.setCidades(daoCategoria.verificaSelecaoEspecifica(10));
        partida.setQuantidade_perguntas_respondidas(0);
        partida.setQuantidade_perguntas_total(daoCategoria.selecaoQtdPerguntas());
        partida.setVitoria(0);
        partida.setVidasRestantes(vidas);
        partida.setAjudasRestantes(qtdDicas);
        daoPartida.inserePartida(partida);
    }
    private int quantidadePerguntas() {
        int qtd;
        CategoriaDAO categoria_dao = new CategoriaDAO(this);
        qtd=categoria_dao.selecaoQtdPerguntas();
        return qtd;
    }
    private int quantidadeCategorias() {
        int qtdCategorias=0;
        CategoriaDAO categoria_dao = new CategoriaDAO(this);
        qtdCategorias=categoria_dao.qtdCategoria();

        return qtdCategorias;
    }
    @Override
    protected void onResume(){
        super.onResume();
        escondeNavigationBar();
    }
    public void buttonAjuda(View view){
        if(qtdDicas>0&&alternativasDisponiveis>1)
        {
                limpaAlternativaErrada();
                qtdDicas--;
                if(qtdDicas>0)
                {
                    buttonAjuda.setText("AJUDA (" + qtdDicas + ")");
                }else{
                    buttonAjuda.setText("AJUDA (AD)");
                }
                dicasUsadasPergunta++;
                PartidaDAO daoPartida = new PartidaDAO(this);
                codPartida = (daoPartida.consultaUltimoCodPartida() - 1);
                daoPartida.atualizaAjudasdas(codPartida,qtdDicas);


        }else if(qtdDicas==0 && alternativasDisponiveis>1){

            countDownTimer.cancel();

            onShowRewardedAd();
        }
    }
    public void limpaAlternativaErrada(){
        PartidaDAO partidaDAO = new PartidaDAO(this);
        PerguntaDAO pergunta_dao = new PerguntaDAO(this);
        String cod_Pergunta = (String) codPergunta.getText();
        String codCategoriaPergunta = (String) categoriaPergunta.getText();
        ArrayList<Button> botoes = new ArrayList<>();
        if (buttonAlternativa1.isEnabled())
        {
            if (!pergunta_dao.confereResposta((String) buttonAlternativa1.getText(), cod_Pergunta, codCategoriaPergunta))
            {
                botoes.add(buttonAlternativa1);
            }
        }
        if (buttonAlternativa2.isEnabled())
        {
            if (!pergunta_dao.confereResposta((String) buttonAlternativa2.getText(), cod_Pergunta, codCategoriaPergunta))
            {
                botoes.add(buttonAlternativa2);
            }
        }
        if (buttonAlternativa3.isEnabled())
        {
            if (!pergunta_dao.confereResposta((String) buttonAlternativa3.getText(), cod_Pergunta, codCategoriaPergunta))
            {
                botoes.add(buttonAlternativa3);
            }
        }

        if (buttonAlternativa4.isEnabled())
        {
            if (!pergunta_dao.confereResposta((String) buttonAlternativa4.getText(), cod_Pergunta, codCategoriaPergunta))
            {
                botoes.add(buttonAlternativa4);
            }
        }
        Collections.shuffle(botoes);

        alternativasDisponiveis--;
        if(alternativasDisponiveis==1){
            buttonAjuda.setEnabled(false);
            buttonAjuda.setBackgroundResource(R.drawable.buttom_help_false);
        }
        partidaDAO.updateAlternativasDisponiveis(alternativasDisponiveis);
        partidaDAO.atualizaTabelaAlternativasDisponiveis(botoes.get(0).getText());
        botoes.get(0).setEnabled(false);
        botoes.get(0).setVisibility(View.INVISIBLE);
    }
    @SuppressLint("ResourceAsColor")
    public void button_Alternativa1(View view){
        setEnableFalseButtons();
        stopTimer();
        int timeLeft = Integer.parseInt(String.valueOf(textTimer.getText()));
        PerguntaDAO pergunta_dao = new PerguntaDAO(this);
        String alternativaSelecionada = (String) buttonAlternativa1.getText();
        String cod_Pergunta = (String) codPergunta.getText();
        String codCategoriaPergunta = (String) categoriaPergunta.getText();
        mostraAreaPopulacaoPais(codCategoriaPergunta);
        int codPerguntaInt = Integer.parseInt(cod_Pergunta);
        int codCategoriaInt = Integer.parseInt(codCategoriaPergunta);
        boolean respostaCerta = pergunta_dao.confereResposta(alternativaSelecionada,cod_Pergunta,codCategoriaPergunta);
        buttonAlternativa1 = verificaResposta(respostaCerta, buttonAlternativa1,timeLeft);
        buttonAlternativa1.postDelayed(new Runnable() {
            @Override
            public void run() {

                    buttonAlternativa1.setBackground(backgorundButton);
                    buscaPergunta();

            }
        },2500);
        deletePerguntaTemp();
        updatePartida();
        pergunta_dao.atualizaPartidaPerguntas(codPartida,codPerguntaInt,codCategoriaInt,respostaCerta);

    }
    @SuppressLint("ResourceAsColor")
    public void button_Alternativa2(View view){
        setEnableFalseButtons();
        stopTimer();
        int timeLeft = Integer.parseInt(String.valueOf(textTimer.getText()));
        PerguntaDAO pergunta_dao = new PerguntaDAO(this);
        String alternativaSelecionada = (String) buttonAlternativa2.getText();
        String cod_Pergunta = (String) codPergunta.getText();
        String codCategoriaPergunta = (String) categoriaPergunta.getText();
        mostraAreaPopulacaoPais(codCategoriaPergunta);
        int codPerguntaInt = Integer.parseInt(cod_Pergunta);
        int codCategoriaInt = Integer.parseInt(codCategoriaPergunta);
        boolean respostaCerta = pergunta_dao.confereResposta(alternativaSelecionada,cod_Pergunta,codCategoriaPergunta);
        buttonAlternativa2 = verificaResposta(respostaCerta, buttonAlternativa2,timeLeft);
        buttonAlternativa2.postDelayed(new Runnable() {
            @Override
            public void run() {
                buttonAlternativa2.setBackground(backgorundButton);
                buscaPergunta();
            }
        },2500);
        deletePerguntaTemp();
        updatePartida();
        pergunta_dao.atualizaPartidaPerguntas(codPartida,codPerguntaInt,codCategoriaInt,respostaCerta);

    }
    @SuppressLint("ResourceAsColor")
    public void button_Alternativa3(View view){
        setEnableFalseButtons();
        stopTimer();
        int timeLeft = Integer.parseInt(String.valueOf(textTimer.getText()));
        PerguntaDAO pergunta_dao = new PerguntaDAO(this);
        String alternativaSelecionada = (String) buttonAlternativa3.getText();
        String cod_Pergunta = (String) codPergunta.getText();
        String codCategoriaPergunta = (String) categoriaPergunta.getText();
        mostraAreaPopulacaoPais(codCategoriaPergunta);
        int codPerguntaInt = Integer.parseInt(cod_Pergunta);
        int codCategoriaInt = Integer.parseInt(codCategoriaPergunta);
        boolean respostaCerta = pergunta_dao.confereResposta(alternativaSelecionada,cod_Pergunta,codCategoriaPergunta);
        buttonAlternativa3 = verificaResposta(respostaCerta, buttonAlternativa3,timeLeft);
        buttonAlternativa3.postDelayed(new Runnable() {
            @Override
            public void run() {
                buttonAlternativa3.setBackground(backgorundButton);
                buscaPergunta();
            }
        },2500);
        deletePerguntaTemp();
        updatePartida();
        pergunta_dao.atualizaPartidaPerguntas(codPartida,codPerguntaInt,codCategoriaInt,respostaCerta);
    }
    @SuppressLint("ResourceAsColor")
    public void button_Alternativa4(View view){
        setEnableFalseButtons();
        stopTimer();
        int timeLeft = Integer.parseInt(String.valueOf(textTimer.getText()));
        PerguntaDAO pergunta_dao = new PerguntaDAO(this);
        String alternativaSelecionada = (String) buttonAlternativa4.getText();
        String cod_Pergunta = (String) codPergunta.getText();
        String codCategoriaPergunta = (String) categoriaPergunta.getText();
        mostraAreaPopulacaoPais(codCategoriaPergunta);
        int codPerguntaInt = Integer.parseInt(cod_Pergunta);
        int codCategoriaInt = Integer.parseInt(codCategoriaPergunta);
        boolean respostaCerta = pergunta_dao.confereResposta(alternativaSelecionada,cod_Pergunta,codCategoriaPergunta);
        buttonAlternativa4 = verificaResposta(respostaCerta, buttonAlternativa4, timeLeft);
        buttonAlternativa4.postDelayed(new Runnable() {
            @Override
            public void run() {
                buttonAlternativa4.setBackground(backgorundButton);
                buscaPergunta();
            }
        },2500);
        deletePerguntaTemp();
        updatePartida();
        pergunta_dao.atualizaPartidaPerguntas(codPartida,codPerguntaInt,codCategoriaInt,respostaCerta);
    }

    private void mostraAreaPopulacaoPais(String codCategoriaPergunta) {
        Locale localeBR = new Locale("pt","BR");

        NumberFormat numberFormat = NumberFormat.getNumberInstance(localeBR);
        int populacao = paisTemporario.getPopulacao();
        double area = paisTemporario.getArea();

        if(Integer.parseInt(codCategoriaPergunta)==4){
            bandeira.setImageResource(0);
            enunciado.setText("A população "+paisTemporario.getPronomeD()
                    +" "+paisTemporario.getNomePais()+ " é de "+
                    numberFormat.format(populacao)+" pessoas. ");
        }else if(Integer.parseInt(codCategoriaPergunta)==5){
            bandeira.setImageResource(0);
            enunciado.setText("A Área "+paisTemporario.getPronomeD()
                    +" "+paisTemporario.getNomePais()+ " é de "+
                    numberFormat.format(area)+" Km².");
        }
    }
    private void updatePartida() {
        PartidaDAO dao_partida = new PartidaDAO(this);
       dao_partida.atualizaPartida(codPartida,pontos,listaPerguntas.size());
    }
    private Button verificaResposta(boolean respostaCerta, Button botao, int timeLeft) {
        Button b = botao;
        if(respostaCerta){
            PerguntaDAO dao_pergunta = new PerguntaDAO(this);
            b.setBackgroundResource(R.drawable.borda_arredondada_alternativa_certa);
            playRespostaCerta();
            pontos=pontos+((71*timeLeft*coeficientePontos)/(dicasUsadasPergunta+1));
            dicasUsadasPergunta=0;
            atualizaAcertosContinente();
        }else{
            vidas--;
            PartidaDAO daoPartida = new PartidaDAO(this);
            codPartida = (daoPartida.consultaUltimoCodPartida() - 1);
            daoPartida.atualizaVidas(codPartida,vidas);
            b.setBackgroundResource(R.drawable.borda_arredondada_alternativa_errada);
            vibrar();

            b.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mostraRespostaCerta();
                }
            },1000);


        }


        escondeNavigationBar();
        return b;

    }
    private void atualizaAcertosContinente() {
        int i = acertosContinentes.get(paisTemporario.getContinente());
        acertosContinentes.put(paisTemporario.getContinente(),i+1);
    }
    private void mostraRespostaCerta() {
        ArrayList<Button> listButtons = new ArrayList<>();
        listButtons.add(buttonAlternativa1);listButtons.add(buttonAlternativa2);listButtons.add(buttonAlternativa3);listButtons.add(buttonAlternativa4);


        for(int i=0;i<4;i++)
        {
            PerguntaDAO pergunta_dao = new PerguntaDAO(this);
            String alternativaSelecionada = (String) listButtons.get(i).getText();
            String cod_Pergunta = (String) codPergunta.getText();
            String codCategoriaPergunta = (String) categoriaPergunta.getText();

            boolean respostaCerta = pergunta_dao.confereResposta(alternativaSelecionada,cod_Pergunta,codCategoriaPergunta);
            if (respostaCerta)
            {

                    listButtons.get(i).setBackgroundResource(R.drawable.borda_arredondada_alternativa_certa);

            }

            int finalI = i;
            listButtons.get(i).postDelayed(new Runnable() {
                @Override
                public void run() {
                    listButtons.get(finalI).setBackground(backgorundButton);

                }
            },1500);

        }


    }
    public void buscaPergunta(){



        if(vidas<=0){
            gameOver(1);
        }else {

                if (listaPerguntas.size() < qtdPerguntas) {
                    PerguntaDAO pergunta_dao = new PerguntaDAO(this);
                    Pergunta p = new Pergunta();

                    if (flagJogoPendente == 1) {
                        p = pergunta_dao.buscaPerguntaEspecifica(String.valueOf(codPergunta.getText()), String.valueOf(categoriaPergunta.getText()));
                    } else {
                        p = pergunta_dao.buscaPergunta();
                    }


                    boolean perguntaRepetida = verificaPerguntaDuplciada(p);


                    if (!perguntaRepetida) {


                        mostraPergunta(p);
                        paisTemporario = pergunta_dao.buscaPaisEspecifico(p.getCodPergunta());
                        listaPerguntas.add(p.getCodPergunta());
                        labelQtdPergunta.setText(Integer.toString(listaPerguntas.size()) + " / " + qtdPerguntas);


                    } else {


                        buscaPergunta();
                    }
                } else {


                    fimJogo();
                }
            }

    }
    private void gameOver(int flag) {

        PartidaDAO dao_partida = new PartidaDAO(this);
        codPartida = (dao_partida.consultaUltimoCodPartida() - 1);
        dao_partida.finalizaPartidaPerdida(codPartida);
        dao_partida.zerarSequenciaAtual(qtdPerguntas);
        setEnableFalseButtons();

        //a Variavel 'flag' verifica se o encerramento do jogo se deu pelo botão 'Voltar'(2) do Android
        //ou pela sequencia da partida(1)
        if(flag==1){
        Intent i = new Intent(com.ex.geografando.Activitys.JogoActivity.this, FinalJogo.class);
        i.putExtra("pontos", 0);
        startActivity(i);
        finishAffinity();
        }



    }
    private void fimJogo() {
        PartidaDAO dao_partida = new PartidaDAO(this);
        dao_partida.atualizaAcertosContinente(acertosContinentes);
        dao_partida.concluiPartida(codPartida);

        dao_partida.atualizaSequenciaVitorias(qtdPerguntas);

        Intent i = new Intent(com.ex.geografando.Activitys.JogoActivity.this, FinalJogo.class);
        i.putExtra("pontos", pontos);



        startActivity(i);
        finishAffinity();

    }
    private boolean verificaPerguntaDuplciada(Pergunta p) {

        boolean repetida=false;

        if(!listaPerguntas.isEmpty()) {

            for (int i = 0; i < listaPerguntas.size(); i++) {
                if (p.getCodPergunta() == listaPerguntas.get(i)) {
                    repetida = true;
                }
            }
        }
        return repetida;


    }
    public void escondeNavigationBar(){

            this.getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);




    }
    @Override
    public void onBackPressed(){

        String[] opcao = {"SIM", "NÃO"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Deseja encerrar esta partida?");
        builder.setItems(opcao, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if ("SIM".equals(opcao[which])) {

                    stopTimer();
                    gameOver(2);
                   if(mInterstitialAd != null){
                        mInterstitialAd.show(JogoActivity.this);
                    }else {
                        activityOnResume = false;
                        Intent i = new Intent(com.ex.geografando.Activitys.JogoActivity.this, MainActivity.class);
                        startActivity(i);
                        finishAffinity();
                    }

                } else if ("NÃO".equals(opcao[which])) {

                }


                // the user clicked on colors[which]
            }
        });
        builder.show();

    }
    public void mostraPergunta(Pergunta pergunta){



            bandeira.setImageResource(0);
            AlternativaDAO alternativa_dao = new AlternativaDAO(this);
            ArrayList<Alternativa> alternativas = new ArrayList<>();
            ArrayList<String> alternativasOpcoes = new ArrayList<>();

            setEnableTrueButtons();
            setVisibleTrueButtons();
            if(flagJogoPendente==0){
                alternativasDisponiveis=4;
              alternativas = alternativa_dao.buscaAlternativas(pergunta.getResposta(),pergunta.getCodTipoCategoria());
                if(pergunta.getCodTipoCategoria()==1){
                    Resources res = getResources();
                    int resourceId = res.getIdentifier("capitol","drawable", getPackageName());
                    bandeira.setImageResource(resourceId);
                    alternativasOpcoes = retornaAlternativas(alternativas);
                    alternativasOpcoes.add(pergunta.getResposta());

                }else if(pergunta.getCodTipoCategoria()==10){
                    Resources res = getResources();
                    int resourceId = res.getIdentifier("cidade","drawable", getPackageName());
                    bandeira.setImageResource(resourceId);
                    alternativasOpcoes = retornaAlternativas(alternativas);
                    alternativasOpcoes.add(pergunta.getResposta());
                }
                else if(pergunta.getCodTipoCategoria()==4){
                    Resources res = getResources();
                    int resourceId = res.getIdentifier("populacao","drawable", getPackageName());
                    bandeira.setImageResource(resourceId);
                    alternativasOpcoes = retornaAlternativas(alternativas);
                }
                else if(pergunta.getCodTipoCategoria()==7){
                    Resources res = getResources();
                    int resourceId = res.getIdentifier("moeda","drawable", getPackageName());
                    bandeira.setImageResource(resourceId);
                    alternativasOpcoes = retornaAlternativas(alternativas);
                    alternativasOpcoes.add(pergunta.getResposta());
                }
                else if(pergunta.getCodTipoCategoria()==5){
                    Resources res = getResources();
                    int resourceId = res.getIdentifier("area","drawable", getPackageName());
                    bandeira.setImageResource(resourceId);
                    alternativasOpcoes = retornaAlternativas(alternativas);
                }
                else if(pergunta.getCodTipoCategoria()==6){
                        Resources res = getResources();
                        int resourceId = res.getIdentifier("continentes","drawable", getPackageName());
                        bandeira.setImageResource(resourceId);
                        alternativasOpcoes = retornaAlternativas(alternativas);
                        alternativasOpcoes.add(pergunta.getResposta());
                }
                else if(pergunta.getCodTipoCategoria()==3){
                    Resources res = getResources();
                    int resourceId = res.getIdentifier("idioma","drawable", getPackageName());
                    bandeira.setImageResource(resourceId);
                    alternativasOpcoes = retornaAlternativas(alternativas);
                    alternativasOpcoes.add(pergunta.getResposta());
                }
                else if(pergunta.getCodTipoCategoria()==2){
                        Resources res = getResources();
                        int resourceId = res.getIdentifier("bandeira"+pergunta.getCodPergunta(),"drawable", getPackageName());
                        bandeira.setImageResource(resourceId);
                        alternativasOpcoes = retornaAlternativas(alternativas);
                        alternativasOpcoes.add(pergunta.getResposta());
                }else if(pergunta.getCodTipoCategoria()==8){
                    Resources res = getResources();
                    int resourceId = res.getIdentifier("emblema"+pergunta.getCodPergunta(),"drawable", getPackageName());
                    bandeira.setImageResource(resourceId);
                    alternativasOpcoes = retornaAlternativas(alternativas);
                    alternativasOpcoes.add(pergunta.getResposta());
                }else if(pergunta.getCodTipoCategoria()==9){

                    Resources res = getResources();
                    int resourceId = res.getIdentifier("map"+pergunta.getCodPergunta(),"drawable", getPackageName());

                    bandeira.setImageResource(resourceId);
                    alternativasOpcoes = retornaAlternativas(alternativas);
                    alternativasOpcoes.add(pergunta.getResposta());
                }
                if(alternativasDisponiveis>1){
                    buttonAjuda.setEnabled(true);
                    buttonAjuda.setBackgroundResource(R.drawable.buttom_help);
                }else{
                    buttonAjuda.setEnabled(false);
                    buttonAjuda.setBackgroundResource(R.drawable.buttom_help_false);
                }
                Collections.shuffle(alternativasOpcoes);
                buttonAlternativa1.setText(alternativasOpcoes.get(0));
                buttonAlternativa2.setText(alternativasOpcoes.get(1));
                buttonAlternativa3.setText(alternativasOpcoes.get(2));
                buttonAlternativa4.setText(alternativasOpcoes.get(3));
            }else{
               for(int i=0; i<listaAlternativasTemp.size();i++){

                    alternativasOpcoes.add(listaAlternativasTemp.get(i));
                }

               ArrayList<String> alternativasDisponiveis = new ArrayList<>();
                PartidaDAO dao = new PartidaDAO(this);
                alternativasDisponiveis = dao.consultaalternativasDisponiveis();
                if(alternativasDisponiveis.size()>1){
                    buttonAjuda.setEnabled(true);
                    buttonAjuda.setBackgroundResource(R.drawable.buttom_help);
                }else{
                    buttonAjuda.setEnabled(false);
                    buttonAjuda.setBackgroundResource(R.drawable.buttom_help_false);
                }
                buttonAlternativa1.setText(alternativasOpcoes.get(0));
                buttonAlternativa2.setText(alternativasOpcoes.get(1));
                buttonAlternativa3.setText(alternativasOpcoes.get(2));
                buttonAlternativa4.setText(alternativasOpcoes.get(3));

                boolean alt1=false,alt2=false,alt3=false,alt4=false;

                for(int i=0;i<alternativasDisponiveis.size();i++){
                    String alternativaConsultada = alternativasDisponiveis.get(i);
                    String strAlt = String.valueOf(buttonAlternativa1.getText());
                    if(alternativaConsultada.equals(strAlt))
                    {
                        alt1=true;
                    }
                }

                for(int i=0;i<alternativasDisponiveis.size();i++){
                    String alternativaConsultada = alternativasDisponiveis.get(i);
                    String strAlt = String.valueOf(buttonAlternativa2.getText());
                    if(alternativaConsultada.equals(strAlt))
                    {
                        alt2=true;
                    }
                }

                for(int i=0;i<alternativasDisponiveis.size();i++){
                    String alternativaConsultada = alternativasDisponiveis.get(i);
                    String strAlt = String.valueOf(buttonAlternativa3.getText());
                    if(alternativaConsultada.equals(strAlt))
                    {
                        alt3=true;
                    }
                }

                for(int i=0;i<alternativasDisponiveis.size();i++){
                    String alternativaConsultada = alternativasDisponiveis.get(i);
                    String strAlt = String.valueOf(buttonAlternativa4.getText());
                    if(alternativaConsultada.equals(strAlt))
                    {
                        alt4=true;
                    }
                }
                if(!alt1){
                    buttonAlternativa1.setEnabled(false);
                    buttonAlternativa1.setVisibility(View.INVISIBLE);
                }
                if(!alt2){
                    buttonAlternativa2.setEnabled(false);
                    buttonAlternativa2.setVisibility(View.INVISIBLE);
                }
                if(!alt3){
                    buttonAlternativa3.setEnabled(false);
                    buttonAlternativa3.setVisibility(View.INVISIBLE);
                }
                if(!alt4){
                    buttonAlternativa4.setEnabled(false);
                    buttonAlternativa4.setVisibility(View.INVISIBLE);
                }


                if(pergunta.getCodTipoCategoria()==2){
                    Resources res = getResources();
                    int resourceId = res.getIdentifier("bandeira"+pergunta.getCodPergunta(),"drawable", getPackageName());
                    bandeira.setImageResource(resourceId);
                }
                flagJogoPendente=0;
            }
            enunciado.setText(pergunta.getPergunta());



            codPergunta.setText(Integer.toString(pergunta.getCodPergunta()));
            categoriaPergunta.setText(Integer.toString(pergunta.getCodTipoCategoria()));
            if(qtdDicas>0)
            {
                buttonAjuda.setText("AJUDA (" + qtdDicas + ")");
            }else{
                buttonAjuda.setText("AJUDA (AD)");
            }
            codPergunta.setVisibility(View.INVISIBLE);
            categoriaPergunta.setVisibility(View.INVISIBLE);

            mostraVidas();


            alternativasDisponiveis=4;

            inserePerguntaTemp();
            PartidaDAO dao = new PartidaDAO(this);
            dao.gravaJogoPendente(1);
            startTimer();
    }
    private void mostraVidas() {
        if(vidas==3){} else
        if(vidas==2){
            imageViewVida1.setVisibility(View.INVISIBLE);
        }else if(vidas==1){
            imageViewVida1.setVisibility(View.INVISIBLE);
            imageViewVida2.setVisibility(View.INVISIBLE);

        }
    }
    private void inserePerguntaTemp() {
        PartidaDAO dao = new PartidaDAO(this);
        int codPergunta = Integer.parseInt((String) this.codPergunta.getText());
        int codCategoria = Integer.parseInt((String) this.categoriaPergunta.getText());
        dao.inserePartidaTemp(codPartida,codPergunta,codCategoria,
                this.buttonAlternativa1.getText(),this.buttonAlternativa2.getText(),
                this.buttonAlternativa3.getText(),this.buttonAlternativa4.getText());
        dao.insereAlternativasDisponiveis(this.buttonAlternativa1.getText(),this.buttonAlternativa2.getText(),
                this.buttonAlternativa3.getText(),this.buttonAlternativa4.getText());
    }
    public void startTimer() {
        countDownTimer = new CountDownTimer(timeLeft,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;

                if(activityOnResume==true){
                updateTimer();}
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }
    public void stopTimer(){
        countDownTimer.cancel();

        timeLeft = 31000;
        timerRun=false;
    }
    public void updateTimer(){

        int seconds = (int) timeLeft % 31000 / 1000;
        String timeString = Integer.toString(seconds);
        if(seconds>0) {
            textTimer.setText(timeString);
            barTimer.setProgress(seconds);
            updateTimerRestPerguntaTemp(seconds);
        }else{
            textTimer.setText(timeString);
            barTimer.setProgress(seconds);
            tempoEsgotado();
        }

        if(seconds<=5){
            textTimer.setTextColor(getResources().getColor(R.color.red));
        }else{
            textTimer.setTextColor(getResources().getColor(R.color.black));
        }

    }
    private void updateTimerRestPerguntaTemp(int seconds) {
        PartidaDAO dao = new PartidaDAO(this);
        int timeRest = Integer.parseInt((String)textTimer.getText());
       dao.updateTimerRest(timeRest);
    }
    private void tempoEsgotado() {

        deletePerguntaTemp();
        escondeNavigationBar();
        setEnableFalseButtons();
        final AlertDialog alerta;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Tempo Esgotado!!");
        alerta = builder.create();
        alerta.show();
        vidas=vidas-1;
        escondeNavigationBar();
        buttonAlternativa1.postDelayed(new Runnable() {
            @Override
            public void run() {
                mostraRespostaCerta();
            }
        },800);

        textTimer.postDelayed(new Runnable() {
            @Override
            public void run() {
                timeLeft=31000;
                alerta.dismiss();
                 buscaPergunta();
            }
        },2000);

    }
    private void deletePerguntaTemp() {
        PartidaDAO dao = new PartidaDAO(this);
        dao.deletePerguntaTemp();
    }
    public void setEnableFalseButtons(){
        buttonAlternativa1.setEnabled(false);
        buttonAlternativa2.setEnabled(false);
        buttonAlternativa3.setEnabled(false);
        buttonAlternativa4.setEnabled(false);
        buttonAjuda.setEnabled(false);
    }
    public void setEnableTrueButtons(){

        buttonAlternativa2.setEnabled(true);
        buttonAlternativa3.setEnabled(true);
        buttonAlternativa4.setEnabled(true);
        buttonAlternativa1.setEnabled(true);

        if(qtdDicas>0) {
            buttonAjuda.setEnabled(true);
        }
    }
    public void setVisibleTrueButtons(){
        buttonAlternativa2.setVisibility(View.VISIBLE);
        buttonAlternativa3.setVisibility(View.VISIBLE);
        buttonAlternativa4.setVisibility(View.VISIBLE);
        buttonAlternativa1.setVisibility(View.VISIBLE);

    }
    private ArrayList<String> retornaAlternativas(ArrayList<Alternativa> alternativas) {

        ArrayList<String> alternativasListadas = new ArrayList<>();

        for(int i=0; i<alternativas.size();i++){
            Alternativa a = new Alternativa();
            a=alternativas.get(i);
            alternativasListadas.add(a.getAlternativa());
        }
        return alternativasListadas;
    }
    public void vibrar(){
        if(vibrar) {
            Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            long milleseconds = 180;
            vib.vibrate(milleseconds);
        }
    }

    private void setInterstitiaCallBack(){
        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
            @Override
            public void onAdClicked() {
                // Called when a click is recorded for an ad.
                Log.d(TAG, "Ad was clicked.");
            }

            @Override
            public void onAdDismissedFullScreenContent() {
                // Called when ad is dismissed.
                // Set the ad reference to null so you don't show the ad a second time.
                activityOnResume = false;
                Intent i = new Intent(com.ex.geografando.Activitys.JogoActivity.this, MainActivity.class);
                startActivity(i);
                finishAffinity();
                Log.d(TAG, "Ad dismissed fullscreen content.");
                mInterstitialAd = null;
            }

            @Override
            public void onAdFailedToShowFullScreenContent(AdError adError) {
                // Called when ad fails to show.
                Log.e(TAG, "Ad failed to show fullscreen content.");
                mInterstitialAd = null;
            }

            @Override
            public void onAdImpression() {
                // Called when an impression is recorded for an ad.
                Log.d(TAG, "Ad recorded an impression.");
            }

            @Override
            public void onAdShowedFullScreenContent() {
                // Called when ad is shown.
                Log.d(TAG, "Ad showed fullscreen content.");
            }
        });
    }


    private void loadAdMob() {

        AdRequest thisAdRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this, String.valueOf(R.string.admob_interstitial_activity_jogo), thisAdRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        setInterstitiaCallBack();
                        Log.i(TAG,"onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.d(TAG, loadAdError.toString());
                        mInterstitialAd = null;
                    }

                });



    }

}
