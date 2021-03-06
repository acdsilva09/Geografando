package com.ex.geografando.Activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.ex.geografando.DAO.CategoriaDAO;
import com.ex.geografando.DAO.PartidaDAO;
import com.ex.geografando.Model.Partida;
import com.ex.geografando.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private Button buttonJogar, buttonRecords, buttonCategorias, buttonSair, buttonConquistas;





    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        consultaCodPartida();



        buttonJogar = (Button) findViewById(R.id.buttonJogar);
        buttonRecords = (Button) findViewById(R.id.buttonRecords);
        buttonCategorias = (Button) findViewById(R.id.buttonCategorias);
        buttonConquistas = (Button) findViewById(R.id.buttonConquistas);
        buttonSair = (Button) findViewById(R.id.buttonSair);



        escondeNavigationBar();

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adViewBannerMain);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //mAdView = new AdView(this);

        //mAdView.loadAd(new AdRequest.Builder().build());

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        //loadAdMob();




    }






    public void onClickButtonJogar(View view){
        enableFalseButtons();
        boolean categoriasSelecionadas;
        categoriasSelecionadas = verificaCategoriaSelecionada();
        if(categoriasSelecionadas){




            PartidaDAO daoPartida = new PartidaDAO(this);
            daoPartida.gravaJogoPendente(0);
            Intent i = new Intent(com.ex.geografando.Activitys.MainActivity.this, JogoActivity.class);
            startActivity(i);
            finishAffinity();



        }else{
            enableTrueButtons();
            semSelecao();

        }

    }

    private void consultaCodPartida() {
        PartidaDAO daoP = new PartidaDAO(this);
        ArrayList<Partida> partidas = new ArrayList<>();
        partidas = daoP.listaPartidas();
        for(int i =0; i<partidas.size();i++){
            Partida p = partidas.get(i);


        }
    }



    private void semSelecao() {
        AlertDialog alerta;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("N??o h?? nenhuma categoria selecionada.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(com.ex.geografando.Activitys.MainActivity.this, null, Toast.LENGTH_SHORT);
            }
        });
        alerta = builder.create();
        alerta.show();
        escondeNavigationBar();

    }


    public boolean verificaCategoriaSelecionada(){
        boolean selecionada = false;

        CategoriaDAO categoria_dao = new CategoriaDAO(this);
        selecionada = categoria_dao.verificaSelecao();
        return selecionada;
    }




    public void activityRecords(View view) {

        enableFalseButtons();
        startActivity(new Intent(com.ex.geografando.Activitys.MainActivity.this, RecordsActivity.class));

    }

    public void activityConquistas(View view) {

        enableFalseButtons();
        startActivity(new Intent(com.ex.geografando.Activitys.MainActivity.this, ConquistasActivity.class));

    }


    public void activitySair(View view) {
        enableFalseButtons();
        finishAffinity();

    }

    public void activityCategorias(View view) {
        enableFalseButtons();
        startActivity(new Intent(com.ex.geografando.Activitys.MainActivity.this, CategoriasActivity.class));

    }


    public void enableFalseButtons(){
        buttonRecords.setEnabled(false);
        buttonJogar.setEnabled(false);
        buttonCategorias.setEnabled(false);
        buttonConquistas.setEnabled(false);
        buttonSair.setEnabled(false);

    }

    public void enableTrueButtons(){
        buttonRecords.setEnabled(true);
        buttonJogar.setEnabled(true);
        buttonCategorias.setEnabled(true);
        buttonConquistas.setEnabled(true);
        buttonSair.setEnabled(true);
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
        finishAffinity();
    }



    private void loadAdMob() {
        mAdView = new AdView(this);
        mAdView.setAdUnitId(getString(R.string.admob_banner_teste));
        mAdView.loadAd(new AdRequest.Builder().build());
    }


}
