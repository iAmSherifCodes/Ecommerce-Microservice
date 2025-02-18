package com.ecommerce.order_service.repository;

import com.ecommerce.order_service.Exception.OrderNotFoundException;
import com.ecommerce.order_service.dto.reponse.OrderResponse;
import com.ecommerce.order_service.dto.request.OrderRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IRepository {
    List<OrderResponse> findAllOrders();
    OrderResponse findByOrderId(String id) throws OrderNotFoundException;
    OrderResponse addOrder(OrderRequest orderRequest);
}
