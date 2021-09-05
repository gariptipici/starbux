package com.bestseller.starbux.admin.controller;

import com.bestseller.starbux.admin.service.ProductService;
import com.bestseller.starbux.common.dto.SideProductDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/sideproducts")
@Api("Set of endpoints for Creating, Retrieving, Updating and Deleting of side products.")
public class SideProductController {

  private final ProductService productService;

  public SideProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping(consumes = "application/json")
  @ApiOperation("Creates a new side product.")
  @ResponseStatus(HttpStatus.CREATED)
  public SideProductDto createSideProduct(
      @ApiParam("Side product information for a new side product to be created.") @RequestBody SideProductDto sideProductDto) {
    return productService.createSideProduct(sideProductDto);
  }

  @GetMapping
  @ApiOperation("Retrieves all of side product information.")
  @ResponseStatus(HttpStatus.OK)
  public List<SideProductDto> readSideProducts() {
    return productService.readSideProducts();
  }

  @GetMapping("/{sideProductId}")
  @ApiOperation("Retrieves the side product information.")
  @ResponseStatus(HttpStatus.OK)
  public SideProductDto readSideProduct(
      @ApiParam(value = "Id of the side product to be retrieved. Cannot be empty.", example = "1") @PathVariable Long sideProductId) {
    return productService.readSideProduct(sideProductId);
  }

  @PutMapping("/{sideProductId}")
  @ApiOperation("Updates the side product information.")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public SideProductDto updateSideProduct(
      @ApiParam(value = "Id of the side product to be updated. Cannot be empty.", example = "1") @PathVariable Long sideProductId,
      @ApiParam("New side product information for a the side product to be updated.") @RequestBody SideProductDto sideProductDto) {
    return productService.updateSideProduct(sideProductId, sideProductDto);
  }

  @DeleteMapping("/{sideProductId}")
  @ApiOperation("Deletes the side product information.")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteSideProduct(
      @ApiParam(value = "Id of the side product to be deleted. Cannot be empty.", example = "1") @PathVariable Long sideProductId) {
    productService.deleteSideProduct(sideProductId);
  }
}
