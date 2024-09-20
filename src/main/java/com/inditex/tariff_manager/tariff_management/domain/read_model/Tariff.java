package com.inditex.tariff_manager.tariff_management.domain.read_model;

import com.inditex.tariff_manager.tariff_management.domain.exceptions.InvalidTariff;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.Price;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.TariffEndDate;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.TariffId;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.TariffStartDate;
import lombok.Builder;
import lombok.Getter;

@Getter
public final class Tariff {

    private final TariffId id;
    private final Product product;
    private final Brand brand;
    private final TariffStartDate startDate;
    private final TariffEndDate endDate;
    private final Price price;

    @Builder(toBuilder = true)
    public Tariff(
        TariffId id,
        Product product,
        Brand brand,
        TariffStartDate startDate,
        TariffEndDate endDate,
        Price price
    ) {

        if (endDate.getValue().isAfter(startDate.getValue())) {
            throw InvalidTariff.endDateEarlierThanStartDate();
        }

        this.id = id;
        this.product = product;
        this.brand = brand;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }
}
