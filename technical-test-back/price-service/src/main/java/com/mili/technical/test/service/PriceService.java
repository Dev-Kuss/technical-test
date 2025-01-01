package com.mili.technical.test.service;

import com.mili.technical.test.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceService {
    
    private final RestTemplate restTemplate;
    private static final String PRODUCT_SERVICE_URL = "http://product-service:8080/api/products";

    public Product getMostExpensiveProduct() {
        Product[] products = restTemplate.getForObject(PRODUCT_SERVICE_URL, Product[].class);
        return Arrays.stream(products)
                .max(Comparator.comparing(Product::getPrice))
                .orElseThrow(() -> new RuntimeException("No products found"));
    }

    public BigDecimal getAveragePrice() {
        Product[] products = restTemplate.getForObject(PRODUCT_SERVICE_URL, Product[].class);
        List<Product> productList = Arrays.asList(products);
        
        if (productList.isEmpty()) {
            throw new RuntimeException("No products found");
        }

        BigDecimal totalPrice = productList.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalPrice.divide(BigDecimal.valueOf(productList.size()), 2, RoundingMode.HALF_UP);
    }
}
