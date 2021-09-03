package com.bestseller.starbux.admin.controller;

import com.bestseller.starbux.admin.service.ProductService;
import com.bestseller.starbux.common.dto.ProductDto;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(consumes = "application/json")
    public ProductDto createProduct(@RequestBody ProductDto productDto){
        return productService.createProduct(productDto);
    }

    @GetMapping
    public List<ProductDto> readProducts(){
        return productService.readProducts();
    }

    @GetMapping("/{productId}")
    public ProductDto readProduct(@PathVariable Long productId){
        return productService.readProduct(productId);
    }

    @PutMapping("/{productId}")
    public ProductDto updateProduct(@PathVariable Long productId, @RequestBody ProductDto productDto){
        return productService.updateProduct(productId, productDto);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId){
        productService.deleteProduct(productId);
    }
}
