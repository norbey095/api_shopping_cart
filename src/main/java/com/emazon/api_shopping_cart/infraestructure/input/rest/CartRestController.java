package com.emazon.api_shopping_cart.infraestructure.input.rest;

import com.emazon.api_shopping_cart.application.dto.CartSaveRequestDto;
import com.emazon.api_shopping_cart.application.dto.ResponseSuccess;
import com.emazon.api_shopping_cart.application.handler.ICartHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopping/cart")
@RequiredArgsConstructor
public class CartRestController {

    private final ICartHandler cartSaveHandler;

    @Operation(summary = "Add a new article to cart",
            description = "Add a new article to cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "article created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid fields", content = @Content)
    })
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PostMapping("/")
    public ResponseEntity<ResponseSuccess> createCart(@Validated @RequestBody CartSaveRequestDto cartSaveRequestDto){
        ResponseSuccess responseSuccess = cartSaveHandler.saveArticle(cartSaveRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseSuccess);
    }
}
