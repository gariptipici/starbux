package com.bestseller.starbux.common.dto;

import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;

public abstract class AbstractProductDto extends BaseDto {

    @ApiModelProperty(notes = "Name of the product.", example = "Espresso", required = true, position = 2)
    private String productName;

    @ApiModelProperty(notes = "Price of the product.", example = "5.0", required = true, position = 3)
    private BigDecimal price;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
