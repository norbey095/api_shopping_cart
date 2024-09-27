package com.emazon.api_shopping_cart.domain.spi;

import com.emazon.api_shopping_cart.domain.model.cartdetail.ArticleCartRequest;
import com.emazon.api_shopping_cart.domain.model.stock.ArticleResponse;

import java.util.List;

public interface ICartStockPersistencePort {

      ArticleResponse existArticleById(Integer articleId);

      List<ArticleResponse> getArticleDetails(ArticleCartRequest articleCartRequest);

}
