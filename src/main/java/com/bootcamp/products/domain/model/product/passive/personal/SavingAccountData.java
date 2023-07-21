package com.bootcamp.products.domain.model.product.passive.personal;

import com.bootcamp.products.domain.model.product.ProductData;
import com.bootcamp.products.domain.model.DebitCard;
import lombok.Data;

@Data
public class SavingAccountData implements ProductData {
  private String accountNumber;
  private int maxMonthlyTransactions;
  private double minimumOpeningAmount;
  private static final int freeTransactionsLimit = 20;
  private double transactionFee;
  private DebitCard debitCard;
}
