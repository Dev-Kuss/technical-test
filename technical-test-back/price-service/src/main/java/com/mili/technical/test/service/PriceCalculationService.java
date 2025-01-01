package com.mili.technical.test.service;

import com.mili.technical.test.dto.PriceCalculationRequest;
import com.mili.technical.test.dto.PriceCalculationResponse;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class PriceCalculationService {
    
    private static final BigDecimal DISCOUNT_PERCENTAGE = new BigDecimal("0.90"); // 10% discount
    private static final BigDecimal SHIPPING_RATE_PER_KG = new BigDecimal("10.0");
    
    public PriceCalculationResponse calculatePrice(PriceCalculationRequest request) {
        BigDecimal basePrice = request.getBasePrice();
        BigDecimal discountedPrice = calculateDiscountedPrice(basePrice, request.isOnSale());
        BigDecimal shippingCost = calculateShippingCost(request);
        BigDecimal totalPrice = discountedPrice.add(shippingCost);
        
        return PriceCalculationResponse.builder()
                .basePrice(basePrice)
                .discountedPrice(discountedPrice)
                .shippingCost(shippingCost)
                .totalPrice(totalPrice)
                .onSale(request.isOnSale())
                .build();
    }
    
    private BigDecimal calculateDiscountedPrice(BigDecimal basePrice, boolean onSale) {
        if (onSale) {
            return basePrice.multiply(DISCOUNT_PERCENTAGE);
        }
        return basePrice;
    }
    
    private BigDecimal calculateShippingCost(PriceCalculationRequest request) {
        if ("PHYSICAL".equals(request.getProductType()) && request.getWeight() != null) {
            return SHIPPING_RATE_PER_KG.multiply(BigDecimal.valueOf(request.getWeight()));
        }
        return BigDecimal.ZERO;
    }
}
