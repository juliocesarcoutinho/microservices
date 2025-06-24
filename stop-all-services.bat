@echo off
echo ===== Parando todos os microservicos =====

echo.
echo [1/6] Parando Email Service...
cd email-service
docker-compose down
cd ..

echo.
echo [2/6] Parando User Service...
cd user-service
docker-compose down
cd ..

echo.
echo [3/6] Parando API Gateway...
cd api-gateway
docker-compose down
cd ..

echo.
echo [4/6] Parando Eureka Server...
cd eureka-server
docker-compose down
cd ..

echo.
echo [5/6] Parando infraestrutura compartilhada...
cd shared-infrastructure
docker-compose down
cd ..

echo.
echo [6/6] Verificando se algum container ainda está rodando...
docker ps

echo.
echo ===== Todos os servicos foram parados! =====
