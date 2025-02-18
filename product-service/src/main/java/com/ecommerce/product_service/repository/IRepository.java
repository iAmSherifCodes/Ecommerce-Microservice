package com.ecommerce.product_service.repository;

import com.ecommerce.product_service.Exception.ProductNotFoundException;
import com.ecommerce.product_service.dto.reponse.ProductResponse;
import com.ecommerce.product_service.dto.request.ProductRequest;
import com.ecommerce.product_service.dto.request.UpdateProductRequest;
import com.ecommerce.product_service.model.Product;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public interface IRepository {
    List<ProductResponse> findAllProducts();
    ProductResponse findByProductId(String id) throws ProductNotFoundException;
    ProductResponse addProduct(ProductRequest productRequest);
    ProductResponse updateProduct(UpdateProductRequest productRequest) throws ProductNotFoundException;
    String deleteProduct(String id) throws ProductNotFoundException;
}
