package com.emazon.api_shopping_cart.application.dto.cartdetail;

import lombok.Data;

import java.util.List;

@Data
public class CartDetailResponseDto {

        private List<CartDetailDto> cartDetail;
        private double totalPrice;

}
