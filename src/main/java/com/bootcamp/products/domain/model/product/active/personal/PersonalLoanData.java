package com.bootcamp.products.domain.model.product.active.personal;

import com.bootcamp.products.domain.model.product.ProductData;
import lombok.Data;

@Data
public class PersonalLoanData implements ProductData {
  private double creditLimit;
  private double totalAmountUsed;
  private double minimumMonthlyPayment;
}
