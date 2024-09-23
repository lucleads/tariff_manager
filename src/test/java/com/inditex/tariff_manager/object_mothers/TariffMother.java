package com.inditex.tariff_manager.object_mothers;

import com.inditex.tariff_manager.tariff_management.domain.read_model.Tariff;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.TariffEndDate;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.TariffStartDate;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TariffMother {

    public static Tariff random() {

        return Tariff.builder()
            .id(TariffIdMother.random())
            .price(PriceMother.random())
            .brand(BrandMother.random())
            .endDate(TariffEndDate.of(LocalDateTime.now().plusDays(1)))
            .startDate(TariffStartDate.of(LocalDateTime.now()))
            .product(ProductMother.random())
            .build();
    }
}
