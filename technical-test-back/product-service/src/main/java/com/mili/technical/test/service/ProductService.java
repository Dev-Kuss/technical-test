package com.mili.technical.test.service;

import com.mili.technical.test.model.Product;
import com.mili.technical.test.model.PhysicalProduct;
import com.mili.technical.test.model.DigitalProduct;
import com.mili.technical.test.repository.ProductRepository;
import com.mili.technical.test.event.ProductEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.springframework.beans.BeanUtils;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository productRepository;
    private final ProductEventPublisher eventPublisher;
    
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }
    
    @Transactional
    public Product createProduct(Product product) {
        // Ensure the product has no ID set
        product.setId(null);
        Product savedProduct = productRepository.save(product);
        eventPublisher.publishProductCreated(savedProduct);
        return savedProduct;
    }
    
    @Transactional
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        
        // Delete the existing product
        productRepository.delete(existingProduct);
        
        // Create a new product with the updated values
        Product newProduct = updatedProduct.getClass().equals(PhysicalProduct.class) ?
            new PhysicalProduct() : new DigitalProduct();
        
        // Copy properties from updated product
        BeanUtils.copyProperties(updatedProduct, newProduct);
        newProduct.setId(null); // Let Hibernate generate a new ID
        
        // Save the new product
        Product savedProduct = productRepository.save(newProduct);
        eventPublisher.publishProductUpdated(savedProduct);
        return savedProduct;
    }
    
    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        productRepository.delete(product);
        eventPublisher.publishProductDeleted(product);
    }
}
