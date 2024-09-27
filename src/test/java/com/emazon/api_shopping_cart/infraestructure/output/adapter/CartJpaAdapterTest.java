package com.emazon.api_shopping_cart.infraestructure.output.adapter;

import com.emazon.api_shopping_cart.domain.model.CartSave;
import com.emazon.api_shopping_cart.domain.util.ConstantsDomain;
import com.emazon.api_shopping_cart.infraestructure.output.entity.CartEntity;
import com.emazon.api_shopping_cart.infraestructure.output.mapper.ICartEntityMapper;
import com.emazon.api_shopping_cart.infraestructure.output.repository.ICartRepository;
import com.emazon.api_shopping_cart.infraestructure.util.ConstantsInfTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class CartJpaAdapterTest {

    @Mock
    private ICartRepository cartRepository;

    @Mock
    private ICartEntityMapper cartEntityMapper;

    @InjectMocks
    private CartJpaAdapter cartJpaAdapter;

    private Integer day = ConstantsInfTest.NUMBER_10;

    @BeforeEach
    public void setUp() {
        cartJpaAdapter = new CartJpaAdapter(cartRepository, cartEntityMapper);
        ReflectionTestUtils.setField(cartJpaAdapter, ConstantsInfTest.DAY, day);
    }

    @Test
    void testSaveCart() {
        CartSave cartSave = new CartSave();
        CartEntity cartEntity = new CartEntity();

        Mockito.when(cartEntityMapper.cartToCartSaveEntity(cartSave)).thenReturn(cartEntity);

        cartJpaAdapter.saveCart(cartSave);

        Mockito.verify(cartRepository).save(cartEntity);
    }

    @Test
    void testFindCartByUserNameAndArticleId() {
        Integer articleId = ConstantsInfTest.NUMBER_1;
        String userName = ConstantsInfTest.NAME;
        CartEntity cartEntity = new CartEntity();
        CartSave expectedCartSave = new CartSave();

        Mockito.when(cartRepository.findCartByUserNameAndArticleId(articleId, userName))
                .thenReturn(Optional.of(cartEntity));
        Mockito.when(cartEntityMapper.cartEntityToCartSave(cartEntity)).thenReturn(expectedCartSave);

        CartSave actualCartSave = cartJpaAdapter.findCartByUserNameAndArticleId(articleId, userName);

        Assertions.assertEquals(expectedCartSave, actualCartSave);
    }

    @Test
    void testFindCartByUserName() {
        String userName = ConstantsInfTest.NAME;
        List<Integer> expectedCartIds = List.of(ConstantsInfTest.NUMBER_1, ConstantsInfTest.NUMBER_2
                , ConstantsInfTest.NUMBER_3);

        Mockito.when(cartRepository.findCartByUserName(userName)).thenReturn(expectedCartIds);

        List<Integer> actualCartIds = cartJpaAdapter.findCartByUserName(userName);

        Assertions.assertEquals(expectedCartIds, actualCartIds);
    }

    @Test
    void testGetNextDate() {
        LocalDate expectedNextDate = LocalDate.now().plusDays(day);

        LocalDate actualNextDate = cartJpaAdapter.getNextDate();

        Assertions.assertEquals(expectedNextDate, actualNextDate);
    }

    @Test
    void testDeleteCart() {
        Integer idArticle = ConstantsInfTest.NUMBER_1;
        String userName= ConstantsInfTest.NAME;

        cartJpaAdapter.deleteCart(idArticle,userName);

        Mockito.verify(cartRepository, Mockito.times(ConstantsDomain.NUMBER_1))
                .deleteByIdArticle(idArticle, userName);
    }

    @Test
    void testUpdateProductDateByEmail() {
        String userName= ConstantsInfTest.NAME;
        LocalDateTime updateDate = LocalDateTime.now().plusDays(day);

        cartJpaAdapter.updateProductDateByEmail(userName,updateDate);

        ArgumentCaptor<LocalDateTime> captor = ArgumentCaptor.forClass(LocalDateTime.class);
        Mockito.verify(cartRepository, Mockito.times(ConstantsDomain.NUMBER_1))
                .updateProductDateByEmail(Mockito.eq(userName), captor.capture());

        LocalDateTime capturedDate = captor.getValue();
        Assertions.assertTrue(capturedDate.isAfter(LocalDateTime.now().minusMinutes(1)) &&
                        capturedDate.isBefore(LocalDateTime.now().plusMinutes(1)),
                ConstantsDomain.DATE_NO_EXPE);
    }

    @Test
    void testFindAllCartByUserName() {
        String userName = ConstantsInfTest.NAME;

        Mockito.when(cartRepository.findAllCartByUserName(userName))
                .thenReturn(new ArrayList<>());
        Mockito.when(cartEntityMapper.cartEntityToCartSaveList(Mockito.anyList())).thenReturn(new ArrayList<>());

        cartJpaAdapter.findAllCartByUserName(userName);

        Mockito.verify(cartRepository, Mockito.times(ConstantsDomain.NUMBER_1))
                .findAllCartByUserName(userName);

        Mockito.verify(cartEntityMapper, Mockito.times(ConstantsDomain.NUMBER_1))
                .cartEntityToCartSaveList(Mockito.anyList());
    }
}