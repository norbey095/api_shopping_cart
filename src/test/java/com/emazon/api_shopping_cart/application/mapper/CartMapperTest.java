package com.emazon.api_shopping_cart.application.mapper;

import com.emazon.api_shopping_cart.application.dto.CartSaveRequestDto;
import com.emazon.api_shopping_cart.application.util.ConstantsApplication;
import com.emazon.api_shopping_cart.domain.model.CartSave;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CartMapperTest {

    private final CartMapper cartMapper = Mappers.getMapper(CartMapper.class);

    @Test
    void testUserRequestDtoToUserSave() {
        CartSaveRequestDto cartSaveRequestDto = new CartSaveRequestDto(ConstantsApplication.ID_ARTICLE
                ,ConstantsApplication.QUANTITY);

        CartSave usertestSave = cartMapper.cartSaveRequestDtoToCartSave(cartSaveRequestDto);

        assertNotNull(usertestSave);
        assertEquals(cartSaveRequestDto.getIdArticle(),usertestSave.getIdArticle());
        assertEquals(cartSaveRequestDto.getQuantity(),usertestSave.getQuantity());
    }
}