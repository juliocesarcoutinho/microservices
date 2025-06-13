# Serviço Compartilhado de MySQL para Microsserviços

Este diretório contém a configuração do serviço compartilhado de MySQL que será usado por todos os microsserviços.

## Estrutura

- `docker-compose.yml`: Configuração do container Docker para o MySQL compartilhado
- `init/`: Scripts de inicialização para o MySQL
  - `init-databases.sql`: Script para criar bancos de dados e usuários para cada microsserviço

## Como usar

1. Inicie o serviço compartilhado do MySQL antes de iniciar seus microsserviços:

```bash
cd shared-infrastructure
docker-compose up -d
```

2. Configure seus microsserviços para usar o MySQL compartilhado, definindo as seguintes variáveis de ambiente:

```
DB_HOST=mysql-shared
DB_PORT=3306
DB_NAME=<nome_do_banco_específico_do_serviço>
DB_USERNAME=<usuário_específico_do_serviço>
DB_PASSWORD=<senha_específica_do_serviço>
```

3. Adicione novos bancos de dados e usuários no arquivo `init/init-databases.sql` conforme necessário para novos microsserviços.

## Importante

- Todos os microsserviços usarão o mesmo container do MySQL, mas cada um terá seu próprio banco de dados.
- O container do MySQL está configurado para ser acessível na porta 3306.
- A rede `spring-network` deve ser criada previamente usando:

```bash
docker network create spring-network
```

- Para verificar se o MySQL está em execução:

```bash
docker ps
```

- Para verificar os logs do MySQL:

```bash
docker logs mysql-shared
```

- Para se conectar ao MySQL:

```bash
docker exec -it mysql-shared mysql -u root -p
```
