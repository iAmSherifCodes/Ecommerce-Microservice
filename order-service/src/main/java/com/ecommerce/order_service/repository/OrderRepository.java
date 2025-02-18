package com.ecommerce.order_service.repository;

import com.ecommerce.order_service.Exception.OrderNotFoundException;
import com.ecommerce.order_service.dto.reponse.OrderResponse;
import com.ecommerce.order_service.dto.request.OrderRequest;
import com.ecommerce.order_service.model.Order;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OrderRepository implements IRepository {
    private final Map<String, Order> repository = new HashMap<>();

    @Override
    public List<OrderResponse> findAllOrders() {
        Collection<Order> orders =  repository.values();
        orders.forEach(this::buildOrderResponse);
        return null;
    }


    private OrderResponse buildOrderResponse(Order order) {
       return  OrderResponse.builder().
                id(order.getId()).productId(order.getProductId())
                .totalPrice(order.getTotalPrice())
               .quantity(order.getQuantity())
                .status(order.getStatus()).build();
    }

    @Override
    public OrderResponse addOrder(OrderRequest orderRequest) {
        String id = UUID.randomUUID().toString();
        Order newOrder = Order.builder().
                 id(id)
                .productId(orderRequest.getProductId())
                .totalPrice(orderRequest.getTotalPrice())
                .quantity(orderRequest.getQuantity())
                .status(orderRequest.getStatus()).build();
       Order savedOrder =  repository.put(id, newOrder);
        return buildOrderResponse(savedOrder);
    }

    private Order validateOrderId(String id) throws OrderNotFoundException {
        if (!repository.containsKey(id)) {
            throw new OrderNotFoundException("Order not found");
        }
        return repository.get(id);
    }

    @Override
    public OrderResponse findByOrderId(String id) throws OrderNotFoundException {
        Order vOrder = validateOrderId(id);
        return buildOrderResponse(vOrder);
    }

}
