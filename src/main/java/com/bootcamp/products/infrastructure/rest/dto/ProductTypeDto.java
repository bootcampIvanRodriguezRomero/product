package com.bootcamp.products.infrastructure.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Data Transfer Object (DTO) representing a product type.
 */
@Data
public class ProductTypeDto {
  @JsonProperty("identification")
  @Id
  private String id;
  private String name;
}
