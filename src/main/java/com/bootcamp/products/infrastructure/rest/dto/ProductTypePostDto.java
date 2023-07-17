package com.bootcamp.products.infrastructure.rest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Data Transfer Object (DTO) representing a request to create a product type.
 */
@Data
public class ProductTypePostDto {
  @NotBlank(message = "Name is required")
  private String name;
}
