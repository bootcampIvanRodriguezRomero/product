package com.bootcamp.products.infrastructure.rest.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.Data;

/**
 * Data Transfer Object (DTO) representing the payload for creating a new product.
 */
@Data
public class ProductPostDto {
  @NotBlank(message = "Type is required")
  private String type;
  @NotBlank(message = "Amount is required")
  private double amount;
}
