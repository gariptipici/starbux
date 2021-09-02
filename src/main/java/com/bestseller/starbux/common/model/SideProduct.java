package com.bestseller.starbux.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "SIDE_PRODUCT")
public class SideProduct extends AbstractProduct {
}
