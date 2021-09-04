package com.bestseller.starbux.common.dto;

import io.swagger.annotations.ApiModelProperty;

public abstract class BaseDto {

  @ApiModelProperty(notes = "ID of the data transfer object.", example = "1", position = 1)
  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

}
