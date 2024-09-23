package com.inditex.tariff_manager.unit.entrypoints.tariff_management.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.inditex.tariff_manager.entrypoints.tariff_management.dto.TariffDto;
import com.inditex.tariff_manager.entrypoints.tariff_management.mappers.TariffToTariffDtoMapper;
import com.inditex.tariff_manager.object_mothers.TariffMother;
import com.inditex.tariff_manager.tariff_management.domain.read_model.Tariff;
import org.junit.jupiter.api.Test;

class TariffToTariffDtoMapperTest {

    @Test
    void tariffDtoIsMappedProperly() {
        // given
        Tariff tariff = TariffMother.random();

        // when
        TariffDto result = TariffToTariffDtoMapper.toTariffDto(tariff);

        // then
        assertEquals(tariff.getProduct().id().getValue(), result.getProductId());
        assertEquals(tariff.getBrand().id().getValue(), result.getBrandId());
        assertEquals(tariff.getId().getValue(), result.getTariffId());
        assertEquals(tariff.getStartDate().getValue(), result.getTariffStartDate());
        assertEquals(tariff.getEndDate().getValue(), result.getTariffEndDate());
        assertNotNull(result.getPrice());
    }
}