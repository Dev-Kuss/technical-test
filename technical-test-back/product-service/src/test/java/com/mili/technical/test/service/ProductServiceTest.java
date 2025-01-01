package com.mili.technical.test.service;

import com.mili.technical.test.model.Product;
import com.mili.technical.test.model.PhysicalProduct;
import com.mili.technical.test.model.DigitalProduct;
import com.mili.technical.test.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService(productRepository);
    }

    @Test
    void getAllProducts_ShouldReturnAllProducts() {
        // Arrange
        PhysicalProduct physicalProduct = new PhysicalProduct();
        physicalProduct.setId(1L);
        physicalProduct.setName("Physical Product");
        physicalProduct.setPrice(new BigDecimal("10.00"));
        physicalProduct.setWeight(1.5);

        DigitalProduct digitalProduct = new DigitalProduct();
        digitalProduct.setId(2L);
        digitalProduct.setName("Digital Product");
        digitalProduct.setPrice(new BigDecimal("20.00"));
        digitalProduct.setSizeMB(500.0);

        List<Product> expectedProducts = Arrays.asList(physicalProduct, digitalProduct);
        when(productRepository.findAll()).thenReturn(expectedProducts);

        // Act
        List<Product> actualProducts = productService.getAllProducts();

        // Assert
        assertEquals(expectedProducts.size(), actualProducts.size());
        assertEquals(expectedProducts, actualProducts);
    }

    @Test
    void getProductById_WithExistingId_ShouldReturnProduct() {
        // Arrange
        PhysicalProduct expectedProduct = new PhysicalProduct();
        expectedProduct.setId(1L);
        expectedProduct.setName("Test Product");
        expectedProduct.setPrice(new BigDecimal("10.00"));
        expectedProduct.setWeight(2.0);

        when(productRepository.findById(1L)).thenReturn(Optional.of(expectedProduct));

        // Act
        Product actualProduct = productService.getProductById(1L);

        // Assert
        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    void getProductById_WithNonExistingId_ShouldThrowException() {
        // Arrange
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> productService.getProductById(1L));
    }

    @Test
    void createProduct_ShouldSaveAndReturnProduct() {
        // Arrange
        PhysicalProduct productToCreate = new PhysicalProduct();
        productToCreate.setName("New Product");
        productToCreate.setPrice(new BigDecimal("15.00"));
        productToCreate.setWeight(1.0);

        PhysicalProduct savedProduct = new PhysicalProduct();
        savedProduct.setId(1L);
        savedProduct.setName("New Product");
        savedProduct.setPrice(new BigDecimal("15.00"));
        savedProduct.setWeight(1.0);

        when(productRepository.save(productToCreate)).thenReturn(savedProduct);

        // Act
        Product createdProduct = productService.createProduct(productToCreate);

        // Assert
        assertEquals(savedProduct, createdProduct);
        verify(productRepository).save(productToCreate);
    }

    @Test
    void updateProduct_WithExistingId_ShouldUpdateAndReturnProduct() {
        // Arrange
        Long productId = 1L;
        PhysicalProduct existingProduct = new PhysicalProduct();
        existingProduct.setId(productId);
        existingProduct.setName("Old Name");
        existingProduct.setOnSale(false);
        existingProduct.setWeight(1.0);

        PhysicalProduct updatedProduct = new PhysicalProduct();
        updatedProduct.setId(productId);
        updatedProduct.setName("New Name");
        updatedProduct.setOnSale(true);
        updatedProduct.setWeight(1.0);

        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

        // Act
        Product result = productService.updateProduct(productId, updatedProduct);

        // Assert
        assertEquals("New Name", result.getName());
        assertTrue(result.isOnSale());
        verify(productRepository).save(any(Product.class));
    }

    @Test
    void deleteProduct_ShouldCallRepositoryDelete() {
        // Arrange
        Long productId = 1L;

        // Act
        productService.deleteProduct(productId);

        // Assert
        verify(productRepository).deleteById(productId);
    }
}
