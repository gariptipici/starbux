package com.bestseller.starbux.admin.controller;

import com.bestseller.starbux.admin.exception.ProductNotFoundException;
import com.bestseller.starbux.admin.exception.ProductNotModifiableException;
import com.bestseller.starbux.admin.service.ProductService;
import com.bestseller.starbux.common.dto.ProductDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/products")
@Api("Set of endpoints for Creating, Retrieving, Updating and Deleting of products.")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @ApiOperation("Creates a new product.")
  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public ProductDto createProduct(
      @ApiParam("Product information for a new product to be created.") @RequestBody ProductDto productDto) {
    return productService.createProduct(productDto);
  }

  @GetMapping
  @ApiOperation("Retrieves all of product information.")
  @ResponseStatus(HttpStatus.OK)
  public List<ProductDto> readProducts() {
    return productService.readProducts();
  }

  @GetMapping("/{productId}")
  @ApiOperation("Retrieves the product information.")
  @ResponseStatus(HttpStatus.OK)
  public ProductDto readProduct(
      @ApiParam(value = "Id of the product to be retrieved. Cannot be empty.", example = "1") @PathVariable Long productId) {
    return productService.readProduct(productId);
  }

  @PutMapping("/{productId}")
  @ApiOperation("Updates the product information.")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public ProductDto updateProduct(
      @ApiParam(value = "Id of the product to be updated. Cannot be empty.", example = "1") @PathVariable Long productId,
      @ApiParam("New product information for a the product to be updated.") @RequestBody ProductDto productDto) {
    return productService.updateProduct(productId, productDto);
  }

  @DeleteMapping("/{productId}")
  @ApiOperation("Deletes the product information.")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteProduct(
      @ApiParam(value = "Id of the product to be deleted. Cannot be empty.", example = "1") @PathVariable Long productId) {
    try {
      productService.deleteProduct(productId);
    } catch (EmptyResultDataAccessException empty) {
      throw new ProductNotFoundException();
    } catch (Exception sql) {
      throw new ProductNotModifiableException();
    }
  }
}
