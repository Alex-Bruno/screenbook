package br.com.alura.screenbook.screenbook.service;

import br.com.alura.screenbook.screenbook.model.Autor;
import br.com.alura.screenbook.screenbook.model.DadosAutor;
import br.com.alura.screenbook.screenbook.model.Livro;
import br.com.alura.screenbook.screenbook.repository.AutorRepository;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class AutorService {
    private AutorRepository repositorio;

    public AutorService(AutorRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<Autor> findAll() {
        return repositorio.findAll();
    }


    public Optional<Autor> find(Long id) {
        return repositorio.findById(id);
    }

    public Autor criarOuBuscarAutor(DadosAutor dadosAutor) {
        Optional<Autor> optionalAutor = repositorio.findFirstByNomeContainingIgnoreCase(dadosAutor.nome());

        if (optionalAutor.isPresent()) {
            Autor autor = optionalAutor.get();
            return autor;
        }
        return new Autor(dadosAutor);
    }

    public void salvarAutor(Livro livro) {
        Autor autor = livro.getAutor();
        autor.getLivros().add(livro);
        repositorio.save(autor);
    }

    public List<Autor> obterAutoresVivosEmDeterninadoAno(Integer ano) {
        return repositorio.obterAutoresVivosEmDeterninadoAno(ano);
    }
}
