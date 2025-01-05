package com.mili.technical.test.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import java.math.BigDecimal;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "product_type", include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
    @JsonSubTypes.Type(value = PhysicalProduct.class, name = "PHYSICAL"),
    @JsonSubTypes.Type(value = DigitalProduct.class, name = "DIGITAL")
})
public abstract class Product {
    private Long id;
    private String name;
    private BigDecimal price;
    private boolean onSale;
    
    public BigDecimal calculateDiscount() {
        if (onSale) {
            return price.multiply(BigDecimal.valueOf(0.9)); // 10% discount
        }
        return price;
    }
    
    public abstract BigDecimal calculateTotal();
}
