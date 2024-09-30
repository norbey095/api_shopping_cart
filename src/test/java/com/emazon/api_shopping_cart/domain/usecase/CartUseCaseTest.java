package com.emazon.api_shopping_cart.domain.usecase;

import com.emazon.api_shopping_cart.domain.exception.*;
import com.emazon.api_shopping_cart.domain.model.CartSave;
import com.emazon.api_shopping_cart.domain.model.stock.ArticlePriceResponse;
import com.emazon.api_shopping_cart.domain.model.stock.ArticleResponse;
import com.emazon.api_shopping_cart.domain.model.stock.CategoryResponseList;
import com.emazon.api_shopping_cart.domain.spi.IAthenticationPersistencePort;
import com.emazon.api_shopping_cart.domain.spi.ICartPersistencePort;
import com.emazon.api_shopping_cart.domain.spi.ICartStockPersistencePort;
import com.emazon.api_shopping_cart.domain.spi.ICartTransactionPersistencePort;
import com.emazon.api_shopping_cart.domain.util.ConstantsDomain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class CartUseCaseTest {

    @Mock
    private ICartPersistencePort cartPersistencePort;

    @Mock
    private IAthenticationPersistencePort authenticationPersistencePort;

    @Mock
    private ICartStockPersistencePort cartStockPersistencePort;


    @Mock
    private ICartTransactionPersistencePort cartTransactionPersistencePort;

    @InjectMocks
    private CartUseCase cartUseCase;

    private CartSave cartRequest;
    private CartSave cartDataBase;
    private ArticleResponse articleResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        cartRequest = new CartSave();
        cartRequest.setIdArticle(ConstantsDomain.ID_ARTICLE);
        cartRequest.setQuantity(ConstantsDomain.QUANTITY);

        cartDataBase = new CartSave();
        cartDataBase.setIdArticle(ConstantsDomain.ID_ARTICLE);
        cartDataBase.setQuantity(ConstantsDomain.QUANTITY);

        articleResponse = new ArticleResponse();
        articleResponse.setId(ConstantsDomain.ID_ARTICLE);
        articleResponse.setQuantity(ConstantsDomain.QUANTITY);
        articleResponse.setCategories(new ArrayList<>());
    }

    @Test
    void testCartSave() {
        Mockito.when(authenticationPersistencePort.getUserName()).thenReturn(ConstantsDomain.EMAIL);
        Mockito.when(cartStockPersistencePort.existArticleById(cartRequest.getIdArticle()))
                .thenReturn(articleResponse);
        Mockito.when(cartPersistencePort.findCartByUserNameAndArticleId(cartRequest.getIdArticle(),
                ConstantsDomain.EMAIL)).thenReturn(null);

        cartUseCase.cartSave(cartRequest);

        Mockito.verify(cartPersistencePort).saveCart(Mockito.any(CartSave.class));
        Assertions.assertEquals(ConstantsDomain.EMAIL, cartRequest.getEmail());
        Assertions.assertNotNull(cartRequest.getIdArticle());
        Assertions.assertNotNull(cartRequest.getQuantity());
    }

    @Test
    void testCartSaveExistingArticleWithSufficientStock() {
        Mockito.when(authenticationPersistencePort.getUserName()).thenReturn(ConstantsDomain.EMAIL);
        Mockito.when(cartStockPersistencePort.existArticleById(ConstantsDomain.ID_ARTICLE))
                .thenReturn(articleResponse);
        Mockito.when(cartPersistencePort.findCartByUserNameAndArticleId(ConstantsDomain.ID_ARTICLE
                        , ConstantsDomain.EMAIL))
                .thenReturn(cartDataBase);

        cartUseCase.cartSave(cartRequest);

        Mockito.verify(cartPersistencePort).saveCart(Mockito.any(CartSave.class));
    }

    @Test
    void testCartSaveNotAvailableStock() {
        ArticleResponse articleResponseNotAvailable = new ArticleResponse();
        articleResponseNotAvailable.setQuantity(ConstantsDomain.NUMBER_0);

        Mockito.when(authenticationPersistencePort.getUserName()).thenReturn(ConstantsDomain.EMAIL);
        Mockito.when(cartStockPersistencePort.existArticleById(ConstantsDomain.ID_ARTICLE))
                .thenReturn(articleResponseNotAvailable);
        Mockito.when(cartPersistencePort.getNextDate()).thenReturn(LocalDate.now());

        Assertions.assertThrows(TheItemIsNotAvailable.class, () -> {
            cartUseCase.cartSave(cartRequest);
        });

        Mockito.verify(authenticationPersistencePort, Mockito.times(ConstantsDomain.NUMBER_1))
                .getUserName();
        Mockito.verify(cartStockPersistencePort, Mockito.times(ConstantsDomain.NUMBER_1))
                .existArticleById(ConstantsDomain.ID_ARTICLE);
    }

    @Test
    void testValidateCategoryLimitExceeded() {
        List<CategoryResponseList> categoryResponseLists = new ArrayList<>();
        CategoryResponseList categoryResponseList = new
                CategoryResponseList(ConstantsDomain.ID_ARTICLE,ConstantsDomain.DESCRIPTION);
        categoryResponseLists.add(categoryResponseList);
        categoryResponseLists.add(categoryResponseList);
        categoryResponseLists.add(categoryResponseList);
        categoryResponseLists.add(categoryResponseList);

        ArticleResponse articleResponseLimit = new ArticleResponse();
        articleResponseLimit.setQuantity(ConstantsDomain.QUANTITY);
        articleResponseLimit.setCategories(categoryResponseLists);

        Mockito.when(authenticationPersistencePort.getUserName()).thenReturn(ConstantsDomain.EMAIL);
        Mockito.when(cartStockPersistencePort.existArticleById(ConstantsDomain.ID_ARTICLE))
                .thenReturn(articleResponseLimit);
        Mockito.when(cartPersistencePort.findCartByUserNameAndArticleId(ConstantsDomain.ID_ARTICLE
                        , ConstantsDomain.EMAIL))
                .thenReturn(null);
        List<Integer> mockCart = new ArrayList<>(List.of(ConstantsDomain.ID_ARTICLE
                , ConstantsDomain.ID_ARTICLE, ConstantsDomain.ID_ARTICLE));

        Mockito.when(cartPersistencePort.findCartByUserName(ConstantsDomain.EMAIL)).thenReturn(mockCart);

        Assertions.assertThrows(CategoryLimitException.class, () -> {
            cartUseCase.cartSave(cartRequest);
        });

        Mockito.verify(cartPersistencePort, Mockito.times(ConstantsDomain.NUMBER_0))
                .saveCart(Mockito.any(CartSave.class));
        Mockito.verify(cartStockPersistencePort, Mockito.times(ConstantsDomain.NUMBER_2))
                .existArticleById(ConstantsDomain.ID_ARTICLE);
    }

    @Test
    void testValidateCategoryNotLimitExceeded() {
        Mockito.when(authenticationPersistencePort.getUserName()).thenReturn(ConstantsDomain.EMAIL);
        Mockito.when(cartStockPersistencePort.existArticleById(ConstantsDomain.ID_ARTICLE))
                .thenReturn(articleResponse);
        Mockito.when(cartPersistencePort.findCartByUserNameAndArticleId(ConstantsDomain.ID_ARTICLE
                        , ConstantsDomain.EMAIL))
                .thenReturn(null);
        List<Integer> mockCart = new ArrayList<>(List.of(ConstantsDomain.ID_ARTICLE
                , ConstantsDomain.ID_ARTICLE, ConstantsDomain.ID_ARTICLE));
        Mockito.when(cartPersistencePort.findCartByUserName(ConstantsDomain.EMAIL)).thenReturn(mockCart);

        cartUseCase.cartSave(cartRequest);


        Mockito.verify(cartPersistencePort, Mockito.times(ConstantsDomain.NUMBER_1))
                .saveCart(Mockito.any(CartSave.class));
        Mockito.verify(cartStockPersistencePort, Mockito.times(ConstantsDomain.NUMBER_5))
                .existArticleById(ConstantsDomain.ID_ARTICLE);
    }

    @Test
    void testDeleteArticleInCart() {
        Integer idArticle = 1;
        String userName = ConstantsDomain.EMAIL;

        CartSave cartSave = new CartSave();

        Mockito.when(authenticationPersistencePort.getUserName()).thenReturn(userName);
        Mockito.when(cartPersistencePort.findCartByUserNameAndArticleId(idArticle, userName)).thenReturn(cartSave);

        cartUseCase.deleteCart(idArticle);

        Mockito.verify(cartPersistencePort, Mockito.times(ConstantsDomain.NUMBER_1))
                .deleteItemCart(idArticle, userName);

    }

    @Test
    void testDeleteArticleInCartNull() {
        Integer idArticle = ConstantsDomain.NUMBER_1;
        String userName = ConstantsDomain.EMAIL;

        Mockito.when(authenticationPersistencePort.getUserName()).thenReturn(userName);
        Mockito.when(cartPersistencePort.findCartByUserNameAndArticleId(idArticle, userName)).thenReturn(null);

        Assertions.assertThrows(TheArticleNotExistException.class, () -> {
            cartUseCase.deleteCart(idArticle);
        });

        Mockito.verify(cartPersistencePort, Mockito.times(ConstantsDomain.NUMBER_0))
                .deleteItemCart(idArticle, userName);
    }

    @Test
    void testGetCart() {
        List<CartSave> cartSaveList = new ArrayList<>();
        cartSaveList.add(cartDataBase);
        List<ArticleResponse> articleResponseList = new ArrayList<>();
        articleResponseList.add(articleResponse);

        String userName = ConstantsDomain.EMAIL;
        List<Integer> ids = new ArrayList<>();
        ids.add(ConstantsDomain.ID_ARTICLE);
        ArticlePriceResponse articlePriceResponses = new
                ArticlePriceResponse(ConstantsDomain.NUMBER_1,ConstantsDomain.PRICE,
                ConstantsDomain.NUMBER_1);
        List<ArticlePriceResponse> articlePriceResponsesList = new ArrayList<>();
        articlePriceResponsesList.add(articlePriceResponses);

        Mockito.when(authenticationPersistencePort.getUserName()).thenReturn(userName);
        Mockito.when(cartPersistencePort.findAllCartByUserName(userName)).thenReturn(cartSaveList);
        Mockito.when(cartStockPersistencePort.getArticleDetails(ConstantsDomain.NUMBER_1, ConstantsDomain.NUMBER_0
                ,true,ids, ConstantsDomain.FIELD_NAME, ConstantsDomain.FIELD_NAME))
                .thenReturn(articleResponseList);
        Mockito.when(cartStockPersistencePort.getPriceByIds(ids))
                .thenReturn(articlePriceResponsesList);

        cartUseCase.getCart(ConstantsDomain.NUMBER_1, ConstantsDomain.NUMBER_0
                ,true, ConstantsDomain.FIELD_NAME, ConstantsDomain.FIELD_NAME);

        Mockito.verify(cartPersistencePort, Mockito.times(ConstantsDomain.NUMBER_1))
                .findAllCartByUserName(userName);
        Mockito.verify(authenticationPersistencePort, Mockito.times(ConstantsDomain.NUMBER_1))
                .getUserName();
    }

    @Test
    void testGetCartPageNull() {
        Assertions.assertThrows(PaginationNotAllowedException.class, () -> {
            cartUseCase.getCart(null, ConstantsDomain.NUMBER_0
                    ,true, ConstantsDomain.FIELD_NAME, ConstantsDomain.FIELD_NAME);
        });

        String userName = ConstantsDomain.EMAIL;

        Mockito.verify(cartPersistencePort, Mockito.times(ConstantsDomain.NUMBER_0))
                .findAllCartByUserName(userName);
        Mockito.verify(authenticationPersistencePort, Mockito.times(ConstantsDomain.NUMBER_0))
                .getUserName();
        Mockito.verify(cartStockPersistencePort, Mockito.times(ConstantsDomain.NUMBER_0))
                .getArticleDetails(null, ConstantsDomain.NUMBER_0
                        ,true,new ArrayList<>(), ConstantsDomain.FIELD_NAME, ConstantsDomain.FIELD_NAME);
    }

    @Test
    void testGetCartSizeNull() {
        Assertions.assertThrows(PaginationNotAllowedException.class, () -> {
            cartUseCase.getCart(ConstantsDomain.NUMBER_1, null
                    ,true, ConstantsDomain.FIELD_NAME, ConstantsDomain.FIELD_NAME);
        });

        String userName = ConstantsDomain.EMAIL;

        Mockito.verify(cartPersistencePort, Mockito.times(ConstantsDomain.NUMBER_0))
                .findAllCartByUserName(userName);
        Mockito.verify(authenticationPersistencePort, Mockito.times(ConstantsDomain.NUMBER_0))
                .getUserName();
    }

    @Test
    void testGetCartSizeNegative() {
        Assertions.assertThrows(PaginationNotAllowedException.class, () -> {
            cartUseCase.getCart(ConstantsDomain.NUMBER_1, -ConstantsDomain.NUMBER_1
                    ,true, ConstantsDomain.FIELD_NAME, ConstantsDomain.FIELD_NAME);
        });

        String userName = ConstantsDomain.EMAIL;

        Mockito.verify(cartPersistencePort, Mockito.times(ConstantsDomain.NUMBER_0))
                .findAllCartByUserName(userName);
        Mockito.verify(authenticationPersistencePort, Mockito.times(ConstantsDomain.NUMBER_0))
                .getUserName();
        Mockito.verify(cartStockPersistencePort, Mockito.times(ConstantsDomain.NUMBER_0))
                .getArticleDetails(ConstantsDomain.NUMBER_1, -ConstantsDomain.NUMBER_1
                        ,true,new ArrayList<>(), ConstantsDomain.FIELD_NAME, ConstantsDomain.FIELD_NAME);
    }

    @Test
    void testGetCartPageNegative() {
        Assertions.assertThrows(PaginationNotAllowedException.class, () -> {
            cartUseCase.getCart(-ConstantsDomain.NUMBER_1, ConstantsDomain.NUMBER_1
                    ,true, ConstantsDomain.FIELD_NAME, ConstantsDomain.FIELD_NAME);
        });

        String userName = ConstantsDomain.EMAIL;

        Mockito.verify(cartPersistencePort, Mockito.times(ConstantsDomain.NUMBER_0))
                .findAllCartByUserName(userName);
        Mockito.verify(authenticationPersistencePort, Mockito.times(ConstantsDomain.NUMBER_0))
                .getUserName();
        Mockito.verify(cartStockPersistencePort, Mockito.times(ConstantsDomain.NUMBER_0))
                .getArticleDetails(-ConstantsDomain.NUMBER_1, ConstantsDomain.NUMBER_1
                        ,true,new ArrayList<>(), ConstantsDomain.FIELD_NAME, ConstantsDomain.FIELD_NAME);
    }

    @Test
    void testGetCartArticleResponseNull() {
        List<CartSave> cartSaveList = new ArrayList<>();
        cartSaveList.add(cartDataBase);

        String userName = ConstantsDomain.EMAIL;
        List<Integer> ids = new ArrayList<>();
        ids.add(ConstantsDomain.ID_ARTICLE);
        ArticlePriceResponse articlePriceResponse = new ArticlePriceResponse(ConstantsDomain.NUMBER_1,ConstantsDomain.PRICE,
                ConstantsDomain.NUMBER_1);
        List<ArticlePriceResponse> articlePriceResponsesList = new ArrayList<>();
        articlePriceResponsesList.add(articlePriceResponse);

        Mockito.when(authenticationPersistencePort.getUserName()).thenReturn(userName);
        Mockito.when(cartPersistencePort.findAllCartByUserName(userName)).thenReturn(cartSaveList);
        Mockito.when(cartStockPersistencePort.getArticleDetails(ConstantsDomain.NUMBER_1, ConstantsDomain.NUMBER_0
                ,true,ids, ConstantsDomain.FIELD_NAME, ConstantsDomain.FIELD_NAME))
                .thenReturn(null);
        Mockito.when(cartStockPersistencePort.getPriceByIds(ids))
                .thenReturn(articlePriceResponsesList);

        Assertions.assertThrows(NoDataFoundException.class, () -> {
            cartUseCase.getCart(ConstantsDomain.NUMBER_1, ConstantsDomain.NUMBER_0
                    ,true, ConstantsDomain.FIELD_NAME, ConstantsDomain.FIELD_NAME);
        });

        Mockito.verify(cartPersistencePort, Mockito.times(ConstantsDomain.NUMBER_1))
                .findAllCartByUserName(userName);
        Mockito.verify(authenticationPersistencePort, Mockito.times(ConstantsDomain.NUMBER_1))
                .getUserName();
        Mockito.verify(cartStockPersistencePort, Mockito.times(ConstantsDomain.NUMBER_1))
                .getArticleDetails(ConstantsDomain.NUMBER_1, ConstantsDomain.NUMBER_0
                        ,true,ids, ConstantsDomain.FIELD_NAME, ConstantsDomain.FIELD_NAME);
    }


    @Test
    void testGetCartArticleResponseEmpty() {
        List<CartSave> cartSaveList = new ArrayList<>();
        cartSaveList.add(cartDataBase);
        List<ArticleResponse> articleResponseList = new ArrayList<>();

        String userName = ConstantsDomain.EMAIL;
        List<Integer> ids = new ArrayList<>();
        ids.add(ConstantsDomain.ID_ARTICLE);
        ArticlePriceResponse articlePriceResponse = new ArticlePriceResponse(ConstantsDomain.NUMBER_1,ConstantsDomain.PRICE,
                ConstantsDomain.NUMBER_1);
        List<ArticlePriceResponse> articlePriceResponsesList = new ArrayList<>();
        articlePriceResponsesList.add(articlePriceResponse);

        Mockito.when(authenticationPersistencePort.getUserName()).thenReturn(userName);
        Mockito.when(cartPersistencePort.findAllCartByUserName(userName)).thenReturn(cartSaveList);
        Mockito.when(cartStockPersistencePort.getArticleDetails(ConstantsDomain.NUMBER_1, ConstantsDomain.NUMBER_0
                        ,true,ids, ConstantsDomain.FIELD_NAME, ConstantsDomain.FIELD_NAME))
                .thenReturn(articleResponseList);
        Mockito.when(cartStockPersistencePort.getPriceByIds(ids))
                .thenReturn(articlePriceResponsesList);

        Assertions.assertThrows(NoDataFoundException.class, () -> {
            cartUseCase.getCart(ConstantsDomain.NUMBER_1, ConstantsDomain.NUMBER_0
                    ,true, ConstantsDomain.FIELD_NAME, ConstantsDomain.FIELD_NAME);
        });

        Mockito.verify(cartPersistencePort, Mockito.times(ConstantsDomain.NUMBER_1))
                .findAllCartByUserName(userName);
        Mockito.verify(authenticationPersistencePort, Mockito.times(ConstantsDomain.NUMBER_1))
                .getUserName();
        Mockito.verify(cartStockPersistencePort, Mockito.times(ConstantsDomain.NUMBER_1))
                .getArticleDetails(ConstantsDomain.NUMBER_1, ConstantsDomain.NUMBER_0
                        ,true,ids, ConstantsDomain.FIELD_NAME, ConstantsDomain.FIELD_NAME);
    }

    @Test
    void testGetCartQuantityNotAvailable() {

        List<CartSave> cartSaveList = new ArrayList<>();
        cartSaveList.add(cartDataBase);

        List<ArticleResponse> articleResponseList = new ArrayList<>();
        ArticleResponse articleRes= new ArticleResponse();
        articleRes.setId(ConstantsDomain.ID_ARTICLE);
        articleRes.setQuantity(ConstantsDomain.NUMBER_0);
        articleRes.setCategories(new ArrayList<>());

        articleResponseList.add(articleRes);

        String userName = ConstantsDomain.EMAIL;
        List<Integer> ids = new ArrayList<>();
        ids.add(ConstantsDomain.ID_ARTICLE);

        Mockito.when(authenticationPersistencePort.getUserName()).thenReturn(userName);
        Mockito.when(cartPersistencePort.findAllCartByUserName(userName)).thenReturn(cartSaveList);
        Mockito.when(cartPersistencePort.getNextDate()).thenReturn(LocalDate.now());
        Mockito.when(cartStockPersistencePort.getArticleDetails(ConstantsDomain.NUMBER_1, ConstantsDomain.NUMBER_0
                ,true, ids, ConstantsDomain.FIELD_NAME, ConstantsDomain.FIELD_NAME))
                .thenReturn(articleResponseList);
        Mockito.when(cartStockPersistencePort.getPriceByIds(ids))
                .thenReturn(null);

        cartUseCase.getCart(ConstantsDomain.NUMBER_1, ConstantsDomain.NUMBER_0
                ,true, ConstantsDomain.FIELD_NAME, ConstantsDomain.FIELD_NAME);

        Mockito.verify(cartPersistencePort, Mockito.times(ConstantsDomain.NUMBER_1))
                .findAllCartByUserName(userName);
        Mockito.verify(authenticationPersistencePort, Mockito.times(ConstantsDomain.NUMBER_1))
                .getUserName();
        Mockito.verify(cartStockPersistencePort, Mockito.times(ConstantsDomain.NUMBER_1))
                .getArticleDetails(ConstantsDomain.NUMBER_1, ConstantsDomain.NUMBER_0
                        ,true,ids, ConstantsDomain.FIELD_NAME, ConstantsDomain.FIELD_NAME);
    }

    @Test
    void testBuyArticle() {
        List<CartSave> cartSaveList = new ArrayList<>();
        cartSaveList.add(cartDataBase);

        List<ArticleResponse> articleResponseList = new ArrayList<>();
        articleResponseList.add(articleResponse);

        List<Integer> ids = new ArrayList<>();
        ids.add(ConstantsDomain.ID_ARTICLE);

        String userName = ConstantsDomain.EMAIL;

        Mockito.when(authenticationPersistencePort.getUserName()).thenReturn(userName);
        Mockito.when(cartPersistencePort.findAllCartByUserName(userName)).thenReturn(cartSaveList);
        Mockito.when(cartStockPersistencePort.getArticleDetails(ConstantsDomain.NUMBER_0, ConstantsDomain.NUMBER_1
                        ,false,ids, null, null)).thenReturn(articleResponseList);

        cartUseCase.buyArticle();

        Mockito.verify(cartStockPersistencePort, Mockito.times(ConstantsDomain.NUMBER_1))
                .getArticleDetails(ConstantsDomain.NUMBER_0, ConstantsDomain.NUMBER_1
                        ,false,ids, null, null);
        Mockito.verify(authenticationPersistencePort, Mockito.times(ConstantsDomain.NUMBER_1))
                .getUserName();
        Mockito.verify(cartPersistencePort, Mockito.times(ConstantsDomain.NUMBER_1))
                .findAllCartByUserName(userName);
    }

    @Test
    void testBuyArticleWithArticleNotAvailable() {
        List<CartSave> cartSaveList = new ArrayList<>();
        cartSaveList.add(cartDataBase);

        ArticleResponse cartDataB = new ArticleResponse();
        cartDataB.setId(ConstantsDomain.ID_ARTICLE);
        cartDataB.setQuantity(ConstantsDomain.NUMBER_0);

        List<ArticleResponse> articleResponseList = new ArrayList<>();
        articleResponseList.add(cartDataB);

        List<Integer> ids = new ArrayList<>();
        ids.add(ConstantsDomain.ID_ARTICLE);

        String userName = ConstantsDomain.EMAIL;

        Mockito.when(authenticationPersistencePort.getUserName()).thenReturn(userName);
        Mockito.when(cartPersistencePort.findAllCartByUserName(userName)).thenReturn(cartSaveList);
        Mockito.when(cartStockPersistencePort.getArticleDetails(ConstantsDomain.NUMBER_0, ConstantsDomain.NUMBER_1
                        ,false,ids, null, null))
                .thenReturn(articleResponseList);


        Assertions.assertThrows(TheItemIsNotAvailable.class, () -> {
            cartUseCase.buyArticle();
        });


        Mockito.verify(cartStockPersistencePort, Mockito.times(ConstantsDomain.NUMBER_1))
                .getArticleDetails(ConstantsDomain.NUMBER_0, ConstantsDomain.NUMBER_1
                        ,false,ids, null, null);
        Mockito.verify(authenticationPersistencePort, Mockito.times(ConstantsDomain.NUMBER_1))
                .getUserName();
        Mockito.verify(cartPersistencePort, Mockito.times(ConstantsDomain.NUMBER_1))
                .findAllCartByUserName(userName);
        Mockito.verify(cartPersistencePort, Mockito.times(ConstantsDomain.NUMBER_0))
                .deleteCart(userName);
    }

    @Test
    void testBuyArticleWithArticleNotAvailableWithName() {
        List<CartSave> cartSaveList = new ArrayList<>();
        cartSaveList.add(cartDataBase);

        ArticleResponse cartDataB = new ArticleResponse();
        cartDataB.setId(ConstantsDomain.ID_ARTICLE);
        cartDataB.setQuantity(ConstantsDomain.NUMBER_0);
        cartDataB.setName(ConstantsDomain.FIELD_NAME);

        List<ArticleResponse> articleResponseList = new ArrayList<>();
        articleResponseList.add(cartDataB);

        List<Integer> ids = new ArrayList<>();
        ids.add(ConstantsDomain.ID_ARTICLE);

        String userName = ConstantsDomain.EMAIL;

        Mockito.when(authenticationPersistencePort.getUserName()).thenReturn(userName);
        Mockito.when(cartPersistencePort.findAllCartByUserName(userName)).thenReturn(cartSaveList);
        Mockito.when(cartStockPersistencePort.getArticleDetails(ConstantsDomain.NUMBER_0, ConstantsDomain.NUMBER_1
                        ,false,ids, null, null))
                .thenReturn(articleResponseList);


        Assertions.assertThrows(TheItemIsNotAvailable.class, () -> {
            cartUseCase.buyArticle();
        });


        Mockito.verify(cartStockPersistencePort, Mockito.times(ConstantsDomain.NUMBER_1))
                .getArticleDetails(ConstantsDomain.NUMBER_0, ConstantsDomain.NUMBER_1
                        ,false,ids, null, null);
        Mockito.verify(authenticationPersistencePort, Mockito.times(ConstantsDomain.NUMBER_1))
                .getUserName();
        Mockito.verify(cartPersistencePort, Mockito.times(ConstantsDomain.NUMBER_1))
                .findAllCartByUserName(userName);
        Mockito.verify(cartPersistencePort, Mockito.times(ConstantsDomain.NUMBER_0))
                .deleteCart(userName);
    }

    @Test
    void testBuyArticleWithException() {
        List<CartSave> cartSaveList = new ArrayList<>();
        cartSaveList.add(cartDataBase);

        List<Integer> ids = new ArrayList<>();
        ids.add(ConstantsDomain.ID_ARTICLE);

        String userName = ConstantsDomain.EMAIL;

        Mockito.when(authenticationPersistencePort.getUserName()).thenReturn(userName);
        Mockito.when(cartPersistencePort.findAllCartByUserName(userName)).thenReturn(cartSaveList);
        Mockito.doThrow(new IllegalArgumentException()).when(cartStockPersistencePort)
                .getArticleDetails(ConstantsDomain.NUMBER_0, ConstantsDomain.NUMBER_1
                        ,false,ids, null, null);


        Assertions.assertThrows(PurchaseFailureException.class, () -> {
            cartUseCase.buyArticle();
        });


        Mockito.verify(cartPersistencePort, Mockito.times(ConstantsDomain.NUMBER_1))
                .findAllCartByUserName(userName);
        Mockito.verify(cartStockPersistencePort, Mockito.times(ConstantsDomain.NUMBER_1))
                .getArticleDetails(ConstantsDomain.NUMBER_0, ConstantsDomain.NUMBER_1
                        ,false,ids, null, null);
        Mockito.verify(authenticationPersistencePort, Mockito.times(ConstantsDomain.NUMBER_1))
                .getUserName();
        Mockito.verify(cartPersistencePort, Mockito.times(ConstantsDomain.NUMBER_0))
                .deleteCart(userName);
    }

    @Test
    void testBuyArticleNoDataFoundException () {
        List<CartSave> cartSaveList = new ArrayList<>();
        cartSaveList.add(cartDataBase);

        List<Integer> ids = new ArrayList<>();
        ids.add(ConstantsDomain.ID_ARTICLE);

        String userName = ConstantsDomain.EMAIL;

        Mockito.when(authenticationPersistencePort.getUserName()).thenReturn(userName);
        Mockito.when(cartPersistencePort.findAllCartByUserName(userName)).thenReturn(cartSaveList);
        Mockito.when(cartStockPersistencePort.getArticleDetails(ConstantsDomain.NUMBER_0, ConstantsDomain.NUMBER_1
                ,false,ids, null, null)).thenReturn(new ArrayList<>());

        Assertions.assertThrows(NoDataFoundException.class, () -> {
            cartUseCase.buyArticle();
        });

        Mockito.verify(authenticationPersistencePort, Mockito.times(ConstantsDomain.NUMBER_1))
                .getUserName();
        Mockito.verify(cartPersistencePort, Mockito.times(ConstantsDomain.NUMBER_1))
                .findAllCartByUserName(userName);
        Mockito.verify(cartStockPersistencePort, Mockito.times(ConstantsDomain.NUMBER_1))
                .getArticleDetails(ConstantsDomain.NUMBER_0, ConstantsDomain.NUMBER_1
                        ,false,ids, null, null);
    }
}