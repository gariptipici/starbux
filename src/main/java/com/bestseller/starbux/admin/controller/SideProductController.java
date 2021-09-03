package com.bestseller.starbux.admin.controller;

import com.bestseller.starbux.admin.service.ProductService;
import com.bestseller.starbux.common.dto.ProductDto;
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

    @PostMapping(consumes = "application/json")
    public SideProductDto createSideProduct(@RequestBody SideProductDto sideProductDto){
        return productService.createSideProduct(sideProductDto);
    }

    @GetMapping
    public List<SideProductDto> readSideProducts(){
        return productService.readSideProducts();
    }

    @GetMapping("/{sideProductId}")
    public SideProductDto readSideProduct(@PathVariable Long sideProductId){
        return productService.readSideProduct(sideProductId);
    }

    @PutMapping("/{sideProductId}")
    public SideProductDto updateSideProduct(@PathVariable Long sideProductId, @RequestBody SideProductDto sideProductDto){
        return productService.updateSideProduct(sideProductId, sideProductDto);
    }

    @DeleteMapping("/{sideProductId}")
    public void deleteSideProduct(@PathVariable Long sideProductId){
        productService.deleteSideProduct(sideProductId);
    }
}
