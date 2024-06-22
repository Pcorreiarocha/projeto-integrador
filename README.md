# Dependências do Projeto

Instalar banco de dados postgres

https://www.postgresql.org/download/

As credenciais do banco está configurada no arquivo application.properties

O banco é criado através da prop: spring.jpa.hibernate.ddl-auto=update

'Hibernate atualiza o esquema do banco de dados para se alinhar com as entidades mapeadas. Isso significa que ele tentará alterar a estrutura do banco de dados existente para acomodar quaisquer mudanças nas entidades, como a adição de novas colunas ou tabelas.'
