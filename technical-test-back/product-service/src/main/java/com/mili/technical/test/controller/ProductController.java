package com.mili.technical.test.controller;

import com.mili.technical.test.model.Product;
import com.mili.technical.test.repository.ProductRepository;
import com.mili.technical.test.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Slf4j
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name = "Product Controller", description = "APIs for product management")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ProductController {
    
    private final ProductService productService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @GetMapping
    @Operation(summary = "Get all products")
    public ResponseEntity<Product[]> getAllProducts() {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return ResponseEntity.ok(productService.getAllProducts().toArray(new Product[0]));
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }
    
    @PostMapping
    @Operation(summary = "Create a new product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        log.info("Creating product: {}", product);
        return ResponseEntity.ok(productService.createProduct(product));
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing product")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        try {
            log.info("Updating product with id {}: {}", id, objectMapper.writeValueAsString(product));
            return ResponseEntity.ok(productService.updateProduct(id, product));
        } catch (Exception e) {
            log.error("Error updating product: {}", e.getMessage(), e);
            throw new RuntimeException("Error updating product", e);
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
