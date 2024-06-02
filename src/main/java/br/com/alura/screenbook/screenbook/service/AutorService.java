package br.com.alura.screenbook.screenbook.service;

import br.com.alura.screenbook.screenbook.model.Autor;
import br.com.alura.screenbook.screenbook.model.DadosAutor;
import br.com.alura.screenbook.screenbook.repository.AutorRepository;

import java.util.List;
import java.util.Optional;

public class AutorService {
    private AutorRepository repositorio;

    public AutorService(AutorRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Autor criarAutor(DadosAutor dadosAutor) {
        Optional<Autor> autor = repositorio.findFirstByNomeContainingIgnoreCase(dadosAutor.nome());
        return autor.orElseGet(() -> new Autor(dadosAutor));
    }
}
