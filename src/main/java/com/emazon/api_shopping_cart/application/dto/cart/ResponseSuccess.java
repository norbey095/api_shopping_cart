package com.emazon.api_shopping_cart.application.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseSuccess {

    private String messages;
    private String status;
}
