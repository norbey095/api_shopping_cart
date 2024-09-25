package com.emazon.api_shopping_cart.domain.spi;

import com.emazon.api_shopping_cart.domain.model.stock.ArticleResponse;

public interface ICartStockPersistencePort {

      ArticleResponse existArticleById(Integer articleId);

}
