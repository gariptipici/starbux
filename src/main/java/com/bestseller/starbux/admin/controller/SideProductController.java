package com.bestseller.starbux.admin.controller;

import com.bestseller.starbux.admin.service.ProductService;
import com.bestseller.starbux.common.dto.SideProductDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/sideproducts")
public class SideProductController {

    private final ProductService productService;

    public SideProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public SideProductDto createSideProduct(@RequestBody SideProductDto sideProductDto){
        return productService.createSideProduct(sideProductDto);
    }

    @GetMapping
    public List<SideProductDto> getSideProducts(){
        return productService.readSideProducts();
    }
}
