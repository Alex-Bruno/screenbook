package br.com.alura.screenbook.screenbook.service;

public interface IConverteDados {
    <T> T  obterDados(String json, Class<T> classe);
}
