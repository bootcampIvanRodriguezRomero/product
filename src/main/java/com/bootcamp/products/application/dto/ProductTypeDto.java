package com.bootcamp.products.application.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ProductTypeDto {
  private String id;
  @NotBlank
  private String name;
  @NotBlank
  private String description;
  private LocalDateTime created;
  private LocalDateTime updated;
}
