package com.inditex.tariff_manager.tariff_management.domain.read_model;

import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.Price;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.TariffEndDate;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.TariffId;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.TariffStartDate;
import lombok.Builder;

@Builder(toBuilder = true)
public record Tariff(
    TariffId id,
    Product product,
    Brand brand,
    TariffStartDate startDate,
    TariffEndDate endDate,

    Price price
) {

}
