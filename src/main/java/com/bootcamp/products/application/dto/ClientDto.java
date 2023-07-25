package com.bootcamp.products.application.dto;

import com.bootcamp.products.domain.model.ClientType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import reactor.core.publisher.Flux;

@Data
public class ClientDto {
  @JsonProperty("identification")
  private String id;
  private ClientType type;
  private Flux<ProductDto> products;
  public ClientDto withProducts(Flux<ProductDto> products) {
    this.products = products;
    return this;
  }
}
