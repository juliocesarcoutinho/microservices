#!/bin/bash

echo "===== Parando todos os microserviços ====="
echo

echo "[1/6] Parando Email Service..."
cd email-service || exit
docker compose down
cd ..

echo
echo "[2/6] Parando User Service..."
cd user-service || exit
docker compose down
cd ..

echo
echo "[3/6] Parando API Gateway..."
cd api-gateway || exit
docker compose down
cd ..

echo
echo "[4/6] Parando Eureka Server..."
cd eureka-server || exit
docker compose down
cd ..

echo
echo "[5/6] Parando infraestrutura compartilhada..."
cd shared-infrastructure || exit
docker compose down
cd ..

echo
echo "[6/6] Verificando se algum container ainda está rodando..."
docker ps

echo
echo "===== Todos os serviços foram parados! ====="
