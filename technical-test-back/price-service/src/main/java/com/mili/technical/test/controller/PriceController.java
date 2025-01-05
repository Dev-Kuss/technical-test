package com.mili.technical.test.controller;

import com.mili.technical.test.dto.PriceCalculationRequest;
import com.mili.technical.test.dto.PriceCalculationResponse;
import com.mili.technical.test.model.Product;
import com.mili.technical.test.service.PriceCalculationService;
import com.mili.technical.test.service.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/prices")
@RequiredArgsConstructor
@Tag(name = "Price Controller", description = "APIs for price calculations and management")
@CrossOrigin(origins = "*")
public class PriceController {
    
    private final PriceCalculationService priceCalculationService;
    private final PriceService priceService;
    
    @PostMapping("/calculate")
    @Operation(summary = "Calculate total price including discounts and shipping")
    public ResponseEntity<PriceCalculationResponse> calculatePrice(
            @Valid @RequestBody PriceCalculationRequest request) {
        return ResponseEntity.ok(priceCalculationService.calculatePrice(request));
    }
    
    @GetMapping(value = "/most-expensive", produces = "application/json")
    @Operation(summary = "Get the most expensive product")
    public ResponseEntity<Product> getMostExpensiveProduct() {
        try {
            return ResponseEntity.ok(priceService.getMostExpensiveProduct());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping(value = "/average", produces = "application/json")
    @Operation(summary = "Get the average price of all products")
    public ResponseEntity<Map<String, Double>> getAveragePrice() {
        try {
            BigDecimal avgPrice = priceService.getAveragePrice();
            return ResponseEntity.ok(Map.of("average", avgPrice.doubleValue()));
        } catch (RuntimeException e) {
            return ResponseEntity.ok(Map.of("average", 0.0));
        }
    }
}
