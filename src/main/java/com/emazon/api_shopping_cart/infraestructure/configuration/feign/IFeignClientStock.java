package com.emazon.api_shopping_cart.infraestructure.configuration.feign;

import com.emazon.api_shopping_cart.application.dto.stock.ArticleResponseDto;
import com.emazon.api_shopping_cart.infraestructure.util.ConstantsConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = ConstantsConfig.STOCK, url = ConstantsConfig.URL_STOCK_PORT
        , configuration = FeignConfig.class)
public interface IFeignClientStock {

    @GetMapping(ConstantsConfig.URL_STOCK_ARTICLEID)
    ArticleResponseDto existById(@PathVariable Integer articleId);

}
