package com.bootcamp.products.domain.model.product.active.personal;

import com.bootcamp.products.domain.model.product.ProductData;
import lombok.Data;

@Data
public class PersonalCreditCardData implements ProductData {
  private String cardNumber;
  private double creditLimit;
  private double totalAmountUsed;
  private double minimumMonthlyPayment;
}
