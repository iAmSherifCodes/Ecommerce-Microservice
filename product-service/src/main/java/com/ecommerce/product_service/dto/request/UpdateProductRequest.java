package com.ecommerce.product_service.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class UpdateProductRequest {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
