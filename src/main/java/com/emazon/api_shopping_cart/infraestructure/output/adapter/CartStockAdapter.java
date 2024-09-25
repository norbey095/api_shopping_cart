package com.emazon.api_shopping_cart.infraestructure.output.adapter;

import com.emazon.api_shopping_cart.domain.model.stock.ArticleResponse;
import com.emazon.api_shopping_cart.domain.spi.ICartStockPersistencePort;
import com.emazon.api_shopping_cart.infraestructure.configuration.feign.IFeignClientStock;
import com.emazon.api_shopping_cart.infraestructure.output.mapper.ICartEntityMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CartStockAdapter implements ICartStockPersistencePort {

    private final IFeignClientStock feignClientStock;
    private final ICartEntityMapper cartEntityMapper;

    @Override
    public ArticleResponse existArticleById(Integer articleId) {
        return cartEntityMapper.articleResponseDtoToArticleResponse(feignClientStock.existById(articleId));
    }
}
