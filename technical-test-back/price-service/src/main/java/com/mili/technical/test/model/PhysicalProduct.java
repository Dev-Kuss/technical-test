package com.mili.technical.test.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonTypeName("PHYSICAL")
public class PhysicalProduct extends Product {
    private Double weight;
    
    @Override
    public BigDecimal calculateTotal() {
        BigDecimal basePrice = calculateDiscount();
        // Add shipping cost based on weight
        BigDecimal shippingCost = BigDecimal.valueOf(weight * 10); // $10 per kg
        return basePrice.add(shippingCost);
    }
}
