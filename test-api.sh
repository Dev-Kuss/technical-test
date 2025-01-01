#!/bin/bash

echo "Testing Product Service API..."

# Create a physical product
echo "Creating physical product..."
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Notebook",
    "price": 1000.00,
    "onSale": true,
    "weight": 2.5,
    "productType": "PHYSICAL"
  }'
echo -e "\n"

# Create a digital product
echo "Creating digital product..."
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "E-book",
    "price": 50.00,
    "onSale": true,
    "sizeMB": 15.0,
    "productType": "DIGITAL"
  }'
echo -e "\n"

# List all products
echo "Listing all products..."
curl -X GET http://localhost:8080/api/products
echo -e "\n"

# Get most expensive product
echo "Getting most expensive product..."
curl -X GET http://localhost:8080/api/products/most-expensive
echo -e "\n"

# Get average price
echo "Getting average price..."
curl -X GET http://localhost:8080/api/products/average-price
echo -e "\n"

echo "Testing Price Service API..."

# Calculate price for physical product
echo "Calculating price for physical product..."
curl -X POST http://localhost:8081/api/prices/calculate \
  -H "Content-Type: application/json" \
  -d '{
    "basePrice": 1000.00,
    "onSale": true,
    "productType": "PHYSICAL",
    "weight": 2.5
  }'
echo -e "\n"

# Calculate price for digital product
echo "Calculating price for digital product..."
curl -X POST http://localhost:8081/api/prices/calculate \
  -H "Content-Type: application/json" \
  -d '{
    "basePrice": 50.00,
    "onSale": true,
    "productType": "DIGITAL",
    "sizeMB": 15.0
  }'
echo -e "\n"
