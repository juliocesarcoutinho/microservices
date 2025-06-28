#!/bin/bash

echo "===== Iniciando todos os microserviços ====="
echo

echo "[1/6] Criando rede docker 'spring-network' (se não existir)..."
docker network create spring-network 2>/dev/null || echo "Rede já existe."

echo
echo "[2/6] Iniciando infraestrutura compartilhada..."
cd shared-infrastructure || exit
docker compose up -d
cd ..

echo
echo "[3/6] Iniciando Eureka Server..."
cd eureka-server || exit
docker compose up -d
cd ..

echo
echo "[4/6] Iniciando API Gateway..."
cd api-gateway || exit
docker compose up -d
cd ..

echo
echo "[5/6] Iniciando User Service..."
cd user-service || exit
docker compose up -d
cd ..

echo
echo "[6/6] Iniciando Email Service..."
cd email-service || exit
docker compose up -d
cd ..

echo
echo "===== Todos os serviços foram iniciados! ====="
echo
echo "Verificando os containers em execução:"
docker ps

echo
echo "Para parar todos os serviços, execute o script stop-all-services.sh"
