package com.mili.technical.test.controller;

import com.mili.technical.test.dto.PriceCalculationRequest;
import com.mili.technical.test.dto.PriceCalculationResponse;
import com.mili.technical.test.service.PriceCalculationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prices")
@RequiredArgsConstructor
@Tag(name = "Price Controller", description = "APIs for price calculations")
@CrossOrigin(origins = "*")
public class PriceController {
    
    private final PriceCalculationService priceCalculationService;
    
    @PostMapping("/calculate")
    @Operation(summary = "Calculate total price including discounts and shipping")
    public ResponseEntity<PriceCalculationResponse> calculatePrice(
            @Valid @RequestBody PriceCalculationRequest request) {
        return ResponseEntity.ok(priceCalculationService.calculatePrice(request));
    }
}
