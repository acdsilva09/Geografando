package com.ex.geografando.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.ex.geografando.DAO.CategoriaDAO;
import com.ex.geografando.DAO.PartidaDAO;
import com.ex.geografando.Fragments.FragmentPagerAdapter;
import com.ex.geografando.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class RecordsActivity extends AppCompatActivity {


    public static com.ex.geografando.Activitys.RecordsActivity ra;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private Button btnVoltar;


    @Override
    public void onBackPressed(){
        finishAffinity();
        Intent i = new Intent(com.ex.geografando.Activitys.RecordsActivity.this, MainActivity.class);
        startActivity(i);
    }

    public ArrayList dadosEstatisticasGeral(int i) {
        ArrayList estatisticasGeral = new ArrayList<>();
        PartidaDAO daoPartida = new PartidaDAO(this);

        int totalPartidas = daoPartida.consultaTotalPartidasConcluidas(i);
        int totalVencidas = daoPartida.consultaTotalPartidasVencidas(i);

        if(totalPartidas==0){
           for(int a=0;a<11;a++){
               estatisticasGeral.add(0);
           }
        }else{
            if(totalVencidas==0){
                estatisticasGeral.add(totalPartidas);
                estatisticasGeral.add(0);
                estatisticasGeral.add(0);
            }else{
                estatisticasGeral.add(totalPartidas);
                estatisticasGeral.add(totalVencidas);
                estatisticasGeral.add((totalVencidas*100)/totalPartidas);
            }


            int totalPerguntas = daoPartida.consultatotalPerguntasRespondidas(i);
            int totalPerguntasCertas = daoPartida.consulTatotalPerguntasCertas(i);
            estatisticasGeral.add(totalPerguntas);
            estatisticasGeral.add(totalPerguntasCertas);
            estatisticasGeral.add((totalPerguntasCertas*100)/totalPerguntas);

            estatisticasGeral.add(daoPartida.consultaMelhorPontuacao(i));
            int totalPontuacao=daoPartida.consultaTotalPontuacao(i);
            estatisticasGeral.add(totalPontuacao/totalPartidas);

            estatisticasGeral.add(daoPartida.consultaSequenciaAtual(i));
            estatisticasGeral.add(daoPartida.consultaMaiorSequencia(i));
            estatisticasGeral.add(daoPartida.consultaPartidasSemErros(i));

        }






        return  estatisticasGeral;
    }


    //private android.widget.Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);


        escondeNavigationBar();
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        btnVoltar = (Button) findViewById(R.id.buttonVoltarRecords);

        FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);


       ra = this;
    }

    public void escondeNavigationBar(){

        this.getWindow().getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);


    }

    public ArrayList dadosEstatisticasCategorias() {
        ArrayList dadosEstatisticasCategorias = new ArrayList();
        PartidaDAO daoPartida = new PartidaDAO(this);
        for(int i =1;i<=10;i++){
            int totalRespondidas = daoPartida.consultaTotalPerguntasRespondidasCategoria(i);
            int totalAcertos = daoPartida.consultaTotalPerguntasCertasCategoria(i);
            if(totalRespondidas==0){
                dadosEstatisticasCategorias.add(0);
                dadosEstatisticasCategorias.add(0);
                dadosEstatisticasCategorias.add(0);

            }else

                if(totalAcertos==0){

                dadosEstatisticasCategorias.add(totalRespondidas);
                dadosEstatisticasCategorias.add(totalAcertos);
                dadosEstatisticasCategorias.add(0);

            }else{

                int percentual = (totalAcertos*100)/totalRespondidas;
                dadosEstatisticasCategorias.add(totalRespondidas);
                dadosEstatisticasCategorias.add(totalAcertos);
                dadosEstatisticasCategorias.add(percentual);}

        }
        return dadosEstatisticasCategorias;
    }

    public ArrayList consultaDadosPorQuantidadePerguntas() {
        ArrayList list = new ArrayList();
        PartidaDAO partida = new PartidaDAO(this);

        for(int i = 2;i<=4;i++) {
            int totalPartidasConcluidas = partida.consultaTotalPartidasConcluidas(i);
            int totalPartidasVencidas = partida.consultaTotalPartidasVencidas(i);
            if (totalPartidasConcluidas == 0) {
                for (int a = 0; a < 8; a++) {
                    list.add(0);
                }
            } else {

                if (totalPartidasVencidas == 0) {
                    list.add(totalPartidasConcluidas);
                    list.add(totalPartidasVencidas);
                    list.add(0);
                } else {
                    int percentual = (totalPartidasVencidas * 100) / totalPartidasConcluidas;
                    list.add(totalPartidasConcluidas);
                    list.add(totalPartidasVencidas);
                    list.add(percentual);

                }

                int pontuacaoTotal = partida.consultaTotalPontuacao(i);
                int pontuacaoMaxima = partida.consultaMelhorPontuacao(i);
                int pontuacaoMedia = pontuacaoTotal / totalPartidasConcluidas;
                list.add(pontuacaoMaxima);
                list.add(pontuacaoMedia);

                list.add(partida.consultaSequenciaAtual(i));
                list.add(partida.consultaMaiorSequencia(i));
                list.add(partida.consultaPartidasSemErros(i));

            }


        }

        return list;
    }

    public void voltar(View view){

        startActivity(new Intent(com.ex.geografando.Activitys.RecordsActivity.this, MainActivity.class));
        finishAffinity();
    }

    public boolean consultaCategoriasDesbloqueadas(int cod) {
        CategoriaDAO dao = new CategoriaDAO(this);
        boolean habilitada = dao.consultaCategoriaHabilitada(cod);
        return habilitada;
    }

    public ArrayList<Integer> consultaQtdPartidasDesbloqueadas() {
        CategoriaDAO dao = new CategoriaDAO(this);
        ArrayList<Integer> habilitada = (ArrayList<Integer>) dao.consultaQtdHabilitadaEspecifica();
        return habilitada;
    }
}
