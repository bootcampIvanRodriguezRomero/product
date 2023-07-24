package com.bootcamp.products.application.dto;

import com.bootcamp.products.domain.model.Client;
import com.bootcamp.products.domain.model.product.ProductData;
import com.bootcamp.products.domain.model.product.ProductType;
import jakarta.validation.constraints.NotBlank;
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
  private ProductData data;
  private LocalDateTime created;
  private LocalDateTime updated;
  private List<Client> clients;
}