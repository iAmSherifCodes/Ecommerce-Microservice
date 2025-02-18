package com.ecommerce.product_service.service;

import com.ecommerce.product_service.Exception.ProductNotFoundException;
import com.ecommerce.product_service.dto.reponse.ProductResponse;
import com.ecommerce.product_service.dto.request.ProductRequest;
import com.ecommerce.product_service.dto.request.UpdateProductRequest;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IService {
    ProductResponse getProduct(String id) throws ProductNotFoundException;
    ProductResponse createProduct(ProductRequest productRequest);
    List<ProductResponse> getProducts();
    String deleteProduct(String id) throws ProductNotFoundException;
    ProductResponse updateProduct(UpdateProductRequest productRequest) throws ProductNotFoundException;

}
