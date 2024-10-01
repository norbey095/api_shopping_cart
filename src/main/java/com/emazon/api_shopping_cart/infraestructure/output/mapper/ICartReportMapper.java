package com.emazon.api_shopping_cart.infraestructure.output.mapper;

import com.emazon.api_shopping_cart.application.dto.report.ReportBuyDto;
import com.emazon.api_shopping_cart.domain.model.report.ReportBuy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICartReportMapper {


    ReportBuyDto reportBuyToReportBuyDto(ReportBuy reportBuy);

}
