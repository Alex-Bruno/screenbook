package br.com.alura.screenbook.screenbook.service;

import br.com.alura.screenbook.screenbook.model.*;
import br.com.alura.screenbook.screenbook.repository.AutorRepository;
import br.com.alura.screenbook.screenbook.repository.LivroRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    private LivroRepository repositorio;
    private AutorService autorService;

    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "https://gutendex.com/books/?search=";

    public LivroService(LivroRepository repositorio, AutorRepository autorRepository) {
        this.repositorio = repositorio;
        this.autorService = new AutorService(autorRepository);
    }

    public List<Livro> findAll() {
        return repositorio.findAll();
    }

    public Optional<DadosLivro> getDadosLivro(String nomeLivro) {
        var json = consumo.obterDados(ENDERECO + nomeLivro.replace(" ", "+"));
        ApiResposta resposta = conversor.obterDados(json, ApiResposta.class);

        if (resposta.resultado().isEmpty()) {
            System.out.println("Não foi possível encontrar o livro.");
            return Optional.empty();
        }

        return Optional.ofNullable(resposta.resultado().get(0));
    }

    public Livro salvarLivro(DadosLivro dadosLivro) {
        Autor autor = autorService.criarOuBuscarAutor(dadosLivro.autores().get(0));

        Livro livro = new Livro(dadosLivro);
        livro.setAutor(autor);

        autorService.salvarAutor(livro);

        return livro;
    }

    public List<Livro> obterLivrosPeloIdioma(Idioma idioma) {
        return repositorio.obterLivrosPeloIdioma(idioma);
    }
}
