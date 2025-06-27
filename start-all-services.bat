@echo off
echo ===== Iniciando todos os microservicos =====

echo.
echo [1/6] Criando rede docker "spring-network" (se nao existir)...
docker network create spring-network 2>nul || echo Rede ja existe.

echo.
echo [2/6] Iniciando infraestrutura compartilhada...
cd shared-infrastructure
docker compose up -d
cd ..

echo.
echo [3/6] Iniciando Eureka Server...
cd eureka-server
docker compose up -d
cd ..

echo.
echo [4/6] Iniciando API Gateway...
cd api-gateway
docker compose up -d
cd ..

echo.
echo [5/6] Iniciando User Service...
cd user-service
docker compose up -d
cd ..

echo.
echo [6/6] Iniciando Email Service...
cd email-service
docker compose up -d
cd ..

echo.
echo ===== Todos os servicos foram iniciados! =====
echo.
echo Verificando os containers em execucao:
docker ps

echo.
echo Para parar todos os servicos, execute o script stop-all-services.bat
