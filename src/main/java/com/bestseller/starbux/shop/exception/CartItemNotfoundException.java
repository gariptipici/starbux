package com.bestseller.starbux.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Cart item not found")
public class CartItemNotfoundException extends RuntimeException{
}
