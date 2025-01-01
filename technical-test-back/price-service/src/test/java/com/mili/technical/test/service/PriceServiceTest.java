package com.mili.technical.test.service;

import com.mili.technical.test.model.Product;
import com.mili.technical.test.model.PhysicalProduct;
import com.mili.technical.test.model.DigitalProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @Mock
    private RestTemplate restTemplate;

    private PriceService priceService;

    @BeforeEach
    void setUp() {
        priceService = new PriceService(restTemplate);
    }

    @Test
    void getMostExpensiveProduct_ShouldReturnProductWithHighestPrice() {
        // Arrange
        PhysicalProduct product1 = new PhysicalProduct();
        product1.setId(1L);
        product1.setName("Product 1");
        product1.setPrice(new BigDecimal("10.00"));
        product1.setWeight(1.0);

        DigitalProduct product2 = new DigitalProduct();
        product2.setId(2L);
        product2.setName("Product 2");
        product2.setPrice(new BigDecimal("20.00"));
        product2.setSizeMB(100.0);

        PhysicalProduct product3 = new PhysicalProduct();
        product3.setId(3L);
        product3.setName("Product 3");
        product3.setPrice(new BigDecimal("15.00"));
        product3.setWeight(2.0);

        Product[] products = {product1, product2, product3};
        when(restTemplate.getForObject(anyString(), eq(Product[].class))).thenReturn(products);

        // Act
        Product mostExpensive = priceService.getMostExpensiveProduct();

        // Assert
        assertEquals(product2, mostExpensive);
        assertEquals(new BigDecimal("20.00"), mostExpensive.getPrice());
    }

    @Test
    void getAveragePrice_ShouldCalculateCorrectAverage() {
        // Arrange
        PhysicalProduct product1 = new PhysicalProduct();
        product1.setId(1L);
        product1.setName("Product 1");
        product1.setPrice(new BigDecimal("10.00"));
        product1.setWeight(1.0);

        DigitalProduct product2 = new DigitalProduct();
        product2.setId(2L);
        product2.setName("Product 2");
        product2.setPrice(new BigDecimal("20.00"));
        product2.setSizeMB(100.0);

        PhysicalProduct product3 = new PhysicalProduct();
        product3.setId(3L);
        product3.setName("Product 3");
        product3.setPrice(new BigDecimal("15.00"));
        product3.setWeight(2.0);

        Product[] products = {product1, product2, product3};
        when(restTemplate.getForObject(anyString(), eq(Product[].class))).thenReturn(products);

        // Act
        BigDecimal average = priceService.getAveragePrice();

        // Assert
        assertEquals(new BigDecimal("15.00"), average);
    }

    @Test
    void getMostExpensiveProduct_WithNoProducts_ShouldThrowException() {
        // Arrange
        when(restTemplate.getForObject(anyString(), eq(Product[].class))).thenReturn(new Product[0]);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> priceService.getMostExpensiveProduct());
    }
}
