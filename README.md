<h1 align="center">
  Biblioteca API
</h1>

Esta é uma API RESTful completa para gerenciamento de uma biblioteca, permitindo o controle de Autores e seus respectivos Livros.
O projeto foi desenvolvido focando em boas práticas de arquitetura, separação de responsabilidades e tratamento de erros robusto.  
> Este projeto será futuramente preparado para execução com **Docker + MySQL**.

  <p align="center">
  <a href="https://skillicons.dev">
    <img src="https://skillicons.dev/icons?i=java,spring,vscode,postman&theme=dark" />
  </a>
</p>

---

## Sumário

- [Inicio](#sumário)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Arquitetura](#arquitetura)
- [Funcionalidades Principais](#funcionalidades-principais)
- [Endpoints](#endpoints-da-api)
- [Como executar](#como-executar-o-projeto)
- [Autor](#autor)

---

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot** 3.5.9
- **Spring Data JPA:** Camada de persistência e consultas automáticas
- **MapStruct:** Mapeamento performático entre Entidades e DTOs
- **H2 Database:** Banco de dados para desenvolvimento
- **Jakarta Validation:** Validação de dados de entrada
- **Global Exception Handling:** Tratamento centralizado de erros com ```@ControllerAdvice```
- **DTOs:** Objeto de Transferência de Dados

---

## Arquitetura

O projeto segue a arquitetura de camadas para garantir a separação de responsabilidades:

- **Controller:** Exposição dos endpoints e tratamento de requisições HTTP.
- **Service:** Camada de regras de negócio e validações.
- **Repository:** Interface de comunicação com o banco de dados via JPA.
- **Entity:** Representação do modelo de dados.

---

## Funcionalidades Principais
#### Autores
- Cadastro de autores
- Listagem com contagem dinâmica de livros
- Atualização de dados
- Exclusão Segura: Impede a exclusão de autores com livros vinculados, a menos que uma confirmação seja enviada via parâmetro ```(?confirm=true)```

### Livros
- Cadastro de livros vinculados a um autor existente
- Busca por título (filtro parcial)
- Busca de todos os livros de um autor específico
- Atualização de título e troca de autor com validação de consistência

## Endpoints da API

A API permite as seguintes operações:

### Autores  

| Método HTTP | Endpoint                            | Descrição                                 |
|------------|--------------------------------------|-------------------------------------------|
| POST       | `/autores`                           | Cria um novo autor                        |
| GET        | `/autores/{id}`                      | Busca autor por ID                        |
| PUT        | `/autores/{id}`                      | Atualiza um autor                         |
| DELETE     | `/autores/{id}?confirm=true`         | Remove autor e seus livros                |

### Livros  

| Método HTTP | Endpoint                            | Descrição                                             |
|------------|--------------------------------------|-------------------------------------------------------|
| POST       | `/livros`                            | Cria um novo livro                                    |
| GET        | `/livros`                            | Lista todos ou filtra por título ```(?titulo=...)```  |
| GET        | `/livros/{id}`                       | Busca livro por ID                                    |
| GET        | `/livros/autor/{id}`                 | Busca livro por autor específico                      |
| PUT        | `/autores/{id}`                      | Atualiza um livro                                     |
| DELETE     | `/autores/{id}`                      | Remove um livro específico                            |

#### Exemplo de Requisição (JSON):

Criação de um Livro:
```json
{
  "titulo": "Dom Casmurro",
  "autorId": 1
}
```
Resposta de Erro Padronizada (404 Not Found):

```json
{
  "status": 404,
  "mensagem": "Autor com o ID 1 não encontrado.",
  "timestamp": 1706184000000
}
```

---

## Como executar o projeto

1. Clone o repositório:
```
git clone https://github.com/seu-usuario/biblioteca-api.git
```
2. Importe o projeto na sua IDE favorita (IntelliJ, VS Code, Eclipse).
3. Certifique-se de ter o Java 17+ instalado.
4. Execute a classe principal ```BibliotecaApplication```.
A API estará disponível em ```http://localhost:8080```.

---

## Autor

Desenvolvido por **Paulo Cesar**  
📧 [PauloCesarCoder@gmail.com](mailto:PauloCesarCoder@gmail.com)  
🌐 [GitHub](https://github.com/paulo-czr)  
💼 [Meu LinkedIn](https://www.linkedin.com/in/paulo-czr)
