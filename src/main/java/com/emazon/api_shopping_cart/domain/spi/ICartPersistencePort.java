package com.emazon.api_shopping_cart.domain.spi;

import com.emazon.api_shopping_cart.domain.model.CartSave;

import java.time.LocalDate;
import java.util.List;

public interface ICartPersistencePort {

    void saveCart(CartSave cartSave);

    CartSave findCartByUserNameAndArticleId(Integer articleID,String userName);

    List<Integer> findCartByUserName(String userName);

    LocalDate getNextDate();
}
