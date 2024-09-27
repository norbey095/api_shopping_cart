package com.emazon.api_shopping_cart.domain.exception;

public class TheArticleNotExistException extends RuntimeException {
    public TheArticleNotExistException(String messages) {
        super(messages);
    }
}
