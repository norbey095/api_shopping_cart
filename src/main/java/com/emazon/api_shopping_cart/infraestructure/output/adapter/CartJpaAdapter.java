package com.emazon.api_shopping_cart.infraestructure.output.adapter;


import com.emazon.api_shopping_cart.domain.model.CartSave;
import com.emazon.api_shopping_cart.domain.spi.ICartPersistencePort;
import com.emazon.api_shopping_cart.infraestructure.output.entity.CartEntity;
import com.emazon.api_shopping_cart.infraestructure.output.mapper.ICartEntityMapper;
import com.emazon.api_shopping_cart.infraestructure.output.repository.ICartRepository;
import com.emazon.api_shopping_cart.infraestructure.util.ConstantsOutput;
import org.springframework.beans.factory.annotation.Value;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class CartJpaAdapter implements ICartPersistencePort {

    private final ICartRepository cartRepository;
    private final ICartEntityMapper cartEntityMapper;

    @Value(ConstantsOutput.RANGE_DAYS)
    private Integer day;

    @Override
    public void saveCart(CartSave cartSave) {
        cartRepository.save(cartEntityMapper.cartToCartSaveEntity(cartSave));
    }

    @Override
    public CartSave findCartByUserNameAndArticleId(Integer articleId,String userName) {
        CartEntity cartEntity = cartRepository.findCartByUserNameAndArticleId(articleId,userName)
                .orElse(null);
        return cartEntityMapper.cartEntityToCartSave(cartEntity);
    }

    @Override
    public List<Integer> findCartByUserName(String userName) {
        return cartRepository.findCartByUserName(userName);
    }

    @Override
    public LocalDate getNextDate() {
        return LocalDate.now().plusDays(day);
    }

    @Override
    public void deleteCart(Integer idArticle,String userName) {
        cartRepository.deleteByIdArticle(idArticle, userName);
    }

    @Override
    public void updateProductDateByEmail(String userName, LocalDateTime updateDate) {
        cartRepository.updateProductDateByEmail(userName, LocalDateTime.now());
    }
}
