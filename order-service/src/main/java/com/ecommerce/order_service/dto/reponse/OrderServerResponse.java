package com.ecommerce.order_service.dto.reponse;

import com.ecommerce.order_service.model.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter @Setter
@Builder
public class OrderServerResponse {
    private String id;
    private String productId;
    private Integer quantity;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
}
