package com.example.trendyolBackendCase.controller;


import com.example.trendyolBackendCase.dto.CartDTO;
import com.example.trendyolBackendCase.dto.DisplayCartResponse;
import com.example.trendyolBackendCase.dto.GenericResponse;
import com.example.trendyolBackendCase.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @DeleteMapping("/resetCart")
    public ResponseEntity<GenericResponse> resetCart() {
        GenericResponse response = new GenericResponse();
        Boolean result = cartService.resetCart();
        response.setResult(result);
        response.setMessage(result ? "Cart reset successfully" : "Failed to reset cart");
        return new ResponseEntity<>(response, result ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/displayCart")
    public ResponseEntity<DisplayCartResponse> displayCart() {
        DisplayCartResponse response = new DisplayCartResponse();
        try {
            CartDTO cartDTO = cartService.displayCart();
            response.setResult(true);
            response.setMessage(cartDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setResult(false);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
