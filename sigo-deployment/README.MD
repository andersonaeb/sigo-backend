
Acessar cada um dos microserviços e rodar o mvn package. Irá criar as imagens docker.

Para o docker-compose local:

Criar os seguintes bancos de dados:

CREATE DATABASE sigo_partner;
GRANT ALL PRIVILEGES ON sigo_partner.* TO 'sigo';

CREATE DATABASE sigo_standard;
GRANT ALL PRIVILEGES ON sigo_standard.* TO 'sigo';

Após, usar o docker-compose up -d para subir os serviços.