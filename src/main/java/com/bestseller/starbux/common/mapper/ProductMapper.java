package com.bestseller.starbux.common.mapper;

import com.bestseller.starbux.common.dto.ProductDto;
import com.bestseller.starbux.common.dto.SideProductDto;
import com.bestseller.starbux.common.model.Product;
import com.bestseller.starbux.common.model.SideProduct;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class ProductMapper {
    public static final ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    public abstract Product productDtoToProduct(ProductDto productDto);
    public abstract SideProduct sideProductDtoToSideProduct(SideProductDto sideProductDto);

    public abstract ProductDto productToProductDto(Product product);
    public abstract SideProductDto sideProductToSideProductDto(SideProduct sideProduct);

    public abstract List<ProductDto> productsToProductDtos(Iterable<Product> products);
    public abstract List<SideProductDto> sideProductsToSideProductDtos(Iterable<SideProduct> sideProducts);
}
