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

public class EstatisticaCategorias extends Fragment {

    TextView textViewPerguntasRespondidasCapitais,
             textViewPerguntasCertasCapitais,
             textViewPercentualCapitais,
            textViewPerguntasRespondidasBandeiras,
            textViewPerguntasCertaBandeiras,
            textViewPercentualBandeiras,
            textViewPerguntasRespondidasIdiomas,
            textViewPerguntasCertasIdiomas,
            textViewPercentualIdiomas,
            textViewPerguntasRespondidasPopulacao,
            textViewPerguntasCertasPopulacao,
            textViewPercentualPopulacao,
            textViewPerguntasRespondidasArea,
            textViewPerguntasCertasArea,
            textViewPercentualArea,
            textViewPerguntasRespondidasContinente,
            textViewPerguntasCertasContinente,
            textViewPercentualContinente,
            textViewPerguntasRespondidasMoeda,
            textViewPerguntasCertasMoeda,
            textViewPercentualMoeda,
            textViewPerguntasRespondidasEmblema,
            textViewPerguntasCertasEmblema,
            textViewPercentualEmblema,
            textViewPerguntasRespondidasMapa,
            textViewPerguntasCertasMapa,
            textViewPercentualMapa,
            textViewPerguntasRespondidasCidades,
            textViewPerguntasCertasCidades,
            textViewPercentualCidades;

    LinearLayout linearContinente, linearCapitais, linearIdiomas,linearBandeiras,linearPopulacao,
            linearMapa,linearArea,linearEmblema,linearCidades, linearMoedas;



    ArrayList<TextView> arrayText;
    ArrayList<LinearLayout> arrayLinear;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_estatisticas_categorias,container,false);
        arrayLinear = new ArrayList<LinearLayout>();
        linearArea = (LinearLayout) view.findViewById(R.id.linearArea);
        linearContinente = (LinearLayout) view.findViewById(R.id.linearContinentes);
        linearMoedas = (LinearLayout) view.findViewById(R.id.linearMoeda);
        linearBandeiras = (LinearLayout) view.findViewById(R.id.linearBandeiras);
        linearEmblema = (LinearLayout) view.findViewById(R.id.linearEmblema);
        linearCidades = (LinearLayout) view.findViewById(R.id.linearCidades);
        linearPopulacao = (LinearLayout) view.findViewById(R.id.linearPopulacao);
        linearIdiomas = (LinearLayout) view.findViewById(R.id.linearIdiomas);
        linearMapa = (LinearLayout) view.findViewById(R.id.linearMapas);
        linearCapitais = (LinearLayout) view.findViewById(R.id.linearCapitais);
        textViewPerguntasRespondidasCapitais   = (TextView) view.findViewById(R.id.texViewQuantidadePerguntasRespondidasCapitais);
        textViewPerguntasCertasCapitais        = (TextView) view.findViewById(R.id.texViewQuantidadePerguntasCertasCapitais);
        textViewPercentualCapitais             = (TextView) view.findViewById(R.id.texViewPercentualValorPerguntasCertasCapitais);
        textViewPerguntasRespondidasBandeiras  = (TextView) view.findViewById(R.id.texViewQuantidadePerguntasRespondidasBandeiras);
        textViewPerguntasCertaBandeiras        = (TextView) view.findViewById(R.id.texViewQuantidadePerguntasCertasBandeiras);
        textViewPercentualBandeiras            = (TextView) view.findViewById(R.id.texViewPercentualValorPerguntasCertasBandeiras);
        textViewPerguntasRespondidasIdiomas    = (TextView) view.findViewById(R.id.texViewQuantidadePerguntasRespondidasIdiomas);
        textViewPerguntasCertasIdiomas         = (TextView) view.findViewById(R.id.texViewQuantidadePerguntasCertasIdiomas);
        textViewPercentualIdiomas              = (TextView) view.findViewById(R.id.texViewPercentualValorPerguntasCertasIdiomas);
        textViewPerguntasRespondidasPopulacao  = (TextView) view.findViewById(R.id.texViewQuantidadePerguntasRespondidasPopulacao);
        textViewPerguntasCertasPopulacao       = (TextView) view.findViewById(R.id.texViewQuantidadePerguntasCertasPopulacao);
        textViewPercentualPopulacao            = (TextView) view.findViewById(R.id.texViewPercentualValorPerguntasCertasPopulacao);
        textViewPerguntasRespondidasArea       = (TextView) view.findViewById(R.id.texViewQuantidadePerguntasRespondidasArea);
        textViewPerguntasCertasArea            = (TextView) view.findViewById(R.id.texViewQuantidadePerguntasCertasArea);
        textViewPercentualArea                 = (TextView) view.findViewById(R.id.texViewPercentualValorPerguntasCertasArea);
        textViewPerguntasRespondidasContinente = (TextView) view.findViewById(R.id.texViewQuantidadePerguntasRespondidasContinente);
        textViewPerguntasCertasContinente      = (TextView) view.findViewById(R.id.texViewQuantidadePerguntasCertasContinente);
        textViewPercentualContinente           = (TextView) view.findViewById(R.id.texViewPercentualValorPerguntasCertasContinente);
        textViewPerguntasRespondidasMoeda      = (TextView) view.findViewById(R.id.texViewQuantidadePerguntasRespondidasMoeda);
        textViewPerguntasCertasMoeda           = (TextView) view.findViewById(R.id.texViewQuantidadePerguntasCertasMoeda);
        textViewPercentualMoeda                = (TextView) view.findViewById(R.id.texViewPercentualValorPerguntasCertasMoeda);
        textViewPerguntasRespondidasEmblema    = (TextView) view.findViewById(R.id.texViewQuantidadePerguntasRespondidasEmblema);
        textViewPerguntasCertasEmblema         = (TextView) view.findViewById(R.id.texViewQuantidadePerguntasCertasEmblema);
        textViewPercentualEmblema              = (TextView) view.findViewById(R.id.texViewPercentualValorPerguntasCertasEmblema);
        textViewPerguntasRespondidasMapa       = (TextView) view.findViewById(R.id.texViewQuantidadePerguntasRespondidasMapa);
        textViewPerguntasCertasMapa            = (TextView) view.findViewById(R.id.texViewQuantidadePerguntasCertasMapa);
        textViewPercentualMapa                 = (TextView) view.findViewById(R.id.texViewPercentualValorPerguntasCertasMapa);
        textViewPerguntasRespondidasCidades    = (TextView) view.findViewById(R.id.texViewQuantidadePerguntasRespondidasCidades);
        textViewPerguntasCertasCidades         = (TextView) view.findViewById(R.id.texViewQuantidadePerguntasCertasCidades);
        textViewPercentualCidades              = (TextView) view.findViewById(R.id.texViewPercentualValorPerguntasCertasCidades);

        addTextViewArray();
        addLinearLayoutArray();
        verificaCategoriasDesbloqueadas();

        buscaValoresCategorias();

        return view;
    }

    private void verificaCategoriasDesbloqueadas() {
        RecordsActivity ra = RecordsActivity.ra;
        for(int i=0; i<10;i++){
            boolean habilitada = ra.consultaCategoriasDesbloqueadas(i+1);
            if(!habilitada){
                arrayLinear.get(i).setAlpha((float)0.4);
            }
        }
    }

    private void addLinearLayoutArray(){
        arrayLinear.add(linearCapitais);
        arrayLinear.add(linearBandeiras);
        arrayLinear.add(linearIdiomas);
        arrayLinear.add(linearPopulacao);
        arrayLinear.add(linearArea);
        arrayLinear.add(linearContinente);
        arrayLinear.add(linearMoedas);
        arrayLinear.add(linearEmblema);
        arrayLinear.add(linearMapa);
        arrayLinear.add(linearCidades);
    }

    private void addTextViewArray() {
        arrayText.add(textViewPerguntasRespondidasCapitais  );
        arrayText.add(textViewPerguntasCertasCapitais       );
        arrayText.add(textViewPercentualCapitais            );
        arrayText.add(textViewPerguntasRespondidasBandeiras );
        arrayText.add(textViewPerguntasCertaBandeiras       );
        arrayText.add(textViewPercentualBandeiras           );
        arrayText.add(textViewPerguntasRespondidasIdiomas   );
        arrayText.add(textViewPerguntasCertasIdiomas        );
        arrayText.add(textViewPercentualIdiomas             );
        arrayText.add(textViewPerguntasRespondidasPopulacao );
        arrayText.add(textViewPerguntasCertasPopulacao      );
        arrayText.add(textViewPercentualPopulacao           );
        arrayText.add(textViewPerguntasRespondidasArea      );
        arrayText.add(textViewPerguntasCertasArea           );
        arrayText.add(textViewPercentualArea                );
        arrayText.add(textViewPerguntasRespondidasContinente);
        arrayText.add(textViewPerguntasCertasContinente     );
        arrayText.add(textViewPercentualContinente          );
        arrayText.add(textViewPerguntasRespondidasMoeda     );
        arrayText.add(textViewPerguntasCertasMoeda          );
        arrayText.add(textViewPercentualMoeda               );
        arrayText.add(textViewPerguntasRespondidasEmblema   );
        arrayText.add(textViewPerguntasCertasEmblema        );
        arrayText.add(textViewPercentualEmblema             );
        arrayText.add(textViewPerguntasRespondidasMapa      );
        arrayText.add(textViewPerguntasCertasMapa           );
        arrayText.add(textViewPercentualMapa                );
        arrayText.add(textViewPerguntasRespondidasCidades     );
        arrayText.add(textViewPerguntasCertasCidades           );
        arrayText.add(textViewPercentualCidades                );


    }

    private void buscaValoresCategorias() {
        RecordsActivity ra = RecordsActivity.ra;
        ArrayList dadosEstatisticasCategorias = (ArrayList) ra.dadosEstatisticasCategorias();// 1 é o codigo para resultados GERAL
        mostraValores(dadosEstatisticasCategorias);
    }

    private void mostraValores(ArrayList dadosEstatisticasCategorias) {
        //27 é a quantidade de itens, sendo que cada categoria possui 3 itens
        for(int i=0;i<30;i++){

            if((i+1)%3==0)
            {
                arrayText.get(i).setText(Integer.toString((Integer)dadosEstatisticasCategorias.get(i))+"%");
            }else{
            arrayText.get(i).setText(Integer.toString((Integer)dadosEstatisticasCategorias.get(i)));
            }
        }
    }

    public EstatisticaCategorias(){

    }

    public static com.ex.geografando.Fragments.EstatisticaCategorias newInstance(){
        com.ex.geografando.Fragments.EstatisticaCategorias fragment = new com.ex.geografando.Fragments.EstatisticaCategorias();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(getArguments() != null){

        }
        
        arrayText = new ArrayList<>();
    }

}
