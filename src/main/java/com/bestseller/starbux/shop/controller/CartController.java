package com.bestseller.starbux.shop.controller;

import com.bestseller.starbux.shop.dto.CartDto;

import com.bestseller.starbux.shop.service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("shop/customers/{customerId}/carts")
public class CartController {

    public final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{cartId}")
    public CartDto readCart(@PathVariable Long customerId, @PathVariable Long cartId){
        return cartService.readCart(customerId, cartId);
    }

    @PutMapping("/{cartId}")
    public CartDto updateCart(@PathVariable Long customerId, @PathVariable Long cartId, @RequestBody CartDto cartDto){
        return cartService.updateCart(customerId, cartId, cartDto);
    }
}
