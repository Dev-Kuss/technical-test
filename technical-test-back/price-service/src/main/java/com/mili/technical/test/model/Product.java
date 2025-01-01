package com.mili.technical.test.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import java.math.BigDecimal;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = PhysicalProduct.class, name = "PhysicalProduct"),
    @JsonSubTypes.Type(value = DigitalProduct.class, name = "DigitalProduct")
})
public abstract class Product {
    private Long id;
    private String name;
    private BigDecimal price;
    private boolean onSale;
    
    public BigDecimal getPrice() {
        return price;
    }
}
