package com.emazon.api_shopping_cart.domain.exception;

public class theArticleNotExistException extends RuntimeException {
    public theArticleNotExistException(String messages) {
        super(messages);
    }
}
