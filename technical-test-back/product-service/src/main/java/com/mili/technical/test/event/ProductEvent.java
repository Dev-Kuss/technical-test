package com.mili.technical.test.event;

import com.mili.technical.test.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEvent {
    private String eventType;
    private Product product;
    private LocalDateTime timestamp;

    public ProductEvent(String eventType, Product product) {
        this.eventType = eventType;
        this.product = product;
        this.timestamp = LocalDateTime.now();
    }

    public static ProductEvent of(String eventType, Product product) {
        return new ProductEvent(eventType, product, LocalDateTime.now());
    }
}
