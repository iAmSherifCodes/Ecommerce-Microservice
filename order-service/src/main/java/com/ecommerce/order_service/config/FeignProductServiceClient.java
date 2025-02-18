package com.ecommerce.order_service.config;

import com.ecommerce.order_service.dto.reponse.ProductDTO;
import com.ecommerce.order_service.dto.reponse.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "product-service", url = "/product")
public interface FeignProductServiceClient {
        @GetMapping( "/{id}")
        ResponseEntity<ProductResponse> getProduct(@PathVariable String id);

}
