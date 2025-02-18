package com.ecommerce.order_service.dto.request;

import com.ecommerce.order_service.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter @Setter
public class OrderRequest {
    private String productId;
    private Integer quantity;
    private BigDecimal totalPrice;
    private OrderStatus status;
}
