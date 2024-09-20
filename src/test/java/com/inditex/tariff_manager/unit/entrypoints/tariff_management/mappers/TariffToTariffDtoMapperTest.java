package com.inditex.tariff_manager.unit.entrypoints.tariff_management.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.inditex.tariff_manager.entrypoints.tariff_management.dto.TariffDto;
import com.inditex.tariff_manager.entrypoints.tariff_management.mappers.TariffToTariffDtoMapper;
import com.inditex.tariff_manager.tariff_management.domain.read_model.Tariff;
import java.time.ZoneOffset;
import org.instancio.Instancio;
import org.instancio.junit.InstancioExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(InstancioExtension.class)
class TariffToTariffDtoMapperTest {

    @Test
    void tariffDtoIsMappedProperly() {
        // given
        Tariff tariff = Instancio.create(Tariff.class);

        // when
        TariffDto result = TariffToTariffDtoMapper.toTariffDto(tariff);

        // then
        assertEquals(tariff.product().id().getValue(), result.getProductId());
        assertEquals(tariff.brand().id().getValue(), result.getBrandId());
        assertEquals(tariff.id().getValue(), result.getTariffId());
        assertEquals(tariff.startDate().getValue().atOffset(ZoneOffset.UTC), result.getTariffStartDate());
        assertEquals(tariff.endDate().getValue().atOffset(ZoneOffset.UTC), result.getTariffEndDate());
        assertNotNull(result.getPrice());
    }
}