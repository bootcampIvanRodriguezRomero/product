package com.bootcamp.products.application.dto;

import com.bootcamp.products.domain.model.Client;
import com.bootcamp.products.domain.model.product.ProductData;
import com.bootcamp.products.domain.model.product.ProductType;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class ProductDto {
  private String id;
  @NotBlank
  private ProductType type;
  @NotBlank
  private Boolean isActive;
  @NotBlank
  private Double balance;
  @NotBlank
  private Double interestRate;
  @Valid
  private ProductData data;
  private LocalDateTime created;
  private LocalDateTime updated;
  @NotEmpty
  private List<Client> clients;
}
