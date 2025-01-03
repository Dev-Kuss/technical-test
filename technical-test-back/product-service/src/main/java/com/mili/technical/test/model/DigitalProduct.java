package com.mili.technical.test.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("DIGITAL")
@JsonTypeName("DIGITAL")
public class DigitalProduct extends Product {
    
    @Column(name = "size_mb")
    private Double sizeMB;
    
    @Override
    public BigDecimal calculateTotal() {
        BigDecimal basePrice = calculateDiscount();
        // Add storage cost based on size
        BigDecimal storageCost = BigDecimal.valueOf(sizeMB * 0.1); // $0.10 per MB
        return basePrice.add(storageCost);
    }
}
