package br.com.alura.screenbook.screenbook.principal;

import br.com.alura.screenbook.screenbook.model.Autor;
import br.com.alura.screenbook.screenbook.model.DadosLivro;
import br.com.alura.screenbook.screenbook.model.Idioma;
import br.com.alura.screenbook.screenbook.model.Livro;
import br.com.alura.screenbook.screenbook.repository.AutorRepository;
import br.com.alura.screenbook.screenbook.repository.LivroRepository;
import br.com.alura.screenbook.screenbook.service.AutorService;
import br.com.alura.screenbook.screenbook.service.LivroService;
import jakarta.persistence.EntityManager;

import java.util.*;

public class Principal {
    private LivroService service;
    private AutorService autorService;

    private Scanner leitura = new Scanner(System.in);

    public Principal(AutorRepository autorRepository, LivroRepository livroRepository) {
        this.service = new LivroService(livroRepository, autorRepository);
        this.autorService = new AutorService(autorRepository);
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                --------------
                Escolha o número de sua opção:
                1 - Buscar livro pelo título
                2 - Listar livros registrados
                3 - Listar autores registrados
                4 - Listar autores vivos em determinado ano
                5 - Listar livros em um determinado idioma
                0 - Sair
                """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            executaOpcaoSelecionada(opcao);
        }
    }

    private void executaOpcaoSelecionada(int opcao) {
        switch (opcao) {
            case 1:
                buscarLivroPeloTitulo();
                break;
            case 2:
                listarLivrosRegistrados();
                break;
            case 3:
                listarAutoresRegistrados();
                break;
            case 4:
                listarAutoresVivosEmDeterninadoAno();
                break;
            case 5:
                listarLivrosEmUmDeterminadoIdioma();
                break;
            case 0:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opção inválida");
        }
    }

    private void buscarLivroPeloTitulo() {
        System.out.println("Digite o nome do livro para busca: ");
        var nomeLivro = leitura.nextLine();
        Optional<DadosLivro> dadosLivro = service.getDadosLivro(nomeLivro);

        if (dadosLivro.isPresent()) {
            Livro livro = service.salvarLivro(dadosLivro.get());

            System.out.println(livro);
        }
    }

    private void listarLivrosRegistrados() {
        List<Livro> livros = service.findAll();

        livros.forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = autorService.findAll();

        autores.forEach(System.out::println);
    }


    private void listarAutoresVivosEmDeterninadoAno() {
        System.out.println("Insira o ano em que deseja pesquisar: ");
        var ano = leitura.nextInt();
        leitura.nextLine();
        List<Autor> autores = autorService.obterAutoresVivosEmDeterninadoAno(ano);

        autores.forEach(System.out::println);
    }

    private void listarLivrosEmUmDeterminadoIdioma() {
        System.out.println("Insira o idioma para realizar a busca: ");
        exibeMenuIdiomas();
        String entrada = leitura.nextLine();
        Idioma idioma = Idioma.valueOf(entrada);

        List<Livro> livros = service.obterLivrosPeloIdioma(idioma);

        livros.forEach(System.out::println);
    }

    private void exibeMenuIdiomas() {
        List<Idioma> idiomas = Arrays.asList(Idioma.es, Idioma.en, Idioma.fr, Idioma.pt, Idioma.de);
        for (Idioma idi : idiomas) {
            System.out.println(idi.name() + ": " + idi.getNomeCompleto());
        }
    }
}
