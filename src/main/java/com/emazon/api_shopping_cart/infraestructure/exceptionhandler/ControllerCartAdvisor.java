package com.emazon.api_shopping_cart.infraestructure.exceptionhandler;

import com.emazon.api_shopping_cart.domain.exception.CategoryLimitException;
import com.emazon.api_shopping_cart.domain.exception.TheItemIsNotAvailable;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerCartAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException
            (MethodArgumentNotValidException ex) {
        StringBuilder messageBuilder = new StringBuilder();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();
            messageBuilder.append(errorMessage);
        });

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                messageBuilder.toString().trim(),
                HttpStatus.BAD_REQUEST.toString()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(FeignException.FeignClientException.class)
    public ResponseEntity<ExceptionResponse> handleFeignClientException(FeignException.FeignClientException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).
                body(new ExceptionResponse(ExceptionResponseConstants.STOCK_CONFLICT.getMessage()
                        , HttpStatus.CONFLICT.toString()));
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ExceptionResponse> handleFeignException(FeignException exception) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).
                body(new ExceptionResponse(ExceptionResponseConstants.SERVICE_NOT_AVAILABLE.getMessage()
                        , HttpStatus.SERVICE_UNAVAILABLE.toString()));

    }

    @ExceptionHandler(TheItemIsNotAvailable.class)
    public ResponseEntity<ExceptionResponse> handleTheItemIsNotAvailable(TheItemIsNotAvailable exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).
                body(new ExceptionResponse(exception.getMessage()
                        , HttpStatus.NOT_FOUND.toString()));
    }

    @ExceptionHandler(CategoryLimitException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryLimitException(CategoryLimitException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).
                body(new ExceptionResponse(exception.getMessage()
                        , HttpStatus.FORBIDDEN.toString()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleBadCredentialsException (BadCredentialsException  exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionResponse(
                ExceptionResponseConstants.INCORRECT_DATA.getMessage(),
                HttpStatus.UNAUTHORIZED.toString()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponse> handleAccessDeniedException (AccessDeniedException  exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ExceptionResponse(
                ExceptionResponseConstants.ACCESS_DENE.getMessage(),
                HttpStatus.FORBIDDEN.toString()));
    }

}