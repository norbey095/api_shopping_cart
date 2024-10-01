package com.emazon.api_shopping_cart.infraestructure.output.adapter;

import com.emazon.api_shopping_cart.application.dto.report.ReportBuyDto;
import com.emazon.api_shopping_cart.domain.model.report.ReportBuy;
import com.emazon.api_shopping_cart.infraestructure.configuration.feign.IFeignClientReport;
import com.emazon.api_shopping_cart.infraestructure.output.mapper.ICartReportMapper;
import com.emazon.api_shopping_cart.infraestructure.util.ConstantsInfTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class CartReportAdapterTest {

    @Mock
    private IFeignClientReport feignClientReport;

    @Mock
    private ICartReportMapper cartReportMapper;

    @InjectMocks
    private CartReportAdapter cartReportAdapter;

    @Test
    void testSaveReport() {
        LocalDateTime buyDate = LocalDateTime.now();
        ReportBuy reportBuy= new ReportBuy(ConstantsInfTest.NAME, ConstantsInfTest.NAME, buyDate,
                buyDate,ConstantsInfTest.PRICE,new ArrayList<>());
        ReportBuyDto reportBuyDto = new ReportBuyDto();

        Mockito.when(cartReportMapper.reportBuyToReportBuyDto(reportBuy))
                .thenReturn(reportBuyDto);
        Mockito.doNothing().when(feignClientReport).createReport(reportBuyDto);

        cartReportAdapter.saveReport(reportBuy);

        Mockito.verify(cartReportMapper, Mockito.times(ConstantsInfTest.NUMBER_1))
                .reportBuyToReportBuyDto(reportBuy);
        Mockito.verify(feignClientReport, Mockito.times(ConstantsInfTest.NUMBER_1))
                .createReport(reportBuyDto);
    }


}