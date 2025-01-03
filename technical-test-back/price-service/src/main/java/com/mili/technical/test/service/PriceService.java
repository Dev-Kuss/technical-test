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
@RequiredArgsConstructor
public class PriceService {
    
    private final RestTemplate restTemplate;
    @Value("${product.service.url}")
    private final String productServiceUrl;

    public Product getMostExpensiveProduct() {
        Product[] products = restTemplate.getForObject(productServiceUrl + "/api/products", Product[].class);
        return Arrays.stream(products)
                .max(Comparator.comparing(Product::getPrice))
                .orElseThrow(() -> new RuntimeException("No products found"));
    }

    public BigDecimal getAveragePrice() {
        Product[] products = restTemplate.getForObject(productServiceUrl + "/api/products", Product[].class);
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
