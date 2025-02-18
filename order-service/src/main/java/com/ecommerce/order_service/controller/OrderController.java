package com.ecommerce.order_service.controller;

import com.ecommerce.order_service.Exception.OrderNotFoundException;
import com.ecommerce.order_service.dto.reponse.OrderResponse;
import com.ecommerce.order_service.dto.reponse.OrderServerResponse;
import com.ecommerce.order_service.dto.request.OrderRequest;
import com.ecommerce.order_service.service.IService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final IService orderService;

    @GetMapping("/")
    public ResponseEntity<List<OrderServerResponse>> getOrders() {
        return new ResponseEntity<>(orderService.getOrders(), HttpStatus.OK);
    };

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable String id) throws OrderNotFoundException {
        return new ResponseEntity<>(orderService.getOrder(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<OrderServerResponse> createOrder(@RequestBody OrderRequest orderRequest){
        return new ResponseEntity<>(orderService.createOrder(orderRequest), HttpStatus.CREATED);
    };

}
