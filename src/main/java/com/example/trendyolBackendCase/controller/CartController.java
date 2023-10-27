package com.example.trendyolBackendCase.controller;


import com.example.trendyolBackendCase.dto.CartDTO;
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

    @DeleteMapping//TODO
    public ResponseEntity<Boolean> resetCart()
    {
        return new ResponseEntity<>(cartService.resetCart(), HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<CartDTO> displayCart()
    {
        return new ResponseEntity<>(cartService.displayCart() ,HttpStatus.CREATED);
    }


}
