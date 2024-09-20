package com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects;

import java.time.LocalDateTime;
import lombok.Value;

@Value(staticConstructor = "of")
public class TariffStartDate {

    LocalDateTime value;
}
