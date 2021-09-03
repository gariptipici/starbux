package com.bestseller.starbux.shop.dto;

import com.bestseller.starbux.common.dto.BaseDto;
import com.bestseller.starbux.common.dto.ProductDto;
import com.bestseller.starbux.common.dto.SideProductDto;

import java.math.BigDecimal;
import java.util.List;

public class CartItemDto extends BaseDto {
    private Integer quantity;
    private BigDecimal price;
    private ProductDto product;
    private List<SideProductDto> sideProducts;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public List<SideProductDto> getSideProducts() {
        return sideProducts;
    }

    public void setSideProducts(List<SideProductDto> sideProducts) {
        this.sideProducts = sideProducts;
    }
}
