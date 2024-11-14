# Dependências do Projeto

## Banco de dados

https://www.postgresql.org/download/

As credenciais do banco está configurada no arquivo application.properties

O banco é criado através da prop: spring.jpa.hibernate.ddl-auto=update

'Hibernate atualiza o esquema do banco de dados para se alinhar com as entidades mapeadas. Isso significa que ele tentará alterar a estrutura do banco de dados existente para acomodar quaisquer mudanças nas entidades, como a adição de novas colunas ou tabelas.'


## Pré-requisitos

É necessário que você possua instalada a configurada a JDK 17 ou posterior. Você pode baixar daqui:
- [Oracle](https://www.oracle.com/br/java/technologies/downloads/#java17)

Além disso, é indicado possuir instalado e configurado o Maven 3. Você pode baixar daqui:

- [Apache Maven](https://maven.apache.org/download.cgi#)

## Acessar o projeto

Poderá ser utilizado a IDE de preferencia, ou rodar diretamente pelo maven utilizando o comando: mvn spring-boot:run

Projeto utiliza a porta default:8080 -> http://localhost:8080

## Desenvolvedor do Sistema:

João Pedro Lima de Oliveira

