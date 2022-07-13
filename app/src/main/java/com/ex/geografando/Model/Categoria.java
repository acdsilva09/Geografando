package com.ex.geografando.Model;

public class Categoria {

    private int codCategoria;
    private String categoria;
    private int habilitada;
    private int sselecionada;


    public int getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(int codCategoria) {
        this.codCategoria = codCategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getSselecionada() {
        return sselecionada;
    }

    public void setSselecionada(int sselecionada) {
        this.sselecionada = sselecionada;
    }

    public int getHabilitada() {
        return habilitada;
    }

    public void setHabilitada(int habilitada) {
        this.habilitada = habilitada;
    }
}
