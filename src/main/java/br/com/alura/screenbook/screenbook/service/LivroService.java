package br.com.alura.screenbook.screenbook.service;

import br.com.alura.screenbook.screenbook.model.ApiResposta;
import br.com.alura.screenbook.screenbook.model.Autor;
import br.com.alura.screenbook.screenbook.model.DadosLivro;
import br.com.alura.screenbook.screenbook.model.Livro;
import br.com.alura.screenbook.screenbook.repository.AutorRepository;
import br.com.alura.screenbook.screenbook.repository.LivroRepository;
import org.springframework.stereotype.Service;

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
        Autor autor = autorService.criarAutor(dadosLivro.autores().get(0));

        Livro livro = new Livro(dadosLivro);
        livro.setAutor(autor);

        repositorio.save(livro);

        return livro;
    }


}
