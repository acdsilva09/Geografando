package com.ex.geografando.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ex.geografando.Conexao.Conexao;
import com.ex.geografando.Model.Partida;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class PartidaDAO {


    private Conexao conexao;
    private static SQLiteDatabase banco;

    public PartidaDAO(Context context){
        conexao= new Conexao(context);
        banco = conexao.getWritableDatabase();
    }


    public static ArrayList buscaPartida (){
      ArrayList <Partida> listaPartidas = new ArrayList();


        Cursor cursor = banco.query("partida order by PONTUACAO desc", new String[]{"*"},null,
        null,null,null,null,"8");


        while (cursor.moveToNext()){
            Partida p = new Partida();
            p.setCodPartida(cursor.getInt(0));
            p.setCodUsuario(cursor.getInt(1));
            p.setPontuacao(cursor.getInt(2));
            p.setNivel(cursor.getInt(3));
            p.setConcluida(cursor.getInt(4));
            p.setCapital(cursor.getInt(5));
            p.setBandeira(cursor.getInt(6));
            p.setIdioma(cursor.getInt(7));
            p.setPopulacao(cursor.getInt(8));
            p.setArea(cursor.getInt(9));
            p.setContinente(cursor.getInt(10));
            p.setMoeda(cursor.getInt(11));
            p.setQuantidade_perguntas_respondidas(cursor.getInt(12));
            p.setQuantidade_perguntas_total(cursor.getInt(13));
            p.setVitoria(cursor.getInt(14));
            p.setVidasRestantes(cursor.getInt(15));
            p.setAjudasRestantes(16);
            p.setEmblema(cursor.getInt(17));
            p.setMapa(cursor.getInt(18));
            p.setCidades(cursor.getInt(19));
            listaPartidas.add(p);

        }

        cursor.close();
        return listaPartidas;

    }


    public static void inserePartida(Partida partida){
       ContentValues values = new ContentValues();

        int codPartida = consultaUltimoCodPartida();
        values.put("CODPARTIDA", codPartida);
        values.put("CODUSUARIO", partida.getCodUsuario() );
        values.put("PONTUACAO",partida.getPontuacao() );
        values.put("NIVEL",partida.getNivel() );
        values.put("CONCLUIDA", partida.getConcluida());
        values.put("CAPITAL",partida.getCapital());
        values.put("BANDEIRA",partida.getBandeira());
        values.put("IDIOMA",partida.getIdioma());
        values.put("POPULACAO",partida.getPopulacao());
        values.put("AREA",partida.getArea());
        values.put("CONTINENTE",partida.getContinente());
        values.put("MOEDA",partida.getMoeda());
        values.put("QUANTIDADE_PERGUNTAS_RESPONDIDAS",partida.getQuantidade_perguntas_respondidas());
        values.put("QUANTIDADE_PERGUNTAS_TOTAL",partida.getQuantidade_perguntas_total());
        values.put("VITORIA",partida.getVitoria());
        values.put("VIDAS_RESTANTES",partida.getVidasRestantes());
        values.put("AJUDAS_RESTANTES",partida.getAjudasRestantes());
        values.put("EMBLEMA",partida.getEmblema());
        values.put("MAPA",partida.getMapa());
        values.put("CIDADES",partida.getCidades());

        banco.insert("partida",null,values);


    }

    public static int consultaUltimoCodPartida() {

        int codPartida=0;
        Cursor cursor = banco.query("partida order by CODPARTIDA desc", new String[]{"CODPARTIDA"},
                null,null,null,null,null, "1");

        while (cursor.moveToNext()){
            codPartida = cursor.getInt(0);
        }

        cursor.close();
        return  codPartida+1;

    }

    public static ArrayList<Integer> consultaPerguntasPorPartida(int codPartida) {
        ArrayList<Integer> listaPerguntas = new ArrayList<>();

        Cursor cursor = banco.query("perguntas_partidas", new String[]{"*"},
                "codPartida="+codPartida,null,null,null,null);
        while(cursor.moveToNext()){
            listaPerguntas.add(cursor.getInt(2));
        }cursor.close();



        return  listaPerguntas;
    }

    public int verificaJogoPendente(){
        int jogoPendente=0;
        int codPartida = (consultaUltimoCodPartida()-1);

        Cursor cursor = banco.query("partida", new String[]{"*"},
                "CODPARTIDA="+codPartida,null,null,null,null,null);
        while (cursor.moveToNext()){

            if(cursor.getInt(4)==0 && consultaRegistrosPendente()){
                jogoPendente=1;
            }
        }cursor.close();

        return jogoPendente;
    }

    private boolean consultaRegistrosPendente() {
        boolean registroPendente = false;
        Cursor cursor = banco.query("perguntas_partida_temporaria", new String[]{"*"},null,null,null,null,null);
        if(cursor.getCount()>0){
            registroPendente=true;
        }

        return registroPendente;
    }


    public int consultaPontuacaoPartida(int codPartida) {
        int pontos=0;
        Cursor cursor = banco.query("partida", new String[]{"*"},
                "CODPARTIDA="+codPartida,null,null,null,null, "1");
        while (cursor.moveToNext()){
            pontos = cursor.getInt(2);
        }cursor.close();
        return pontos;


    }

    public int consultaQuatidadePerguntas(int codPartida) {
        int qtd=0;

        Cursor cursor = banco.query("partida", new String[]{"*"},
                "CODPARTIDA="+codPartida,null,null,null,null, "1");
        while (cursor.moveToNext()){
            qtd = cursor.getInt(13);
        }cursor.close();

        return qtd;
    }

    public int consultaCoeficientePontos(int codPartida) {
        int coeficiente=0;

        Cursor cursor = banco.query("partida", new String[]{"*"},
                "CODPARTIDA="+codPartida,null,null,null,null);
        while (cursor.moveToNext()){
            for(int c=5;c<=11;c++){
                if(cursor.getInt(c)==1){
                    coeficiente+=1;
                }
            }
        }cursor.close();

        return coeficiente;

    }

    public void atualizaPartida(int codPartida, int pontos, int size) {

        ContentValues values = new ContentValues();



        values.put("PONTUACAO",pontos);
        values.put("QUANTIDADE_PERGUNTAS_RESPONDIDAS",size);
        banco.update("partida",values,"CODPARTIDA="+codPartida,null);


    }

    public void concluiPartida(int codPartida) {

        ContentValues values = new ContentValues();
        values.put("CONCLUIDA",1);
        values.put("VITORIA",1);
        banco.update("partida",values,"CODPARTIDA="+codPartida,null);


    }


    public static ArrayList listaPartidas (){
        ArrayList <Partida> listaPartidas = new ArrayList();


        Cursor cursor = banco.query("partida", new String[]{"*"},null,
                null,null,null,"CODPARTIDA");


        while (cursor.moveToNext()){
            Partida p = new Partida();
            p.setCodPartida(cursor.getInt(0));
            p.setCodUsuario(cursor.getInt(1));
            p.setPontuacao(cursor.getInt(2));
            p.setNivel(cursor.getInt(3));
            p.setConcluida(cursor.getInt(4));
            p.setCapital(cursor.getInt(5));
            p.setBandeira(cursor.getInt(6));
            p.setIdioma(cursor.getInt(7));
            p.setPopulacao(cursor.getInt(8));
            p.setArea(cursor.getInt(9));
            p.setContinente(cursor.getInt(10));
            p.setMoeda(cursor.getInt(11));
            p.setQuantidade_perguntas_respondidas(cursor.getInt(12));
            p.setQuantidade_perguntas_total(cursor.getInt(13));
            p.setVitoria(cursor.getInt(14));
            p.setVidasRestantes(cursor.getInt(15));
            p.setAjudasRestantes(cursor.getInt(16));
            p.setEmblema(cursor.getInt(17));
            p.setMapa(cursor.getInt(18));
            p.setCidades(cursor.getInt(19));

            listaPartidas.add(p);

        }

        cursor.close();
        return listaPartidas;

    }


    public void gravaJogoPendente(int i) {

        ContentValues values = new ContentValues();
        values.put("jogopendente",i);
        banco.update("jogopendente",values,"ID=1",null);

    }
    public int consultaJogoPendente() {
        int jogoPendente=0;

        Cursor cursor = banco.query("jogopendente",new String[]{"jogopendente"},"ID=1",null,null,null,null,null);
        while(cursor.moveToNext()){
            jogoPendente=cursor.getInt(0);
        }cursor.close();

        return jogoPendente;

    }

    public void finalizaPartidaPerdida(int codPartida) {

        ContentValues values = new ContentValues();
        values.put("CONCLUIDA",1);
        values.put("PONTUACAO",0);
        values.put("VITORIA",0);
        banco.update("partida",values,"CODPARTIDA="+codPartida,null);

    }

    public void atualizaVidas(int codPartida, int vidas) {

        ContentValues values = new ContentValues();
        values.put("VIDAS_RESTANTES",vidas);
        banco.update("partida",values,"CODPARTIDA="+codPartida,null);

    }

    public int consultaVidasRestantes(int codPartida) {

        int vidas=0;

        Cursor cursor = banco.query("partida", new String[]{"VIDAS_RESTANTES"},
                "CODPARTIDA="+codPartida,null,null,null,null);
        while (cursor.moveToNext()){
            vidas=cursor.getInt(0);
        }
        cursor.close();
        return vidas;
    }

    public int consultaAjudasRestantes(int codPartida) {

        int ajudas=0;

        Cursor cursor = banco.query("partida", new String[]{"AJUDAS_RESTANTES"},
                "CODPARTIDA="+codPartida,null,null,null,null);
        while (cursor.moveToNext()){
            ajudas=cursor.getInt(0);
        }
        cursor.close();
        return ajudas;

    }

    public void atualizaAjudasdas(int codPartida, int qtdDicas) {

        ContentValues values = new ContentValues();
        values.put("AJUDAS_RESTANTES",qtdDicas);

        banco.update("partida",values,"CODPARTIDA="+codPartida,null);

    }

    public int consultaTotalPartidasConcluidas(int i) {
        int totalPartidas=0;
        int codQtdPerguntas=0;

        if(i==1){
            String query = "select * from partida where CONCLUIDA = 1";
            Cursor cursor = banco.rawQuery(query,null);
            totalPartidas = cursor.getCount();
            cursor.close();
        }else{
            if(i==2){
                codQtdPerguntas =15;
            }
            if(i==3){
                codQtdPerguntas =30;
            }
            if(i==4){
                codQtdPerguntas =50;
            }

            String query = "select * from partida where CONCLUIDA = 1 and QUANTIDADE_PERGUNTAS_TOTAL";
            Cursor cursor = banco.rawQuery(query+"="+codQtdPerguntas,null);
            totalPartidas = cursor.getCount();
            cursor.close();
        }

        return totalPartidas;
    }

    public int consultaTotalPartidasVencidas(int i) {
        int totalPartidasVencidas=0;
        int codQtdPerguntas=0;

        if(i==1){
            String query = "select * from partida where CONCLUIDA = 1 and VITORIA = 1";
            Cursor cursor = banco.rawQuery(query,null);
            totalPartidasVencidas = cursor.getCount();
            cursor.close();
        }else{
            if(i==2){
                codQtdPerguntas =15;
            }
            if(i==3){
                codQtdPerguntas =30;
            }
            if(i==4){
                codQtdPerguntas =50;
            }

            String query = "select * from partida where CONCLUIDA = 1 and VITORIA=1 and QUANTIDADE_PERGUNTAS_TOTAL";
            Cursor cursor = banco.rawQuery(query+"="+codQtdPerguntas,null);
            totalPartidasVencidas = cursor.getCount();
            cursor.close();
        }

        return totalPartidasVencidas;
    }

    public int consultaMelhorPontuacao(int i) {

        int pontuacao=0;
        int codQtdPerguntas=0;

        if(i==1){
            String query = "select PONTUACAO from partida where CONCLUIDA = 1 ORDER BY PONTUACAO DESC LIMIT(1)";
            Cursor cursor = banco.rawQuery(query,null);
            while(cursor.moveToNext()){
                pontuacao=cursor.getInt(0);
            }cursor.close();
        }else{
            if(i==2){
                codQtdPerguntas =15;
            }
            if(i==3){
                codQtdPerguntas =30;
            }
            if(i==4){
                codQtdPerguntas =50;
            }

            String query = "select PONTUACAO from partida where CONCLUIDA = 1 and QUANTIDADE_PERGUNTAS_TOTAL ="+codQtdPerguntas+" ORDER BY PONTUACAO DESC LIMIT(1)";
            Cursor cursor = banco.rawQuery(query,null);
            while(cursor.moveToNext()){
                pontuacao=cursor.getInt(0);
            }cursor.close();
        }

        return pontuacao;

    }

    public int consultaTotalPontuacao(int i) {

        int pontuacao=0;
        int codQtdPerguntas=0;

        if(i==1){
            String query = "select SUM(PONTUACAO) from partida where CONCLUIDA = 1 ";
            Cursor cursor = banco.rawQuery(query,null);
            while(cursor.moveToNext()){
                pontuacao=cursor.getInt(0);
            }cursor.close();
        }else{
            if(i==2){
                codQtdPerguntas =15;
            }
            if(i==3){
                codQtdPerguntas =30;
            }
            if(i==4){
                codQtdPerguntas =50;
            }

            String query = "select SUM(PONTUACAO) from partida where CONCLUIDA = 1 and QUANTIDADE_PERGUNTAS_TOTAL ="+codQtdPerguntas;
            Cursor cursor = banco.rawQuery(query,null);
            while(cursor.moveToNext()){
                pontuacao=cursor.getInt(0);
            }cursor.close();
        }

        return pontuacao;

    }

    public int consultaSequenciaAtual(int i) {
        int sequencia=0;

            String query = "select SEQUENCIA_ATUAL from sequenciaVitorias where ID = "+i;
            Cursor cursor = banco.rawQuery(query,null);
            while(cursor.moveToNext()){

                sequencia=cursor.getInt(0);
            }
        cursor.close();
        return sequencia;
    }

    public int consultaMaiorSequencia(int i) {
        int sequencia=0;

        String query = "select SEQUENCIA_MAXIMA from sequenciaVitorias where ID = "+i;
        Cursor cursor = banco.rawQuery(query,null);
        while(cursor.moveToNext()){

            sequencia=cursor.getInt(0);

        }
        cursor.close();
        return sequencia;
    }

    public int consultaPartidasSemErros(int i) {
        int partidasSemErros=0;
        int codQtdPerguntas=0;

        if(i==1){
            String query = "select * from partida where CONCLUIDA = 1 and VITORIA=1 and VIDAS_RESTANTES=3";
            Cursor cursor = banco.rawQuery(query,null);
            partidasSemErros=cursor.getCount();
            cursor.close();

        }else{
            if(i==2){
                codQtdPerguntas =15;
            }
            if(i==3){
                codQtdPerguntas =30;
            }
            if(i==4){
                codQtdPerguntas =50;
            }

            String query = "select * from partida where CONCLUIDA = 1 and VIDAS_RESTANTES=3 and VITORIA=1 and QUANTIDADE_PERGUNTAS_TOTAL ="+codQtdPerguntas;
            Cursor cursor = banco.rawQuery(query,null);
            partidasSemErros=cursor.getCount();
            cursor.close();
        }

        return partidasSemErros;
    }

    public int consultatotalPerguntasRespondidas(int i) {
        int perguntas=0;
        int codQtdPerguntas=0;

        if(i==1){
            String query = "select * from perguntas_partidas pp inner join partida pa on pp.codPartida = pa.CODPARTIDA where pa.CONCLUIDA = 1 ";
            Cursor cursor = banco.rawQuery(query,null);
            perguntas=cursor.getCount();
            cursor.close();

            }else{
            if(i==2){
                codQtdPerguntas =15;
            }
            if(i==3){
                codQtdPerguntas =30;
            }
            if(i==4){
                codQtdPerguntas =50;
            }

            String query = "select * from perguntas_partidas pp inner join partida pa on pp.codPartida = pa.CODPARTIDA where pa.CONCLUIDA = 1 and  pa.QUANTIDADE_PERGUNTAS_TOTAL ="+codQtdPerguntas;
            Cursor cursor = banco.rawQuery(query,null);
            perguntas=cursor.getCount();
            cursor.close();
        }

        return perguntas;
    }


    public int consulTatotalPerguntasCertas(int i) {
        int perguntas=0;
        int codQtdPerguntas=0;

        if(i==1){
            String query = "select * from perguntas_partidas pp inner join partida pa on pp.codPartida = pa.CODPARTIDA where pa.CONCLUIDA = 1 and pp.respostaCerta=1";
            Cursor cursor = banco.rawQuery(query,null);
            perguntas=cursor.getCount();
            cursor.close();

        }else{
            if(i==2){
                codQtdPerguntas =15;
            }
            if(i==3){
                codQtdPerguntas =30;
            }
            if(i==4){
                codQtdPerguntas =50;
            }

            String query = "select * from perguntas_partidas pp inner join partida pa on pp.codPartida = pa.CODPARTIDA where pa.CONCLUIDA = 1 and pp.respostaCerta=1 and  pa.QUANTIDADE_PERGUNTAS_TOTAL ="+codQtdPerguntas;
            Cursor cursor = banco.rawQuery(query,null);
            perguntas=cursor.getCount();
            cursor.close();
        }

        return perguntas;
    }

    public void atualizaSequenciaVitorias(int qtdPerguntas) {
        int maiorSequenciaGeral, sequenciaGeralAtual,maiorSequenciaCategoria,sequenciaCategoriaAtual;
        int id=0;
        if(qtdPerguntas==15){
            id=2;
        }else if(qtdPerguntas==30){
            id=3;
        }else if(qtdPerguntas==50){
            id=4;
        }

        maiorSequenciaGeral = consultaMaiorSequencia(1);
        sequenciaGeralAtual = consultaSequenciaAtual(1);
        maiorSequenciaCategoria = consultaMaiorSequencia(id);
        sequenciaCategoriaAtual = consultaSequenciaAtual(id);

        atualizaSequenciaVitoriasAtual(sequenciaGeralAtual,1);
        atualizaSequenciaVitoriasAtual(sequenciaCategoriaAtual,id);

        if((sequenciaGeralAtual+1)>maiorSequenciaGeral){
            atualizaSequenciaVitoriasMaxima(maiorSequenciaGeral,1);
        }

        if((sequenciaCategoriaAtual+1)>maiorSequenciaCategoria){
            atualizaSequenciaVitoriasMaxima(maiorSequenciaCategoria,id);
        }


        maiorSequenciaGeral = consultaMaiorSequencia(1);
        sequenciaGeralAtual = consultaSequenciaAtual(1);
        maiorSequenciaCategoria = consultaMaiorSequencia(id);
        sequenciaCategoriaAtual = consultaSequenciaAtual(id);

    }

    private void atualizaSequenciaVitoriasMaxima(int maiorSequencia, int i) {
        ContentValues values = new ContentValues();
        values.put("SEQUENCIA_MAXIMA", maiorSequencia+1);
        banco.update("sequenciaVitorias",values,"ID="+i,null);
    }

    private void atualizaSequenciaVitoriasAtual(int sequenciaAtual, int i) {
        ContentValues values = new ContentValues();
        values.put("SEQUENCIA_ATUAL", sequenciaAtual+1);
        banco.update("sequenciaVitorias",values,"ID="+i,null);
    }

    public void zerarSequenciaAtual(int qtdPerguntas) {
        int id=0;
        if(qtdPerguntas==15){
            id=2;
        }else if(qtdPerguntas==30){
            id=3;
        }else if(qtdPerguntas==50){
            id=4;
        }



        ContentValues vGeral = new ContentValues();
        ContentValues vCategoria = new ContentValues();
        vGeral.put("SEQUENCIA_ATUAL",0);
        vCategoria.put("SEQUENCIA_ATUAL",0);
        banco.update("sequenciaVitorias",vGeral,"ID=1",null);
        banco.update("sequenciaVitorias",vCategoria,"ID="+id,null);




    }

    public int consultaTotalPerguntasRespondidasCategoria(int i) {
        int total=0;

        String query = "select * from perguntas_partidas pp inner join partida pa on pp.codPartida = pa.CODPARTIDA where pa.CONCLUIDA = 1 and  pp.codCategoria ="+i;
        Cursor cursor = banco.rawQuery(query,null);
        total = cursor.getCount();
        cursor.close();

        return total;
    }

    public int consultaTotalPerguntasCertasCategoria(int i) {
        int total=0;

        String query = "select * from perguntas_partidas pp inner join partida pa on pp.codPartida = pa.CODPARTIDA where pa.CONCLUIDA = 1 and pp.respostaCerta=1 and  pp.codCategoria ="+i;
        Cursor cursor = banco.rawQuery(query,null);
        total = cursor.getCount();
        cursor.close();
        return total;
    }

    public void updateTimerRest(int seconds) {
        ContentValues value = new ContentValues();
        value.put("tempoRestante",seconds);
        banco.update("perguntaTemp",value,null,null);
    }

    public void updateAlternativasDisponiveis(int alt) {
        ContentValues value = new ContentValues();
        value.put("alternativasDisponiveis",alt);
        banco.update("perguntaTemp",value,null,null);
    }

    public void inserePartidaTemp(int codPartida, int codPergunta, int codCategoria, CharSequence alt1, CharSequence alt2, CharSequence alt3, CharSequence alt4) {


        ContentValues values = new ContentValues();
        values.put("ID",1);
        values.put("codPartida",codPartida);
        values.put("codPergunta",codPergunta);
        values.put("codCategoria",codCategoria);
        values.put("alt1",String.valueOf(alt1));
        values.put("alt2",String.valueOf(alt2));
        values.put("alt3",String.valueOf(alt3));
        values.put("alt4",String.valueOf(alt4));
        values.put("tempoRestante",30);
        values.put("alternativasDisponiveis",4);


        banco.delete("perguntaTemp","ID=1",null);
        banco.insert("perguntaTemp",null,values);

    }

    public void deletePerguntaTemp() {
        banco.delete("perguntaTemp",null,null);
    }

    public int consultaCodPerguntaTemp(){
        int cod = 0;
            Cursor cursor = banco.query("perguntaTemp", new String[]{"codPergunta"},null,null,null,null,null);
           while (cursor.moveToNext()){

                cod=cursor.getInt(0);
            }
        cursor.close();
        return cod;
    }

    public int consultaCodCategoriaTemp(){
        int cod = 0;
        Cursor cursor = banco.query("perguntaTemp", new String[]{"codCategoria"},null,null,null,null,null);
        while (cursor.moveToNext()){
            cod=cursor.getInt(0);
        }
        cursor.close();
        return cod;
    }

    public ArrayList consultaAlternativasTemp(){
        ArrayList<String> alternativas = new ArrayList<>();
        Cursor cursor = banco.query("perguntaTemp", new String[]{"*"},null,null,null,null,null);
        while (cursor.moveToNext()){
            for (int i=4;i<8;i++){
                alternativas.add(cursor.getString(i));
            }
        }
        cursor.close();
        return alternativas;
    }

    public void mostraPerguntaTemp() {
        Cursor cursor = banco.query("perguntaTemp", new String[]{"*"},null,null,null,null,null);
        while(cursor.moveToNext()){
            for(int i=0;i<cursor.getColumnCount();i++){

            }
        }
        cursor.close();
    }

    public int consultaTempoRestantePerguntaTemp() {
        int cod = 0;
        Cursor cursor = banco.query("perguntaTemp", new String[]{"tempoRestante"},null,null,null,null,null);
        while (cursor.moveToNext()){
            cod=cursor.getInt(0);
        }
        cursor.close();
        return cod;
    }



    public void insereAlternativasDisponiveis(CharSequence alt1, CharSequence alt2, CharSequence alt3, CharSequence alt4) {

        banco.delete("alternativasDisponiveisJogoPendente",null,null);
        ArrayList<String> alts = new ArrayList<>();
        alts.add(String.valueOf(alt1)); alts.add(String.valueOf(alt2));
        alts.add(String.valueOf(alt3));alts.add(String.valueOf(alt4));
        ContentValues values = new ContentValues();
        for(int i=0; i<4;i++){
            int id = i+1;
            values.put("ID", id);
            values.put("alternativa",String.valueOf(alts.get(i)));
            banco.insert("alternativasDisponiveisJogoPendente",null,values);
        }


    }

    public void atualizaTabelaAlternativasDisponiveis(CharSequence text) {
        String alternativa = String.valueOf(text);
        banco.delete("alternativasDisponiveisJogoPendente","alternativa='"+alternativa+"'",null);
    }

    public ArrayList<String> consultaalternativasDisponiveis() {
        ArrayList<String> alternativas = new ArrayList<>();
        Cursor cursor = banco.query("alternativasDisponiveisJogoPendente", new String[]{"alternativa"},null,null,null,null,null);
        while (cursor.moveToNext()){
            alternativas.add(String.valueOf(cursor.getString(0)));
        }
        cursor.close();
        return alternativas;
    }

    public int qtdRegistrosPerguntaTemp() {
        int qtd = 0;
        String query = "select count(*) from perguntaTemp ";
        Cursor cursor = banco.rawQuery(query,null);
        qtd = cursor.getCount();
        cursor.close();
        return qtd;
    }

    public int consultaQtdAlternativasDisponiveis() {
        ArrayList<String> alternativas = new ArrayList<>();
        Cursor cursor = banco.query("alternativasDisponiveisJogoPendente", new String[]{"alternativa"},null,null,null,null,null);
        while (cursor.moveToNext()){
            alternativas.add(String.valueOf(cursor.getString(0)));
        }
        cursor.close();
        return alternativas.size();
    }

    public int partidasVencidasTodasCategoriasSelecionadas() {
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
        return qtd;
    }

    public int statusVitoriaUltimaPartida() {
        int vitoria=0;
        String query = "select VITORIA from partida ORDER BY CODPARTIDA DESC LIMIT(1)";
        Cursor cursor = banco.rawQuery(query,null);
        while (cursor.moveToNext()){
            vitoria = cursor.getInt(0);
        }
        cursor.close();
        return vitoria;
    }

    public void atualizaAcertosContinente(Map<String, Integer> acertosContinentes) {
        ArrayList<String> continentes = new ArrayList<String>(Arrays.asList("África","América", "Ásia", "Europa", "Oceania"));
        
        for(int i = 0; i<5;i++) {
            String query = "select qtd from acertosContinentes where continente = '" +continentes.get(i)+ "' ";
            Cursor cursor = banco.rawQuery(query, null);
            int qtdAtual = 0;
            while (cursor.moveToNext()) {
                qtdAtual = cursor.getInt(0);
            }
            cursor.close();
            int qtd = qtdAtual + acertosContinentes.get(continentes.get(i));
            ContentValues values = new ContentValues();
            values.put("qtd", qtd);
            banco.update("acertosContinentes", values, "continente='" +continentes.get(i)+  "' ", null);
        }
    }

    public int consultaAcertosContinente(String conquista) {
        int qtd=0;
        String query = "select qtd from acertosContinentes where continente = '"+conquista+"'";
        Cursor cursor = banco.rawQuery(query, null);
        while(cursor.moveToNext()){
            qtd=cursor.getInt(0);
        }
        cursor.close();
        return qtd;
    }
}
