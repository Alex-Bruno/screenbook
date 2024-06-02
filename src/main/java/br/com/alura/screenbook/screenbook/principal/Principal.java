package br.com.alura.screenbook.screenbook.principal;

import br.com.alura.screenbook.screenbook.model.DadosLivro;
import br.com.alura.screenbook.screenbook.model.Livro;
import br.com.alura.screenbook.screenbook.repository.AutorRepository;
import br.com.alura.screenbook.screenbook.repository.LivroRepository;
import br.com.alura.screenbook.screenbook.service.LivroService;

import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private LivroService service;

    private Scanner leitura = new Scanner(System.in);

    public Principal(AutorRepository autorRepository, LivroRepository livroRepository) {
        this.service = new LivroService(livroRepository, autorRepository);
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
}
