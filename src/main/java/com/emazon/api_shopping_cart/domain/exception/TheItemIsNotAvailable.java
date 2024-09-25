package com.emazon.api_shopping_cart.domain.exception;

public class TheItemIsNotAvailable extends RuntimeException {
    public TheItemIsNotAvailable(String messages) {
        super(messages);
    }
}
