package com.mili.technical.test.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DigitalProduct extends Product {
    private Double sizeMB;
}