package com.emazon.api_shopping_cart.domain.api;

import com.emazon.api_shopping_cart.domain.model.CartSave;
import com.emazon.api_shopping_cart.domain.model.cartdetail.CartDetailResponse;

public interface ICartServicePort {

    void cartSave(CartSave cartSave);

    void deleteCart(Integer idArticle);

    CartDetailResponse getCart(Integer page, Integer size,boolean descending,String categoryName,String brandName);

    void buyArticle();
}
