package com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects;

import lombok.Value;

import java.time.LocalDateTime;

@Value(staticConstructor = "of")
public class TariffStartDate {
    LocalDateTime value;
}
