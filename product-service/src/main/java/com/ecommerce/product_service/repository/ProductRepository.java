package com.ecommerce.product_service.repository;

import com.ecommerce.product_service.Exception.ProductNotFoundException;
import com.ecommerce.product_service.dto.reponse.ProductResponse;
import com.ecommerce.product_service.dto.request.ProductRequest;
import com.ecommerce.product_service.dto.request.UpdateProductRequest;
import com.ecommerce.product_service.model.Product;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class ProductRepository implements IRepository {
    private final Map<String, Product> repository = new HashMap<>();

    @Override
    public List<ProductResponse> findAllProducts() {
        Collection<Product> products =  repository.values();
        products.forEach(this::buildProductResponse);
        return null;
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
            throw new ProductNotFoundException("Product not found");
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
        if (!repository.containsKey(id)) {
            throw new ProductNotFoundException("Product not found");
        }
        repository.remove(id);
        return "Product deleted successfully";
    }
}
