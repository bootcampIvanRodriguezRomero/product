package com.bootcamp.products.domain.model.product;

import com.bootcamp.products.domain.model.product.active.business.BusinessCreditCardData;
import com.bootcamp.products.domain.model.product.active.business.BusinessLoanData;
import com.bootcamp.products.domain.model.product.active.personal.PersonalCreditCardData;
import com.bootcamp.products.domain.model.product.active.personal.PersonalLoanData;
import com.bootcamp.products.domain.model.product.passive.business.BusinessCheckingAccountData;
import com.bootcamp.products.domain.model.product.passive.personal.FixedTermAccountData;
import com.bootcamp.products.domain.model.product.passive.personal.PersonalCheckingAccountData;
import com.bootcamp.products.domain.model.product.passive.personal.SavingAccountData;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = SavingAccountData.class, name = "savingAccountData"),
  @JsonSubTypes.Type(value = PersonalCheckingAccountData.class, name = "personalCheckingAccountData"),
  @JsonSubTypes.Type(value = FixedTermAccountData.class, name = "fixedTermAccountData"),
  @JsonSubTypes.Type(value = BusinessCheckingAccountData.class, name = "businessCheckingAccountData"),
  @JsonSubTypes.Type(value = PersonalLoanData.class, name = "personalLoanData"),
  @JsonSubTypes.Type(value = BusinessLoanData.class, name = "businessLoanData"),
  @JsonSubTypes.Type(value = PersonalCreditCardData.class, name = "personalCreditCardData"),
  @JsonSubTypes.Type(value = BusinessCreditCardData.class, name = "businessCreditCardData"),
})
public interface ProductData {
}
