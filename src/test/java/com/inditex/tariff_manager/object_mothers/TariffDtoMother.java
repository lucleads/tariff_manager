package com.inditex.tariff_manager.object_mothers;

import com.inditex.tariff_manager.entrypoints.tariff_management.dto.TariffDto;
import com.inditex.tariff_manager.entrypoints.tariff_management.dto.TariffPriceDto;
import com.inditex.tariff_manager.shared.persistence.h2.entities.PriceEntity;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TariffDtoMother {

    public static TariffDto fromPriceEntity(PriceEntity priceEntity) {
        TariffDto tariffDto = new TariffDto();
        TariffPriceDto priceDto = new TariffPriceDto();
        priceDto.setAmount(BigDecimal.valueOf(priceEntity.getPrice()));
        priceDto.setCurrency(priceEntity.getCurrency());
        tariffDto.setPrice(priceDto);
        tariffDto.setTariffId(priceEntity.getPriceList());
        tariffDto.setBrandId(priceEntity.getBrandId());
        tariffDto.setTariffEndDate(priceEntity.getEndDate());
        tariffDto.setTariffStartDate(priceEntity.getStartDate());
        tariffDto.setProductId(priceEntity.getProductId());

        return tariffDto;
    }
}
