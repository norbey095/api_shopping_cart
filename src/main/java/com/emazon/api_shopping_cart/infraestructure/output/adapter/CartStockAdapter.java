package com.emazon.api_shopping_cart.infraestructure.output.adapter;

import com.emazon.api_shopping_cart.application.dto.stock.ArticlePriceResponseDto;
import com.emazon.api_shopping_cart.application.dto.stock.ArticleResponseDto;
import com.emazon.api_shopping_cart.domain.model.stock.ArticlePriceResponse;
import com.emazon.api_shopping_cart.domain.model.stock.ArticleResponse;
import com.emazon.api_shopping_cart.domain.spi.ICartStockPersistencePort;
import com.emazon.api_shopping_cart.infraestructure.configuration.feign.IFeignClientStock;
import com.emazon.api_shopping_cart.infraestructure.output.mapper.ICartStockMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CartStockAdapter implements ICartStockPersistencePort {

    private final IFeignClientStock feignClientStock;
    private final ICartStockMapper cartStockMapper;

    @Override
    public ArticleResponse existArticleById(Integer articleId) {
        return cartStockMapper.articleResponseDtoToArticleResponse(feignClientStock.existById(articleId));
    }

    @Override
    public List<ArticleResponse> getArticleDetails(Integer page, Integer size,boolean descending,
                                                   List<Integer> articlesId,
                                                   String categoryName,String brandName) {

        List<ArticleResponseDto> articleResponseDtoList = feignClientStock
                .getArticleDetails(page,size,descending,articlesId,categoryName,brandName);

        return cartStockMapper.articleResponseDtoListToArticleResponseList(articleResponseDtoList);
    }

    @Override
    public List<ArticlePriceResponse> getPriceByIds(List<Integer> articlesId) {
        List<ArticlePriceResponseDto> articleResponseDtoList = feignClientStock
                .getPriceByIds(articlesId);
        return cartStockMapper.articlePriceResponseDtosToArticlePriceResponse(articleResponseDtoList);
    }
}
