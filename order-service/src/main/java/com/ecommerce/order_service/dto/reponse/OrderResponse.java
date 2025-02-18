package com.ecommerce.order_service.dto.reponse;

import com.ecommerce.order_service.model.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter @Getter @Builder
public class OrderResponse {
    private String id;
    private String productId;
    private Integer quantity;
    private BigDecimal totalPrice;
    private OrderStatus status;
}
