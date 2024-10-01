package com.emazon.api_shopping_cart.infraestructure.configuration.feign;

import com.emazon.api_shopping_cart.application.dto.report.ReportBuyDto;
import com.emazon.api_shopping_cart.infraestructure.util.ConstantsConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = ConstantsConfig.REPORT, url = ConstantsConfig.URL_REPORT_PORT
        , configuration = FeignConfig.class)
public interface IFeignClientReport {

    @PostMapping(ConstantsConfig.URL_REPORT_SAVE)
    void createReport(@RequestBody ReportBuyDto reportBuyDto);

}
