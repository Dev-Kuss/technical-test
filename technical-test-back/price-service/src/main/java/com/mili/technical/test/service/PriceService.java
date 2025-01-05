package com.mili.technical.test.service;

import com.mili.technical.test.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class PriceService {
    
    private final RestTemplate restTemplate;
    private final String productServiceUrl;

    public PriceService(RestTemplate restTemplate, @Value("${product.service.url}") String productServiceUrl) {
        this.restTemplate = restTemplate;
        this.productServiceUrl = productServiceUrl;
    }

    public Product getMostExpensiveProduct() {
        try {
            Product[] products = restTemplate.getForObject(productServiceUrl + "/api/products", Product[].class);
            if (products == null || products.length == 0) {
                throw new RuntimeException("No products found");
            }
            return Arrays.stream(products)
                    .filter(p -> p.getPrice() != null)
                    .max(Comparator.comparing(Product::getPrice))
                    .orElseThrow(() -> new RuntimeException("No products with valid prices found"));
        } catch (Exception e) {
            throw new RuntimeException("Error fetching most expensive product: " + e.getMessage(), e);
        }
    }

    public BigDecimal getAveragePrice() {
        try {
            Product[] products = restTemplate.getForObject(productServiceUrl + "/api/products", Product[].class);
            if (products == null || products.length == 0) {
                throw new RuntimeException("No products found");
            }

            List<Product> productList = Arrays.stream(products)
                    .filter(p -> p.getPrice() != null)
                    .toList();
            
            if (productList.isEmpty()) {
                throw new RuntimeException("No products with valid prices found");
            }

            BigDecimal totalPrice = productList.stream()
                    .map(Product::getPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            return totalPrice.divide(BigDecimal.valueOf(productList.size()), 2, RoundingMode.HALF_UP);
        } catch (Exception e) {
            throw new RuntimeException("Error calculating average price: " + e.getMessage(), e);
        }
    }
}
