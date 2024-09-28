package com.emazon.api_shopping_cart.domain.spi;

import com.emazon.api_shopping_cart.domain.model.stock.ArticlePriceResponse;
import com.emazon.api_shopping_cart.domain.model.stock.ArticleResponse;

import java.util.List;

public interface ICartStockPersistencePort {

      ArticleResponse existArticleById(Integer articleId);

      List<ArticleResponse> getArticleDetails(Integer page, Integer size,boolean descending,List<Integer> articlesId,
                                              String categoryName,String brandName);

      List<ArticlePriceResponse> getPriceByIds(List<Integer> articlesId);

}
