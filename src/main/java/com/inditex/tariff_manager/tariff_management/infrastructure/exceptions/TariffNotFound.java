package com.inditex.tariff_manager.tariff_management.infrastructure.exceptions;

public class TariffNotFound extends NotFoundException {

    public static final String TARIFF_NOT_FOUND_MESSAGE = "Tariff not found";

    TariffNotFound(String message) {
        super(message);
    }

    public static TariffNotFound tariffNotFound() {

        return new TariffNotFound(TARIFF_NOT_FOUND_MESSAGE);
    }
}
