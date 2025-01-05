package com.mili.technical.test.service;

import com.mili.technical.test.dto.PriceCalculationRequest;
import com.mili.technical.test.dto.PriceCalculationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class PriceCalculationServiceTest {

    private PriceCalculationService priceCalculationService;
    private static final int DECIMAL_PLACES = 2;

    @BeforeEach
    void setUp() {
        priceCalculationService = new PriceCalculationService();
    }

    @Test
    void calculatePrice_WithPhysicalProductOnSale_ShouldApplyDiscountAndShipping() {
        // Arrange
        PriceCalculationRequest request = PriceCalculationRequest.builder()
                .basePrice(new BigDecimal("100.00"))
                .onSale(true)
                .productType("PHYSICAL")
                .weight(2.0)
                .build();

        // Act
        PriceCalculationResponse response = priceCalculationService.calculatePrice(request);

        // Assert
        assertEquals(new BigDecimal("100.00"), response.getBasePrice().setScale(DECIMAL_PLACES, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal("90.00"), response.getDiscountedPrice().setScale(DECIMAL_PLACES, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal("20.00"), response.getShippingCost().setScale(DECIMAL_PLACES, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal("110.00"), response.getTotalPrice().setScale(DECIMAL_PLACES, RoundingMode.HALF_UP));
        assertTrue(response.isOnSale());
    }

    @Test
    void calculatePrice_WithDigitalProductNotOnSale_ShouldNotApplyDiscountOrShipping() {
        // Arrange
        PriceCalculationRequest request = PriceCalculationRequest.builder()
                .basePrice(new BigDecimal("100.00"))
                .onSale(false)
                .productType("DIGITAL")
                .weight(null)
                .build();

        // Act
        PriceCalculationResponse response = priceCalculationService.calculatePrice(request);

        // Assert
        assertEquals(new BigDecimal("100.00"), response.getBasePrice().setScale(DECIMAL_PLACES, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal("100.00"), response.getDiscountedPrice().setScale(DECIMAL_PLACES, RoundingMode.HALF_UP));
        assertEquals(BigDecimal.ZERO.setScale(DECIMAL_PLACES, RoundingMode.HALF_UP), response.getShippingCost().setScale(DECIMAL_PLACES, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal("100.00"), response.getTotalPrice().setScale(DECIMAL_PLACES, RoundingMode.HALF_UP));
        assertFalse(response.isOnSale());
    }
}
