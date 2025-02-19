package com.ecommerce.product_service;

import com.ecommerce.product_service.Exception.ProductNotFoundException;
import com.ecommerce.product_service.dto.reponse.ProductResponse;
import com.ecommerce.product_service.dto.request.ProductRequest;
import com.ecommerce.product_service.dto.request.UpdateProductRequest;
import com.ecommerce.product_service.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductRepositoryTest {
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
    }

    @Test
    void testAddProductSuccess() {
        ProductRequest request = new ProductRequest("Laptop", BigDecimal.valueOf(10000), "Gaming Laptop");
        ProductResponse response = productRepository.addProduct(request);

        assertNotNull(response);
        assertEquals("Laptop", response.getName());
        assertEquals(BigDecimal.valueOf(10000), response.getPrice());
        assertEquals("Gaming Laptop", response.getDescription());
    }

    @Test
    void testFindAllProductsSuccess() {
        productRepository.addProduct(new ProductRequest("Phone", BigDecimal.valueOf(500), "Smartphone"));
        productRepository.addProduct(new ProductRequest("Tablet", BigDecimal.valueOf(700), "Android Tablet"));

        List<ProductResponse> products = productRepository.findAllProducts();
        assertEquals(2, products.size());
    }

    @Test
    void testFindByProductIdSuccess() throws ProductNotFoundException {
        ProductResponse addedProduct = productRepository.addProduct(new ProductRequest("Monitor", BigDecimal.valueOf(300), "HD Monitor"));
        ProductResponse foundProduct = productRepository.findByProductId(addedProduct.getId());

        assertNotNull(foundProduct);
        assertEquals("Monitor", foundProduct.getName());
    }

    @Test
    void testFindByProductIdFailure() {
        Exception exception = assertThrows(ProductNotFoundException.class, () -> {
            productRepository.findByProductId("invalid-id");
        });
        assertTrue(exception.getMessage().contains("Product with ID"));
    }

    @Test
    void testUpdateProductSuccess() throws ProductNotFoundException {
        ProductResponse addedProduct = productRepository.addProduct(new ProductRequest("Keyboard", BigDecimal.valueOf(50), "Mechanical Keyboard"));
        UpdateProductRequest updateRequest = new UpdateProductRequest(addedProduct.getId(), "Keyboard Pro", BigDecimal.valueOf(70), "Wireless Keyboard");

        ProductResponse updatedProduct = productRepository.updateProduct(updateRequest);
        assertEquals("Keyboard Pro", updatedProduct.getName());
        assertEquals(BigDecimal.valueOf(70), updatedProduct.getPrice());
    }

    @Test
    void testUpdateProductFailure() {
        UpdateProductRequest updateRequest = new UpdateProductRequest("invalid-id", "Mouse", BigDecimal.valueOf(40), "Wireless Mouse");

        Exception exception = assertThrows(ProductNotFoundException.class, () -> {
            productRepository.updateProduct(updateRequest);
        });
        assertTrue(exception.getMessage().contains("Product with ID"));
    }

    @Test
    void testDeleteProductSuccess() throws ProductNotFoundException {
        ProductResponse addedProduct = productRepository.addProduct(new ProductRequest("Speaker", BigDecimal.valueOf(170), "Bluetooth Speaker"));
        String response = productRepository.deleteProduct(addedProduct.getId());

        assertEquals("Product deleted successfully", response);
    }

    @Test
    void testDeleteProductFailure() {
        Exception exception = assertThrows(ProductNotFoundException.class, () -> {
            productRepository.deleteProduct("invalid-id");
        });
        assertTrue(exception.getMessage().contains("Product with ID"));
    }
}
