package com.emazon.api_shopping_cart.domain.model.transaction;

import com.emazon.api_shopping_cart.domain.util.ConstantsDomain;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TransactionRequestTest {

    @Test
    void shouldCreateBrandWhenNameAndDescriptionAreValid() {
        LocalDateTime buyDate = LocalDateTime.now();
        TransactionRequest transactionRequest = new TransactionRequest (ConstantsDomain.NUMBER_1, ConstantsDomain.EMAIL,
                buyDate,ConstantsDomain.ID_ARTICLE,ConstantsDomain.QUANTITY);

        assertNotNull(transactionRequest);
        assertEquals(ConstantsDomain.NUMBER_1, transactionRequest.getId());
        assertEquals(ConstantsDomain.EMAIL, transactionRequest.getEmail());
        assertEquals(buyDate, transactionRequest.getBuyDate());
        assertEquals(ConstantsDomain.ID_ARTICLE, transactionRequest.getArticleId());
        assertEquals(ConstantsDomain.QUANTITY, transactionRequest.getQuantity());
    }

    @Test
    void shouldCreateBrandWhenNameAndDescriptionAreValidSet() {
        LocalDateTime buyDate = LocalDateTime.now();
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setId(ConstantsDomain.NUMBER_1);
        transactionRequest.setEmail(ConstantsDomain.EMAIL);
        transactionRequest.setBuyDate(buyDate);
        transactionRequest.setArticleId(ConstantsDomain.ID_ARTICLE);
        transactionRequest.setQuantity(ConstantsDomain.QUANTITY);

        assertNotNull(transactionRequest);
        assertEquals(ConstantsDomain.NUMBER_1, transactionRequest.getId());
        assertEquals(ConstantsDomain.EMAIL, transactionRequest.getEmail());
        assertEquals(buyDate, transactionRequest.getBuyDate());
        assertEquals(ConstantsDomain.ID_ARTICLE, transactionRequest.getArticleId());
        assertEquals(ConstantsDomain.QUANTITY, transactionRequest.getQuantity());
    }
}
