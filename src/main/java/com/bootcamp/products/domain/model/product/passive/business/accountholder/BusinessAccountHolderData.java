package com.bootcamp.products.domain.model.product.passive.business.accountholder;

import lombok.Data;

@Data
public class BusinessAccountHolderData implements AccountHolderData {
  private String businessName;
  private String businessRegistrationNumber;
  private String contactPersonFullName;
  private String contactPersonEmail;
}
