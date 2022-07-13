package com.ex.geografando.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ex.geografando.Conexao.Conexao;
import com.ex.geografando.Model.Alternativa;

import java.util.ArrayList;

public class AlternativaDAO {

    private Conexao conexao;
    private static SQLiteDatabase banco;

    public AlternativaDAO(Context context){
        conexao= new Conexao(context);
        banco = conexao.getWritableDatabase();
    }


    public static ArrayList buscaAlternativas(String resposta, int categoria){
       ArrayList<Alternativa> listaAlternativas = new ArrayList<>();
        boolean alternativaRepetida = false;
        int respostaPopulacao;
        double respostaArea;


        if(categoria==4||categoria==5){

            if(categoria==4) {
                respostaPopulacao = Integer.parseInt(resposta);
                listaAlternativas.add(verificaPopulacao(respostaPopulacao));
            }else if(categoria==5){
                respostaArea = Double.parseDouble(resposta);
                listaAlternativas.add(verificaArea(respostaArea));
            }
            while(listaAlternativas.size()<4) {
                Cursor cursor = banco.query("alternativas",
                        new String[]{"*"}, "tipoAlternativa = "+categoria, null, null, null, "RANDOM()","1");
                Alternativa alternativa = new Alternativa();
                while (cursor.moveToNext()){
                    alternativa.setCodAlternativa(cursor.getInt(0));
                    alternativa.setAlternativa(cursor.getString(1));
                    alternativa.setTipoAlternativa(Integer.parseInt(cursor.getString(2)));
                    alternativa.setValorMinimo(Long.parseLong(cursor.getString(3)));
                    alternativa.setValorMaximo(Long.parseLong(cursor.getString(4)));

                     alternativaRepetida = verificaAlternativaDuplicada(listaAlternativas,alternativa);

                        if (!alternativaRepetida) {
                            listaAlternativas.add(alternativa);
                        }

                }

                cursor.close();

            }

        }else if(categoria==2||categoria==8||categoria==9||categoria==10){

            while (listaAlternativas.size() < 3){
                Cursor cursor = banco.query("pais",
                        new String[]{"*"},  null, null, null, null,"RANDOM()","1");
            Alternativa alternativa = new Alternativa();
                while (cursor.moveToNext()){
                    alternativa.setCodAlternativa(cursor.getInt(0));
                    alternativa.setAlternativa(cursor.getString(1));
                    alternativa.setTipoAlternativa(categoria);
                    alternativa.setValorMaximo(0);
                    alternativa.setValorMinimo(0);

                    if(!alternativa.getAlternativa().equals(resposta)){

                        alternativaRepetida = verificaAlternativaDuplicada(listaAlternativas, alternativa);

                        if (!alternativaRepetida){
                            listaAlternativas.add(alternativa);
                        }

                    }
                }

                cursor.close();
            }

        }else {

           while (listaAlternativas.size() < 3) {
               Cursor cursor = banco.query("alternativas",
                        new String[]{"*"}, "tipoAlternativa = "+categoria, null, null, null, "RANDOM()", "1");
                Alternativa alternativa = new Alternativa();
                while (cursor.moveToNext()) {
                     alternativa.setCodAlternativa(cursor.getInt(0));
                    alternativa.setAlternativa(cursor.getString(1));
                    alternativa.setTipoAlternativa(Integer.parseInt(cursor.getString(2)));
                    alternativa.setValorMinimo(Long.parseLong(cursor.getString(3)));
                    alternativa.setValorMaximo(Long.parseLong(cursor.getString(4)));


                    if (!alternativa.getAlternativa().equals(resposta)) {
                        alternativaRepetida = verificaAlternativaDuplicada(listaAlternativas, alternativa);

                        if (!alternativaRepetida) {
                             listaAlternativas.add(alternativa);
                        }

                    }

                }


                cursor.close();

            }
        }

        return listaAlternativas;
    }

    private static Alternativa verificaArea(double respostaArea) {
        Alternativa a = new Alternativa();

        if(respostaArea<10000){
            a=buscaAlternativaPopulacaoArea(356);
        }else if(respostaArea<50000){
            a=buscaAlternativaPopulacaoArea(357);
        }else if(respostaArea<100000){
            a=buscaAlternativaPopulacaoArea(358);
        }else if(respostaArea<500000){
            a=buscaAlternativaPopulacaoArea(359);
        }else if(respostaArea<1000000){
            a=buscaAlternativaPopulacaoArea(360);
        }else if(respostaArea<5000000){
            a=buscaAlternativaPopulacaoArea(361);
        }else if(respostaArea>=5000000){
            a=buscaAlternativaPopulacaoArea(362);
        }

        return a;
    }

    private static Alternativa verificaPopulacao(int respostaPopulacao) {

        Alternativa a = new Alternativa();

        if(respostaPopulacao<100000){
             a = buscaAlternativaPopulacaoArea(348);
        }else
            if(respostaPopulacao<1000000){
                a = buscaAlternativaPopulacaoArea(349);
        }else
            if(respostaPopulacao<10000000){
               a = buscaAlternativaPopulacaoArea(350);
            }else
            if(respostaPopulacao<50000000){
                a = buscaAlternativaPopulacaoArea(351);
            }else
            if(respostaPopulacao<100000000){
                a = buscaAlternativaPopulacaoArea(352);
            }else
            if(respostaPopulacao<250000000){
                a = buscaAlternativaPopulacaoArea(353);
            }else
            if(respostaPopulacao<500000000){
                 a = buscaAlternativaPopulacaoArea(354);
            }else
            if(respostaPopulacao>=500000000){
                a = buscaAlternativaPopulacaoArea(355);
            }
        return a;
    }

    private static Alternativa buscaAlternativaPopulacaoArea(int codAlternativa) {
        Alternativa alternativa = new Alternativa();

        Cursor cursor = banco.query("alternativas",
                new String[]{"*"}, "codAlternativa = "+codAlternativa, null, null, null, null,null);

        while (cursor.moveToNext()){
            alternativa.setCodAlternativa(cursor.getInt(0));
            alternativa.setAlternativa(cursor.getString(1));
            alternativa.setTipoAlternativa(Integer.parseInt(cursor.getString(2)));
            alternativa.setValorMinimo(Long.parseLong(cursor.getString(3)));
            alternativa.setValorMaximo(Long.parseLong(cursor.getString(4)));
        }
        cursor.close();

        return alternativa;
    }

    public static boolean verificaAlternativaDuplicada(ArrayList<Alternativa> listaAlternativa, Alternativa alternativa){
        boolean alternativaDuplicada= false;
        Alternativa a = new Alternativa();
        for(int i=0; i<listaAlternativa.size();i++){
                a=listaAlternativa.get(i);

            if(a.getCodAlternativa()==alternativa.getCodAlternativa()){
                    alternativaDuplicada=true;
                }
            }
        return alternativaDuplicada;
    }

}
