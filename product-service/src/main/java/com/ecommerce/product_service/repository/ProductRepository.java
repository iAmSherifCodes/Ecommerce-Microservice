package com.ecommerce.product_service.repository;


import com.ecommerce.product_service.Exception.ProductNotFoundException;
import com.ecommerce.product_service.dto.reponse.ProductResponse;
import com.ecommerce.product_service.dto.request.ProductRequest;
import com.ecommerce.product_service.dto.request.UpdateProductRequest;
import com.ecommerce.product_service.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository @RequiredArgsConstructor
public class ProductRepository implements IRepository {
    private final Map<String, Product> repository = new HashMap<>();

    @Override
    public List<ProductResponse> findAllProducts() {
        return buildListOfProductResponse(List.copyOf(repository.values()));
    }

    private List<ProductResponse> buildListOfProductResponse(Collection<Product> products) {
        return products.stream()
                .map(product -> ProductResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .description(product.getDescription())
                        .build()
                )
                .collect(Collectors.toList());
    }




    private ProductResponse buildProductResponse(Product product) {
       return ProductResponse.builder().
                name(product.getName())
               .id(product.getId())
                .price(product.getPrice())
                .description(product.getDescription())
                .id(product.getId()).build();
    }

    @Override
    public ProductResponse findByProductId(String id) throws ProductNotFoundException {
        Product vProduct = validateProductId(id);
        return buildProductResponse(vProduct);
    }

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        String id = UUID.randomUUID().toString();
        Product newProduct = Product.builder()
                .name(productRequest.getName())
                .id(id)
                .price(productRequest.getPrice())
                .description(productRequest.getDescription())
                .build();
        repository.put(id, newProduct);
        return buildProductResponse(newProduct);
    }

    private Product validateProductId(String id) throws ProductNotFoundException {
        if (!repository.containsKey(id)) {
            throw new ProductNotFoundException("Product with ID " + id + "is Not Found");
        }
        return repository.get(id);
    }
    @Override
    public ProductResponse updateProduct(UpdateProductRequest productRequest) throws ProductNotFoundException {
        Product vProduct = validateProductId(productRequest.getId());
        String id = vProduct.getId();
        Product savedProduct = repository.compute(id, (k, product) -> vProduct);
        return buildProductResponse(savedProduct);
    }

    @Override
    public String deleteProduct(String id) throws ProductNotFoundException {
        validateProductId(id);
        repository.remove(id);
        return "Product deleted successfully";
    }
}
