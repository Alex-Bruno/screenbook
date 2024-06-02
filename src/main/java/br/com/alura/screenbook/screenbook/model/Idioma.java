package br.com.alura.screenbook.screenbook.model;

public enum Idioma {
    es("Espanhol"),
    en("Inglês"),
    fr("Francês"),
    pt("Português"),
    de("Alemão");

    private final String nomeCompleto;
    Idioma(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }
}
