package com.ex.geografando.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.ex.geografando.Activitys.RecordsActivity;
import com.ex.geografando.R;

import java.util.ArrayList;

public class EstatisticaRecords extends Fragment {

    private TextView totalPartidas15, partidasVencidas15, percentualVitorias15, pontuacaoMaxima15,
                    pontuacaoMedia15, sequenciaAtual15, maiorSequencia15, vitoriasSemErros15;
    private TextView totalPartidas30, partidasVencidas30, percentualVitorias30, pontuacaoMaxima30,
            pontuacaoMedia30, sequenciaAtual30, maiorSequencia30, vitoriasSemErros30;
    private TextView totalPartidas50, partidasVencidas50, percentualVitorias50, pontuacaoMaxima50,
            pontuacaoMedia50, sequenciaAtual50, maiorSequencia50, vitoriasSemErros50;
    private LinearLayout linear15, linear30, linear50;

    ArrayList<LinearLayout> listLinear;
    ArrayList<TextView> texts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_estatisticas_records,container,false);
        texts = new ArrayList();
        listLinear = new ArrayList();
        linear15 = (LinearLayout) view.findViewById(R.id.linear15);
        linear30 = (LinearLayout) view.findViewById(R.id.linear30);
        linear50 = (LinearLayout) view.findViewById(R.id.linear50);
        totalPartidas15 = (TextView) view.findViewById(R.id.texViewQuantidadePartidasJogadas15);
        partidasVencidas15 = (TextView) view.findViewById(R.id.texViewQuantidadePartidasVencidas15);
        percentualVitorias15 = (TextView) view.findViewById(R.id.texViewPercentualVitoriasValor15);
        pontuacaoMaxima15 = (TextView) view.findViewById(R.id.texViewTotalMelhorPontuacao15);
        pontuacaoMedia15 = (TextView) view.findViewById(R.id.texViewTotalMediaPontos15);
        sequenciaAtual15 = (TextView) view.findViewById(R.id.texViewQuantidadSequenciaAtual15);
        maiorSequencia15 = (TextView) view.findViewById(R.id.texViewQuantidadeMaiorSequencia15);
        vitoriasSemErros15 = (TextView) view.findViewById(R.id.texViewQuantidadePartidasSemErros15);
        totalPartidas30 = (TextView) view.findViewById(R.id.texViewQuantidadePartidasJogadas30);
        partidasVencidas30 = (TextView) view.findViewById(R.id.texViewQuantidadePartidasVencidas30);
        percentualVitorias30 = (TextView) view.findViewById(R.id.texViewPercentualVitoriasValor30);
        pontuacaoMaxima30 = (TextView) view.findViewById(R.id.texViewTotalMelhorPontuacao30);
        pontuacaoMedia30 = (TextView) view.findViewById(R.id.texViewTotalMediaPontos30);
        sequenciaAtual30 = (TextView) view.findViewById(R.id.texViewQuantidadSequenciaAtual30);
        maiorSequencia30 = (TextView) view.findViewById(R.id.texViewQuantidadeMaiorSequencia30);
        vitoriasSemErros30 = (TextView) view.findViewById(R.id.texViewQuantidadePartidasSemErros30);
        totalPartidas50 = (TextView) view.findViewById(R.id.texViewQuantidadePartidasJogadas50);
        partidasVencidas50 = (TextView) view.findViewById(R.id.texViewQuantidadePartidasVencidas50);
        percentualVitorias50 = (TextView) view.findViewById(R.id.texViewPercentualVitoriasValor50);
        pontuacaoMaxima50 = (TextView) view.findViewById(R.id.texViewTotalMelhorPontuacao50);
        pontuacaoMedia50 = (TextView) view.findViewById(R.id.texViewTotalMediaPontos50);
        sequenciaAtual50 = (TextView) view.findViewById(R.id.texViewQuantidadSequenciaAtual50);
        maiorSequencia50 = (TextView) view.findViewById(R.id.texViewQuantidadeMaiorSequencia50);
        vitoriasSemErros50 = (TextView) view.findViewById(R.id.texViewQuantidadePartidasSemErros50);

        arrayTextViews();
        arrayLinear();
        verificaDesbloqueioQtdPartidas();

        buscaValores();

        return view;
    }

    private void verificaDesbloqueioQtdPartidas() {
        RecordsActivity ra = RecordsActivity.ra;
        for(int i=0; i<3;i++){
            ArrayList<Integer> habilitada = ra.consultaQtdPartidasDesbloqueadas();
            if(habilitada.get(i)==0){
                listLinear.get(i).setAlpha((float)0.4);
            }
        }
    }

    private void arrayLinear() {
        listLinear.add(linear15);
        listLinear.add(linear30);
        listLinear.add(linear50);

    }

    private void arrayTextViews() {
        texts.add(totalPartidas15);
        texts.add(partidasVencidas15);
        texts.add(percentualVitorias15);
        texts.add(pontuacaoMaxima15);
        texts.add(pontuacaoMedia15);
        texts.add(sequenciaAtual15);
        texts.add(maiorSequencia15);
        texts.add(vitoriasSemErros15);
        texts.add(totalPartidas30);
        texts.add(partidasVencidas30);
        texts.add(percentualVitorias30);
        texts.add(pontuacaoMaxima30);
        texts.add(pontuacaoMedia30);
        texts.add(sequenciaAtual30);
        texts.add(maiorSequencia30);
        texts.add(vitoriasSemErros30);
        texts.add(totalPartidas50);
        texts.add(partidasVencidas50);
        texts.add(percentualVitorias50);
        texts.add(pontuacaoMaxima50);
        texts.add(pontuacaoMedia50);
        texts.add(sequenciaAtual50);
        texts.add(maiorSequencia50);
        texts.add(vitoriasSemErros50);
    }

    private void buscaValores() {
        RecordsActivity ra = RecordsActivity.ra;
        ArrayList valores =(ArrayList)ra.consultaDadosPorQuantidadePerguntas();
        mostraValores(valores);



    }

    private void mostraValores(ArrayList valores) {
        for(int i=0;i<24;i++){

            if(i==2||i==10||i==18){
                texts.get(i).setText(Integer.toString((Integer)valores.get(i))+"%");
            }else {
                texts.get(i).setText(Integer.toString((Integer) valores.get(i)));
            }
        }
    }

    public EstatisticaRecords(){

    }

    public static com.ex.geografando.Fragments.EstatisticaRecords newInstance(){
        com.ex.geografando.Fragments.EstatisticaRecords fragment = new com.ex.geografando.Fragments.EstatisticaRecords();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(getArguments() != null){

        }
    }

}
