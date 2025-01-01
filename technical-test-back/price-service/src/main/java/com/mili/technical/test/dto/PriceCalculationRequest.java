package com.mili.technical.test.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Builder;
import java.math.BigDecimal;

@Data
@Builder
public class PriceCalculationRequest {
    @NotNull
    @Positive
    private BigDecimal basePrice;
    
    private boolean onSale;
    
    private String productType; // PHYSICAL or DIGITAL
    
    private Double weight; // for physical products
    
    private Double sizeMB; // for digital products
}
