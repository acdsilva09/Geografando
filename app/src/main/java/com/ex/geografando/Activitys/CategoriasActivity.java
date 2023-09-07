package com.ex.geografando.Activitys;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.ex.geografando.DAO.CategoriaDAO;
import com.ex.geografando.DAO.ConquistasDAO;
import com.ex.geografando.R;

import java.util.List;

public class CategoriasActivity extends AppCompatActivity {

    private RadioButton rb15,rb30,rb50, vibraS, vibraN, somS, somN;
    private CheckBox capital, bandeira, idioma, populacao, area, moeda, continente, emblema, mapa, cidades;
    private Button gravar;
    private CategoriaDAO categoria_dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);
        escondeNavigationBar();
        capital = (CheckBox)findViewById(R.id.checkBoxCapital);
        bandeira = (CheckBox)findViewById(R.id.checkBoxBandeira);
        idioma = (CheckBox)findViewById(R.id.checkBoxIdioma);
        moeda = (CheckBox)findViewById(R.id.checkBoxMoeda);
        emblema = (CheckBox)findViewById(R.id.checkBoxEmblema);
        continente = (CheckBox)findViewById(R.id.checkBoxContinente);
        populacao = (CheckBox)findViewById(R.id.checkBoxPopulacao);
        area = (CheckBox)findViewById(R.id.checkBoxArea);
        mapa = (CheckBox)findViewById(R.id.checkBoxMapa);
        cidades = (CheckBox)findViewById(R.id.checkBoxCidades);
        gravar = (Button)findViewById(R.id.buttonGravar);
        rb15 = (RadioButton)findViewById(R.id.radioButton15);
        rb30 = (RadioButton)findViewById(R.id.radioButton30);
        rb50 = (RadioButton)findViewById(R.id.radioButton50);
        vibraS = (RadioButton)findViewById(R.id.radioVibraSim);
        vibraN = (RadioButton)findViewById(R.id.radioVibraNao);
        somS = (RadioButton)findViewById(R.id.radioSonsSim);
        somN = (RadioButton)findViewById(R.id.radioSonsNao);
        categoria_dao = new CategoriaDAO(this);
        capital.setButtonTintList(new ColorStateList(CHECK_BOX_STATES, CHECK_BOX_COLORS));
        continente.setButtonTintList(new ColorStateList(CHECK_BOX_STATES, CHECK_BOX_COLORS));
        mapa.setButtonTintList(new ColorStateList(CHECK_BOX_STATES, CHECK_BOX_COLORS));
        area.setButtonTintList(new ColorStateList(CHECK_BOX_STATES, CHECK_BOX_COLORS));
        populacao.setButtonTintList(new ColorStateList(CHECK_BOX_STATES, CHECK_BOX_COLORS));
        cidades.setButtonTintList(new ColorStateList(CHECK_BOX_STATES, CHECK_BOX_COLORS));
        moeda.setButtonTintList(new ColorStateList(CHECK_BOX_STATES, CHECK_BOX_COLORS));
        bandeira.setButtonTintList(new ColorStateList(CHECK_BOX_STATES, CHECK_BOX_COLORS));
        emblema.setButtonTintList(new ColorStateList(CHECK_BOX_STATES, CHECK_BOX_COLORS));
        idioma.setButtonTintList(new ColorStateList(CHECK_BOX_STATES, CHECK_BOX_COLORS));
        rb15.setButtonTintList(new ColorStateList(CHECK_BOX_STATES, CHECK_BOX_COLORS));
        rb30.setButtonTintList(new ColorStateList(CHECK_BOX_STATES, CHECK_BOX_COLORS));
        rb50.setButtonTintList(new ColorStateList(CHECK_BOX_STATES, CHECK_BOX_COLORS));
        vibraN.setButtonTintList(new ColorStateList(CHECK_BOX_STATES, CHECK_BOX_COLORS));
        vibraS.setButtonTintList(new ColorStateList(CHECK_BOX_STATES, CHECK_BOX_COLORS));
        somN.setButtonTintList(new ColorStateList(CHECK_BOX_STATES, CHECK_BOX_COLORS));
        somS.setButtonTintList(new ColorStateList(CHECK_BOX_STATES, CHECK_BOX_COLORS));
        verificacategoriaHabilitada();
        verificaCategoriaSelecionada();
        verificaQuantidadeSelecionada();
        verificaQtdPerguntaHabilitada();
        verificaParametrosSelecionados();
        verificaPendenciaVisualizacao();
    }

    private void verificaPendenciaVisualizacao() {
        CategoriaDAO dao = new CategoriaDAO(this);
        if(dao.verificaDesbloqueioCategoriaPendente()==1){
            dao.atualizaCategoriaNAOPendente();
        }
    }

    private void verificaQtdPerguntaHabilitada() {
        CategoriaDAO dao = new CategoriaDAO(this);
        List<Integer> qtdHabilitada = dao.consultaQtdHabilitada();
        if(qtdHabilitada.get(0)==0){
            rb30.setEnabled(false);
            rb30.setText("30    Vença "+qtdHabilitada.get(1)+" partidas de 15 perguntas");
        }
        if(qtdHabilitada.get(2)==0){
            rb50.setEnabled(false);
            rb50.setText("50    Vença "+qtdHabilitada.get(3)+" partidas de 30 perguntas");
        }

    }

    private void verificaParametrosSelecionados() {
        CategoriaDAO categoria_dao = new CategoriaDAO(this);
        boolean sons, vibra;
        sons = categoria_dao.consultaSonsHabilitados();
        vibra = categoria_dao.consultaVibrarHabilitado();


        if(sons){
            somS.setChecked(true);
            somN.setChecked(false);
        }else{
            somN.setChecked(true);
            somS.setChecked(false);
        }

        if(vibra){
            vibraS.setChecked(true);
            vibraN.setChecked(false);
        }else{
            vibraN.setChecked(true);
            vibraS.setChecked(false);
        }

    }

    private void verificacategoriaHabilitada() {

        CategoriaDAO categoria_dao = new CategoriaDAO(this);
        boolean selectCapital, selectBandeira, selectIdioma, selectMoeda,
                selectContinente, selectPopulacao, selectArea, selectEmblema, selectMapa, selectCidades;
        selectCapital = categoria_dao.consultaCategoriaHabilitada(1);
        selectBandeira = categoria_dao.consultaCategoriaHabilitada(2);
        selectIdioma = categoria_dao.consultaCategoriaHabilitada(3);
        selectPopulacao = categoria_dao.consultaCategoriaHabilitada(4);
        selectArea = categoria_dao.consultaCategoriaHabilitada(5);
        selectContinente = categoria_dao.consultaCategoriaHabilitada(6);
        selectMoeda = categoria_dao.consultaCategoriaHabilitada(7);
        selectEmblema = categoria_dao.consultaCategoriaHabilitada(8);
        selectMapa = categoria_dao.consultaCategoriaHabilitada(9);
        selectCidades = categoria_dao.consultaCategoriaHabilitada(10);



        if(!selectContinente){
            continente.setEnabled(false);

        }

        if(!selectIdioma){
            idioma.setEnabled(false);
            int qtd = 50 - (categoria_dao.consultaPerguntasCertasCategoria(6));

            idioma.setText("Idiomas       ("+qtd+" Continentes para desbloquear)");

        }

        if(!selectCapital){
            capital.setEnabled(false);
            int qtd = 50 - (categoria_dao.consultaPerguntasCertasCategoria(3));
            capital.setText("Capitais       ("+qtd+" Idiomas para desbloquear)");

        }
        if(!selectBandeira){
            bandeira.setEnabled(false);
            int qtd = 50 - (categoria_dao.consultaPerguntasCertasCategoria(1));
            bandeira.setText("Bandeiras    ("+qtd+" Capitais para desbloquear)");

        }

        if(!selectPopulacao){
            populacao.setEnabled(false);
            int qtd = 50 - (categoria_dao.consultaPerguntasCertasCategoria(2));
            populacao.setText("População   ("+qtd+" Bandeiras para desbloquear)");

        }

        if(!selectMapa){
            mapa.setEnabled(false);
            int qtd = 50 - (categoria_dao.consultaPerguntasCertasCategoria(4));
            mapa.setText("Mapa      ("+qtd+" Populações para desbloquear)");

        }


        if(!selectArea){
            area.setEnabled(false);
            int qtd = 50 - (categoria_dao.consultaPerguntasCertasCategoria(9));
            area.setText("Área             ("+qtd+" Mapas para desbloquear)");

        }

        if(!selectMoeda){
            moeda.setEnabled(false);
            int qtd = 50 - (categoria_dao.consultaPerguntasCertasCategoria(5));
            moeda.setText("Moeda         ("+qtd+" Áreas para desbloquear)");

        }

        if(!selectEmblema){
            emblema.setEnabled(false);
            int qtd = 50 - (categoria_dao.consultaPerguntasCertasCategoria(7));
            emblema.setText("Brasão          ("+qtd+" Moedas para desbloquear)");

        }

        if(!selectCidades){
            cidades.setEnabled(false);
            int qtd = 50 - (categoria_dao.consultaPerguntasCertasCategoria(8));
            cidades.setText("Cidades         ("+qtd+" Brasões para desbloquear)");

        }

    }

    public void escondeNavigationBar(){

        this.getWindow().getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);


    }

    private void verificaQuantidadeSelecionada() {
        CategoriaDAO categoria_dao = new CategoriaDAO(this);
        int qtd = categoria_dao.selecaoQtdPerguntas();


        if(qtd == 15){
            rb15.setChecked(true);
            rb30.setChecked(false);
            rb50.setChecked(false);
        } else if(qtd == 30){
            rb15.setChecked(false);
            rb30.setChecked(true);
            rb50.setChecked(false);
        } else if(qtd == 50){
            rb15.setChecked(false);
            rb30.setChecked(false);
            rb50.setChecked(true);
        }

    }

    private static final int[][] CHECK_BOX_STATES = new int[][] {
            new int[] {-android.R.attr.state_enabled}, // desabilitado
            new int[] { android.R.attr.state_checked}, // marcado
            new int[] {-android.R.attr.state_checked}, // desmarcado
            new int[] {} // default
    };

    private static final int[] CHECK_BOX_COLORS = new int[] {
            Color.LTGRAY,
            Color.BLUE,
            Color.GRAY,
            Color.GRAY
    };


    public void verificaCategoriaSelecionada() {


        CategoriaDAO categoria_dao = new CategoriaDAO(this);
        boolean selectCapital, selectBandeira, selectIdioma, selectMoeda,
                selectContinente, selectPopulacao, selectArea, selectEmblema, selectMapa, selectCidades;
        selectCapital = categoria_dao.consultaSelecaoCategoria(1);
        selectBandeira = categoria_dao.consultaSelecaoCategoria(2);
        selectIdioma = categoria_dao.consultaSelecaoCategoria(3);
        selectPopulacao = categoria_dao.consultaSelecaoCategoria(4);
        selectArea = categoria_dao.consultaSelecaoCategoria(5);
        selectContinente = categoria_dao.consultaSelecaoCategoria(6);
        selectMoeda = categoria_dao.consultaSelecaoCategoria(7);
        selectEmblema= categoria_dao.consultaSelecaoCategoria(8);
        selectMapa = categoria_dao.consultaSelecaoCategoria(9);
        selectCidades = categoria_dao.consultaSelecaoCategoria(10);



        if(selectCapital){
            capital.setChecked(true);
            capital.setHintTextColor(Color.BLUE);

        }

        if(selectMapa){
            mapa.setChecked(true);

        }
        if(selectBandeira){
            bandeira.setChecked(true);

        }
        if(selectIdioma){
            idioma.setChecked(true);

        }
        if(selectMoeda){
            moeda.setChecked(true);

        }
        if(selectPopulacao){
            populacao.setChecked(true);

        }
        if(selectArea){
            area.setChecked(true);

        }
        if(selectContinente){
            continente.setChecked(true);

        }

        if(selectEmblema){
            emblema.setChecked(true);

        }
        if(selectCidades){
            cidades.setChecked(true);

        }

    }



    public void clickSonsSim(View view){
        somN.setChecked(false);
    }
    public void clickSonsNao(View view){
        somS.setChecked(false);
    }
    public void clickVibraSim(View view){
        vibraN.setChecked(false);
    }
    public void clickVibraNao(View view){
        vibraS.setChecked(false);
    }

    public void clickRb15(View view){
        rb30.setChecked(false);
        rb50.setChecked(false);
    }
    public void clickRb30(View view){
        rb15.setChecked(false);
        rb50.setChecked(false);
    }
    public void clickRb50(View view){
        rb30.setChecked(false);
        rb15.setChecked(false);
    }




    public void gravar(){
        CategoriaDAO categoria_dao = new CategoriaDAO(this);
        int som = 1;
        int vibra = 1;

        int qtd=0;
        int selectCapital=0,selectBandeira=0, selectIdioma=0,selectMoeda=0,
                selectContinente=0, selectPopulacao=0, selectArea=0, selectEmblema=0, selectMapa=0, selectCidades=0;

        if(capital.isChecked()){
            selectCapital=1;
        }
        if(bandeira.isChecked()){
            selectBandeira=1;
        }
        if(mapa.isChecked()){
            selectMapa=1;
        }
        if(moeda.isChecked()){
            selectMoeda=1;
        }
        if(idioma.isChecked()){
            selectIdioma=1;
        }
        if(populacao.isChecked()){
            selectPopulacao=1;
        }
        if(continente.isChecked()){
            selectContinente=1;
        }
        if(area.isChecked()){
            selectArea=1;
        }
        if(emblema.isChecked()){
            selectEmblema=1;
        }

        if(cidades.isChecked()){
            selectCidades=1;
        }

        if(rb15.isChecked()){
            qtd=15;
        }else
        if(rb30.isChecked()){
            qtd=30;
        }else
        if(rb50.isChecked()){
            qtd=50;
        }

        if(somN.isChecked()){
            som=0;
        }

        if(vibraN.isChecked()){
            vibra=0;
        }

        categoria_dao.atualizaCategoriaselecionada(1, selectCapital);
        categoria_dao.atualizaCategoriaselecionada(2, selectBandeira);
        categoria_dao.atualizaCategoriaselecionada(3, selectIdioma);
        categoria_dao.atualizaCategoriaselecionada(4, selectPopulacao);
        categoria_dao.atualizaCategoriaselecionada(5, selectArea);
        categoria_dao.atualizaCategoriaselecionada(6, selectContinente);
        categoria_dao.atualizaCategoriaselecionada(7, selectMoeda);
        categoria_dao.atualizaCategoriaselecionada(8, selectEmblema);
        categoria_dao.atualizaCategoriaselecionada(9, selectMapa);
        categoria_dao.atualizaCategoriaselecionada(10,selectCidades);
        categoria_dao.atualizaQuantidadePergunta(qtd);
        categoria_dao.atualizaParametros(som, vibra);
    }

    public void voltar(View view){
        gravar();
        startActivity(new Intent(com.ex.geografando.Activitys.CategoriasActivity.this, MainActivity.class));
        finishAffinity();
    }




    @Override
    public void onBackPressed(){
        gravar();

        Intent i = new Intent(com.ex.geografando.Activitys.CategoriasActivity.this, MainActivity.class);
        startActivity(i);
        finishAffinity();

    }
}
