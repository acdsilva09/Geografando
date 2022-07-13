package com.ex.geografando.Activitys;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.ex.geografando.DAO.CategoriaDAO;
import com.ex.geografando.DAO.ConquistasDAO;
import com.ex.geografando.DAO.PartidaDAO;
import com.ex.geografando.Model.Conquista;
import com.ex.geografando.R;

import java.util.ArrayList;

public class ConquistasActivity extends AppCompatActivity {

    private TextView textQtdConquistas;

    private LinearLayout linearDesbloqueiaCategorias;
    private ProgressBar progressBarDesbloqueiaCategorias;
    private TextView textProgressBarDesbloqueiaCategorias;


    private LinearLayout linearEspecialistaAsia,linearEspecialistaAfrica, linearEspecialistaAmerica, linearEspecialistaOceania, linearEspecialistaEuropa;
    private ProgressBar progressBarEspecialistaAsia, progressBarEspecialistaAfrica, progressBarEspecialistaAmerica, progressBarEspecialistaOceania, progressBarEspecialistaEuropa;
    private TextView textProgressBarEspecialistaAsia, textProgressBarEspecialistaAfrica, textProgressBarEspecialistaAmerica, textProgressBarEspecialistaOceania, textProgressBarEspecialistaEuropa;


    private LinearLayout linear10PartidasTodasCategorias,linear1PartidaTodasCategorias, linear50PartidasTodasCategorias, linear100PartidasTodasCategorias, linear200PartidasTodasCategorias;
    private ProgressBar progressBar10PartidasTodasCategorias,progressBar1PartidaTodasCategorias, progressBar50PartidasTodasCategorias, progressBar100PartidasTodasCategorias, progressBar200PartidasTodasCategorias;
    private TextView textProgressBar10PartidasTodasCategorias,textProgressBar1PartidaTodasCategorias, textProgressBar50PartidasTodasCategorias, textProgressBar100PartidasTodasCategorias, textProgressBar200PartidasTodasCategorias;


    private LinearLayout linear10PartidasSeguidas, linear50PartidasSeguidas, linear100PartidasSeguidas, linear5PartidasSeguidas;
    private ProgressBar progressBar10PartidasSeguidas, progressBar50PartidasSeguidas, progressBar100PartidasSeguidas, progressBar5PartidasSeguidas;
    private TextView textProgressBar10PartidasSeguidas, textProgressBar50PartidasSeguidas, textProgressBar100PartidasSeguidas, textProgressBar5PartidasSeguidas;


    private LinearLayout linearLinguistaJunior,linearLinguistaPleno,linearLinguistaSenior;
    private ProgressBar progressBarLinguistaJR, progressBarLinguistaPL,progressBarLinguistaSR;
    private TextView textProgressBarLinguistaJR, textProgressBarLinguistaPL, textProgressBarLinguistaSR;

    private LinearLayout linearGeografoJunior,linearGeografoPleno,linearGeografoSenior;
    private ProgressBar progressBarGeografoJR, progressBarGeografoPL,progressBarGeografoSR;
    private TextView textProgressBarGeografoJR, textProgressBarGeografoPL, textProgressBarGeografoSR;

    private LinearLayout linearPoliticoJunior,linearPoliticoPleno,linearPoliticoSenior;
    private ProgressBar progressBarPoliticoJR, progressBarPoliticoPL,progressBarPoliticoSR;
    private TextView textProgressBarPoliticoJR, textProgressBarPoliticoPL, textProgressBarPoliticoSR;

    private LinearLayout linearVexilologistaJunior,linearVexilologistaPleno,linearVexilologistaSenior;
    private ProgressBar progressBarVexilologistaJR, progressBarVexilologistaPL,progressBarVexilologistaSR;
    private TextView textProgressBarVexilologistaJR, textProgressBarVexilologistaPL, textProgressBarVexilologistaSR;

    private LinearLayout linearDemografoJunior,linearDemografoPleno,linearDemografoSenior;
    private ProgressBar progressBarDemografoJR, progressBarDemografoPL,progressBarDemografoSR;
    private TextView textProgressBarDemografoJR, textProgressBarDemografoPL, textProgressBarDemografoSR;

    private LinearLayout linearCartografoJunior,linearCartografoPleno,linearCartografoSenior;
    private ProgressBar progressBarCartografoJR, progressBarCartografoPL,progressBarCartografoSR;
    private TextView textProgressBarCartografoJR, textProgressBarCartografoPL, textProgressBarCartografoSR;

    private LinearLayout linearTopografoJunior,linearTopografoPleno,linearTopografoSenior;
    private ProgressBar progressBarTopografoJR, progressBarTopografoPL,progressBarTopografoSR;
    private TextView textProgressBarTopografoJR, textProgressBarTopografoPL, textProgressBarTopografoSR;

    private LinearLayout linearEconomistaJunior,linearEconomistaPleno,linearEconomistaSenior;
    private ProgressBar progressBarEconomistaJR, progressBarEconomistaPL,progressBarEconomistaSR;
    private TextView textProgressBarEconomistaJR, textProgressBarEconomistaPL, textProgressBarEconomistaSR;

    private LinearLayout linearHeraldistaJunior,linearHeraldistaPleno,linearHeraldistaSenior;
    private ProgressBar progressBarHeraldistaJR, progressBarHeraldistaPL,progressBarHeraldistaSR;
    private TextView textProgressBarHeraldistaJR, textProgressBarHeraldistaPL, textProgressBarHeraldistaSR;

    private LinearLayout linearTuristaJunior,linearTuristaPleno,linearTuristaSenior;
    private ProgressBar progressBarTuristaJR, progressBarTuristaPL,progressBarTuristaSR;
    private TextView textProgressBarTuristaJR, textProgressBarTuristaPL, textProgressBarTuristaSR;

    private LinearLayout linearVenca1Partida, linearVenca10Partida, linearVenca50Partida, linearVenca100Partida, linearVenca200Partida, linearVenca500Partida, linearVenca1000Partida;
    private ProgressBar progressBarVenca1Partida, progressBarVenca10Partida, progressBarVenca50Partida, progressBarVenca100Partida, progressBarVenca200Partida, progressBarVenca500Partida, progressBarVenca1000Partida;
    private TextView textProgressBarVenca1Partida, textProgressBarVenca10Partida, textProgressBarVenca50Partida, textProgressBarVenca100Partida, textProgressBarVenca200Partida, textProgressBarVenca500Partida, textProgressBarVenca1000Partida;

    private LinearLayout linearVenca10PartidaSemErros, linearVenca50PartidaSemErros, linearVenca100PartidaSemErros, linearVenca200PartidaSemErros;
    private ProgressBar progressBarVenca10PartidaSemErros, progressBarVenca50PartidaSemErros, progressBarVenca100PartidaSemErros, progressBarVenca200PartidaSemErros;
    private TextView textProgressBarVenca10PartidaSemErros, textProgressBarVenca50PartidaSemErros, textProgressBarVenca100PartidaSemErros, textProgressBarVenca200PartidaSemErros;

    private LinearLayout linear30MilPontos, linear50MilPontos, linear100MilPontos, linear500MilPontos, linear800MilPontos, linear1MilhaoPontos;
    private ProgressBar progressBar30MilPontos, progressBar50MilPontos, progressBar100MilPontos, progressBar500MilPontos, progressBar800MilPontos, progressBar1MilhaoPontos;
    private TextView textProgressBar30MilPontos, textProgressBar50MilPontos, textProgressBar100MilPontos, textProgressBar500MilPontos, textProgressBar800MilPontos, textProgressBar1MilhaoPontos;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conquistas);
        setViewsById();

        verificaQtdConquistas();
        verificaConquistasDesbloqueadas(defineArrayCamposConquistas(),defineArrayProgressBar(), defineArrayTextViewProgressBar(), statusConquistas());
        verificaConquistasSemCategoriaDesbloqueadas(defineArrayCamposConquistasSemCategoria(),defineArrayProgressBarSemCategoria(), defineArrayTextViewProgressBarSemCategoria(), statusConquistasSemCategoria());

        escondeNavigationBar();
    }

    private void verificaQtdConquistas() {
        ConquistasDAO dao = new ConquistasDAO(this);
        int qtd = dao.consultaQtdConquistasDesbloqueadas();
        textQtdConquistas.setText(qtd+" / 62");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void verificaConquistasSemCategoriaDesbloqueadas(ArrayList<LinearLayout> listLinearLayout,
                                                             ArrayList<ProgressBar> listProgressBar,
                                                             ArrayList <TextView> listTextProgressBar,
                                                             ArrayList<Conquista> listConquistas){
        PartidaDAO dao = new PartidaDAO(this);
        CategoriaDAO catDao = new CategoriaDAO(this);
        int partidasVencidas=dao.consultaTotalPartidasVencidas(1);
        int partidasVencidasSemErros = dao.consultaPartidasSemErros(1);
        int categoriasDesbloqueadas = catDao.qtdCategoriasDesbloqueadas();
        int partidasTodasCategorias = dao.partidasVencidasTodasCategoriasSelecionadas();
        int partidasSeguidas = dao.consultaSequenciaAtual(1);



        //Conquistas de Total de Partidas Vencidas
        for(int i=0; i<7;i++){
            Conquista c = listConquistas.get(i);
            listProgressBar.get(i).setProgress(partidasVencidas);
            listProgressBar.get(i).setTooltipText("true");
            if(listProgressBar.get(i).getMax()>=partidasVencidas) {
                listTextProgressBar.get(i).setText(Integer.toString(partidasVencidas));
            }else{
                listTextProgressBar.get(i).setText(Integer.toString(listProgressBar.get(i).getMax()));
            }
            if(c.getDesbloqueio()==1){
                listProgressBar.get(i).setProgressDrawable(getDrawable(R.drawable.custom_progress_bar_horizontal_desbloqueada));
                listTextProgressBar.get(i).setVisibility(View.INVISIBLE);
            }else{
                listLinearLayout.get(i).setAlpha((float) 0.4);
            }
        }

        //Conquistas de Partidas Vencidas sem Erros
        for(int x=7; x<11;x++){
            Conquista c = listConquistas.get(x);
            listProgressBar.get(x).setProgress(partidasVencidasSemErros);
            listProgressBar.get(x).setTooltipText("true");
            if(listProgressBar.get(x).getMax()>=partidasVencidasSemErros) {
                listTextProgressBar.get(x).setText(Integer.toString(partidasVencidasSemErros));
            }else{
                listTextProgressBar.get(x).setText(Integer.toString(listProgressBar.get(x).getMax()));
            }
            if(c.getDesbloqueio()==1){
                listProgressBar.get(x).setProgressDrawable(getDrawable(R.drawable.custom_progress_bar_horizontal_desbloqueada));
                listTextProgressBar.get(x).setVisibility(View.INVISIBLE);
            }else{
                listLinearLayout.get(x).setAlpha((float) 0.4);
            }
        }

        //Conquista de Categorias Desbloqueadas
        for (int j=11; j<12;j++){
            Conquista c = listConquistas.get(j);
            listProgressBar.get(j).setProgress(categoriasDesbloqueadas);
            listProgressBar.get(j).setTooltipText("true");
            ConquistasDAO conquistasDAO = new ConquistasDAO(this);
            if(listProgressBar.get(j).getMax()>=categoriasDesbloqueadas) {
                listTextProgressBar.get(j).setText(Integer.toString(categoriasDesbloqueadas));
            }else{
                listTextProgressBar.get(j).setText(Integer.toString(listProgressBar.get(j).getMax()));
            }
            if(categoriasDesbloqueadas>=10){
                conquistasDAO.updateConquistaTodasCategorias(1);
                listProgressBar.get(j).setProgressDrawable(getDrawable(R.drawable.custom_progress_bar_horizontal_desbloqueada));
                listTextProgressBar.get(j).setVisibility(View.INVISIBLE);
            }else{
                conquistasDAO.updateConquistaTodasCategorias(0);
                listLinearLayout.get(j).setAlpha((float) 0.4);
            }
        }


        //
        for (int m=12; m<18;m++){
            Conquista c = listConquistas.get(m);
            listProgressBar.get(m).setTooltipText("true");
            listTextProgressBar.get(m).setVisibility(View.INVISIBLE);
            if(c.getDesbloqueio()==1){
                listProgressBar.get(m).setProgressDrawable(getDrawable(R.drawable.custom_progress_bar_horizontal_desbloqueada));
                listProgressBar.get(m).setProgress(1);
            }else{
                listLinearLayout.get(m).setAlpha((float)0.4);
                listProgressBar.get(m).setProgress(0);
            }

        }

        for(int x=18; x<22;x++){
            Conquista c = listConquistas.get(x);
            listProgressBar.get(x).setTooltipText("true");
            if(c.getDesbloqueio()==1){
                listProgressBar.get(x).setProgressDrawable(getDrawable(R.drawable.custom_progress_bar_horizontal_desbloqueada));
                listTextProgressBar.get(x).setVisibility(View.INVISIBLE);
                listProgressBar.get(x).setProgress(listProgressBar.get(x).getMax());
            }else{
                listLinearLayout.get(x).setAlpha((float) 0.4);
                listProgressBar.get(x).setProgress(partidasSeguidas);
                listTextProgressBar.get(x).setText(Integer.toString(partidasSeguidas));
            }
        }



        for (int m=22; m<27;m++){
            Conquista c = listConquistas.get(m);
            listProgressBar.get(m).setTooltipText("true");
            listProgressBar.get(m).setProgress(partidasTodasCategorias);
            if(listProgressBar.get(m).getMax()>=partidasTodasCategorias) {
                listTextProgressBar.get(m).setText(Integer.toString(partidasTodasCategorias));
            }else{
                listTextProgressBar.get(m).setText(Integer.toString(listProgressBar.get(m).getMax()));
            }
            if(c.getDesbloqueio()==1){
                listProgressBar.get(m).setProgressDrawable(getDrawable(R.drawable.custom_progress_bar_horizontal_desbloqueada));
                listTextProgressBar.get(m).setVisibility(View.INVISIBLE);
            }else{
                listLinearLayout.get(m).setAlpha((float)0.4);
            }
        }

        for(int i=27;i<32;i++){
            Conquista c = listConquistas.get(i);
            int acertos = dao.consultaAcertosContinente(c.getConquista());
            listProgressBar.get(i).setTooltipText("true");
            listProgressBar.get(i).setProgress(acertos);
            if(listProgressBar.get(i).getMax()>=acertos) {
                listTextProgressBar.get(i).setText(Integer.toString(acertos));
            }else{
                listTextProgressBar.get(i).setText(Integer.toString(listProgressBar.get(i).getMax()));
            }
            if(c.getDesbloqueio()==1){
                listProgressBar.get(i).setProgressDrawable(getDrawable(R.drawable.custom_progress_bar_horizontal_desbloqueada));
                listTextProgressBar.get(i).setVisibility(View.INVISIBLE);
            }else{
                listLinearLayout.get(i).setAlpha((float)0.4);
            }
        }
    }

    private void setViewsById() {
        textQtdConquistas = (TextView) findViewById(R.id.textQtdConquistas);
        linearLinguistaJunior = (LinearLayout) findViewById(R.id.linearLinguistaJunior);
        linearLinguistaPleno = (LinearLayout) findViewById(R.id.linearLinguistaPleno);
        linearLinguistaSenior = (LinearLayout) findViewById(R.id.linearLinguistaSenior);
        progressBarLinguistaJR = (ProgressBar) findViewById(R.id.progresbarLinguistaJunior);
        progressBarLinguistaPL = (ProgressBar) findViewById(R.id.progresbarLinguistaPleno);
        progressBarLinguistaSR = (ProgressBar) findViewById(R.id.progresbarLinguistaSenior);
        textProgressBarLinguistaJR = (TextView) findViewById(R.id.textProgressBarLinguistaJunior);
        textProgressBarLinguistaPL = (TextView) findViewById(R.id.textProgressBarLinguistaPleno);
        textProgressBarLinguistaSR = (TextView) findViewById(R.id.textProgressBarLinguistaSenior);
        linearGeografoJunior = (LinearLayout) findViewById(R.id.linearGeografoJunior);
        linearGeografoPleno = (LinearLayout) findViewById(R.id.linearGeografoPleno);
        linearGeografoSenior = (LinearLayout) findViewById(R.id.linearGeografoSenior);
        progressBarGeografoJR = (ProgressBar) findViewById(R.id.progresbarGeografoJunior);
        progressBarGeografoPL = (ProgressBar) findViewById(R.id.progresbarGeografoPleno);
        progressBarGeografoSR = (ProgressBar) findViewById(R.id.progresbarGeografoSenior);
        textProgressBarGeografoJR = (TextView) findViewById(R.id.textProgressBarGeografoJunior);
        textProgressBarGeografoPL = (TextView) findViewById(R.id.textProgressBarGeografoPleno);
        textProgressBarGeografoSR = (TextView) findViewById(R.id.textProgressBarGeografoSenior);
        linearPoliticoJunior = (LinearLayout) findViewById(R.id.linearPoliticoJunior);
        linearPoliticoPleno = (LinearLayout) findViewById(R.id.linearPoliticoPleno);
        linearPoliticoSenior = (LinearLayout) findViewById(R.id.linearPoliticoSenior);
        progressBarPoliticoJR = (ProgressBar) findViewById(R.id.progresbarPoliticoJunior);
        progressBarPoliticoPL = (ProgressBar) findViewById(R.id.progresbarPoliticoPleno);
        progressBarPoliticoSR = (ProgressBar) findViewById(R.id.progresbarPoliticoSenior);
        textProgressBarPoliticoJR = (TextView) findViewById(R.id.textProgressBarPoliticoJunior);
        textProgressBarPoliticoPL = (TextView) findViewById(R.id.textProgressBarPoliticoPleno);
        textProgressBarPoliticoSR = (TextView) findViewById(R.id.textProgressBarPoliticoSenior);
        linearVexilologistaJunior = (LinearLayout) findViewById(R.id.linearVexilologistaJunior);
        linearVexilologistaPleno = (LinearLayout) findViewById(R.id.linearVexilologistaPleno);
        linearVexilologistaSenior = (LinearLayout) findViewById(R.id.linearVexilologistaSenior);
        progressBarVexilologistaJR = (ProgressBar) findViewById(R.id.progresbarVexilologistaJunior);
        progressBarVexilologistaPL = (ProgressBar) findViewById(R.id.progresbarVexilologistaPleno);
        progressBarVexilologistaSR = (ProgressBar) findViewById(R.id.progresbarVexilologistaSenior);
        textProgressBarVexilologistaJR = (TextView) findViewById(R.id.textProgressBarVexilologistaJunior);
        textProgressBarVexilologistaPL = (TextView) findViewById(R.id.textProgressBarVexilologistaPleno);
        textProgressBarVexilologistaSR = (TextView) findViewById(R.id.textProgressBarVexilologistaSenior);
        linearDemografoJunior = (LinearLayout) findViewById(R.id.linearDemografoJunior);
        linearDemografoPleno = (LinearLayout) findViewById(R.id.linearDemografoPleno);
        linearDemografoSenior = (LinearLayout) findViewById(R.id.linearDemografoSenior);
        progressBarDemografoJR = (ProgressBar) findViewById(R.id.progresbarDemografoJunior);
        progressBarDemografoPL = (ProgressBar) findViewById(R.id.progresbarDemografoPleno);
        progressBarDemografoSR = (ProgressBar) findViewById(R.id.progresbarDemografoSenior);
        textProgressBarDemografoJR = (TextView) findViewById(R.id.textProgressBarDemografoJunior);
        textProgressBarDemografoPL = (TextView) findViewById(R.id.textProgressBarDemografoPleno);
        textProgressBarDemografoSR = (TextView) findViewById(R.id.textProgressBarDemografoSenior);
        linearCartografoJunior = (LinearLayout) findViewById(R.id.linearCartografoJunior);
        linearCartografoPleno = (LinearLayout) findViewById(R.id.linearCartografoPleno);
        linearCartografoSenior = (LinearLayout) findViewById(R.id.linearCartografoSenior);
        progressBarCartografoJR = (ProgressBar) findViewById(R.id.progresbarCartografoJunior);
        progressBarCartografoPL = (ProgressBar) findViewById(R.id.progresbarCartografoPleno);
        progressBarCartografoSR = (ProgressBar) findViewById(R.id.progresbarCartografoSenior);
        textProgressBarCartografoJR = (TextView) findViewById(R.id.textProgressBarCartografoJunior);
        textProgressBarCartografoPL = (TextView) findViewById(R.id.textProgressBarCartografoPleno);
        textProgressBarCartografoSR = (TextView) findViewById(R.id.textProgressBarCartografoSenior);
        linearTopografoJunior = (LinearLayout) findViewById(R.id.linearTopografoJunior);
        linearTopografoPleno = (LinearLayout) findViewById(R.id.linearTopografoPleno);
        linearTopografoSenior = (LinearLayout) findViewById(R.id.linearTopografoSenior);
        progressBarTopografoJR = (ProgressBar) findViewById(R.id.progresbarTopografoJunior);
        progressBarTopografoPL = (ProgressBar) findViewById(R.id.progresbarTopografoPleno);
        progressBarTopografoSR = (ProgressBar) findViewById(R.id.progresbarTopografoSenior);
        textProgressBarTopografoJR = (TextView) findViewById(R.id.textProgressBarTopografoJunior);
        textProgressBarTopografoPL = (TextView) findViewById(R.id.textProgressBarTopografoPleno);
        textProgressBarTopografoSR = (TextView) findViewById(R.id.textProgressBarTopografoSenior);
        linearEconomistaJunior = (LinearLayout) findViewById(R.id.linearEconomistaJunior);
        linearEconomistaPleno = (LinearLayout) findViewById(R.id.linearEconomistaPleno);
        linearEconomistaSenior = (LinearLayout) findViewById(R.id.linearEconomistaSenior);
        progressBarEconomistaJR = (ProgressBar) findViewById(R.id.progresbarEconomistaJunior);
        progressBarEconomistaPL = (ProgressBar) findViewById(R.id.progresbarEconomistaPleno);
        progressBarEconomistaSR = (ProgressBar) findViewById(R.id.progresbarEconomistaSenior);
        textProgressBarEconomistaJR = (TextView) findViewById(R.id.textProgressBarEconomistaJunior);
        textProgressBarEconomistaPL = (TextView) findViewById(R.id.textProgressBarEconomistaPleno);
        textProgressBarEconomistaSR = (TextView) findViewById(R.id.textProgressBarEconomistaSenior);
        linearHeraldistaJunior = (LinearLayout) findViewById(R.id.linearHeraldistaJunior);
        linearHeraldistaPleno = (LinearLayout) findViewById(R.id.linearHeraldistaPleno);
        linearHeraldistaSenior = (LinearLayout) findViewById(R.id.linearHeraldistaSenior);
        progressBarHeraldistaJR = (ProgressBar) findViewById(R.id.progresbarHeraldistaJunior);
        progressBarHeraldistaPL = (ProgressBar) findViewById(R.id.progresbarHeraldistaPleno);
        progressBarHeraldistaSR = (ProgressBar) findViewById(R.id.progresbarHeraldistaSenior);
        textProgressBarHeraldistaJR = (TextView) findViewById(R.id.textProgressBarHeraldistaJunior);
        textProgressBarHeraldistaPL = (TextView) findViewById(R.id.textProgressBarHeraldistaPleno);
        textProgressBarHeraldistaSR = (TextView) findViewById(R.id.textProgressBarHeraldistaSenior);
        linearTuristaJunior = (LinearLayout) findViewById(R.id.linearTuristaJunior);
        linearTuristaPleno = (LinearLayout) findViewById(R.id.linearTuristaPleno);
        linearTuristaSenior = (LinearLayout) findViewById(R.id.linearTuristaSenior);
        progressBarTuristaJR = (ProgressBar) findViewById(R.id.progresbarTuristaJunior);
        progressBarTuristaPL = (ProgressBar) findViewById(R.id.progresbarTuristaPleno);
        progressBarTuristaSR = (ProgressBar) findViewById(R.id.progresbarTuristaSenior);
        textProgressBarTuristaJR = (TextView) findViewById(R.id.textProgressBarTuristaJunior);
        textProgressBarTuristaPL = (TextView) findViewById(R.id.textProgressBarTuristaPleno);
        textProgressBarTuristaSR = (TextView) findViewById(R.id.textProgressBarTuristaSenior);
        linearVenca1Partida = (LinearLayout) findViewById(R.id.linearVenca1Partida);
        linearVenca10Partida = (LinearLayout) findViewById(R.id.linearVenca10Partidas);
        linearVenca50Partida = (LinearLayout) findViewById(R.id.linearVenca50Partidas);
        linearVenca100Partida = (LinearLayout) findViewById(R.id.linearVenca100Partidas);
        linearVenca200Partida = (LinearLayout) findViewById(R.id.linearVenca200Partidas);
        linearVenca500Partida = (LinearLayout) findViewById(R.id.linearVenca500Partidas);
        linearVenca1000Partida = (LinearLayout) findViewById(R.id.linearVenca1000Partidas);
        progressBarVenca1Partida = (ProgressBar) findViewById(R.id.progressBarVenca1Partida);
        progressBarVenca10Partida = (ProgressBar) findViewById(R.id.progressBarVenca10Partidas);
        progressBarVenca50Partida = (ProgressBar) findViewById(R.id.progressBarVenca50Partidas);
        progressBarVenca100Partida = (ProgressBar) findViewById(R.id.progressBarVenca100Partidas);
        progressBarVenca200Partida = (ProgressBar) findViewById(R.id.progressBarVenca200Partidas);
        progressBarVenca500Partida = (ProgressBar) findViewById(R.id.progressBarVenca500Partidas);
        progressBarVenca1000Partida = (ProgressBar) findViewById(R.id.progressBarVenca1000Partidas);
        textProgressBarVenca1Partida = (TextView) findViewById(R.id.textProgressBarVenca1Partida);
        textProgressBarVenca10Partida = (TextView) findViewById(R.id.textProgressBarVenca10Partidas);
        textProgressBarVenca50Partida = (TextView) findViewById(R.id.textProgressBarVenca50Partidas);
        textProgressBarVenca100Partida = (TextView) findViewById(R.id.textProgressBarVenca100Partidas);
        textProgressBarVenca200Partida = (TextView) findViewById(R.id.textProgressBarVenca200Partidas);
        textProgressBarVenca500Partida = (TextView) findViewById(R.id.textProgressBarVenca500Partidas);
        textProgressBarVenca1000Partida = (TextView) findViewById(R.id.textProgressBarVenca1000Partidas);
        linearVenca10PartidaSemErros = (LinearLayout) findViewById(R.id.linearVenca10PartidasSemErros);
        linearVenca50PartidaSemErros = (LinearLayout) findViewById(R.id.linearVenca50PartidasSemErros);
        linearVenca100PartidaSemErros = (LinearLayout) findViewById(R.id.linearVenca100PartidasSemErros);
        linearVenca200PartidaSemErros = (LinearLayout) findViewById(R.id.linearVenca200PartidasSemErros);
        progressBarVenca10PartidaSemErros = (ProgressBar) findViewById(R.id.progressBarVenca10PartidasSemErros);
        progressBarVenca50PartidaSemErros = (ProgressBar) findViewById(R.id.progressBarVenca50PartidasSemErros);
        progressBarVenca100PartidaSemErros = (ProgressBar) findViewById(R.id.progressBarVenca100PartidasSemErros);
        progressBarVenca200PartidaSemErros = (ProgressBar) findViewById(R.id.progressBarVenca200PartidasSemErros);
        textProgressBarVenca10PartidaSemErros = (TextView) findViewById(R.id.textProgressBarVenca10PartidasSemErros);
        textProgressBarVenca50PartidaSemErros = (TextView) findViewById(R.id.textProgressBarVenca50PartidasSemErros);
        textProgressBarVenca100PartidaSemErros = (TextView) findViewById(R.id.textProgressBarVenca100PartidasSemErros);
        textProgressBarVenca200PartidaSemErros = (TextView) findViewById(R.id.textProgressBarVenca200PartidasSemErros);
        linearDesbloqueiaCategorias = (LinearLayout) findViewById(R.id.linearDesbloqueieTodasCategorias);
        progressBarDesbloqueiaCategorias = (ProgressBar) findViewById(R.id.progressBarDesbloqueieTodasCategorias);
        textProgressBarDesbloqueiaCategorias = (TextView) findViewById(R.id.textProgressBarDesbloqueieTodasCategorias);
        linear30MilPontos = (LinearLayout)findViewById(R.id.linear30MilPontos);
        linear50MilPontos = (LinearLayout)findViewById(R.id.linear50MilPontos);
        linear100MilPontos = (LinearLayout)findViewById(R.id.linear100MilPontos);
        linear500MilPontos = (LinearLayout)findViewById(R.id.linear500MilPontos);
        linear800MilPontos = (LinearLayout)findViewById(R.id.linear800MilPontos);
        linear1MilhaoPontos = (LinearLayout)findViewById(R.id.linear1MilhaoPontos);
        progressBar30MilPontos = (ProgressBar) findViewById(R.id.progressBar30MilPontos);
        progressBar50MilPontos = (ProgressBar) findViewById(R.id.progressBar50MilPontos);
        progressBar100MilPontos = (ProgressBar) findViewById(R.id.progressBar100MilPontos);
        progressBar500MilPontos = (ProgressBar) findViewById(R.id.progressBar500MilPontos);
        progressBar800MilPontos = (ProgressBar) findViewById(R.id.progressBar800MilPontos);
        progressBar1MilhaoPontos = (ProgressBar) findViewById(R.id.progressBar1MilhaoPontos);
        textProgressBar30MilPontos = (TextView) findViewById(R.id.textProgressBar30MilPontos);
        textProgressBar50MilPontos = (TextView) findViewById(R.id.textProgressBar50MilPontos);
        textProgressBar100MilPontos = (TextView) findViewById(R.id.textProgressBar100MilPontos);
        textProgressBar500MilPontos = (TextView) findViewById(R.id.textProgressBar500MilPontos);
        textProgressBar800MilPontos = (TextView) findViewById(R.id.textProgressBar800MilPontos);
        textProgressBar1MilhaoPontos = (TextView) findViewById(R.id.textProgressBar1MilhaoPontos);
        linear10PartidasSeguidas = (LinearLayout) findViewById(R.id.linear10PartidasSeguidas);
        linear50PartidasSeguidas = (LinearLayout) findViewById(R.id.linear50PartidasSeguidas);
        linear100PartidasSeguidas = (LinearLayout) findViewById(R.id.linear100PartidasSeguidas);
        linear5PartidasSeguidas = (LinearLayout) findViewById(R.id.linear5PartidasSeguidas);
        progressBar10PartidasSeguidas = (ProgressBar) findViewById(R.id.progressBar10PartidasSeguidas);
        progressBar50PartidasSeguidas = (ProgressBar) findViewById(R.id.progressBar50PartidasSeguidas);
        progressBar100PartidasSeguidas = (ProgressBar) findViewById(R.id.progressBar100PartidasSeguidas);
        progressBar5PartidasSeguidas = (ProgressBar) findViewById(R.id.progressBar5PartidasSeguidas);
        textProgressBar10PartidasSeguidas = (TextView) findViewById(R.id.textProgressBar10PartidasSeguidas);
        textProgressBar50PartidasSeguidas = (TextView) findViewById(R.id.textProgressBar50PartidasSeguidas);
        textProgressBar100PartidasSeguidas = (TextView) findViewById(R.id.textProgressBar100PartidasSeguidas);
        textProgressBar5PartidasSeguidas = (TextView) findViewById(R.id.textProgressBar5PartidasSeguidas);
        linear10PartidasTodasCategorias = (LinearLayout) findViewById(R.id.linear10PartidasTodasCategorias);
        linear50PartidasTodasCategorias = (LinearLayout) findViewById(R.id.linear50PartidasTodasCategorias);
        linear100PartidasTodasCategorias = (LinearLayout) findViewById(R.id.linear100PartidasTodasCategorias);
        linear200PartidasTodasCategorias = (LinearLayout) findViewById(R.id.linear200PartidasTodasCategorias);
        linear1PartidaTodasCategorias = (LinearLayout) findViewById(R.id.linear1PartidaTodasCategorias);
        progressBar10PartidasTodasCategorias = (ProgressBar) findViewById(R.id.progressBar10PartidasTodasCategorias);
        progressBar50PartidasTodasCategorias = (ProgressBar) findViewById(R.id.progressBar50PartidasTodasCategorias);
        progressBar100PartidasTodasCategorias = (ProgressBar) findViewById(R.id.progressBar100PartidasTodasCategorias);
        progressBar200PartidasTodasCategorias = (ProgressBar) findViewById(R.id.progressBar200PartidasTodasCategorias);
        progressBar1PartidaTodasCategorias = (ProgressBar) findViewById(R.id.progressBar1PartidaTodasCategorias);
        textProgressBar10PartidasTodasCategorias = (TextView) findViewById(R.id.textProgressBar10PartidasTodasCategorias);
        textProgressBar50PartidasTodasCategorias = (TextView) findViewById(R.id.textProgressBar50PartidasTodasCategorias);
        textProgressBar100PartidasTodasCategorias = (TextView) findViewById(R.id.textProgressBar100PartidasTodasCategorias);
        textProgressBar200PartidasTodasCategorias = (TextView) findViewById(R.id.textProgressBar200PartidasTodasCategorias);
        textProgressBar1PartidaTodasCategorias = (TextView) findViewById(R.id.textProgressBar1PartidaTodasCategorias);
        linearEspecialistaAsia = (LinearLayout) findViewById(R.id.linearEspecialistaAsia);
        linearEspecialistaAfrica = (LinearLayout) findViewById(R.id.linearEspecialistaAfrica);
        linearEspecialistaAmerica = (LinearLayout) findViewById(R.id.linearEspecialistaAmerica);
        linearEspecialistaOceania = (LinearLayout) findViewById(R.id.linearEspecialistaOceania);
        linearEspecialistaEuropa = (LinearLayout) findViewById(R.id.linearEspecialistaEuropa);


        progressBarEspecialistaAsia = (ProgressBar) findViewById(R.id.progressBarEspecialistaAsia);
        progressBarEspecialistaAfrica = (ProgressBar) findViewById(R.id.progressBarEspecialistaAfrica);
        progressBarEspecialistaAmerica = (ProgressBar) findViewById(R.id.progressBarEspecialistaAmerica);
        progressBarEspecialistaOceania = (ProgressBar) findViewById(R.id.progressBarEspecialistaOceania);
        progressBarEspecialistaEuropa = (ProgressBar) findViewById(R.id.progressBarEspecialistaEuropa);



        textProgressBarEspecialistaAsia = (TextView) findViewById(R.id.textProgressBarEspecialistaAsia);
        textProgressBarEspecialistaAfrica = (TextView) findViewById(R.id.textProgressBarEspecialistaAfrica);
        textProgressBarEspecialistaAmerica = (TextView) findViewById(R.id.textProgressBarEspecialistaAmerica);
        textProgressBarEspecialistaOceania = (TextView) findViewById(R.id.textProgressBarEspecialistaOceania);
        textProgressBarEspecialistaEuropa = (TextView) findViewById(R.id.textProgressBarEspecialistaEuropa);



    }

    private ArrayList statusConquistas() {
        ConquistasDAO dao=new ConquistasDAO(this);
        ArrayList <Conquista> list = new ArrayList();
        list=dao.consultaConquistas();
        return list;
    }

    private ArrayList statusConquistasSemCategoria() {
        ConquistasDAO dao=new ConquistasDAO(this);
        ArrayList <Conquista> list = new ArrayList();
        list=dao.consultaConquistasSemCategoria();
        return list;
    }

    private ArrayList defineArrayCamposConquistas() {
        ArrayList <LinearLayout> list = new ArrayList();
        list.add(linearGeografoJunior);
        list.add(linearGeografoPleno);
        list.add(linearGeografoSenior);
        list.add(linearLinguistaJunior);
        list.add(linearLinguistaPleno);
        list.add(linearLinguistaSenior);
        list.add(linearPoliticoJunior);
        list.add(linearPoliticoPleno);
        list.add(linearPoliticoSenior);
        list.add(linearVexilologistaJunior);
        list.add(linearVexilologistaPleno);
        list.add(linearVexilologistaSenior);
        list.add(linearDemografoJunior);
        list.add(linearDemografoPleno);
        list.add(linearDemografoSenior);
        list.add(linearCartografoJunior);
        list.add(linearCartografoPleno);
        list.add(linearCartografoSenior);
        list.add(linearTopografoJunior);
        list.add(linearTopografoPleno);
        list.add(linearTopografoSenior);
        list.add(linearEconomistaJunior);
        list.add(linearEconomistaPleno);
        list.add(linearEconomistaSenior);
        list.add(linearHeraldistaJunior);
        list.add(linearHeraldistaPleno);
        list.add(linearHeraldistaSenior);
        list.add(linearTuristaJunior);
        list.add(linearTuristaPleno);
        list.add(linearTuristaSenior);
        return list;
    }

    private ArrayList defineArrayTextViewProgressBar() {
        ArrayList <TextView> list = new ArrayList();
        list.add(textProgressBarGeografoJR);
        list.add(textProgressBarGeografoPL);
        list.add(textProgressBarGeografoSR);
        list.add(textProgressBarLinguistaJR);
        list.add(textProgressBarLinguistaPL);
        list.add(textProgressBarLinguistaSR);
        list.add(textProgressBarPoliticoJR);
        list.add(textProgressBarPoliticoPL);
        list.add(textProgressBarPoliticoSR);
        list.add(textProgressBarVexilologistaJR);
        list.add(textProgressBarVexilologistaPL);
        list.add(textProgressBarVexilologistaSR);
        list.add(textProgressBarDemografoJR);
        list.add(textProgressBarDemografoPL);
        list.add(textProgressBarDemografoSR);
        list.add(textProgressBarCartografoJR);
        list.add(textProgressBarCartografoPL);
        list.add(textProgressBarCartografoSR);
        list.add(textProgressBarTopografoJR);
        list.add(textProgressBarTopografoPL);
        list.add(textProgressBarTopografoSR);
        list.add(textProgressBarEconomistaJR);
        list.add(textProgressBarEconomistaPL);
        list.add(textProgressBarEconomistaSR);
        list.add(textProgressBarHeraldistaJR);
        list.add(textProgressBarHeraldistaPL);
        list.add(textProgressBarHeraldistaSR);
        list.add(textProgressBarTuristaJR);
        list.add(textProgressBarTuristaPL);
        list.add(textProgressBarTuristaSR);
        return list;
    }

    private ArrayList defineArrayProgressBar() {
        ArrayList <ProgressBar> list = new ArrayList();
        list.add(progressBarGeografoJR);
        list.add(progressBarGeografoPL);
        list.add(progressBarGeografoSR);
        list.add(progressBarLinguistaJR);
        list.add(progressBarLinguistaPL);
        list.add(progressBarLinguistaSR);
        list.add(progressBarPoliticoJR);
        list.add(progressBarPoliticoPL);
        list.add(progressBarPoliticoSR);
        list.add(progressBarVexilologistaJR);
        list.add(progressBarVexilologistaPL);
        list.add(progressBarVexilologistaSR);
        list.add(progressBarDemografoJR);
        list.add(progressBarDemografoPL);
        list.add(progressBarDemografoSR);
        list.add(progressBarCartografoJR);
        list.add(progressBarCartografoPL);
        list.add(progressBarCartografoSR);
        list.add(progressBarTopografoJR);
        list.add(progressBarTopografoPL);
        list.add(progressBarTopografoSR);
        list.add(progressBarEconomistaJR);
        list.add(progressBarEconomistaPL);
        list.add(progressBarEconomistaSR);
        list.add(progressBarHeraldistaJR);
        list.add(progressBarHeraldistaPL);
        list.add(progressBarHeraldistaSR);
        list.add(progressBarTuristaJR);
        list.add(progressBarTuristaPL);
        list.add(progressBarTuristaSR);
        return list;
    }

    private ArrayList defineArrayCamposConquistasSemCategoria(){
        ArrayList<LinearLayout> list = new ArrayList<LinearLayout>();
        list.add(linearVenca1Partida);
        list.add(linearVenca10Partida);
        list.add(linearVenca50Partida);
        list.add(linearVenca100Partida);
        list.add(linearVenca200Partida);
        list.add(linearVenca500Partida);
        list.add(linearVenca1000Partida);
        list.add(linearVenca10PartidaSemErros);
        list.add(linearVenca50PartidaSemErros);
        list.add(linearVenca100PartidaSemErros);
        list.add(linearVenca200PartidaSemErros);
        list.add(linearDesbloqueiaCategorias);
        list.add(linear30MilPontos);
        list.add(linear50MilPontos);
        list.add(linear100MilPontos);
        list.add(linear500MilPontos);
        list.add(linear800MilPontos);
        list.add(linear1MilhaoPontos);
        list.add(linear5PartidasSeguidas);
        list.add(linear10PartidasSeguidas);
        list.add(linear50PartidasSeguidas);
        list.add(linear100PartidasSeguidas);
        list.add(linear1PartidaTodasCategorias);
        list.add(linear10PartidasTodasCategorias);
        list.add(linear50PartidasTodasCategorias);
        list.add(linear100PartidasTodasCategorias);
        list.add(linear200PartidasTodasCategorias);
        list.add(linearEspecialistaAfrica);
        list.add(linearEspecialistaAmerica);
        list.add(linearEspecialistaAsia);
        list.add(linearEspecialistaEuropa);
        list.add(linearEspecialistaOceania);
        return list;
    }

    private ArrayList defineArrayTextViewProgressBarSemCategoria(){
        ArrayList<TextView> list = new ArrayList<TextView>();
        list.add(textProgressBarVenca1Partida);
        list.add(textProgressBarVenca10Partida);
        list.add(textProgressBarVenca50Partida);
        list.add(textProgressBarVenca100Partida);
        list.add(textProgressBarVenca200Partida);
        list.add(textProgressBarVenca500Partida);
        list.add(textProgressBarVenca1000Partida);
        list.add(textProgressBarVenca10PartidaSemErros);
        list.add(textProgressBarVenca50PartidaSemErros);
        list.add(textProgressBarVenca100PartidaSemErros);
        list.add(textProgressBarVenca200PartidaSemErros);
        list.add(textProgressBarDesbloqueiaCategorias);
        list.add(textProgressBar30MilPontos);
        list.add(textProgressBar50MilPontos);
        list.add(textProgressBar100MilPontos);
        list.add(textProgressBar500MilPontos);
        list.add(textProgressBar800MilPontos);
        list.add(textProgressBar1MilhaoPontos);
        list.add(textProgressBar5PartidasSeguidas);
        list.add(textProgressBar10PartidasSeguidas);
        list.add(textProgressBar50PartidasSeguidas);
        list.add(textProgressBar100PartidasSeguidas);
        list.add(textProgressBar1PartidaTodasCategorias);
        list.add(textProgressBar10PartidasTodasCategorias);
        list.add(textProgressBar50PartidasTodasCategorias);
        list.add(textProgressBar100PartidasTodasCategorias);
        list.add(textProgressBar200PartidasTodasCategorias);
        list.add(textProgressBarEspecialistaAfrica);
        list.add(textProgressBarEspecialistaAmerica);
        list.add(textProgressBarEspecialistaAsia);
        list.add(textProgressBarEspecialistaEuropa);
        list.add(textProgressBarEspecialistaOceania);
        return list;
    }

    private ArrayList defineArrayProgressBarSemCategoria(){
        ArrayList<ProgressBar> list = new ArrayList<ProgressBar>();
        list.add(progressBarVenca1Partida);
        list.add(progressBarVenca10Partida);
        list.add(progressBarVenca50Partida);
        list.add(progressBarVenca100Partida);
        list.add(progressBarVenca200Partida);
        list.add(progressBarVenca500Partida);
        list.add(progressBarVenca1000Partida);
        list.add(progressBarVenca10PartidaSemErros);
        list.add(progressBarVenca50PartidaSemErros);
        list.add(progressBarVenca100PartidaSemErros);
        list.add(progressBarVenca200PartidaSemErros);
        list.add(progressBarDesbloqueiaCategorias);
        list.add(progressBar30MilPontos);
        list.add(progressBar50MilPontos);
        list.add(progressBar100MilPontos);
        list.add(progressBar500MilPontos);
        list.add(progressBar800MilPontos);
        list.add(progressBar1MilhaoPontos);
        list.add(progressBar5PartidasSeguidas);
        list.add(progressBar10PartidasSeguidas);
        list.add(progressBar50PartidasSeguidas);
        list.add(progressBar100PartidasSeguidas);
        list.add(progressBar1PartidaTodasCategorias);
        list.add(progressBar10PartidasTodasCategorias);
        list.add(progressBar50PartidasTodasCategorias);
        list.add(progressBar100PartidasTodasCategorias);
        list.add(progressBar200PartidasTodasCategorias);
        list.add(progressBarEspecialistaAfrica);
        list.add(progressBarEspecialistaAmerica);
        list.add(progressBarEspecialistaAsia);
        list.add(progressBarEspecialistaEuropa);
        list.add(progressBarEspecialistaOceania);
        return list;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint({"UseCompatLoadingForDrawables", "ResourceType"})
    private void verificaConquistasDesbloqueadas(ArrayList<LinearLayout> listLinearLayout,
                                                 ArrayList<ProgressBar> listProgressBar,
                                                 ArrayList <TextView> listTextProgressBar,
                                                 ArrayList<Conquista> listConquistas) {
        for(int i=0; i<listLinearLayout.size();i++){
            Conquista c = listConquistas.get(i);
            listProgressBar.get(i).setProgress(c.getQtdAcertos());
            listProgressBar.get(i).setTooltipText("true");
            if(listProgressBar.get(i).getMax()>=c.getQtdAcertos()) {
                listTextProgressBar.get(i).setText(Integer.toString(c.getQtdAcertos()));
            }else{
                listTextProgressBar.get(i).setText(Integer.toString(listProgressBar.get(i).getMax()));
            }
            if(c.getDesbloqueio()==1){
                listProgressBar.get(i).setProgressDrawable(getDrawable(R.drawable.custom_progress_bar_horizontal_desbloqueada));
                listTextProgressBar.get(i).setVisibility(View.INVISIBLE);
            }else{
                listLinearLayout.get(i).setAlpha((float) 0.4);
            }
        }
    }

    @Override
    public void onBackPressed(){

        Intent i = new Intent(com.ex.geografando.Activitys.ConquistasActivity.this, MainActivity.class);
        startActivity(i);
        finishAffinity();

    }

    public void voltar(View view){

        startActivity(new Intent(com.ex.geografando.Activitys.ConquistasActivity.this, MainActivity.class));
        finishAffinity();
    }

    public void escondeNavigationBar(){

        this.getWindow().getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);


    }

}