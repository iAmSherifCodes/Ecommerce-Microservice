package com.ecommerce.order_service.service;

import com.ecommerce.order_service.Exception.OrderNotFoundException;
import com.ecommerce.order_service.config.FeignProductServiceClient;
import com.ecommerce.order_service.dto.reponse.OrderResponse;
import com.ecommerce.order_service.dto.reponse.OrderServerResponse;
import com.ecommerce.order_service.dto.reponse.ProductDTO;
import com.ecommerce.order_service.dto.reponse.ProductResponse;
import com.ecommerce.order_service.dto.request.OrderRequest;
import com.ecommerce.order_service.repository.IRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IService {

    private final FeignProductServiceClient feignProductServiceClient;
    private final IRepository orderRepository;

    @Override
    public OrderResponse getOrder(String id) throws OrderNotFoundException {
        return orderRepository.findByOrderId(id);
    }

    @Override
    public OrderServerResponse createOrder(OrderRequest orderRequest){
        ResponseEntity<ProductResponse> product = feignProductServiceClient.getProduct(orderRequest.getProductId());
       OrderResponse orderResponse =  orderRepository.addOrder(orderRequest);
       ProductDTO productDTO = buildProductDTO(product);
        return buildServeResponse(orderResponse, productDTO);
    }

    private ProductDTO buildProductDTO(ResponseEntity<ProductResponse> product){
         ProductDTO newProductDto = new ProductDTO();
         newProductDto.setId(product.getBody().getId());
         newProductDto.setName(product.getBody().getName());
         newProductDto.setPrice(product.getBody().getPrice());
         newProductDto.setDescription(product.getBody().getDescription());

         return newProductDto;
    }

    private OrderServerResponse buildServeResponse(OrderResponse orderResponse, ProductDTO product){
        return OrderServerResponse.builder()
                .id(orderResponse.getId())
                .status(orderResponse.getStatus())
                .totalPrice(orderResponse.getTotalPrice())
                .quantity(orderResponse.getQuantity())
                .productId(product.getId())
                .productName(product.getName())
                .productDescription(product.getDescription())
                .productPrice(product.getPrice())
                .build();
    }

    @Override
    public List<OrderServerResponse> getOrders() {
        List<OrderResponse> orders = orderRepository.findAllOrders();
        List<OrderServerResponse> newServerResponses = new ArrayList<>(List.of());
        orders.forEach(order -> {
            ResponseEntity<ProductResponse> product = feignProductServiceClient.getProduct(order.getProductId());
            ProductDTO productDTO = buildProductDTO(product);
            newServerResponses.add(buildServeResponse(order, productDTO));
        });
        return newServerResponses;
    }

}
