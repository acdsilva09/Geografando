package com.ex.geografando.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ex.geografando.Conexao.Conexao;
import com.ex.geografando.Model.Conquista;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConquistasDAO {

    private Conexao conexao;
    private static SQLiteDatabase banco;

    public ConquistasDAO(Context context){
        conexao= new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public ArrayList consultaConquistas() {
        ArrayList <Conquista> list = new ArrayList();
        Cursor cursor = banco.query("conquistas", new String[]{"*"},null,
                null,null,null,null);


        while (cursor.moveToNext()) {
            Conquista c = new Conquista();
            c.setID(cursor.getInt(0));
            c.setConquista(cursor.getString(1));
            c.setQtd_desbloqueio(cursor.getInt(2));
            c.setQtdAcertos(cursor.getInt(3));
            c.setDesbloqueio(cursor.getInt(4));
            c.setCategoria(cursor.getInt(5));
            list.add(c);
        }
        cursor.close();
        return list;

    }

    public ArrayList<Integer> consultaListaPaisesDiferentesCertos(int categoria){

        ArrayList<Integer> list = new ArrayList();
        String query = "select distinct per.codpergunta from perguntas_partidas per\n" +
                "inner join partida par \n" +
                "on per.codpartida = par.codpartida\n" +
                "where per.codcategoria = "+categoria+"\n" +
                "and par.vitoria=1\n";
        Cursor cursor = banco.rawQuery(query,null);

        while(cursor.moveToNext()){
            list.add(cursor.getInt(0));
        }
        cursor.close();
        return list;
    }

    public int verificaDesbloqueioConquistaPendente(){
        int pendente = 0;
        Cursor cursor = banco.query("parametros", new String[]{"CONQUISTADESBLOQUEADA"},null,null,null,null,null);
        while (cursor.moveToNext()){
            pendente = cursor.getInt(0);
        }
        return pendente;
    }

    public void consultaDesbloqueioConquista() {
        List<Integer> categorias = Arrays.asList(6,3,1,2,4,9,5,7,8,10);
        for(int a=0; a<categorias.size();a++){
            ArrayList<Conquista> conquista = consultaConquistas();
            ArrayList<Integer> listPaises = consultaListaPaisesDiferentesCertos(categorias.get(a));
            ArrayList<Conquista> listConquista = conquistasPorCategoria(conquista, categorias.get(a));
            int qtdPaises = listPaises.size();
            for(int i=0;i<3;i++){
                Conquista c = new Conquista();
                c= listConquista.get(i);
                if(c.getDesbloqueio()==0&&
                        qtdPaises>=c.getQtd_desbloqueio()){
                    debloqueiaConquista(c.getID(),qtdPaises);
                }else{
                    atualizaAcertosConquistas(c.getID(), qtdPaises);
                }
            }
        }
        verificaDesbloqueioConquistaPartidasVencidas();
        verificaDesbloqueioConquistaPartidasVencidasSemErros();
        verificaDesbloqueioConquistaTodasCategorias();
        verificaDesbloqueioConquistasPontuacao();
        verificaDesbloqueioConquistaSequenciaVitorias();
        verificaDesbloqueioConquistaPartdidaComTodasCategorias();
        verificaDesbloqueioConquistaContinentes();
    }

    private void verificaDesbloqueioConquistaContinentes() {
        String query = "select * from acertosContinentes";
                Cursor cursor = banco.rawQuery(query, null);
        ArrayList<Integer> listAcertos = new ArrayList<Integer>();
        while(cursor.moveToNext()){
            listAcertos.add(cursor.getInt(2));
        }
        ArrayList<Conquista> conquistas = consultaConquistasSemCategoria();
        int a=0;
        cursor.close();
        for(int i=27;i<32;i++){
            if(listAcertos.get(a)>=conquistas.get(i).getQtd_desbloqueio()
                    &&conquistas.get(i).getDesbloqueio()==0){
                ContentValues values = new ContentValues();
                values.put("desbloqueio", 1);
                banco.update("conquistas_sem_categorias",values,"ID="+conquistas.get(i).getID(),null);
                atualizaConquistaPendente();
            }
            a++;
        }
    }

    private void verificaDesbloqueioConquistaPartdidaComTodasCategorias() {
        String query = "select count(*) from partida " +
                "WHERE CONCLUIDA = 1 " +
                "AND VITORIA = 1 " +
                "AND CAPITAL = 1 " +
                "AND IDIOMA = 1 " +
                "AND BANDEIRA = 1 " +
                "AND MAPA = 1 " +
                "AND POPULACAO = 1 " +
                "AND AREA = 1 " +
                "AND CONTINENTE = 1 " +
                "AND CIDADES = 1 " +
                "AND MOEDA = 1 " +
                "AND EMBLEMA = 1";
        Cursor cursor = banco.rawQuery(query,null);
        int qtd=0;
        while (cursor.moveToNext()){
            qtd=cursor.getInt(0);
        }
        cursor.close();
        ArrayList<Conquista> conquistas = consultaConquistasSemCategoria();
        for(int i=22;i<27;i++){
            if(qtd>=conquistas.get(i).getQtd_desbloqueio()
                    &&conquistas.get(i).getDesbloqueio()==0){
                ContentValues values = new ContentValues();
                values.put("desbloqueio", 1);
                banco.update("conquistas_sem_categorias",values,"ID="+conquistas.get(i).getID(),null);
                atualizaConquistaPendente();
            }
        }
    }


    private void verificaDesbloqueioConquistaSequenciaVitorias() {
        int sequenciaAtual=0;
        String query = "select SEQUENCIA_ATUAL from sequenciaVitorias where ID = 1";
        Cursor cursor = banco.rawQuery(query,null);
        while(cursor.moveToNext()){
            sequenciaAtual=cursor.getInt(0);
        }
        cursor.close();
        ArrayList<Conquista> conquistas = consultaConquistasSemCategoria();
        for(int i=18;i<22;i++){
            if(sequenciaAtual>=conquistas.get(i).getQtd_desbloqueio()
                    &&conquistas.get(i).getDesbloqueio()==0){
                ContentValues values = new ContentValues();
                values.put("desbloqueio", 1);
                banco.update("conquistas_sem_categorias",values,"ID="+conquistas.get(i).getID(),null);
                atualizaConquistaPendente();
            }
        }

    }

    private void verificaDesbloqueioConquistasPontuacao() {
        String query = "select PONTUACAO from partida where CONCLUIDA = 1 ORDER BY PONTUACAO DESC LIMIT(1)";
        Cursor cursor = banco.rawQuery(query,null);
        int pontuacao=0;
        while(cursor.moveToNext()){
            pontuacao=cursor.getInt(0);
        }cursor.close();

        ArrayList<Conquista> conquistas = consultaConquistasSemCategoria();

        for(int i=12;i<18;i++){
            if(pontuacao>=conquistas.get(i).getQtd_desbloqueio()
                &&conquistas.get(i).getDesbloqueio()==0){

                ContentValues values = new ContentValues();
                values.put("desbloqueio", 1);
                banco.update("conquistas_sem_categorias",values,"ID="+conquistas.get(i).getID(),null);
                atualizaConquistaPendente();
            }
        }

    }

    private void verificaDesbloqueioConquistaTodasCategorias() {
        int qtd = 0;
        Cursor cursor = banco.query("categorias", new String[]{"habilitada"},"habilitada=1",null,null,null,null,null);
        qtd=cursor.getCount();
        cursor.close();
        if(qtd>=10){
           updateConquistaTodasCategorias(1);
        }else{
            updateConquistaTodasCategorias(0);
        }
    }

    public void updateConquistaTodasCategorias(int habilita){
        ContentValues values = new ContentValues();
        values.put("desbloqueio", habilita);
        banco.update("conquistas_sem_categorias",values,"ID=12",null);
        atualizaConquistaPendente();
    }

    private void verificaDesbloqueioConquistaPartidasVencidasSemErros() {
        String query = "select * from partida where CONCLUIDA = 1 and VITORIA=1 and VIDAS_RESTANTES=3";
        Cursor cursor = banco.rawQuery(query,null);
        int partidasSemErros=cursor.getCount();
        cursor.close();
        ArrayList<Conquista> conquistas = consultaConquistasSemCategoria();
        for(int i=7; i<12;i++){
            if(conquistas.get(i).getQtd_desbloqueio()<=partidasSemErros
                    &&conquistas.get(i).getDesbloqueio()==0){
                desbloqueiaConquistaSemCat(conquistas.get(i).getID());
            }
        }
    }

    private void verificaDesbloqueioConquistaPartidasVencidas() {
        String query = "select * from partida where CONCLUIDA = 1 and VITORIA = 1";
        Cursor cursor = banco.rawQuery(query,null);
        int totalPartidasVencidas = cursor.getCount();
        cursor.close();
        ArrayList<Conquista> conquistas = consultaConquistasSemCategoria();
        for(int i=0; i<7;i++){
            if(conquistas.get(i).getQtd_desbloqueio()<=totalPartidasVencidas
                    &&conquistas.get(i).getDesbloqueio()==0){
                desbloqueiaConquistaSemCat(conquistas.get(i).getID());
            }
        }

    }

    private void desbloqueiaConquistaSemCat(int id) {
        ContentValues values = new ContentValues();
        values.put("desbloqueio", 1);
        banco.update("conquistas_sem_categorias",values,"ID="+id,null);
        atualizaConquistaPendente();
    }

    private ArrayList<Conquista> conquistasPorCategoria(ArrayList<Conquista> conquista, int categoria) {
        ArrayList<Conquista> list = new ArrayList<Conquista>();

            for(int i = 0; i<conquista.size();i++){
                Conquista c = conquista.get(i);
                if(c.getCategoria()==categoria){
                    list.add(c);
                }
            }

        return list;
    }


    private void atualizaAcertosConquistas(int id, int qtdPaises) {
        ContentValues values = new ContentValues();
        values.put("qtd_de_acertos", qtdPaises);
        banco.update("conquistas",values,"ID="+id,null);
    }


    private void debloqueiaConquista(int id, int qtdAcertos) {
        ContentValues values = new ContentValues();
        values.put("desbloqueio", 1);
        values.put("qtd_de_acertos", qtdAcertos);
        banco.update("conquistas",values,"ID="+id,null);
        atualizaConquistaPendente();
    }

    public ArrayList<Conquista> consultaConquistasSemCategoria() {

        ArrayList <Conquista> list = new ArrayList();
        Cursor cursor = banco.query("conquistas_sem_categorias", new String[]{"*"},null,
                null,null,null,null);


        while (cursor.moveToNext()) {
            Conquista c = new Conquista();
            c.setID(cursor.getInt(0));
            c.setConquista(cursor.getString(1));
            c.setQtd_desbloqueio(cursor.getInt(2));
            c.setDesbloqueio(cursor.getInt(3));
            list.add(c);
        }
        cursor.close();
        return list;

    }

    public int consultaQtdConquistasDesbloqueadas() {
        int qtd=0;
        String q1 = "select * from conquistas where desbloqueio = 1";
        String q2 = "select * from conquistas_sem_categorias where desbloqueio = 1";
        Cursor c1 = banco.rawQuery(q1,null);
        Cursor c2 = banco.rawQuery(q2,null);
        qtd= c1.getCount() + c2.getCount();
        c1.close();
        c2.close();
        return qtd;
    }

    public void atualizaConquistaPendente(){
        ContentValues values = new ContentValues();
        values.put("CONQUISTADESBLOQUEADA", 1);
        banco.update("parametros",values,null,null);
    }

    public void atualizaConquistaNAOPendente() {
        ContentValues values = new ContentValues();
        values.put("CONQUISTADESBLOQUEADA", 0);
        banco.update("parametros",values,null,null);
    }
}
