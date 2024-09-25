package com.emazon.api_shopping_cart.infraestructure.output.mapper;

import com.emazon.api_shopping_cart.application.dto.stock.ArticleResponseDto;
import com.emazon.api_shopping_cart.application.dto.stock.BrandArticleResponseDto;
import com.emazon.api_shopping_cart.domain.model.CartSave;
import com.emazon.api_shopping_cart.domain.model.stock.ArticleResponse;
import com.emazon.api_shopping_cart.infraestructure.output.entity.CartEntity;
import com.emazon.api_shopping_cart.infraestructure.util.ConstantsInfTest;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class ICartEntityMapperTest {

    private final ICartEntityMapper cartEntityMapper = Mappers.getMapper(ICartEntityMapper.class);


    @Test
    void testCartEntityToCartSave() {
        LocalDate date = LocalDate.now();
        CartEntity cartEntity = new CartEntity();
        cartEntity.setId(ConstantsInfTest.ID);
        cartEntity.setEmail(ConstantsInfTest.EMAIL);
        cartEntity.setIdArticle(ConstantsInfTest.ID_ARTICLE);
        cartEntity.setQuantity(ConstantsInfTest.QUANTITY);
        cartEntity.setCreateDate(date);
        cartEntity.setUpdateDate(date);


        CartSave cartSave = cartEntityMapper.cartEntityToCartSave(cartEntity);


        assertNotNull(cartSave);
        assertEquals(cartSave.getId(), cartEntity.getId());
        assertEquals(cartSave.getEmail(), cartEntity.getEmail());
        assertEquals(cartSave.getIdArticle(), cartEntity.getIdArticle());
        assertEquals(cartSave.getQuantity(), cartEntity.getQuantity());
        assertEquals(cartSave.getCreateDate(), cartEntity.getCreateDate());
        assertEquals(cartSave.getUpdateDate(), cartEntity.getUpdateDate());
    }

    @Test
    void testCartEntityToCartSaveNull() {
        CartSave cartSave = cartEntityMapper.cartEntityToCartSave(null);

        assertNull(cartSave);
    }

    @Test
    void testCartToCartSaveEntity() {
        LocalDate date = LocalDate.now();
        CartSave cartSave = new CartSave();
        cartSave.setId(ConstantsInfTest.ID);
        cartSave.setEmail(ConstantsInfTest.EMAIL);
        cartSave.setIdArticle(ConstantsInfTest.ID_ARTICLE);
        cartSave.setQuantity(ConstantsInfTest.QUANTITY);
        cartSave.setCreateDate(date);
        cartSave.setUpdateDate(date);


        CartEntity cartEntity = cartEntityMapper.cartToCartSaveEntity(cartSave);


        assertNotNull(cartEntity);
        assertEquals(cartSave.getId(), cartEntity.getId());
        assertEquals(cartSave.getEmail(), cartEntity.getEmail());
        assertEquals(cartSave.getIdArticle(), cartEntity.getIdArticle());
        assertEquals(cartSave.getQuantity(), cartEntity.getQuantity());
        assertEquals(cartSave.getCreateDate(), cartEntity.getCreateDate());
        assertEquals(cartSave.getUpdateDate(), cartEntity.getUpdateDate());
    }

    @Test
    void testCartToCartSaveEntityNull() {


        CartEntity cartEntity = cartEntityMapper.cartToCartSaveEntity(null);


        assertNull(cartEntity);
    }

    @Test
    void testArticleResponseDtoToArticleResponse() {
        ArticleResponseDto articleResponseDto = new ArticleResponseDto();
        articleResponseDto.setId(ConstantsInfTest.ID);
        articleResponseDto.setName(ConstantsInfTest.NAME);
        articleResponseDto.setDescription(ConstantsInfTest.DESCRIPTION);
        articleResponseDto.setQuantity(ConstantsInfTest.QUANTITY);
        articleResponseDto.setPrice(ConstantsInfTest.PRICE);
        articleResponseDto.setBrand(new BrandArticleResponseDto());
        articleResponseDto.setCategories(new ArrayList<>());


        ArticleResponse articleResponse = cartEntityMapper.articleResponseDtoToArticleResponse(articleResponseDto);


        assertNotNull(articleResponse);
        assertEquals(articleResponse.getId(), articleResponseDto.getId());
        assertEquals(articleResponse.getName(), articleResponseDto.getName());
        assertEquals(articleResponse.getDescription(), articleResponseDto.getDescription());
        assertEquals(articleResponse.getQuantity(), articleResponseDto.getQuantity());
        assertEquals(articleResponse.getPrice(), articleResponseDto.getPrice());
    }

    @Test
    void testArticleResponseDtoToArticleResponseNull() {
        ArticleResponse articleResponse = cartEntityMapper.articleResponseDtoToArticleResponse(null);

        assertNull(articleResponse);
    }
}