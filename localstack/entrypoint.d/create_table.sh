#!/bin/bash

# Comando para criar a tabela no DynamoDB usando o AWS CLI
awslocal dynamodb create-table \
  --table-name payment \
  --attribute-definitions AttributeName=orderId,AttributeType=S \
  --key-schema AttributeName=orderId,KeyType=HASH \
  --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5

echo "Created payment table"
