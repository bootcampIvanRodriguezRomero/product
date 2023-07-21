package com.bootcamp.products.domain.model.product.passive.business;

import com.bootcamp.products.domain.model.product.ProductData;
import com.bootcamp.products.domain.model.DebitCard;
import com.bootcamp.products.domain.model.product.passive.business.accountholder.AccountHolder;
import java.util.List;
import lombok.Data;

@Data
public class BusinessCheckingAccountData implements ProductData {
  private String accountNumber;
  private double maintenanceFee;
  private static final int freeTransactionsLimit = 20;
  private double transactionFee;
  private DebitCard debitCard;
  private List<AccountHolder> accountHolders;
  private List<AuthorizedSigner> authorizedSigners;
}
