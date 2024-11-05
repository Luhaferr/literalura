package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.DadosLivro;
import br.com.alura.literalura.model.RespostaApi;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;

import java.util.Scanner;

public class Principal {
    private Scanner scanner;
    private Autor autor;
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?search=dom+casmurro";

    public void teste() {
        String json = consumo.obterDados(ENDERECO);
        System.out.println("Resposta JSON: " + json);  // Adicione esta linha para verificar a resposta

        RespostaApi respostaApi = conversor.converteDados(json, RespostaApi.class);

        if (respostaApi != null && respostaApi.resultados() != null) {
            for (DadosLivro dadosLivro : respostaApi.resultados()) {
                System.out.println("Título: " + dadosLivro.titulo());

                if (dadosLivro.autores() != null && !dadosLivro.autores().isEmpty()) {
                    System.out.println("Autor: " + dadosLivro.autores().get(0).nome());
                }

                if (dadosLivro.idioma() != null && !dadosLivro.idioma().isEmpty()) {
                    System.out.println("Idioma: " + dadosLivro.idioma().get(0));
                }

                System.out.println("Downloads: " + dadosLivro.numeroDownloads());
                System.out.println("------------------------");
            }
        } else {
            System.out.println("Nenhum dado encontrado.");
        }
    }
}
