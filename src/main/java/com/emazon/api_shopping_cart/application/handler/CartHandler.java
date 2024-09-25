package com.emazon.api_shopping_cart.application.handler;

import com.emazon.api_shopping_cart.application.dto.CartSaveRequestDto;
import com.emazon.api_shopping_cart.application.dto.ResponseSuccess;
import com.emazon.api_shopping_cart.application.mapper.CartMapper;
import com.emazon.api_shopping_cart.application.util.ConstantsDto;
import com.emazon.api_shopping_cart.domain.api.ICartSaveServicePort;
import com.emazon.api_shopping_cart.domain.model.CartSave;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Transactional
public class CartHandler implements ICartHandler {

    private final ICartSaveServicePort cartSaveServicePort;
    private final CartMapper cartSaveMapper;

    @Override
    public ResponseSuccess saveArticle(CartSaveRequestDto cartSaveRequestDto) {
        CartSave cartSave = cartSaveMapper.cartSaveRequestDtoToCartSave(cartSaveRequestDto);
        cartSaveServicePort.cartSave(cartSave);
        return new ResponseSuccess(ConstantsDto.PRODUCT_ADD,HttpStatus.OK.toString());
    }
}
