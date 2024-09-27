package com.emazon.api_shopping_cart.application.dto.cartdetail;

import lombok.Data;

@Data
public class CartDetailDto {

        private String name;
        private Integer quantity;
        private double unitPrice;
        private double subPrice;
        private String message;

}
