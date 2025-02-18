package com.ecommerce.product_service.controller;

import com.ecommerce.product_service.Exception.ProductNotFoundException;
import com.ecommerce.product_service.dto.reponse.ProductResponse;
import com.ecommerce.product_service.dto.request.ProductRequest;
import com.ecommerce.product_service.dto.request.UpdateProductRequest;
import com.ecommerce.product_service.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IService productService;
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable String id) throws ProductNotFoundException {
        return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    };

    @GetMapping("/")
    public ResponseEntity<List<ProductResponse>> getProducts() {
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    };
    @PostMapping("/add")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest){
        return new ResponseEntity<>(productService.createProduct(productRequest), HttpStatus.CREATED);
    };

    @PutMapping("/update")
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody UpdateProductRequest productRequest)  throws ProductNotFoundException {
        return new ResponseEntity<>(productService.updateProduct(productRequest), HttpStatus.OK);
    };

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id) throws ProductNotFoundException {
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    };

}
