package com.ex.geografando.Model;

public class Pergunta {

    private int codPergunta;
    private String pergunta;
    private String resposta;
    private int nivel;
    private int codTipoCategoria;

    public int getCodPergunta() {
        return codPergunta;
    }

    public void setCodPergunta(int codPergunta) {
        this.codPergunta = codPergunta;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }


    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getCodTipoCategoria() {
        return codTipoCategoria;
    }

    public void setCodTipoCategoria(int codTipoCategoria) {
        this.codTipoCategoria = codTipoCategoria;
    }
}
