package com.inditex.tariff_manager.entrypoints.tariff_management.mappers;

import com.inditex.tariff_manager.entrypoints.tariff_management.dto.TariffPriceDto;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.Price;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PriceToTariffPriceDto {

    public static TariffPriceDto toTariffPriceDto(final Price price) {
        TariffPriceDto tariffPriceDto = new TariffPriceDto();
        tariffPriceDto.setAmount(BigDecimal.valueOf(price.getAmount()));
        tariffPriceDto.setCurrency(price.getCurrency());

        return tariffPriceDto;
    }
}
