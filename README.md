# API Cadastro de Países - Backend

Este projeto é a API RESTful desenvolvida em Spring Boot para o teste técnico de cadastro de países, com controle de autenticação próprio e persistência em banco de dados em memória.

## Tecnologias Utilizadas
* **Java:** 21
* **Framework:** Spring Boot (4.0.7)
* **Banco de Dados:** H2 Database (em memória)
* **Segurança:** BCrypt (para hash de senhas)
* **Documentação:** SpringDoc OpenAPI (Swagger)

## Como executar o projeto

1. Certifique-se de ter o **Java 21** e o **Maven** instalados na sua máquina.
2. Clone este repositório.
3. Abra o terminal na raiz do projeto e rode o comando: `mvn spring-boot:run`
4. A aplicação iniciará na porta 8080.

## Documentação e Acessos

* **Swagger UI (Documentação da API):** http://localhost:8080/swagger-ui/index.html
* **Painel do Banco H2:** http://localhost:8080/h2-console
  * **JDBC URL:** jdbc:h2:mem:paisdb
  * **Usuário:** sa
  * **Senha:** (deixar em branco)

## Dados Iniciais de Teste (Carga Inicial)

Ao rodar a aplicação, os seguintes usuários já estarão disponíveis no banco para teste:

* **Administrador (Permite todas as ações):**
  * Login: admin
  * Senha: suporte

* **Convidado (Apenas leitura):**
  * Login: convidado
  * Senha: manager
