package com.ecommerce.product_service;

import com.ecommerce.product_service.Exception.ProductNotFoundException;
import com.ecommerce.product_service.dto.reponse.ProductResponse;
import com.ecommerce.product_service.dto.request.ProductRequest;
import com.ecommerce.product_service.dto.request.UpdateProductRequest;
import com.ecommerce.product_service.repository.IRepository;
import com.ecommerce.product_service.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private IRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private final String productId = "123";
    private final ProductResponse mockProduct = ProductResponse.builder().id("123").name("Laptop").price(BigDecimal.valueOf(1200)).build();
    private final ProductRequest newProductRequest = new ProductRequest("Laptop", BigDecimal.valueOf(2000), "Windows and Linux Laptops");
    private final UpdateProductRequest updateRequest = new UpdateProductRequest("123", "Gaming Laptop", BigDecimal.valueOf(1500), "Heavy Duty Laptop");

    @Test
    void testGetProduct_Success() throws ProductNotFoundException {
        when(productRepository.findByProductId(productId)).thenReturn(mockProduct);
        ProductResponse product = productService.getProduct(productId);
        assertNotNull(product);
        assertEquals("123", product.getId());
    }

    @Test
    void testGetProduct_NotFound() throws ProductNotFoundException {
        when(productRepository.findByProductId(productId)).thenThrow(new ProductNotFoundException("Product not found"));
        assertThrows(ProductNotFoundException.class, () -> productService.getProduct(productId));
    }

    @Test
    void testCreateProduct_Success() {
        when(productRepository.addProduct(newProductRequest)).thenReturn(mockProduct);
        ProductResponse createdProduct = productService.createProduct(newProductRequest);
        assertNotNull(createdProduct);
        assertEquals("123", createdProduct.getId());
    }

    @Test
    void testGetProducts_Success() {
        when(productRepository.findAllProducts()).thenReturn(List.of(mockProduct));
        List<ProductResponse> products = productService.getProducts();
        assertFalse(products.isEmpty());
    }

    @Test
    void testGetProducts_EmptyList() {
        when(productRepository.findAllProducts()).thenReturn(Collections.emptyList());
        List<ProductResponse> products = productService.getProducts();
        assertTrue(products.isEmpty());
    }

    @Test
    void testDeleteProduct_Success() throws ProductNotFoundException {
        when(productRepository.deleteProduct(productId)).thenReturn("Product deleted");
        String result = productService.deleteProduct(productId);
        assertEquals("Product deleted", result);
    }

    @Test
    void testDeleteProduct_NotFound() throws ProductNotFoundException {
        when(productRepository.deleteProduct(productId)).thenThrow(new ProductNotFoundException("Product not found"));
        assertThrows(ProductNotFoundException.class, () -> productService.deleteProduct(productId));
    }

    @Test
    void testUpdateProduct_Success() throws ProductNotFoundException {
        ProductResponse updatedProduct =  ProductResponse.builder().id("123").name("Laptop").price(BigDecimal.valueOf(1500)).build();;
        when(productRepository.updateProduct(updateRequest)).thenReturn(updatedProduct);
        ProductResponse result = productService.updateProduct(updateRequest);
        assertNotNull(result);
        assertEquals("Laptop", result.getName());
    }

    @Test
    void testUpdateProduct_NotFound() throws ProductNotFoundException {
        when(productRepository.updateProduct(updateRequest)).thenThrow(new ProductNotFoundException("Product not found"));
        assertThrows(ProductNotFoundException.class, () -> productService.updateProduct(updateRequest));
    }
}
