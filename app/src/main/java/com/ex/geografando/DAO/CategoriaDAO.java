package com.ex.geografando.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ex.geografando.Conexao.Conexao;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private Conexao conexao;
    private static SQLiteDatabase banco;

    public CategoriaDAO(Context context){
        conexao= new Conexao(context);
        banco = conexao.getWritableDatabase();
    }


    public boolean consultaSelecaoCategoria(int codCategoria) {

        int selecionada = 0;

        Cursor cursor = banco.query("categorias", new String[]{"selecionada"},"codCategoria="+codCategoria,null,null,null,null,null);

        while (cursor.moveToNext()){
            selecionada = cursor.getInt(0);

        }

        if(selecionada==1){
            cursor.close();
           return true;
        }else{
            cursor.close();
           return false;
        }

    }

    public boolean consultaCategoriaHabilitada(int codCategoria) {

        int habilitada = 0;

        Cursor cursor = banco.query("categorias", new String[]{"habilitada"},"codCategoria="+codCategoria,null,null,null,null,null);

        while (cursor.moveToNext()){
            habilitada = cursor.getInt(0);

        }

        if(habilitada==1){
            cursor.close();
            return true;
        }else{
            cursor.close();
            return false;
        }

    }


    public void atualizaCategoriaselecionada(int codCategoria, int selecionada){
       ContentValues values = new ContentValues();
        values.put("selecionada", selecionada);
        banco.update("categorias",values,"codCategoria="+codCategoria,null);

    }

    public boolean verificaSelecao() {
        boolean selecionada = false;

        Cursor cursor = banco.query("categorias", new String[]{"selecionada"},"selecionada=1",null,null,null,null,null);

        while (cursor.moveToNext()){
            selecionada = true;
        }
        cursor.close();
        return selecionada;
    }


    public static int verificaSelecaoEspecifica(int categoria) {
        int selecionada = 0;

        Cursor cursor = banco.query("categorias", new String[]{"selecionada"},"codCategoria="+categoria,null,null,null,null,null);

        while (cursor.moveToNext()){
            selecionada = cursor.getInt(0);
        }
        cursor.close();
        return selecionada;
    }


    public int qtdCategoria() {
        int qtd = 0;
        Cursor cursor = banco.query("categorias", new String[]{"selecionada"},"selecionada=1",null,null,null,null,null);

        while (cursor.moveToNext()){
            qtd=qtd+1;
        }
        cursor.close();
        return qtd;
    }

    public int qtdCategoriasDesbloqueadas() {
        int qtd = 0;
        Cursor cursor = banco.query("categorias", new String[]{"habilitada"},"habilitada=1",null,null,null,null,null);
        qtd=cursor.getCount();
        cursor.close();
        return qtd;
    }

    public int selecaoQtdPerguntas() {
        int qtd=0;

        Cursor cursor = banco.query("quantidadePerguntas", new String[]{"QUANTIDADE"},null,null,null,null,null);

        while (cursor.moveToNext()){
            qtd=cursor.getInt(0);
        }
        cursor.close();
        return qtd;

    }

    public void atualizaQuantidadePergunta(int qtd) {
        ContentValues values = new ContentValues();
        values.put("QUANTIDADE",qtd);
        banco.update("quantidadePerguntas",values,"ID=1",null);
    }


    public int consultaPerguntasCertasCategoria(int cod) {

        int qtd=0;



        String query = "select * from perguntas_partidas pp " +
                "inner join partida pa on pp.codPartida = pa.CODPARTIDA " +
                "where pa.CONCLUIDA = 1 " +
                "and pp.codCategoria ="+cod+
                " and pp.respostaCerta = 1 "+
                "and pa.VITORIA = 1 ";
        Cursor cursor = banco.rawQuery(query,null);

        while (cursor.moveToNext()){
            qtd=qtd+1;
        }
        cursor.close();
        return qtd;

    }

    public String consultaNomeCategoria(int cod){
        String categoria=null;

            Cursor cursor = banco.query("categorias", new String[]{"categoria"},"codCategoria="+cod,null,null,null,null);

        while (cursor.moveToNext()){
            categoria= (cursor.getString(0));
        }
        cursor.close();
        return categoria;
    }

    public void desbloqueiaCategoria(Integer cod) {
        ContentValues values = new ContentValues();
        values.put("habilitada",1);
        banco.update("categorias",values,"codCategoria="+cod,null);
    }

    public void atualizaParametros(int som, int vibra) {
        ContentValues values = new ContentValues();
        values.put("VIBRAR",vibra);
        values.put("SONS",som);
        banco.update("parametros",values,"ID=1",null);
    }

    public void atualizaParametroSom(int som) {
        ContentValues values = new ContentValues();
        values.put("SONS",som);
        banco.update("parametros",values,"ID=1",null);
    }

    public boolean consultaSonsHabilitados() {
        boolean som=false;

        Cursor cursor = banco.query("parametros", new String[]{"SONS"},"ID=1",null,null,null,null);

        while (cursor.moveToNext()){
            int i = (cursor.getInt(0));
            if(i==0){
                som=false;
            }
            if (i==1)
            {
                som=true;
            }
        }
        cursor.close();
        return som;
    }

    public boolean consultaVibrarHabilitado() {
        boolean vibra=false;

        Cursor cursor = banco.query("parametros", new String[]{"VIBRAR"},"ID=1",null,null,null,null);

        while (cursor.moveToNext()){
            int i = (cursor.getInt(0));
            if(i==0){
                vibra=false;
            }
            if (i==1)
            {
                vibra=true;
            }
        }
        cursor.close();
        return vibra;
    }

    public List<Integer> consultaQtdHabilitada() {
        List<Integer> list = new ArrayList<>();
        String query = "select * from habilitaQtdPerguntas " +
                "where ID in (2,3) " ;
        Cursor cursor = banco.rawQuery(query,null);
        while (cursor.moveToNext()){
            list.add(cursor.getInt(2));
            list.add(cursor.getInt(3));
        }
        cursor.close();
        return list;
    }

    public List<Integer> consultaQtdHabilitadaEspecifica() {
        List<Integer> list = new ArrayList<>();
        String query = "select habilitada from habilitaQtdPerguntas " ;
        Cursor cursor = banco.rawQuery(query,null);
        while (cursor.moveToNext()){
            list.add(cursor.getInt(0));
        }
        cursor.close();
        return list;
    }

    public void atualizaQtdHabilitada(int i) {
        int qtdAtual = 0;
        String query = "select qtdDesbloqueio from habilitaQtdPerguntas " +
                "where ID = "+i ;
        Cursor cursor = banco.rawQuery(query,null);
        while (cursor.moveToNext()) {
            qtdAtual=cursor.getInt(0);
        }
        cursor.close();

        if(qtdAtual>0){
            if(qtdAtual==1){
                ContentValues values = new ContentValues();
                values.put("qtdDesbloqueio",0);
                values.put("habilitada",1);
                banco.update("habilitaQtdPerguntas",values,"ID= "+i,null);
            }else{
                int qtdDesbloqueio = qtdAtual-1;
                ContentValues values = new ContentValues();
                values.put("qtdDesbloqueio",qtdDesbloqueio);
                banco.update("habilitaQtdPerguntas",values,"ID= "+i,null);
            }
        }
    }
}
