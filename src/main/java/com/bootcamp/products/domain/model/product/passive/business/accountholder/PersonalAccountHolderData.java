package com.bootcamp.products.domain.model.product.passive.business.accountholder;

import lombok.Data;

@Data
public class PersonalAccountHolderData implements AccountHolderData {
  private String documentType;
  private String documentNumber;
  private String fullName;
  private String email;
}
