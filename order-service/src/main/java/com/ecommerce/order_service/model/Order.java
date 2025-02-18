package com.ecommerce.order_service.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter @Builder
public class Order {
    private String id;
    private String productId;
    private Integer quantity;
    private BigDecimal totalPrice;
    private OrderStatus status;
}
