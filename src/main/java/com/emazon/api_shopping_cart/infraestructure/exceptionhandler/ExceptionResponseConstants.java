package com.emazon.api_shopping_cart.infraestructure.exceptionhandler;

import lombok.Getter;

@Getter
public enum ExceptionResponseConstants {
    STOCK_CONFLICT ("The article does not exist in the database."),
    SERVICE_NOT_AVAILABLE("The service is not available, please try again later.");

    private final String message;

    ExceptionResponseConstants(String message) {
        this.message = message;
    }

}
