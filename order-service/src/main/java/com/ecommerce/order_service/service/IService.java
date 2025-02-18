package com.ecommerce.order_service.service;

import com.ecommerce.order_service.Exception.OrderNotFoundException;
import com.ecommerce.order_service.dto.reponse.OrderResponse;
import com.ecommerce.order_service.dto.reponse.OrderServerResponse;
import com.ecommerce.order_service.dto.request.OrderRequest;

import java.util.List;


public interface IService {
    OrderResponse getOrder(String id) throws OrderNotFoundException;
    OrderServerResponse createOrder(OrderRequest orderRequest);
    List<OrderServerResponse> getOrders();
}
