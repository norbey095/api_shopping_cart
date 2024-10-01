package com.emazon.api_shopping_cart.domain.model.report;

import com.emazon.api_shopping_cart.domain.util.ConstantsDomain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ArticleDetailTest {

    @Test
    void shouldCreateArticleDetail() {
        ArticleDetail articleDetail = new ArticleDetail(ConstantsDomain.NUMBER_1, ConstantsDomain.FIELD_NAME,
                ConstantsDomain.PRICE,ConstantsDomain.NUMBER_1);

        assertNotNull(articleDetail);
        assertEquals(ConstantsDomain.NUMBER_1, articleDetail.getArticleId());
        assertEquals(ConstantsDomain.FIELD_NAME, articleDetail.getName());
        assertEquals(ConstantsDomain.PRICE, articleDetail.getUnitPrice());
        assertEquals(ConstantsDomain.NUMBER_1, articleDetail.getQuantity());
    }
}
