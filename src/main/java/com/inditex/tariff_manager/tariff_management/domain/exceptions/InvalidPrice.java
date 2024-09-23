package com.inditex.tariff_manager.tariff_management.domain.exceptions;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InvalidPrice extends RuntimeException {

    public static final String MESSAGE = "Invalid price";

    public InvalidPrice(String message) {
        super(MESSAGE + " : " + message);
    }

    public static InvalidPrice priceLessThanZero() {
        return new InvalidPrice("The price of a product cannot be less than zero.");
    }
}
