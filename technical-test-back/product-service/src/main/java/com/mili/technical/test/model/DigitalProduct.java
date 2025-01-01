package com.mili.technical.test.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("DIGITAL")
public class DigitalProduct extends Product {
    
    @Column(name = "size_mb")
    private Double sizeMB;
    
    @Override
    public BigDecimal calculateTotal() {
        return calculateDiscount(); // Digital products have no shipping cost
    }
}
