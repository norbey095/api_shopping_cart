package com.emazon.api_shopping_cart.infraestructure.exceptionhandler;

import com.emazon.api_shopping_cart.application.handler.ICartHandler;
import com.emazon.api_shopping_cart.domain.exception.*;
import com.emazon.api_shopping_cart.infraestructure.util.ConstantsInfTest;
import feign.FeignException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ControllerCartAdvisorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICartHandler cartHandler;

    @InjectMocks
    private ControllerCartAdvisor advisor;

    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;

    @Mock
    private FeignException.FeignClientException feignClientException;

    @Mock
    private FeignException feignException;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAccessDeniedException() {
        ResponseEntity<ExceptionResponse> response = advisor.handleAccessDeniedException(
                new AccessDeniedException(ConstantsInfTest.EMAIL));

        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ConstantsInfTest.ACCESS_DENE, response.getBody().getMessage());
    }

    @Test
    void testBadCredentialsException() {
        ResponseEntity<ExceptionResponse> response = advisor.handleBadCredentialsException(
                new BadCredentialsException(ConstantsInfTest.EMAIL));

        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ConstantsInfTest.INCORRECT_DATA, response.getBody().getMessage());
    }

    @Test
    void testCategoryLimitException() {
        ResponseEntity<ExceptionResponse> response = advisor.handleCategoryLimitException(
                new CategoryLimitException(ConstantsInfTest.EMAIL));

        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ConstantsInfTest.EMAIL, response.getBody().getMessage());
    }

    @Test
    void testTheItemIsNotAvailable() {
        ResponseEntity<ExceptionResponse> response = advisor.handleTheItemIsNotAvailable(
                new TheItemIsNotAvailable(ConstantsInfTest.EMAIL));

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ConstantsInfTest.EMAIL, response.getBody().getMessage());
    }

    @Test
    void testHandleFeignClientException() {
        Mockito.when(feignClientException.getMessage()).thenReturn(ConstantsInfTest.CLIENT_EXCEPTION);

        ResponseEntity<ExceptionResponse> response = advisor.handleFeignClientException(feignClientException);

        Assertions.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ConstantsInfTest.STOCK_CONFLICT, response.getBody().getMessage());
    }

    @Test
    void testHandleFeignException() {
        Mockito.when(feignException.getMessage()).thenReturn(ConstantsInfTest.FEIGN_EXCEPTION);

        ResponseEntity<ExceptionResponse> response = advisor.handleFeignException(feignException);

        Assertions.assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ConstantsInfTest.SERVICE_NOT_AVAILABLE, response.getBody().getMessage());
    }

    @Test
    void testTheArticleNotExistException() {
        ResponseEntity<ExceptionResponse> response = advisor.handleTheArticleNotExistException(
                new TheArticleNotExistException(ConstantsInfTest.EMAIL));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ConstantsInfTest.EMAIL, response.getBody().getMessage());
    }


    @Test
    void testNoDataFoundException() {
        ResponseEntity<ExceptionResponse> response = advisor.handleNoDataFoundException(new NoDataFoundException());

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ConstantsInfTest.NO_DATA_FOUND_EXCEPTION_MESSAGE, response.getBody().getMessage());
    }

    @Test
    void testPaginationNotAllowedException() {
        ResponseEntity<ExceptionResponse> response = advisor.handlePaginationNotAllowedException
                (new PaginationNotAllowedException());

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ConstantsInfTest.NEGATIVE_NOT_ALLOWED, response.getBody().getMessage());
    }
}