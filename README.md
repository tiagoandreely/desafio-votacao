<p>
<h1>Votação</h1>
</p>

- [ Sobre ](#sobre)
- [ Funcionalidades ](#funcionalidades)
- [ Tecnologias Utilizadas ](#tecnologias-utilizadas)
- [ Inicializar ](#inicializar)
- [ Versionamento da API](#versionamento-da-api)
- [ Documentação da API ](#documentação-da-api)

## Sobre
Projeto com finalidade de implementar um sistema para votação de assembléias.

## Funcionalidades
 - Cadastrar e listar associados.
 - Cadastrar e listar pautas.
 - Cadastrar e listar sessões.
 - Realizar voto em sessões abertas.
 - Listar o resultado e contagem de votos de uma pauta.

## Tecnologias utilizadas

| Framework      | Springboot |
|----------------|------------|
| Banco de dados | Mysql      |
| Documentação   | Swagger    |
| Container| Docker     |

## Inicializar
Com o docker instalado, executar o comando abaixo na raiz do projeto, para inicializar o container com o banco de dados MySQL:
`````
docker-compose up -d
`````

## Versionamento da API:
Utilizado versionamento da API por URL por ser uma das formas mais utilizados.

Dessa forma, o visual da URL fica mais limpo, facilitando a identificação da versão da API.

## Documentação da API
Realizada a documentação da API utilizando Swagger, estando a mesma diposnível no link abaixo, após inicialização da API:

http://localhost:8080/swagger-ui/index.html#/