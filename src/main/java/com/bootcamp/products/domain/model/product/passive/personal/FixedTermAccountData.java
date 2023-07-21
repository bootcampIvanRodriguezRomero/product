package com.bootcamp.products.domain.model.product.passive.personal;

import com.bootcamp.products.domain.model.product.ProductData;
import com.bootcamp.products.domain.model.DebitCard;
import lombok.Data;

@Data
public class FixedTermAccountData implements ProductData {
    private String accountNumber;
    private double minimumOpeningAmount;
    private int specificDayOfMonth;
    private DebitCard debitCard;
}
