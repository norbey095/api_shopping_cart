package com.emazon.api_shopping_cart.infraestructure.input.rest;

import com.emazon.api_shopping_cart.application.dto.cart.CartRequestDto;
import com.emazon.api_shopping_cart.application.dto.cart.ResponseSuccess;
import com.emazon.api_shopping_cart.application.dto.cartdetail.ArticleCartRequestDto;
import com.emazon.api_shopping_cart.application.dto.cartdetail.CartDetailResponseDto;
import com.emazon.api_shopping_cart.application.handler.ICartHandler;
import com.emazon.api_shopping_cart.infraestructure.output.util.JwtService;
import com.emazon.api_shopping_cart.infraestructure.util.ConstantsInfTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class CartRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICartHandler cartHandler;

    @MockBean
    private JwtService jwtService;

    @Test
    @WithMockUser(username = ConstantsInfTest.USER_NAME, roles = {ConstantsInfTest.CLIENT})
    void createUserAux_ShouldReturnStatusCreated() throws Exception {
        ResponseSuccess responseSuccess = new ResponseSuccess(ConstantsInfTest.MESSAGESS_SUCCESS
                , HttpStatus.OK.toString());
        Mockito.when(cartHandler.saveArticle(Mockito.any(CartRequestDto.class)))
                .thenReturn(responseSuccess);

        mockMvc.perform(MockMvcRequestBuilders.post(ConstantsInfTest.URL_CART)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsInfTest.JSON_REQUEST))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @WithMockUser(username = ConstantsInfTest.USER_NAME, roles = {ConstantsInfTest.CLIENT})
    void deleteItems_ShouldReturnStatusOk() throws Exception {
        ResponseSuccess responseSuccess = new ResponseSuccess(ConstantsInfTest.MESSAGESS_SUCCESS
                , HttpStatus.OK.toString());
        Mockito.when(cartHandler.deleteArticle(ConstantsInfTest.NUMBER_1))
                .thenReturn(responseSuccess);

        mockMvc.perform(MockMvcRequestBuilders.delete(ConstantsInfTest.URL_CART+
                                ConstantsInfTest.ID_ARTICLE_DELETE + ConstantsInfTest.NUMBER_1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = ConstantsInfTest.USER_NAME, roles = {ConstantsInfTest.CLIENT})
    void getCartsByUserName_ShouldReturnStatusOk() throws Exception {
        CartDetailResponseDto cartDetailResponseDto = new CartDetailResponseDto();

        ArticleCartRequestDto articleCartRequestDto = new ArticleCartRequestDto();
        articleCartRequestDto.setSize(ConstantsInfTest.NUMBER_1);
        articleCartRequestDto.setPage(ConstantsInfTest.NUMBER_1);

        Mockito.when(cartHandler.getCart(articleCartRequestDto))
                .thenReturn(cartDetailResponseDto);

        mockMvc.perform(MockMvcRequestBuilders.get(ConstantsInfTest.URL_GET_CART+
                                ConstantsInfTest.ID_ARTICLE_DELETE + ConstantsInfTest.NUMBER_1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConstantsInfTest.JSON_REQUEST_GET))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}