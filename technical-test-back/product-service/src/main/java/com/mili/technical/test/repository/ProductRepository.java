package com.mili.technical.test.repository;

import com.mili.technical.test.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
    @Query("SELECT AVG(p.price) FROM Product p")
    BigDecimal calculateAveragePrice();
}
