package com.mili.technical.test.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;

@Data
@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "product_type", include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
    @JsonSubTypes.Type(value = PhysicalProduct.class, name = "PHYSICAL"),
    @JsonSubTypes.Type(value = DigitalProduct.class, name = "DIGITAL")
})
public abstract class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private BigDecimal price;
    
    @Column(name = "on_sale")
    private boolean onSale;
    
    public BigDecimal calculateDiscount() {
        if (onSale) {
            return price.multiply(BigDecimal.valueOf(0.9)); // 10% discount
        }
        return price;
    }
    
    public abstract BigDecimal calculateTotal();
}
