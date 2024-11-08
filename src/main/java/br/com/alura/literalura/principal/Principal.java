package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.*;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private LivroRepository livroRepository;
    private AutorRepository autorRepository;
    private final String ENDERECO = "https://gutendex.com/books/?search=";

    public Principal(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em um determinado idioma
                    6 - Listar os 10 livros mais baixados
                    
                    0 - Sair
                    """;

            System.out.println(menu);
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivroPeloTitulo();
                    break;
                case 2:
                    listaLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosNoAno();
                    break;
                case 5:
                    listarLivrosEmUmIdioma();
                    break;
                case 6:
                    listarTop10Baixados();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    public void buscarLivroPeloTitulo() {
        System.out.println("Insira o nome do livro que deseja buscar");
        String nomeLivro = scanner.nextLine();

        String json = consumo.obterDados(ENDERECO + nomeLivro.replace(" ", "+"));
        RespostaApi respostaApi = conversor.converteDados(json, RespostaApi.class);

        DadosLivro dadosLivro = respostaApi.resultados().get(0);
        DadosAutor dadosAutor = dadosLivro.autores().get(0);

        Optional<Livro> livroBuscado = livroRepository.findByTitulo(dadosLivro.titulo());
        Livro livro;

        if (livroBuscado.isPresent()) {
            livro = livroBuscado.get();
            System.out.println("Livro já está registrado!");
            System.out.println(livro);
        } else {
            Optional<Autor> autorExistente = autorRepository.findByNome(dadosAutor.nome());
            Autor autor;

            if (autorExistente.isPresent()) {
                autor = autorExistente.get();
            } else {
                autor = new Autor(dadosAutor);
                autorRepository.save(autor);
            }

            livro = new Livro(dadosLivro, autor);
            livroRepository.save(livro);

            System.out.println(livro);
        }
    }

    public void listaLivrosRegistrados() {
        List<Livro> livros = livroRepository.findAll();
        livros.forEach(System.out::println);
    }

    public void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();

        autores.forEach(System.out::println);
    }

    public void listarAutoresVivosNoAno() {
        System.out.println("Insira o ano que deseja pesquisar");
        int ano = scanner.nextInt();
        scanner.nextLine();

        List<Autor> autoresVivos = autorRepository.findAutoresVivos(ano);
        autoresVivos.forEach(System.out::println);
    }

    public void listarLivrosEmUmIdioma() {
        String menuIdioma = """
                    Insira o idioma para realizar a busca:
                    es - espanhol
                    en - inglês
                    fr - francês
                    pt - português
                    """;

        System.out.println(menuIdioma);
        String idioma = scanner.nextLine();

        List<Livro> livros = livroRepository.findByIdiomaContainingIgnoreCase(idioma);
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado nesse idioma");
        } else {
            livros.forEach(System.out::println);
        }
    }

    public void listarTop10Baixados() {
        List<Livro> livrosBaixados = livroRepository.findTop10ByOrderByNumeroDownloadsDesc();
        livrosBaixados.forEach(System.out::println);
    }

}
