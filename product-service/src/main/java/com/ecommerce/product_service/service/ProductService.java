package com.ecommerce.product_service.service;

import com.ecommerce.product_service.Exception.ProductNotFoundException;
import com.ecommerce.product_service.dto.reponse.ProductResponse;
import com.ecommerce.product_service.dto.request.ProductRequest;
import com.ecommerce.product_service.dto.request.UpdateProductRequest;
import com.ecommerce.product_service.repository.IRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IService{

    private final IRepository productRepository;

    public ProductService(IRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse getProduct(String id) throws ProductNotFoundException {
        return productRepository.findByProductId(id);
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        return productRepository.addProduct(productRequest);
    }

    @Override
    public List<ProductResponse> getProducts() {
        return productRepository.findAllProducts();
    }

    @Override
    public String deleteProduct(String id) throws ProductNotFoundException {
        return productRepository.deleteProduct(id);
    }

    @Override
    public ProductResponse updateProduct(UpdateProductRequest productRequest) throws ProductNotFoundException {
        return productRepository.updateProduct(productRequest);
    }
}
