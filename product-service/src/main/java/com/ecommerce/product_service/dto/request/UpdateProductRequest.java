package com.ecommerce.product_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter @AllArgsConstructor
public class UpdateProductRequest {
    private String id;
    private String name;
    private BigDecimal price;
    private String description;
}
