package com.ecommerce.order_service.repository;

import com.ecommerce.order_service.Exception.OrderNotFoundException;
import com.ecommerce.order_service.dto.reponse.OrderResponse;
import com.ecommerce.order_service.dto.request.OrderRequest;
import com.ecommerce.order_service.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository @Slf4j
public class OrderRepository implements IRepository {
    private final Map<String, Order> repository = new HashMap<>();

    @Override
    public List<OrderResponse> findAllOrders() {
        return buildListOfOrderResponse(List.copyOf(repository.values()));
    }

    private List<OrderResponse> buildListOfOrderResponse(Collection<Order> products) {
        return products.stream()
                .map(product -> OrderResponse.builder()
                        .id(product.getId())
                        .productId(product.getProductId())
                        .quantity(product.getQuantity())
                        .status(product.getStatus())
                        .totalPrice(product.getTotalPrice())
                        .build()
                )
                .collect(Collectors.toList());
    }


    private OrderResponse buildOrderResponse(Order order) {
       return  OrderResponse.builder().
                id(order.getId())
               .productId(order.getProductId())
                .totalPrice(order.getTotalPrice())
               .quantity(order.getQuantity())
                .status(order.getStatus()).build();
    }

    @Override
    public OrderResponse addOrder(OrderRequest orderRequest) {
        String id = UUID.randomUUID().toString();
        log.info("Adding new order {}", orderRequest.toString());
        log.info(" ID  :: {}",id);
        Order newOrder = Order.builder().
                 id(id)
                .productId(orderRequest.getProductId())
                .totalPrice(orderRequest.getTotalPrice())
                .quantity(orderRequest.getQuantity())
                .status(orderRequest.getStatus()).build();
        log.info(" New Order :: {}",newOrder.toString());
       repository.put(id, newOrder);
        return buildOrderResponse(newOrder);
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
