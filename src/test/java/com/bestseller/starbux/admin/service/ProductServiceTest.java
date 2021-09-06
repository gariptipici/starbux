package com.bestseller.starbux.admin.service;

import com.bestseller.starbux.admin.exception.ProductNotFoundException;
import com.bestseller.starbux.admin.repository.ProductRepository;
import com.bestseller.starbux.admin.repository.SideProductRepository;
import com.bestseller.starbux.admin.service.impl.ProductServiceImpl;
import com.bestseller.starbux.common.dto.ProductDto;
import com.bestseller.starbux.common.dto.SideProductDto;
import com.bestseller.starbux.common.mapper.ProductMapper;
import com.bestseller.starbux.common.model.Product;
import com.bestseller.starbux.common.model.SideProduct;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceTest {

  final ProductMapper productMapper = ProductMapper.INSTANCE;

  @Mock
  ProductRepository productRepository;

  @Mock
  SideProductRepository sideProductRepository;

  ProductService productService;

  SideProduct sideProduct;
  Product product;

  @BeforeEach
  public void init() {
    productService = new ProductServiceImpl(productRepository, sideProductRepository);

    product = new Product();
    product.setId(1L);
    product.setProductName("testProductName");
    product.setPrice(BigDecimal.TEN);

    sideProduct = new SideProduct();
    sideProduct.setId(1L);
    sideProduct.setProductName("testSideProductName");
    sideProduct.setPrice(BigDecimal.ONE);
  }

  @Test
  void createProductTest() {
    Mockito.when(productRepository.save(any())).thenReturn(product);
    ProductDto expected = productMapper.productToProductDto(product);
    ProductDto actual = productService.createProduct(new ProductDto());

    Assertions.assertEquals(expected.getProductName(), actual.getProductName());
  }

  @Test
  void createSideProductTest() {
    Mockito.when(sideProductRepository.save(any())).thenReturn(sideProduct);
    SideProductDto expected = productMapper.sideProductToSideProductDto(sideProduct);
    SideProductDto actual = productService.createSideProduct(new SideProductDto());

    Assertions.assertEquals(expected.getProductName(), actual.getProductName());
  }

  @Test
  void readProductTest() {
    Mockito.when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
    ProductDto expected = productMapper.productToProductDto(product);
    ProductDto actual = productService.readProduct(1L);

    Assertions.assertEquals(expected.getProductName(), actual.getProductName());
  }

  @Test
  void readProductTestNotFound() {
    Mockito.when(productRepository.findById(anyLong())).thenThrow(ProductNotFoundException.class);
    Assertions.assertThrows(ProductNotFoundException.class, () -> productService.readProduct(1L));
  }

  @Test
  void readSideProductTest() {
    Mockito.when(sideProductRepository.findById(anyLong())).thenReturn(Optional.of(sideProduct));
    SideProductDto expected = productMapper.sideProductToSideProductDto(sideProduct);
    SideProductDto actual = productService.readSideProduct(1L);

    Assertions.assertEquals(expected.getProductName(), actual.getProductName());
  }

  @Test
  void readSideProductTestNotFound() {
    Mockito.when(sideProductRepository.findById(anyLong()))
        .thenThrow(ProductNotFoundException.class);
    Assertions
        .assertThrows(ProductNotFoundException.class, () -> productService.readSideProduct(1L));
  }

  @Test
  void readProductsTest() {
    Mockito.when(productRepository.findAll()).thenReturn(Collections.singletonList(product));
    List<ProductDto> expected = Collections
        .singletonList(productMapper.productToProductDto(product));
    List<ProductDto> actual = productService.readProducts();
    Assertions.assertEquals(expected.get(0).getProductName(), actual.get(0).getProductName());
  }

  @Test
  void readSideProductsTest() {
    Mockito.when(sideProductRepository.findAll())
        .thenReturn(Collections.singletonList(sideProduct));
    List<SideProductDto> expected = Collections
        .singletonList(productMapper.sideProductToSideProductDto(sideProduct));
    List<SideProductDto> actual = productService.readSideProducts();
    Assertions.assertEquals(expected.get(0).getProductName(), actual.get(0).getProductName());
  }

  @Test
  void updateProductTest() {
    Mockito.when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
    product.setProductName("newProductName");
    Mockito.when(productRepository.save(any())).thenReturn(product);
    ProductDto expected = productMapper.productToProductDto(product);
    ProductDto actual = productService.updateProduct(1L, expected);
    Assertions.assertEquals(expected.getProductName(), actual.getProductName());
  }

  @Test
  void updateProductTestNotFound() {
    Mockito.when(productRepository.findById(anyLong())).thenThrow(ProductNotFoundException.class);
    product.setProductName("newProductName");
    Mockito.when(productRepository.save(any())).thenReturn(product);
    ProductDto expected = productMapper.productToProductDto(product);
    Assertions.assertThrows(ProductNotFoundException.class,
        () -> productService.updateProduct(1L, expected));
  }

  @Test
  void updateSideProductTest() {
    Mockito.when(sideProductRepository.findById(anyLong())).thenReturn(Optional.of(sideProduct));
    sideProduct.setProductName("newSideProductName");
    Mockito.when(sideProductRepository.save(any())).thenReturn(sideProduct);
    SideProductDto expected = productMapper.sideProductToSideProductDto(sideProduct);
    SideProductDto actual = productService.updateSideProduct(1L, expected);
    Assertions.assertEquals(expected.getProductName(), actual.getProductName());
  }

  @Test
  void updateSideProductTestNotFound() {
    Mockito.when(sideProductRepository.findById(anyLong()))
        .thenThrow(ProductNotFoundException.class);
    product.setProductName("newProductName");
    Mockito.when(sideProductRepository.save(any())).thenReturn(sideProduct);
    SideProductDto expected = productMapper.sideProductToSideProductDto(sideProduct);
    Assertions.assertThrows(ProductNotFoundException.class,
        () -> productService.updateSideProduct(1L, expected));
  }

  @Test
  void deleteProductTest() {
    productService.deleteProduct(anyLong());
    verify(productRepository, times(1)).deleteById(any());
  }

  @Test
  void deleteSideProductTest() {
    productService.deleteSideProduct(anyLong());
    verify(sideProductRepository, times(1)).deleteById(any());
  }

}
