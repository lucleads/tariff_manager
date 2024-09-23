package com.inditex.tariff_manager.tariff_management.domain.exceptions;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InvalidTariff extends RuntimeException {

    public static final String MESSAGE = "Invalid tariff";

    public InvalidTariff(String message) {
        super(MESSAGE + " : " + message);
    }

    public static InvalidTariff endDateEarlierThanStartDate() {
        return new InvalidTariff("The end date cannot be earlier than the start date.");
    }

}
