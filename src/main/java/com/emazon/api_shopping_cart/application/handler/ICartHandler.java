package com.emazon.api_shopping_cart.application.handler;

import com.emazon.api_shopping_cart.application.dto.cart.CartRequestDto;
import com.emazon.api_shopping_cart.application.dto.cart.ResponseSuccess;
import com.emazon.api_shopping_cart.application.dto.cartdetail.ArticleCartRequestDto;
import com.emazon.api_shopping_cart.application.dto.cartdetail.CartDetailResponseDto;

public interface ICartHandler {

    ResponseSuccess saveArticle(CartRequestDto cartRequestDto);

    ResponseSuccess deleteArticle(Integer idArticle);

    CartDetailResponseDto getCart(ArticleCartRequestDto articleCartRequestDto);

    ResponseSuccess buyCart();
}
