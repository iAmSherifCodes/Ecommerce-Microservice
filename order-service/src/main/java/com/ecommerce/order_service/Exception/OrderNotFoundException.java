package com.ecommerce.order_service.Exception;


public class OrderNotFoundException extends EcommerceBaseException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
