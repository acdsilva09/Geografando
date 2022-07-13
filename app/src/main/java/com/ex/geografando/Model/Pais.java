package com.ex.geografando.Model;

public class Pais {

    /*
    codPais int(3) NOT NULL DEFAULT '0',\n" +
                "  nomePais varchar(100) DEFAULT NULL,\n" +
                "  idioma varchar(100) DEFAULT NULL,\n" +
                "  populacao long(10) DEFAULT NULL,\n" +
                "  area varchar(12) DEFAULT NULL,\n" +
                "  continente varchar(30) DEFAULT NULL,\n" +
                "  regiao varchar(30) DEFAULT NULL,\n" +
                "  capital varchar(100) DEFAULT NULL,\n" +
                "  maiorCidade varchar(100) DEFAULT NULL,\n" +
                "  moeda varchar(100) DEFAULT NULL,\n" +
                "  namePaisEnglish varchar(100) DEFAULT NULL,\n" +
     */


    private int codPais;
    private String nomePais;
    private String idioma;
    private int populacao;
    private double area;
    private String continente;
    private String regiao;
    private String capital;
    private String maiorCidade;
    private String moeda;
    private String pronomeD;
    private String namePaisEnglish;

    public int getCodPais() {
        return codPais;
    }

    public void setCodPais(int codPais) {
        this.codPais = codPais;
    }

    public String getNomePais() {
        return nomePais;
    }

    public void setNomePais(String nomePais) {
        this.nomePais = nomePais;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getPopulacao() {
        return populacao;
    }

    public void setPopulacao(int populacao) {
        this.populacao = populacao;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String região) {
        this.regiao = regiao;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getMaiorCidade() {
        return maiorCidade;
    }

    public void setMaiorCidade(String maiorCidade) {
        this.maiorCidade = maiorCidade;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public String getNamePaisEnglish() {
        return namePaisEnglish;
    }

    public void setNamePaisEnglish(String namePaisEnglish) {
        this.namePaisEnglish = namePaisEnglish;
    }

    public String getPronomeD() {
        return pronomeD;
    }

    public void setPronomeD(String pronomeD) {
        this.pronomeD = pronomeD;
    }
}
