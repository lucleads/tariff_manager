package com.inditex.tariff_manager.entrypoints.tariff_management.mappers;

import static java.time.ZoneOffset.UTC;

import com.inditex.tariff_manager.entrypoints.tariff_management.dto.TariffDto;
import com.inditex.tariff_manager.tariff_management.domain.read_model.Tariff;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TariffToTariffDtoMapper {

    public static TariffDto toTariffDto(final Tariff tariff) {
        TariffDto tariffDto = new TariffDto();
        tariffDto.setProductId((long) tariff.product().id().getValue());
        tariffDto.setBrandId((long) tariff.brand().id().getValue());
        tariffDto.setTariffId(tariff.id().getValue());
        tariffDto.setTariffStartDate(tariff.startDate().getValue().atOffset(UTC));
        tariffDto.setTariffEndDate(tariff.endDate().getValue().atOffset(UTC));
        tariffDto.setPrice(PriceToTariffPriceDto.toTariffPriceDto(tariff.price()));

        return tariffDto;
    }
}
