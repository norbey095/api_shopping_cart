package com.emazon.api_shopping_cart.domain.api;

import com.emazon.api_shopping_cart.domain.model.CartSave;

public interface ICartServicePort {

    void cartSave(CartSave cartSave);

    void deleteCart(Integer idArticle);
}
