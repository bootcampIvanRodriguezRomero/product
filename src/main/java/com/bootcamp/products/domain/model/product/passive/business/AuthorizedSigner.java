package com.bootcamp.products.domain.model.product.passive.business;

import lombok.Data;

@Data
public class AuthorizedSigner {
  private String documentType;
  private String documentNumber;
  private String fullName;
  private String email;
}
