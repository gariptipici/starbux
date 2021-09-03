package com.bestseller.starbux.admin.service;

import com.bestseller.starbux.common.dto.ProductDto;
import com.bestseller.starbux.common.dto.SideProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);
    SideProductDto createSideProduct(SideProductDto sideProductDto);

    ProductDto readProduct(Long productId);
    SideProductDto readSideProduct(Long sideProductId);

    List<ProductDto> readProducts();
    List<SideProductDto> readSideProducts();

    ProductDto updateProduct(Long productId, ProductDto productDto);
    SideProductDto updateSideProduct(Long sideProductId, SideProductDto sideProductDto);

    void deleteProduct(Long productId);
    void deleteSideProduct(Long sideProductId);
}
