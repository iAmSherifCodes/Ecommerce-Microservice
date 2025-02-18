package com.ecommerce.product_service.Exception;

public class ProductNotFoundException extends EcommerceBaseException{
    public ProductNotFoundException(String message) {
        super(message);
    }
}
