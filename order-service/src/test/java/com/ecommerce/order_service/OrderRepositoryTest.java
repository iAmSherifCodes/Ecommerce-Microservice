package com.ecommerce.order_service;

import com.ecommerce.order_service.Exception.OrderNotFoundException;
import com.ecommerce.order_service.dto.reponse.OrderResponse;
import com.ecommerce.order_service.dto.request.OrderRequest;
import com.ecommerce.order_service.model.OrderStatus;
import com.ecommerce.order_service.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class OrderRepositoryTest {
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        orderRepository = new OrderRepository();
    }

    @Test
    void testAddOrder_Success() {
        OrderRequest request = new OrderRequest(UUID.randomUUID().toString(), 10, BigDecimal.valueOf(100.0), OrderStatus.PENDING);
        OrderResponse response = orderRepository.addOrder(request);
        assertNotNull(response);
        assertEquals(request.getProductId(), response.getProductId());
        assertEquals(request.getQuantity(), response.getQuantity());
        assertEquals(request.getTotalPrice(), response.getTotalPrice());
        assertEquals(request.getStatus(), response.getStatus());
    }

    @Test
    void testFindAllOrders_Success() {
        OrderRequest request1 = new OrderRequest(UUID.randomUUID().toString(), 5, BigDecimal.valueOf(50), OrderStatus.DELIVERED);
        OrderRequest request2 = new OrderRequest(UUID.randomUUID().toString(), 3, BigDecimal.valueOf(30), OrderStatus.DELIVERED);

        orderRepository.addOrder(request1);
        orderRepository.addOrder(request2);

        List<OrderResponse> orders = orderRepository.findAllOrders();
        assertEquals(2, orders.size());
    }

    @Test
    void testFindByOrderId_Success() throws OrderNotFoundException {
        OrderRequest request = new OrderRequest(UUID.randomUUID().toString(), 8, BigDecimal.valueOf(80.0), OrderStatus.DELIVERED);
        OrderResponse addedOrder = orderRepository.addOrder(request);

        OrderResponse foundOrder = orderRepository.findByOrderId(addedOrder.getId());
        assertNotNull(foundOrder);
        assertEquals(addedOrder.getId(), foundOrder.getId());
    }

    @Test
    void testFindByOrderId_Failure() {
        String invalidId = UUID.randomUUID().toString();
        Executable executable = () -> orderRepository.findByOrderId(invalidId);
        assertThrows(OrderNotFoundException.class, executable);
    }
}
