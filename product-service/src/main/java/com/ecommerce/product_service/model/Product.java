package com.ecommerce.product_service.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
//import java.util.UUID;

import java.math.BigDecimal;

@Getter @Setter @Builder
public class Product {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
