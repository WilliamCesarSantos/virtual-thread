#!/bin/bash

# Comando para criar a tabela no DynamoDB usando o AWS CLI
awslocal dynamodb create-table \
  --table-name payment \
  --attribute-definitions AttributeName=orderId,AttributeType=S \
  --key-schema AttributeName=orderId,KeyType=HASH \
  --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5

echo "Created payment table"

awslocal dynamodb put-item \
  --table-name payment \
  --item '{
    "orderId": {"S": "1"},
    "amount": {"N": "42.00"},
    "status": {"S": "APPROVED"},
    "transactionId": {"S": "uuid-1234-5678"}
  }'
echo "Add new payment on table!"
