package com.emazon.api_shopping_cart.domain.model.cartdetail;

import com.emazon.api_shopping_cart.domain.util.ConstantsDomain;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ArticleCartRequestTest {

    @Test
    void shouldCreateArticleCartRequestConstructor() {
        ArticleCartRequest article = new ArticleCartRequest(ConstantsDomain.NUMBER_1, ConstantsDomain.NUMBER_0
                ,true
                ,new ArrayList<>(), ConstantsDomain.FIELD_NAME, ConstantsDomain.FIELD_NAME);

        assertNotNull(article);
        assertEquals(ConstantsDomain.NUMBER_1, article.getPage());
        assertEquals(ConstantsDomain.NUMBER_0, article.getSize());
        assertTrue(article.isDescending());
        assertEquals(ConstantsDomain.FIELD_NAME, article.getBrandName());
        assertEquals(ConstantsDomain.FIELD_NAME, article.getCategoryName());
        assertEquals(new ArrayList<>(), article.getArticleIds());
    }

    @Test
    void shouldCreateArticleWhenNameAndDescriptionAreValidSet() {
        ArticleCartRequest article = new ArticleCartRequest();

        article.setPage(ConstantsDomain.NUMBER_1);
        article.setSize(ConstantsDomain.NUMBER_0);
        article.setDescending(true);
        article.setArticleIds(new ArrayList<>());
        article.setBrandName(ConstantsDomain.FIELD_NAME);
        article.setCategoryName(ConstantsDomain.FIELD_NAME);


        assertNotNull(article);
        assertEquals(ConstantsDomain.NUMBER_1, article.getPage());
        assertEquals(ConstantsDomain.NUMBER_0, article.getSize());
        assertTrue(article.isDescending());
        assertEquals(ConstantsDomain.FIELD_NAME, article.getBrandName());
        assertEquals(ConstantsDomain.FIELD_NAME, article.getCategoryName());
    }
}
