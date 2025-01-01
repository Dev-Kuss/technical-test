package com.mili.technical.test.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PhysicalProduct extends Product {
    private Double weight;
}
