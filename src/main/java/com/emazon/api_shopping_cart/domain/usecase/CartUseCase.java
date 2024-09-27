package com.emazon.api_shopping_cart.domain.usecase;

import com.emazon.api_shopping_cart.domain.api.ICartServicePort;
import com.emazon.api_shopping_cart.domain.exception.*;
import com.emazon.api_shopping_cart.domain.model.CartSave;
import com.emazon.api_shopping_cart.domain.model.cartdetail.ArticleCartRequest;
import com.emazon.api_shopping_cart.domain.model.cartdetail.CartDetail;
import com.emazon.api_shopping_cart.domain.model.cartdetail.CartDetailResponse;
import com.emazon.api_shopping_cart.domain.model.stock.ArticleResponse;
import com.emazon.api_shopping_cart.domain.model.stock.CategoryResponseList;
import com.emazon.api_shopping_cart.domain.model.transaction.TransactionRequest;
import com.emazon.api_shopping_cart.domain.spi.IAthenticationPersistencePort;
import com.emazon.api_shopping_cart.domain.spi.ICartPersistencePort;
import com.emazon.api_shopping_cart.domain.spi.ICartStockPersistencePort;
import com.emazon.api_shopping_cart.domain.spi.ICartTransactionPersistencePort;
import com.emazon.api_shopping_cart.domain.util.ConstantsUseCase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CartUseCase implements ICartServicePort {

    private final ICartPersistencePort cartPersistencePort;
    private final IAthenticationPersistencePort authenticationPersistencePort;
    private final ICartStockPersistencePort cartStockPersistencePort;
    private final ICartTransactionPersistencePort cartTransactionPersistencePort;

    public CartUseCase(ICartPersistencePort cartPersistencePort,
                       ICartStockPersistencePort cartStockPersistencePort,
                       IAthenticationPersistencePort authenticationPersistencePort,
                       ICartTransactionPersistencePort cartTransactionPersistencePort) {
        this.cartPersistencePort = cartPersistencePort;
        this.cartStockPersistencePort = cartStockPersistencePort;
        this.authenticationPersistencePort = authenticationPersistencePort;
        this.cartTransactionPersistencePort = cartTransactionPersistencePort;
    }

    @Override
    public void cartSave(CartSave cartRequest) {
        String userName = authenticationPersistencePort.getUserName();
        cartRequest.setEmail(userName);

        ArticleResponse articleResponse = validateArticleExists(cartRequest.getIdArticle());
        validateAvailableQuantity(articleResponse.getQuantity(), cartRequest.getQuantity(),null);

        boolean isUpdate = validateExistenceProductInCart(cartRequest.getEmail(),
                cartRequest.getIdArticle(), cartRequest.getQuantity());

        if (!isUpdate) {
            validateArticleByCategory(cartRequest.getEmail(),cartRequest.getIdArticle());
            cartRequest.setCreateDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
            cartRequest.setUpdateDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
            cartPersistencePort.saveCart(cartRequest);
        }
    }

    @Override
    public void deleteCart(Integer idArticle) {
        String userName = authenticationPersistencePort.getUserName();
        validateCartItem(idArticle,userName);

        this.cartPersistencePort.deleteItemCart(idArticle,userName);
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

        this.cartPersistencePort.updateProductDateByEmail(userName,date);
    }

    @Override
    public CartDetailResponse getCart(ArticleCartRequest articleCartRequest) {
        validatePaginationData(articleCartRequest.getPage(),articleCartRequest.getSize());

        String userName = authenticationPersistencePort.getUserName();
        List<CartSave> myCart = cartPersistencePort.findAllCartByUserName(userName);

        articleCartRequest.setArticleIds(getAllArticleIds(myCart));

        List<ArticleResponse> articleResponseList = cartStockPersistencePort.getArticleDetails(articleCartRequest);
        validateData(articleResponseList);

        return getCartDetails(myCart,articleResponseList);
    }

    @Override
    public void buyArticle() {
        String userName = authenticationPersistencePort.getUserName();

        List<CartSave> myCart = cartPersistencePort.findAllCartByUserName(userName);
        List<ArticleResponse> articleResponseList = getArticleDetails(myCart);
        validateData(articleResponseList);

        saveSaleInTransaction(myCart,articleResponseList);

        saveSaleInTransaction(myCart);
        cartPersistencePort.deleteCart(userName);
    }

    private ArticleResponse validateArticleExists(Integer id) {
        return cartStockPersistencePort.existArticleById(id);
    }

    private void validateAvailableQuantity(Integer quantityAvailable, Integer quantityRequest,String name) {
        if (quantityAvailable < quantityRequest) {
            LocalDate dateOfNextSupply = cartPersistencePort.getNextDate();
            throw new TheItemIsNotAvailable(ConstantsUseCase.ITEM_NOT_AVAILABLE + dateOfNextSupply +
                    (name != null ? ConstantsUseCase.ARTICLE + name : ConstantsUseCase.COMILLAS));
        }
    }

    private boolean validateExistenceProductInCart(String userName, Integer id, Integer quantityRequest) {
        boolean isUpdate = false;
        CartSave cartSave = cartPersistencePort.findCartByUserNameAndArticleId(id, userName);
        if (cartSave != null) {
            cartSave.setQuantity((cartSave.getQuantity() + quantityRequest));
            cartSave.setUpdateDate(LocalDateTime.now());

            cartPersistencePort.saveCart(cartSave);
            isUpdate = true;
        }
        return isUpdate;
    }

    private void validateArticleByCategory(String userName, Integer articleId) {
        List<Integer> cart = cartPersistencePort.findCartByUserName(userName);
        if(!cart.isEmpty()) {
            cart.add(articleId);

            Map<Integer, Integer> categoryCountMap = new HashMap<>();
            for (Integer item : cart) {
                ArticleResponse articleResponse = cartStockPersistencePort.existArticleById(item);
                updateCategoryCountMap(categoryCountMap,articleResponse.getCategories());
            }
        }
    }

    private void updateCategoryCountMap(Map<Integer, Integer> categoryCountMap, List<CategoryResponseList> categories) {
        for (CategoryResponseList categoryItem : categories) {
            Integer category = categoryItem.getId();

            Integer currentCount = categoryCountMap.getOrDefault(category, ConstantsUseCase.DEFAULT_VALUE);
            int newCount = currentCount + ConstantsUseCase.ADD_ONE;

            categoryCountMap.put(category, newCount);

            if (newCount > ConstantsUseCase.MAX_NUM_CATEGORY) {
                throw new CategoryLimitException(ConstantsUseCase.CATEGORY_LIMIT + categoryItem.getName());
            }
        }
    }

    private void validateCartItem(Integer idArticle, String userName){
        CartSave cartSave = cartPersistencePort.findCartByUserNameAndArticleId(idArticle, userName);
        if (cartSave == null) {
            throw new TheArticleNotExistException(ConstantsUseCase.ARTICLE_NOT_EXIST);
        }
    }

    private void validatePaginationData(Integer page, Integer size){
        if (page == null || size == null){
            throw new PaginationNotAllowedException();
        }
        if (page < ConstantsUseCase.NUMBER_0 || size <  ConstantsUseCase.NUMBER_0) {
            throw new PaginationNotAllowedException();
        }
    }

    private List<Integer> getAllArticleIds(List<CartSave> cartList){
        return cartList.stream()
                .map(CartSave::getIdArticle)
                .collect(Collectors.toList());
    }

    private void validateData(List<ArticleResponse> articleResponseList){
        if (articleResponseList.isEmpty()) {
            throw new NoDataFoundException();
        }
    }

    private CartDetailResponse getCartDetails(List<CartSave> myCart,List<ArticleResponse> articleResponseList){
        CartDetailResponse cartDetailResponse = new CartDetailResponse();
        List<CartDetail> cartDetailsList = new ArrayList<>();
        double totalPrice = 0;

        for(ArticleResponse article: articleResponseList){
            CartDetail cartDetail = new CartDetail();
            CartSave cartSave = getItemFromCart(myCart,article.getId());

            cartDetail.setName(article.getName());
            cartDetail.setUnitPrice(article.getPrice());

            validateAvailableQuantityMessages(article.getQuantity(),cartSave.getQuantity(),cartDetail);

            double subtotal = article.getPrice()*cartSave.getQuantity();
            cartDetail.setSubPrice(subtotal);

            totalPrice += subtotal;
            cartDetailsList.add(cartDetail);
        }
        cartDetailResponse.setCartDetail(cartDetailsList);
        cartDetailResponse.setTotalPrice(totalPrice);
        return cartDetailResponse;
    }

    private CartSave getItemFromCart(List<CartSave> myCart,Integer id){
        return myCart.stream()
                .filter(cart -> cart.getIdArticle().equals(id))
                .findFirst()
                .orElse(null);
    }

    private void validateAvailableQuantityMessages(Integer quantityAvailable,Integer quantityRequest,CartDetail cartDetail){
        if (quantityAvailable < quantityRequest) {
            LocalDate dateOfNextSupply = cartPersistencePort.getNextDate();
            cartDetail.setMessage(ConstantsUseCase.ITEM_NOT_AVAILABLE + dateOfNextSupply);
        }
        cartDetail.setQuantity(quantityRequest);
    }

    private List<ArticleResponse> getArticleDetails(List<CartSave> myCart){
        ArticleCartRequest articleCartRequest = new ArticleCartRequest();
        articleCartRequest.setPage(ConstantsUseCase.NUMBER_0);
        articleCartRequest.setSize(myCart.size());
        articleCartRequest.setArticleIds(this.getAllArticleIds(myCart));
        return cartStockPersistencePort.getArticleDetails(articleCartRequest);
    }


    private void saveSaleInTransaction(List<CartSave> myCart,List<ArticleResponse> articleResponseList){
        for(ArticleResponse article: articleResponseList){
            CartSave cartSave = getItemFromCart(myCart,article.getId());
            validateAvailableQuantity(article.getQuantity(),cartSave.getQuantity(),article.getName());
        }
    }

    private void saveSaleInTransaction(List<CartSave> myCart){
        LocalDateTime localDateTime = LocalDateTime.now();
        for(CartSave cart: myCart){
            TransactionRequest transactionRequest = new TransactionRequest();
            transactionRequest.setIdArticle(cart.getIdArticle());
            transactionRequest.setQuantity(cart.getQuantity());
            transactionRequest.setEmail(cart.getEmail());
            transactionRequest.setBuyDate(localDateTime);
            cartTransactionPersistencePort.saveBuy(transactionRequest);
        }

    }

}
