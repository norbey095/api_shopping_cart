package com.emazon.api_shopping_cart.application.handler;

import com.emazon.api_shopping_cart.application.dto.CartSaveRequestDto;
import com.emazon.api_shopping_cart.application.dto.ResponseSuccess;

public interface ICartHandler {

    ResponseSuccess saveArticle(CartSaveRequestDto cartSaveRequestDto);

    ResponseSuccess deleteArticle(Integer idArticle);
}
