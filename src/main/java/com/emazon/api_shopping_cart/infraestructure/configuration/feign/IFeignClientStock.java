package com.emazon.api_shopping_cart.infraestructure.configuration.feign;

import com.emazon.api_shopping_cart.application.dto.stock.ArticlePriceResponseDto;
import com.emazon.api_shopping_cart.application.dto.stock.ArticleResponseDto;
import com.emazon.api_shopping_cart.infraestructure.util.ConstantsConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = ConstantsConfig.STOCK, url = ConstantsConfig.URL_STOCK_PORT
        , configuration = FeignConfig.class)
public interface IFeignClientStock {

    @GetMapping(ConstantsConfig.URL_STOCK_ARTICLEID)
    ArticleResponseDto existById(@PathVariable Integer articleId);

    @GetMapping(ConstantsConfig.URL_STOCK_LIST_ARTICLE)
    List<ArticleResponseDto> getArticleDetails(@RequestParam Integer page,
                                               @RequestParam Integer size,
                                               @RequestParam boolean descending,
                                               @RequestParam List<Integer> articlesId,
                                               @RequestParam String categoryName,
                                               @RequestParam String brandName);

    @GetMapping(ConstantsConfig.URL_STOCK_PRICE)
    List<ArticlePriceResponseDto> getPriceByIds(@RequestParam List<Integer> articlesIds);

}
