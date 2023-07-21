package com.bootcamp.products.domain.model.product.passive.personal;

import com.bootcamp.products.domain.model.product.ProductData;
import com.bootcamp.products.domain.model.DebitCard;
import lombok.Data;

@Data
public class PersonalCheckingAccountData implements ProductData {
  private String accountNumber;
  private double maintenanceFee;
  private static final int freeTransactionsLimit = 20;
  private double transactionFee;
  private DebitCard debitCard;
}
