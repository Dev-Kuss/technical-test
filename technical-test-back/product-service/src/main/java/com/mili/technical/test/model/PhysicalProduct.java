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
@DiscriminatorValue("PHYSICAL")
@JsonTypeName("PHYSICAL")
public class PhysicalProduct extends Product {
    
    @Column(name = "weight")
    private Double weight;
    
    @Override
    public BigDecimal calculateTotal() {
        BigDecimal basePrice = calculateDiscount();
        BigDecimal shippingCost = calculateShipping();
        return basePrice.add(shippingCost);
    }
    
    public BigDecimal calculateShipping() {
        return BigDecimal.valueOf(weight * 10.0);
    }
}
