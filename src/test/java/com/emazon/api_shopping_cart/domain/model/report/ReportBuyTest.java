package com.emazon.api_shopping_cart.domain.model.report;

import com.emazon.api_shopping_cart.domain.util.ConstantsDomain;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ReportBuyTest {

    @Test
    void shouldReportBuy() {
        LocalDateTime buyDate = LocalDateTime.now();
        ReportBuy reportBuy = new ReportBuy(ConstantsDomain.FIELD_NAME, ConstantsDomain.FIELD_NAME, buyDate,
                buyDate,ConstantsDomain.PRICE,new ArrayList<>());
        reportBuy.setId(ConstantsDomain.FIELD_NAME);

        assertNotNull(reportBuy);
        assertEquals(ConstantsDomain.FIELD_NAME, reportBuy.getId());
        assertEquals(ConstantsDomain.FIELD_NAME, reportBuy.getUserName());
        assertEquals(buyDate, reportBuy.getCreateDate());
        assertEquals(buyDate, reportBuy.getBuyDate());
        assertEquals(ConstantsDomain.PRICE, reportBuy.getTotalPrice());
        assertEquals(new ArrayList<>(), reportBuy.getArticleDetails());
    }
}
