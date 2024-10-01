package com.emazon.api_shopping_cart.domain.model.stock;

import com.emazon.api_shopping_cart.domain.util.ConstantsDomain;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ArticleSaveTest {

    @Test
    void shouldCreateArticleWhenNameAndDescriptionAreValidConstructor() {
        ArticleResponse article = new ArticleResponse(ConstantsDomain.NUMBER_1, ConstantsDomain.FIELD_NAME
                , ConstantsDomain.FIELD_ARTICLE_DESCRIPTION
                , ConstantsDomain.NUMBER_1, ConstantsDomain.PRICE, null,null);

        assertNotNull(article);
        assertEquals(ConstantsDomain.NUMBER_1, article.getId());
        assertEquals(ConstantsDomain.FIELD_NAME, article.getName());
        assertEquals(ConstantsDomain.FIELD_ARTICLE_DESCRIPTION, article.getDescription());
        assertEquals(ConstantsDomain.NUMBER_1, article.getQuantity());
        assertEquals(ConstantsDomain.PRICE, article.getPrice());
    }

    @Test
    void shouldCreateArticleWhenNameAndDescriptionAreValidSet() {
        ArticleResponse article = new ArticleResponse();
        BrandArticleResponse brandArticleResponse = new BrandArticleResponse();
        List<CategoryResponseList> categoryResponseLists = new ArrayList<>();

        article.setId(ConstantsDomain.NUMBER_1);
        article.setName(ConstantsDomain.FIELD_NAME);
        article.setDescription( ConstantsDomain.FIELD_ARTICLE_DESCRIPTION);
        article.setQuantity(ConstantsDomain.NUMBER_1);
        article.setPrice(ConstantsDomain.PRICE);
        article.setBrand(brandArticleResponse);
        article.setCategories(categoryResponseLists);


        assertNotNull(article);
        assertEquals(ConstantsDomain.NUMBER_1, article.getId());
        assertEquals(ConstantsDomain.FIELD_NAME, article.getName());
        assertEquals(ConstantsDomain.FIELD_ARTICLE_DESCRIPTION, article.getDescription());
        assertEquals(ConstantsDomain.NUMBER_1, article.getQuantity());
        assertEquals(ConstantsDomain.PRICE, article.getPrice());
        assertEquals(ConstantsDomain.PRICE, article.getPrice());
        assertEquals(brandArticleResponse, article.getBrand());
        assertEquals(categoryResponseLists, article.getCategories());
    }
}
