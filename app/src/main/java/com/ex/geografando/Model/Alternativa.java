package com.ex.geografando.Model;

public class Alternativa {

    private int codAlternativa;
    private int tipoAlternativa;
    private String Alternativa;
    private long valorMinimo;
    private long valorMaximo;

    public int getCodAlternativa() {
        return codAlternativa;
    }

    public void setCodAlternativa(int codAlternativa) {
        this.codAlternativa = codAlternativa;
    }

    public String getAlternativa() {
        return Alternativa;
    }

    public void setAlternativa(String alternativa) {
        Alternativa = alternativa;
    }

    public int getTipoAlternativa() {
        return tipoAlternativa;
    }

    public void setTipoAlternativa(int tipoAlternativa) {
        this.tipoAlternativa = tipoAlternativa;
    }

    public long getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(long valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public long getValorMaximo() {
        return valorMaximo;
    }

    public void setValorMaximo(long valorMaximo) {
        this.valorMaximo = valorMaximo;
    }
}
