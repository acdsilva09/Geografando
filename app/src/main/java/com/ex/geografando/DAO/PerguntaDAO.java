package com.ex.geografando.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ex.geografando.Conexao.Conexao;
import com.ex.geografando.Model.Alternativa;
import com.ex.geografando.Model.Pais;
import com.ex.geografando.Model.Pergunta;

import java.util.ArrayList;
import java.util.Collections;

public class PerguntaDAO {

    private Conexao conexao;
    private static SQLiteDatabase banco;
    private static ArrayList<Integer> arrayCategorias = new ArrayList<>();


    public PerguntaDAO(Context context){
        conexao= new Conexao(context);
        banco = conexao.getWritableDatabase();
        arrayCategorias = verificaSelecaoCategoria();

    }

    private ArrayList<Integer> verificaSelecaoCategoria() {
        ArrayList<Integer> categorias = new ArrayList<>();

        Cursor cursor = banco.query("categorias", new String[]{"*"},"selecionada=1",
                null,null,null,null);

        while (cursor.moveToNext()){
            categorias.add(cursor.getInt(0));
        }
        cursor.close();
        return categorias;
    }

    public static Pergunta buscaPergunta(){
       Pergunta pergunta = new Pergunta();
        int categoria;
        Collections.shuffle(arrayCategorias);
        categoria = arrayCategorias.get(0);

        Pais pais = buscaPais(categoria);
        String pronomeD = consultaPronomeD(pais.getCodPais());
        String pronomeN = consultaPronomeN(pais.getCodPais());


        switch(categoria){

            case 1:
                pergunta.setPergunta("Qual a capital "+pronomeD+ " "+pais.getNomePais()+"?");
                pergunta.setResposta(pais.getCapital());
                pergunta.setCodTipoCategoria(1);
                break;

            case 2:
                pergunta.setPergunta("De qual país é essa bandeira?");
                pergunta.setResposta(pais.getNomePais());
                pergunta.setCodTipoCategoria(2);
                break;

            case 3:
                pergunta.setPergunta("Qual o idioma " +pronomeD+ " "+pais.getNomePais()+"?");
                pergunta.setResposta(pais.getIdioma());
                pergunta.setCodTipoCategoria(3);
                break;

            case 4:
                pergunta.setPergunta("Qual a população "+pronomeD+ " "+pais.getNomePais()+"?");
                pergunta.setResposta(Integer.toString(pais.getPopulacao()));
                pergunta.setCodTipoCategoria(4);
                break;

            case 5:
                pergunta.setPergunta("Qual a área "+pronomeD+ " "+pais.getNomePais()+" em km²?");
                pergunta.setResposta(Double.toString(pais.getArea()));
                pergunta.setCodTipoCategoria(5);
                break;

            case 6:
                boolean pron=true;
                String pronome="";
                String começoPergunta;
                if(pronomeD.equals("de")){
                    pron=false;
                }else{
                    pronome = pronomeD.substring(1);

                }

                if(pronomeD.length()>2)
                {
                    começoPergunta="Qual o continente onde se localizam";
                }else
                {
                    começoPergunta="Qual o continente onde se localiza";
                }

                if(pron) {
                    pergunta.setPergunta(começoPergunta + " " + pronome + " " + pais.getNomePais() + "?");
                }else{
                    pergunta.setPergunta(começoPergunta + " " + pais.getNomePais() + "?");
                }
                pergunta.setResposta(pais.getContinente());
                pergunta.setCodTipoCategoria(6);
                break;

            case 7:
                pergunta.setPergunta("Qual a moeda usada "+pronomeN+ " "+pais.getNomePais()+"?");
                pergunta.setResposta(pais.getMoeda());
                pergunta.setCodTipoCategoria(7);
                break;

            case 8:
                pergunta.setPergunta("De qual país é esse brasão?");
                pergunta.setResposta(pais.getNomePais());
                pergunta.setCodTipoCategoria(8);
                break;

            case 9:
                pergunta.setPergunta("De qual país é esse mapa?");
                pergunta.setResposta(pais.getNomePais());
                pergunta.setCodTipoCategoria(9);
                break;
            case 10:

                pergunta.setPergunta("Em qual país se localiza a cidade de "+
                        buscaCidadePaisSelecionado(pais.getCodPais())+" ?");
                pergunta.setResposta(pais.getNomePais());
                pergunta.setCodTipoCategoria(10);
                break;

        }
                pergunta.setCodPergunta(pais.getCodPais());
                pergunta.setNivel(1);




        return pergunta;
    }

    private static String buscaCidadePaisSelecionado(int codPais) {

        String cidade=null;
        String query = "select cidade from cidades where codPais ="+codPais+" Order BY RANDOM() limit 1";
        Cursor cursor = banco.rawQuery(query, null);
        while(cursor.moveToNext())
        {
            cidade = (cursor.getString(0));
        }
        cursor.close();
        return cidade;

    }

    private static String consultaPronomeD(int codPais) {
        String pronome = null;
        Cursor cursor = banco.query("pais", new String[]{"pronomeD"},"codPais = "+codPais,
                null,null,null,null);
        while(cursor.moveToNext())
        {
            pronome = (cursor.getString(0));
        }
        cursor.close();
        return pronome;
    }

    private static String consultaPronomeN(int codPais) {
        String pronome = null;
        Cursor cursor = banco.query("pais", new String[]{"pronomeN"},"codPais = "+codPais,
                null,null,null,null);
        while(cursor.moveToNext())
        {
            pronome = (cursor.getString(0));
        }
        cursor.close();
        return pronome;
    }

    private static Pais buscaPais(int categoria) {

        Pais p = new Pais();

        if(categoria==10) {

            String query = "select * from pais where codPais NOT IN (" +


                    "7,\n"+
                    "13,\n"+
                    "17,\n"+
                    "19,\n"+
                    "22,\n"+
                    "29,\n"+
                    "31,\n"+
                    "32,\n"+
                    "33,\n"+
                    "36,\n"+
                    "38,\n"+
                    "40,\n"+
                    "45,\n"+
                    "53,\n"+
                    "54,\n"+
                    "59,\n"+
                    "66,\n"+
                    "71,\n"+
                    "74,\n"+
                    "75,\n"+
                    "77,\n"+
                    "79,\n"+
                    "85,\n"+
                    "86,\n"+
                    "92,\n"+
                    "99,\n"+
                    "100,\n"+
                    "101,\n"+
                    "102,\n"+
                    "103,\n"+
                    "104,\n"+
                    "106,\n"+
                    "108,\n"+
                    "109,\n"+
                    "113,\n"+
                    "114,\n"+
                    "115,\n"+
                    "117,\n"+
                    "118,\n"+
                    "120,\n"+
                    "123,\n"+
                    "124,\n"+
                    "125,\n"+
                    "127,\n"+
                    "128,\n"+
                    "137,\n"+
                    "138,\n"+
                    "140,\n"+
                    "144,\n"+
                    "148,\n"+
                    "149,\n"+
                    "151,\n"+
                    "156,\n"+
                    "157,\n"+
                    "159,\n"+
                    "160,\n"+
                    "161,\n"+
                    "162,\n"+
                    "163,\n"+
                    "164,\n"+
                    "165,\n"+
                    "167,\n"+
                    "169,\n"+
                    "171,\n"+
                    "172,\n"+
                    "173,\n"+
                    "175,\n"+
                    "178,\n"+
                    "179,\n"+
                    "180,\n"+
                    "181,\n"+
                    "182,\n"+
                    "183,\n"+
                    "184,\n"+
                    "185,\n"+
                    "186,\n"+
                    "190,\n"+
                    "192,\n"+
                    "193,\n"+
                    "194,\n"+
                    "195,\n"+
                    "196,\n"+
                    "199,\n"+
                    "201) Order BY RANDOM() limit 1";

            Cursor cursor_cat10 = banco.rawQuery(query, null);
            while (cursor_cat10.moveToNext()) {
                p.setCodPais(cursor_cat10.getInt(0));
                p.setNomePais(cursor_cat10.getString(1));
                p.setIdioma(cursor_cat10.getString(2));
                p.setPopulacao((int) cursor_cat10.getLong(3));
                p.setArea(Double.parseDouble(cursor_cat10.getString(4)));
                p.setContinente(cursor_cat10.getString(5));
                p.setRegiao(cursor_cat10.getString(6));
                p.setCapital(cursor_cat10.getString(7));
                p.setMaiorCidade(cursor_cat10.getString(8));
                p.setMoeda(cursor_cat10.getString(9));
                p.setNamePaisEnglish(cursor_cat10.getString(10));
                cursor_cat10.close();

            }cursor_cat10.close();
        }else


        if(categoria==9) {

            String query = "select * from pais where codPais NOT IN (8  ,\n" +
                    "7  ,\n" +
                    "13 ,\n" +
                    "20 ,\n" +
                    "22 ,\n" +
                    "29 ,\n" +
                    "34 ,\n" +
                    "45 ,\n" +
                    "54 ,\n" +
                    "74 ,\n" +
                    "75 ,\n" +
                    "80 ,\n" +
                    "85 ,\n" +
                    "98 ,\n" +
                    "103,\n" +
                    "109,\n" +
                    "112,\n" +
                    "113,\n" +
                    "115,\n" +
                    "116,\n" +
                    "117,\n" +
                    "120,\n" +
                    "123,\n" +
                    "126,\n" +
                    "127,\n" +
                    "128,\n" +
                    "129,\n" +
                    "137,\n" +
                    "138,\n" +
                    "140,\n" +
                    "141,\n" +
                    "144,\n" +
                    "149,\n" +
                    "152,\n" +
                    "160,\n" +
                    "161,\n" +
                    "162,\n" +
                    "163,\n" +
                    "164,\n" +
                    "165,\n" +
                    "171,\n" +
                    "173,\n" +
                    "175,\n" +
                    "180,\n" +
                    "183,\n" +
                    "185,\n" +
                    "190,\n" +
                    "196) Order BY RANDOM() limit 1";

            Cursor cursor_cat9 = banco.rawQuery(query, null);
            while (cursor_cat9.moveToNext()) {
                p.setCodPais(cursor_cat9.getInt(0));
                p.setNomePais(cursor_cat9.getString(1));
                p.setIdioma(cursor_cat9.getString(2));
                p.setPopulacao((int) cursor_cat9.getLong(3));
                p.setArea(Double.parseDouble(cursor_cat9.getString(4)));
                p.setContinente(cursor_cat9.getString(5));
                p.setRegiao(cursor_cat9.getString(6));
                p.setCapital(cursor_cat9.getString(7));
                p.setMaiorCidade(cursor_cat9.getString(8));
                p.setMoeda(cursor_cat9.getString(9));
                p.setNamePaisEnglish(cursor_cat9.getString(10));
                cursor_cat9.close();

            }
            cursor_cat9.close();
        }

        else{

            Cursor cursor = banco.query("pais Order BY RANDOM() limit 1", new String[]{"*"},null,
                    null,null,null,null);

            while (cursor.moveToNext()){
                p.setCodPais(cursor.getInt(0));
                p.setNomePais(cursor.getString(1));
                p.setIdioma(cursor.getString(2));
                p.setPopulacao((int) cursor.getLong(3));
                p.setArea(Double.parseDouble(cursor.getString(4)));
                p.setContinente(cursor.getString(5));
                p.setRegiao(cursor.getString(6));
                p.setCapital(cursor.getString(7));
                p.setMaiorCidade(cursor.getString(8));
                p.setMoeda(cursor.getString(9));
                p.setNamePaisEnglish(cursor.getString(10));

            }
            cursor.close();
        }
        return p;

    }


    public boolean confereResposta(String resposta, String codPergunta_string, String codCategoriaPergunta) {

        Pais p = new Pais();
        int codPais = Integer.parseInt(codPergunta_string);
        int codCategoria = Integer.parseInt(codCategoriaPergunta);



        boolean respostaCerta = false;
        Cursor cursor = banco.query("pais",new String[]{"*"}, "codPais="+codPais,null,null,null,null,null );

        while (cursor.moveToNext()) {

            p.setCodPais(cursor.getInt(0));
            p.setNomePais(cursor.getString(1));
            p.setIdioma(cursor.getString(2));
            p.setPopulacao((int) cursor.getLong(3));
            p.setArea(Double.parseDouble(cursor.getString(4)));
            p.setContinente(cursor.getString(5));
            p.setRegiao(cursor.getString(6));
            p.setCapital(cursor.getString(7));
            p.setMaiorCidade(cursor.getString(8));
            p.setMoeda(cursor.getString(9));
            p.setNamePaisEnglish(cursor.getString(10));

        }



        switch (codCategoria){
            case 1:
                if(p.getCapital().equals(resposta)){
                    respostaCerta=true;
                }
                break;

            case 2:
                if(p.getNomePais().equals(resposta)){
                    respostaCerta=true;
                }
                break;

            case 3:
                if(p.getIdioma().equals(resposta)){
                    respostaCerta=true;
                }
                break;

            case 4:
                String resp = resposta+"'";
                Cursor cursorPopulacao = banco.query("alternativas",new String[]{"*"},"alternativa='"+resp,
                        null,null,null,null,null);
                Alternativa alt = new Alternativa();
                ArrayList<Alternativa> listAlternativas = new ArrayList<>();
                while (cursorPopulacao.moveToNext()){
                    alt.setCodAlternativa(cursorPopulacao.getInt(0));
                    alt.setAlternativa(cursorPopulacao.getString(1));
                    alt.setTipoAlternativa(Integer.parseInt(cursorPopulacao.getString(2)));
                    alt.setValorMinimo(Long.parseLong(cursorPopulacao.getString(3)));
                    alt.setValorMaximo(Long.parseLong(cursorPopulacao.getString(4)));

                }

                if(p.getPopulacao()>=alt.getValorMinimo() && p.getPopulacao()<=alt.getValorMaximo()){
                   respostaCerta=true;
                }
                cursorPopulacao.close();
                break;


            case 5:
                String respArea = resposta+"'";
                Cursor cursorArea = banco.query("alternativas",new String[]{"*"},"alternativa='"+respArea,
                        null,null,null,null,null);
                Alternativa altArea = new Alternativa();
                ArrayList<Alternativa> listAlternativasArea = new ArrayList<>();
                while (cursorArea.moveToNext()){
                    altArea.setCodAlternativa(cursorArea.getInt(0));
                    altArea.setAlternativa(cursorArea.getString(1));
                    altArea.setTipoAlternativa(Integer.parseInt(cursorArea.getString(2)));
                    altArea.setValorMinimo(Long.parseLong(cursorArea.getString(3)));
                    altArea.setValorMaximo(Long.parseLong(cursorArea.getString(4)));

                }

                if(p.getArea()>=altArea.getValorMinimo() && p.getArea()<=altArea.getValorMaximo()){
                   respostaCerta=true;
                }
                cursorArea.close();
                break;


            case 6:
                if(p.getContinente().equals(resposta)){
                    respostaCerta=true;
                }
                break;

            case 7:
                if(p.getMoeda().equals(resposta)){
                    respostaCerta=true;
                }
                break;


            case 8:
                if(p.getNomePais().equals(resposta)){
                    respostaCerta=true;
                }
                break;

            case 9:
                if(p.getNomePais().equals(resposta)){
                    respostaCerta=true;
                }
                break;

            case 10:
                if(p.getNomePais().equals(resposta)){
                    respostaCerta=true;
                }
                break;


        }


        cursor.close();
        return respostaCerta;
    }


    public void atualizaPartidaPerguntas(int codPartida, int codPergunta, int codCategoria,boolean respostaCerta) {
        ContentValues values = new ContentValues();
        int acerto=0;
        if(respostaCerta){
            acerto=1;
        }else{
            acerto=0;
        }


        values.put("ID",(consultaUltimoPerguntasPorPartida()));
        values.put("codPartida",codPartida);
        values.put("codPergunta",codPergunta);
        values.put("codCategoria",codCategoria);
        values.put("respostaCerta",acerto);
        banco.insert("perguntas_partidas",null,values);

    }

    public int consultaUltimoPerguntasPorPartida() {
        int id=0;

        Cursor cursor = banco.query("perguntas_partidas",new String[]{"ID"},null,null,null,null,"ID DESC","1");

        while(cursor.moveToNext()){
            id=cursor.getInt(0);
        }cursor.close();

        return id+1;
    }

    public Pergunta buscaPerguntaEspecifica(String codPergunta, String codCategoria) {

        Pergunta pergunta = new Pergunta();
        int codPais = Integer.parseInt(codPergunta);
        int categoria=Integer.parseInt(codCategoria);
        Pais pais = buscaPaisEspecifico(codPais);


        switch(categoria){

            case 1:
                pergunta.setPergunta("Qual a capital "+pais.getNomePais());
                pergunta.setResposta(pais.getCapital());
                pergunta.setCodTipoCategoria(1);
                break;

            case 2:
                pergunta.setPergunta("De qual país é essa bandeira?");
                pergunta.setResposta(pais.getNomePais());
                pergunta.setCodTipoCategoria(2);
                break;

            case 3:
                pergunta.setPergunta("Qual o idioma "+pais.getNomePais());
                pergunta.setResposta(pais.getIdioma());
                pergunta.setCodTipoCategoria(3);
                break;

            case 4:
                pergunta.setPergunta("Qual a população "+pais.getNomePais());
                pergunta.setResposta(Integer.toString(pais.getPopulacao()));
                pergunta.setCodTipoCategoria(4);
                break;

            case 5:
                pergunta.setPergunta("Qual a area "+pais.getNomePais()+" (km²)");
                pergunta.setResposta(Double.toString(pais.getArea()));
                pergunta.setCodTipoCategoria(5);
                break;

            case 6:
                pergunta.setPergunta("Qual o continente onde fica "+pais.getNomePais());
                pergunta.setResposta(pais.getContinente());
                pergunta.setCodTipoCategoria(6);
                break;

            case 7:
                pergunta.setPergunta("Qual a moeda usada "+pais.getNomePais());
                pergunta.setResposta(pais.getMoeda());
                pergunta.setCodTipoCategoria(7);
                break;

            case 8:
                pergunta.setPergunta("De qual país é esse brasão?");
                pergunta.setResposta(pais.getNomePais());
                pergunta.setCodTipoCategoria(8);
                break;

            case 9:
                pergunta.setPergunta("De qual país é esse mapa?");
                pergunta.setResposta(pais.getNomePais());
                pergunta.setCodTipoCategoria(9);
                break;

            case 10:

                pergunta.setPergunta("Em qual país se localiza a cidade de "+
                        buscaCidadePaisSelecionado(pais.getCodPais())+" ?");
                pergunta.setResposta(pais.getNomePais());
                pergunta.setCodTipoCategoria(10);
                break;

        }
        pergunta.setCodPergunta(pais.getCodPais());
        pergunta.setNivel(1);




        return pergunta;

    }

    public Pais buscaPaisEspecifico(int codPais) {
        Pais p = new Pais();
        Cursor cursor = banco.query("pais",new String[]{"*"},"codPais="+codPais,null,null,null,null);

        while (cursor.moveToNext()){
            p.setCodPais(cursor.getInt(0));
            p.setNomePais(cursor.getString(1));
            p.setIdioma(cursor.getString(2));
            p.setPopulacao((int) cursor.getLong(3));
            p.setArea(Double.parseDouble(cursor.getString(4)));
            p.setContinente(cursor.getString(5));
            p.setRegiao(cursor.getString(6));
            p.setCapital(cursor.getString(7));
            p.setMaiorCidade(cursor.getString(8));
            p.setMoeda(cursor.getString(9));
            p.setNamePaisEnglish(cursor.getString(10));
            p.setPronomeD(cursor.getString(11));

        }
        cursor.close();
        return p;

    }



}
