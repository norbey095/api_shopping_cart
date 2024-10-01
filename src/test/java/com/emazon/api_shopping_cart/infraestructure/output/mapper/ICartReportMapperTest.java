package com.emazon.api_shopping_cart.infraestructure.output.mapper;

import com.emazon.api_shopping_cart.application.dto.report.ReportBuyDto;
import com.emazon.api_shopping_cart.domain.model.report.ReportBuy;
import com.emazon.api_shopping_cart.infraestructure.util.ConstantsInfTest;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class ICartReportMapperTest {

    private final ICartReportMapper cartReportMapper = Mappers.getMapper(ICartReportMapper.class);


    @Test
    void testReportBuyToReportBuyDto() {
        LocalDateTime buyDate = LocalDateTime.now();
        ReportBuy reportBuy= new ReportBuy(ConstantsInfTest.NAME, ConstantsInfTest.NAME, buyDate,
                buyDate,ConstantsInfTest.PRICE,new ArrayList<>());


        ReportBuyDto reportBuyDto= cartReportMapper.reportBuyToReportBuyDto(reportBuy);


        assertNotNull(reportBuyDto);
        assertEquals(reportBuyDto.getId(), reportBuy.getId());
        assertEquals(reportBuyDto.getBuyDate(), reportBuy.getBuyDate());
        assertEquals(reportBuyDto.getTotalPrice(), reportBuy.getTotalPrice());
        assertEquals(reportBuyDto.getUserName(), reportBuy.getUserName());
        assertEquals(reportBuyDto.getArticleDetails(), reportBuy.getArticleDetails());
        assertEquals(reportBuyDto.getCreateDate(), reportBuy.getCreateDate());
    }

    @Test
    void testReportBuyToReportBuyDtoNull() {
        ReportBuyDto reportBuyDto = cartReportMapper.reportBuyToReportBuyDto(null);

        assertNull(reportBuyDto);
    }
}