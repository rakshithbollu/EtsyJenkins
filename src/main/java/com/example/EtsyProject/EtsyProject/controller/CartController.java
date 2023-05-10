package com.example.EtsyProject.EtsyProject.controller;

import com.example.EtsyProject.EtsyProject.entity.Cart;
import com.example.EtsyProject.EtsyProject.service.CartService;
import com.example.EtsyProject.EtsyProject.service.Response.CartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class CartController {
    private CartService cartService;

    @Autowired
    public CartController(CartService cartService){

        this.cartService = cartService;
    }

    @PostMapping("/cart")
    public ResponseEntity<String> addCartDetails(@RequestBody Cart cart) {
        return ResponseEntity.ok(cartService.addCartDetails(cart));
    }

    @GetMapping("/cart/{email}")
    public ResponseEntity<Optional<List<CartResponse>>> getCartDetails(@PathVariable String email) throws Exception {
        return ResponseEntity.ok(cartService.getCartItems(email));
    }

    @GetMapping("/deletecart/{cartid}")
    public ResponseEntity<String> deleteCartDetails(@PathVariable Integer cartid) {
        return ResponseEntity.ok(cartService.deleteCartItems(cartid));
    }
}
