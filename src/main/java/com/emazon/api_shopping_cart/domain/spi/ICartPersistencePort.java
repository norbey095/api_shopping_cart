package com.emazon.api_shopping_cart.domain.spi;

import com.emazon.api_shopping_cart.domain.model.CartSave;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ICartPersistencePort {

    void saveCart(CartSave cartSave);

    CartSave findCartByUserNameAndArticleId(Integer articleID,String userName);

    List<Integer> findCartByUserName(String userName);

    LocalDate getNextDate();

    void deleteItemCart(Integer idArticle,String userName);

    void updateProductDateByEmail(String userName, LocalDateTime updateDate);

    List<CartSave> findAllCartByUserName(String userName);

    void deleteCart(String userName);
}
