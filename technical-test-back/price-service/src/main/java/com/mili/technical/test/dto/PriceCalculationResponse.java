package com.mili.technical.test.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class PriceCalculationResponse {
    private BigDecimal basePrice;
    private BigDecimal discountedPrice;
    private BigDecimal shippingCost;
    private BigDecimal totalPrice;
    private boolean onSale;
}
