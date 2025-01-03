package com.mili.technical.test.service;

import com.mili.technical.test.model.Product;
import com.mili.technical.test.model.PhysicalProduct;
import com.mili.technical.test.model.DigitalProduct;
import com.mili.technical.test.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository productRepository;
    
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
        return productRepository.save(product);
    }
    
    @Transactional
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = getProductById(id);
        
        // If product types are different, delete old instance and create a new one
        if ((existingProduct instanceof PhysicalProduct && !(updatedProduct instanceof PhysicalProduct)) ||
            (existingProduct instanceof DigitalProduct && !(updatedProduct instanceof DigitalProduct))) {
            
            // Delete the existing product first and flush to ensure deletion is committed
            productRepository.delete(existingProduct);
            productRepository.flush();
            
            // Create new instance of the correct type without an ID
            Product newProduct;
            if (updatedProduct instanceof PhysicalProduct) {
                newProduct = new PhysicalProduct();
                ((PhysicalProduct) newProduct).setWeight(((PhysicalProduct) updatedProduct).getWeight());
            } else {
                newProduct = new DigitalProduct();
                ((DigitalProduct) newProduct).setSizeMB(((DigitalProduct) updatedProduct).getSizeMB());
            }
            
            // Copy common properties
            newProduct.setName(updatedProduct.getName());
            newProduct.setPrice(updatedProduct.getPrice());
            newProduct.setOnSale(updatedProduct.isOnSale());
            
            // Save and return the new instance
            return productRepository.save(newProduct);
        }
        
        // If product types are the same, delete existing instance and create a new one
        productRepository.delete(existingProduct);
        productRepository.flush();
        
        // Create new instance of the same type without an ID
        Product newProduct;
        if (existingProduct instanceof PhysicalProduct) {
            newProduct = new PhysicalProduct();
            ((PhysicalProduct) newProduct).setWeight(((PhysicalProduct) updatedProduct).getWeight());
        } else {
            newProduct = new DigitalProduct();
            ((DigitalProduct) newProduct).setSizeMB(((DigitalProduct) updatedProduct).getSizeMB());
        }
        
        // Copy all properties from the updated product
        newProduct.setName(updatedProduct.getName());
        newProduct.setPrice(updatedProduct.getPrice());
        newProduct.setOnSale(updatedProduct.isOnSale());
        
        // Save and return the new instance
        return productRepository.save(newProduct);
    }
    
    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
