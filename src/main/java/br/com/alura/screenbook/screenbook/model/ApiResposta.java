package br.com.alura.screenbook.screenbook.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApiResposta(@JsonAlias("results") List<DadosLivro> resultado){
}
