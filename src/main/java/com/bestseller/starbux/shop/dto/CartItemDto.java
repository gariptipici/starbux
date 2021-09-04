package com.bestseller.starbux.shop.dto;

import com.bestseller.starbux.common.dto.BaseDto;
import com.bestseller.starbux.common.dto.ProductDto;
import com.bestseller.starbux.common.dto.SideProductDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.List;

@ApiModel(description = "Class representing an item of the customer's cart in Starbux")
public class CartItemDto extends BaseDto {

    @ApiModelProperty(notes = "Quantity of the product in item.", example = "1", position = 2)
    private Integer quantity;

    @ApiModelProperty(notes = "Price of the product + side product in item.", hidden = true, position = 3)
    private BigDecimal price;

    @ApiModelProperty(notes = "Product in item.", position = 4)
    private ProductDto product;

    @ApiModelProperty(notes = "Side products in item.", position = 5)
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
