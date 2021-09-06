package com.bestseller.starbux.admin.service.impl;

import com.bestseller.starbux.admin.exception.ProductNotFoundException;
import com.bestseller.starbux.admin.repository.ProductRepository;
import com.bestseller.starbux.admin.repository.SideProductRepository;
import com.bestseller.starbux.common.dto.ProductDto;
import com.bestseller.starbux.common.dto.SideProductDto;
import com.bestseller.starbux.admin.service.ProductService;
import com.bestseller.starbux.common.mapper.ProductMapper;
import com.bestseller.starbux.common.model.Product;
import com.bestseller.starbux.common.model.SideProduct;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final SideProductRepository sideProductRepository;

  private static final ProductMapper productMapper = ProductMapper.INSTANCE;

  public ProductServiceImpl(ProductRepository productRepository,
      SideProductRepository sideProductRepository) {
    this.productRepository = productRepository;
    this.sideProductRepository = sideProductRepository;
  }

  @Override
  @Transactional
  public ProductDto createProduct(ProductDto productDto) {
    Product product = productRepository.save(productMapper.productDtoToProduct(productDto));
    return productMapper.productToProductDto(product);
  }

  @Override
  @Transactional
  public SideProductDto createSideProduct(SideProductDto sideProductDto) {
    SideProduct sideProduct = sideProductRepository
        .save(productMapper.sideProductDtoToSideProduct(sideProductDto));
    return productMapper.sideProductToSideProductDto(sideProduct);
  }

  @Override
  @Transactional(readOnly = true)
  public ProductDto readProduct(Long productId) {
    Product product = productRepository.findById(productId)
        .orElseThrow(ProductNotFoundException::new);
    return productMapper.productToProductDto(product);
  }

  @Override
  @Transactional(readOnly = true)
  public SideProductDto readSideProduct(Long sideProductId) {
    SideProduct sideProduct = sideProductRepository.findById(sideProductId)
        .orElseThrow(ProductNotFoundException::new);
    return productMapper.sideProductToSideProductDto(sideProduct);
  }

  @Override
  @Transactional(readOnly = true)
  public List<ProductDto> readProducts() {
    return productMapper.productsToProductDtos(productRepository.findAll());
  }

  @Override
  @Transactional(readOnly = true)
  public List<SideProductDto> readSideProducts() {
    return productMapper.sideProductsToSideProductDtos(sideProductRepository.findAll());
  }

  @Modifying
  @Override
  @Transactional
  public ProductDto updateProduct(Long productId, ProductDto productDto) {
    Product updated = productRepository.findById(productId)
        .map(existing -> {
          Product update = productMapper.productDtoToProduct(productDto);
          update.setId(existing.getId());
          return update;
        }).orElseThrow(ProductNotFoundException::new);

    return productMapper.productToProductDto(productRepository.save(updated));
  }

  @Modifying
  @Override
  @Transactional
  public SideProductDto updateSideProduct(Long sideProductId, SideProductDto sideProductDto) {
    SideProduct updated = sideProductRepository.findById(sideProductId)
        .map(existing -> {
          SideProduct update = productMapper.sideProductDtoToSideProduct(sideProductDto);
          update.setId(existing.getId());
          return update;
        }).orElseThrow(ProductNotFoundException::new);

    return productMapper.sideProductToSideProductDto(sideProductRepository.save(updated));
  }

  @Override
  @Transactional
  public void deleteProduct(Long productId) {
    productRepository.deleteById(productId);
  }

  @Override
  @Transactional
  public void deleteSideProduct(Long sideProductId) {
    sideProductRepository.deleteById(sideProductId);
  }
}
