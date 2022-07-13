package com.ex.geografando.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.ex.geografando.Activitys.RecordsActivity;
import com.ex.geografando.R;

import java.util.ArrayList;

public class EstatisticaGeral extends Fragment {

    private TextView textViewPartidasIniciadas,
            textViewPartidasVencidas,
            textViewPercentualPartidasVencidas,
            textViewPerguntasRespondidas,
            textViewPerguntasCertas,
            textViewPercentualPerguntasCertas,
            texViewTotalMelhorPontuacaoGeral,
            texViewTotalMediaPontosGeral,
            texViewQuantidadSequenciaAtualGeral,
            texViewQuantidadeMaiorSequenciaGeral,
            texViewQuantidadePartidasSemErrosGeral;

    private View view;

    private TableLayout tableLayoutPartidasJogadasGeral;
    private TableRow tableRowPartidasJogadasGeral;



    public EstatisticaGeral(){

    }

    public static com.ex.geografando.Fragments.EstatisticaGeral newInstance(){
        com.ex.geografando.Fragments.EstatisticaGeral fragment = new com.ex.geografando.Fragments.EstatisticaGeral();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_estatisticas_geral,container,false);

        tableLayoutPartidasJogadasGeral = view.findViewById(R.id.tableLayoutPartidasGeral);
        tableRowPartidasJogadasGeral = view.findViewById(R.id.tableRowPartidasJogadasGeral);



        //Quadro PARTIDAS findViewById
        textViewPartidasIniciadas = (TextView) view.findViewById(R.id.texViewQuantidadePartidasJogadasGeral);
        textViewPartidasVencidas = (TextView) view.findViewById(R.id.texViewQuantidadePartidasVencidasGeral);
        textViewPercentualPartidasVencidas = (TextView)view.findViewById(R.id.texViewPercentualVitoriasValorGeral);
        textViewPerguntasRespondidas = (TextView) view.findViewById(R.id.texViewQuantidadeTotalPerguntasGeral);
        textViewPerguntasCertas = (TextView) view.findViewById(R.id.texViewTotalRespostasCertasGeral);
        textViewPercentualPerguntasCertas = (TextView) view.findViewById(R.id.texViewPercentualTotalRespostasCertasGeral);


        //Quadro PONTUAÇÂO findViewById
        texViewTotalMelhorPontuacaoGeral = (TextView) view.findViewById(R.id.texViewTotalMelhorPontuacaoGeral);
        texViewTotalMediaPontosGeral = (TextView) view.findViewById(R.id.texViewTotalMediaPontosGeral);


        //Quadro SEQUENCIA findViewById
        texViewQuantidadSequenciaAtualGeral = (TextView) view.findViewById(R.id.texViewQuantidadSequenciaAtualGeral);
        texViewQuantidadeMaiorSequenciaGeral = (TextView) view.findViewById(R.id.texViewQuantidadeMaiorSequenciaGeral);
        texViewQuantidadePartidasSemErrosGeral = (TextView) view.findViewById(R.id.texViewQuantidadePartidasSemErrosGeral);


        buscaValores();

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(getArguments() != null){

        }

    }



    private void buscaValores() {
        RecordsActivity ra = RecordsActivity.ra;
        ArrayList dadosEstatisticasGeral = (ArrayList) ra.dadosEstatisticasGeral(1);// 1 é o codigo para resultados GERAL
        mostraValores(dadosEstatisticasGeral);
    }

    private void mostraValores(ArrayList dadosEstatisticasGeral) {



        textViewPartidasIniciadas.setText(Integer.toString((Integer) dadosEstatisticasGeral.get(0)));
        textViewPartidasVencidas.setText(Integer.toString((Integer)dadosEstatisticasGeral.get(1)));
        textViewPercentualPartidasVencidas.setText(Integer.toString((Integer)dadosEstatisticasGeral.get(2))+"%");


        textViewPerguntasRespondidas.setText(Integer.toString((Integer)dadosEstatisticasGeral.get(3)));
        textViewPerguntasCertas.setText(Integer.toString((Integer)dadosEstatisticasGeral.get(4)));
        textViewPercentualPerguntasCertas.setText(Integer.toString((Integer)dadosEstatisticasGeral.get(5))+"%");



        texViewTotalMelhorPontuacaoGeral.setText(Integer.toString((Integer)dadosEstatisticasGeral.get(6)));
        texViewTotalMediaPontosGeral.setText(Integer.toString((Integer)dadosEstatisticasGeral.get(7)));



        texViewQuantidadSequenciaAtualGeral.setText(Integer.toString((Integer)dadosEstatisticasGeral.get(8)));
        texViewQuantidadeMaiorSequenciaGeral.setText(Integer.toString((Integer)dadosEstatisticasGeral.get(9)));
        texViewQuantidadePartidasSemErrosGeral.setText(Integer.toString((Integer)dadosEstatisticasGeral.get(10)));


    }

}
