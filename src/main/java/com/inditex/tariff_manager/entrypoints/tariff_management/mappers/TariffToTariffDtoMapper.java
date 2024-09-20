package com.inditex.tariff_manager.entrypoints.tariff_management.mappers;

import com.inditex.tariff_manager.entrypoints.tariff_management.dto.TariffDto;
import com.inditex.tariff_manager.tariff_management.domain.read_model.Tariff;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static java.time.ZoneOffset.UTC;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TariffToTariffDtoMapper {
    public static TariffDto toTariffDto(final Tariff tariff) {
        TariffDto tariffDto = new TariffDto();
        tariffDto.setProductId((long) tariff.getProduct().getId().getValue());
        tariffDto.setBrandId((long) tariff.getBrand().getId().getValue());
        tariffDto.setTariffId(tariff.getId().getValue());
        tariffDto.setTariffStartDate(tariff.getStartDate().getValue().atOffset(UTC));
        tariffDto.setTariffEndDate(tariff.getEndDate().getValue().atOffset(UTC));
        tariffDto.setPrice(PriceToTariffPriceDto.toTariffPriceDto(tariff.getPrice()));

        return tariffDto;
    }
}
