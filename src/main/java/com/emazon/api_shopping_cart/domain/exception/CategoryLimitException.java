package com.emazon.api_shopping_cart.domain.exception;

public class CategoryLimitException extends RuntimeException {
    public CategoryLimitException(String messages) {
        super(messages);
    }
}
