package com.ecommerce.order_service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.ecommerce.order_service.Exception.OrderNotFoundException;
import com.ecommerce.order_service.config.FeignProductServiceClient;
import com.ecommerce.order_service.dto.reponse.OrderResponse;
import com.ecommerce.order_service.dto.reponse.OrderServerResponse;
import com.ecommerce.order_service.dto.reponse.ProductResponse;
import com.ecommerce.order_service.dto.request.OrderRequest;
import com.ecommerce.order_service.model.OrderStatus;
import com.ecommerce.order_service.repository.IRepository;
import com.ecommerce.order_service.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private FeignProductServiceClient feignProductServiceClient;

    @Mock
    private IRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    private OrderRequest orderRequest;
    private OrderResponse orderResponse;
    private ProductResponse productResponse;
    private String orderId = "123";
    private String productId = "456";

    @BeforeEach
    void setUp() {
        orderRequest = new OrderRequest(productId, 2, BigDecimal.valueOf(200.0), OrderStatus.PENDING);
        orderResponse = new OrderResponse(orderId, productId, 2, BigDecimal.valueOf(200.0), OrderStatus.PENDING);
        productResponse = new ProductResponse(productId, "Laptop", "High-end laptop", BigDecimal.valueOf(100.0));
    }

    @Test
    void testGetOrder_Success() throws OrderNotFoundException {
        when(orderRepository.findByOrderId(orderId)).thenReturn(orderResponse);

        OrderResponse response = orderService.getOrder(orderId);

        assertNotNull(response);
        assertEquals(orderId, response.getId());
        verify(orderRepository, times(1)).findByOrderId(orderId);
    }

    @Test
    void testGetOrder_NotFound() throws OrderNotFoundException {
        when(orderRepository.findByOrderId(orderId)).thenThrow(new OrderNotFoundException("Order not found"));

        assertThrows(OrderNotFoundException.class, () -> orderService.getOrder(orderId));
    }

    @Test
    void testCreateOrder_Success() {
        when(feignProductServiceClient.getProduct(productId)).thenReturn(ResponseEntity.ok(productResponse));
        when(orderRepository.addOrder(orderRequest)).thenReturn(orderResponse);

        OrderServerResponse response = orderService.createOrder(orderRequest);

        assertNotNull(response);
        assertEquals(orderId, response.getId());
        assertEquals(productId, response.getProductId());
        verify(feignProductServiceClient, times(1)).getProduct(productId);
        verify(orderRepository, times(1)).addOrder(orderRequest);
    }

    @Test
    void testCreateOrder_ProductNotFound() {
        when(feignProductServiceClient.getProduct(productId)).thenReturn(ResponseEntity.notFound().build());
        when(orderRepository.addOrder(orderRequest)).thenReturn(orderResponse);

        Exception exception = assertThrows(NullPointerException.class, () -> orderService.createOrder(orderRequest));
        assertNotNull(exception);
    }

    @Test
    void testGetOrders_Success() {
        List<OrderResponse> orders = new ArrayList<>();
        orders.add(orderResponse);

        when(orderRepository.findAllOrders()).thenReturn(orders);
        when(feignProductServiceClient.getProduct(productId)).thenReturn(ResponseEntity.ok(productResponse));

        List<OrderServerResponse> responses = orderService.getOrders();

        assertNotNull(responses);
        assertEquals(1, responses.size());
        verify(orderRepository, times(1)).findAllOrders();
    }
}

