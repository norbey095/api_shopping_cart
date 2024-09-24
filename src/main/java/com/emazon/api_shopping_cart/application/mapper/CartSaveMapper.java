package com.emazon.api_shopping_cart.application.mapper;


import com.emazon.api_shopping_cart.application.dto.CartSaveRequestDto;
import com.emazon.api_shopping_cart.application.util.ConstantsDto;
import com.emazon.api_shopping_cart.domain.model.CartSave;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartSaveMapper {

    @Mapping(target = ConstantsDto.ID, ignore = true)
    @Mapping(target = ConstantsDto.EMAIL, ignore = true)
    @Mapping(target = ConstantsDto.CREATE_DATE, ignore = true)
    @Mapping(target = ConstantsDto.UPDATE_DATE, ignore = true)
    CartSave cartSaveRequestDtoToCartSave(CartSaveRequestDto cartSaveRequestDto);
}

