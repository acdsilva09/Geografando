package com.ex.geografando.Model;

public class Conquista {
    private int ID;
    private String conquista;
    private int qtd_desbloqueio;
    private int qtdAcertos;
    private int desbloqueio;
    private int categoria;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getConquista() {
        return conquista;
    }

    public void setConquista(String conquista) {
        this.conquista = conquista;
    }

    public int getQtd_desbloqueio() {
        return qtd_desbloqueio;
    }

    public void setQtd_desbloqueio(int qtd_desbloqueio) {
        this.qtd_desbloqueio = qtd_desbloqueio;
    }

    public int getDesbloqueio() {
        return desbloqueio;
    }

    public void setDesbloqueio(int desbloqueio) {
        this.desbloqueio = desbloqueio;
    }

    public int getQtdAcertos() {
        return qtdAcertos;
    }

    public void setQtdAcertos(int qtdAcertos) {
        this.qtdAcertos = qtdAcertos;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
}
