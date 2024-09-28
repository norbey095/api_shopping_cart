package com.emazon.api_shopping_cart.application.mapper;


import com.emazon.api_shopping_cart.application.dto.cart.CartRequestDto;
import com.emazon.api_shopping_cart.application.dto.cartdetail.CartDetailResponseDto;
import com.emazon.api_shopping_cart.application.util.ConstantsDto;
import com.emazon.api_shopping_cart.domain.model.CartSave;
import com.emazon.api_shopping_cart.domain.model.cartdetail.CartDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(target = ConstantsDto.ID, ignore = true)
    @Mapping(target = ConstantsDto.EMAIL, ignore = true)
    @Mapping(target = ConstantsDto.CREATE_DATE, ignore = true)
    @Mapping(target = ConstantsDto.UPDATE_DATE, ignore = true)
    CartSave cartSaveRequestDtoToCartSave(CartRequestDto cartSaveRequestDto);

    CartDetailResponseDto cartDetailResponseToCartDetailResponseDto(CartDetailResponse cartDetailResponse);
}

