# Projeto Literalura

O **Literalura** é um aplicativo que permite buscar, listar e gerenciar livros e autores utilizando a API do Gutendex. A aplicação oferece várias funcionalidades, incluindo a busca de livros pelo título, listagem de autores registrados, e filtragem de livros por idioma.

## Funcionalidades

- **Buscar Livro pelo Título**: Busque livros diretamente pelo título utilizando a API do Gutendex.
- **Listar Livros Registrados**: Liste todos os livros registrados no sistema.
- **Listar Autores Registrados**: Liste todos os autores registrados no sistema.
- **Listar Autores Vivos em um Ano Específico**: Filtre autores que estavam vivos em um ano específico.
- **Listar Livros em um Idioma Específico**: Filtre livros disponíveis em um idioma específico.
- **Listar Top 10 Livros Baixados**: Liste os 10 livros mais baixados.

## Tecnologias Utilizadas

- [Jakarta EE](https://jakarta.ee/)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Spring MVC](https://spring.io/guides/gs/serving-web-content/)
- [Java SDK 21](https://www.oracle.com/java/technologies/javase-jdk21-downloads.html)

## Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
- [Java 21](https://www.oracle.com/java/technologies/javase-jdk21-downloads.html)
- [Maven](https://maven.apache.org/)
- [Git](https://git-scm.com/)

## Como rodar a aplicação

```bash
# Clone este repositório
$ git clone https://github.com/luhaferr/literalura

# Acesse a pasta do projeto no terminal/cmd
$ cd literalura

# Compile o projeto
$ mvn clean install

# Execute a aplicação
$ mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`.

## Como contribuir

Para contribuir com o projeto, siga estas etapas:

1. Faça um fork deste repositório.
2. Crie uma branch com a sua feature: `git checkout -b minha-feature`
3. Commit suas mudanças: `git commit -m 'feat: Minha nova feature'`
4. Faça o push para a sua branch: `git push origin minha-feature`
5. Abra um Pull Request

## Contato

Luiz Ferreira - luhaferr@gmail.com

Link do Projeto: https://github.com/luhaferr/literalura
