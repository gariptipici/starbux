package com.bestseller.starbux.admin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Product not found")
public class ProductNotModifiableException extends RuntimeException{
}
