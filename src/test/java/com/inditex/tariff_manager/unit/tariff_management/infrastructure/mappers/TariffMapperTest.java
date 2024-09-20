package com.inditex.tariff_manager.unit.tariff_management.infrastructure.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.inditex.tariff_manager.shared.persistence.h2.entities.PriceEntity;
import com.inditex.tariff_manager.tariff_management.domain.read_model.Tariff;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.BrandId;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.Price;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.ProductId;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.TariffEndDate;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.TariffId;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.TariffStartDate;
import com.inditex.tariff_manager.tariff_management.infrastructure.mappers.TariffMapper;
import org.instancio.Instancio;
import org.instancio.junit.InstancioExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(InstancioExtension.class)
class TariffMapperTest {

    @Test
    void testToAggregateWithRandomValues() {
        // given
        PriceEntity priceEntity = Instancio.create(PriceEntity.class);

        // when
        Tariff result = TariffMapper.toAggregate(priceEntity);

        // then
        assertEquals(TariffId.of(priceEntity.getPriceList()), result.getId());
        assertEquals(ProductId.of(priceEntity.getProductId()), result.getProduct().id());
        assertEquals(BrandId.of(priceEntity.getBrandId()), result.getBrand().id());
        assertEquals(TariffStartDate.of(priceEntity.getStartDate()), result.getStartDate());
        assertEquals(TariffEndDate.of(priceEntity.getEndDate()), result.getEndDate());
        assertEquals(Price.of(priceEntity.getPrice(), priceEntity.getCurrency()), result.getPrice());
    }
}