package com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects;

import com.inditex.tariff_manager.tariff_management.domain.exceptions.InvalidPrice;
import lombok.Value;

@Value(staticConstructor = "of")
public class Price {

    double amount;
    String currency;

    public Price(double amount, String currency) {

        if (amount < 0) {
            throw InvalidPrice.priceLessThanZero();
        }

        this.amount = amount;
        this.currency = currency;
    }
}
