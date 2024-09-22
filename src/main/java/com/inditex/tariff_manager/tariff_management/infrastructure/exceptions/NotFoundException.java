package com.inditex.tariff_manager.tariff_management.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public abstract class NotFoundException extends Exception {

    NotFoundException(String message) {
        super(message);
    }

}
