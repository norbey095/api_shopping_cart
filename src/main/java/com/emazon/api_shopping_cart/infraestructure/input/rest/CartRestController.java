package com.emazon.api_shopping_cart.infraestructure.input.rest;

import com.emazon.api_shopping_cart.application.dto.cart.CartRequestDto;
import com.emazon.api_shopping_cart.application.dto.cart.ResponseSuccess;
import com.emazon.api_shopping_cart.application.dto.cartdetail.CartDetailResponseDto;
import com.emazon.api_shopping_cart.application.handler.ICartHandler;
import com.emazon.api_shopping_cart.infraestructure.util.ConstantsConfig;
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

    private final ICartHandler cartHandler;

    @Operation(summary = "Add a new article to cart",
            description = "Add a new article to cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Article created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid fields", content = @Content)
    })
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PostMapping("/")
    public ResponseEntity<ResponseSuccess> createCart(@Validated @RequestBody CartRequestDto cartRequestDto){
        ResponseSuccess responseSuccess = cartHandler.saveArticle(cartRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseSuccess);
    }

    @Operation(summary = "Delete article to cart",
            description = "Delete article to cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok, item successfully deleted", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid action", content = @Content)
    })
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @DeleteMapping("/")
    public ResponseEntity<ResponseSuccess> deleteCart(@Validated @RequestParam Integer idArticle){
        ResponseSuccess responseSuccess = cartHandler.deleteArticle(idArticle);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseSuccess);
    }

    @Operation(summary = "Get my cart",
            description = "Get my cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "No data found in the database", content = @Content),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content)
    })
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @GetMapping("/")
    public ResponseEntity<CartDetailResponseDto> getCartsByUserName(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam(required = false, defaultValue = ConstantsConfig.FALSE) boolean descending,
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) String brandName) {
        return ResponseEntity.ok(cartHandler.getCart(page,size,descending,categoryName,brandName));
    }

    @Operation(summary = "Buy articles",
            description = "Buy articles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content)
    })
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PostMapping("/buy")
    public ResponseEntity<ResponseSuccess> buyArticle(){
        ResponseSuccess responseSuccess = cartHandler.buyCart();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseSuccess);
    }
}
