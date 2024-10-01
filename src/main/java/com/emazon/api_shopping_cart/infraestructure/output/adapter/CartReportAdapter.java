package com.emazon.api_shopping_cart.infraestructure.output.adapter;

import com.emazon.api_shopping_cart.application.dto.report.ReportBuyDto;
import com.emazon.api_shopping_cart.domain.model.report.ReportBuy;
import com.emazon.api_shopping_cart.domain.spi.ICartReportPersistencePort;
import com.emazon.api_shopping_cart.infraestructure.configuration.feign.IFeignClientReport;
import com.emazon.api_shopping_cart.infraestructure.output.mapper.ICartReportMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CartReportAdapter implements ICartReportPersistencePort {

    private final IFeignClientReport feignClientReport;
    private final ICartReportMapper cartReportMapper;

    @Override
    public void saveReport(ReportBuy reportBuy) {
        ReportBuyDto reportBuyDto = cartReportMapper.reportBuyToReportBuyDto(reportBuy);
        feignClientReport.createReport(reportBuyDto);
    }
}
