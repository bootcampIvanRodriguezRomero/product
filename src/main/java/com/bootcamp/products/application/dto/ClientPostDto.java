package com.bootcamp.products.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClientPostDto {
  @NotBlank(message = "type is required")
  private String type;

}
