package com.inditex.tariff_manager.unit.tariff_management.domain.read_model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.inditex.tariff_manager.tariff_management.domain.exceptions.InvalidTariff;
import com.inditex.tariff_manager.tariff_management.domain.read_model.Brand;
import com.inditex.tariff_manager.tariff_management.domain.read_model.Product;
import com.inditex.tariff_manager.tariff_management.domain.read_model.Tariff;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.Price;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.TariffEndDate;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.TariffId;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.TariffStartDate;
import java.time.LocalDateTime;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class TariffTest {

    @Test
    void buildTariffWithValidDates() {
        // given
        TariffId id = Instancio.create(TariffId.class);
        Product product = Instancio.create(Product.class);
        Brand brand = Instancio.create(Brand.class);
        TariffStartDate startDate = TariffStartDate.of(LocalDateTime.now());
        TariffEndDate endDate = TariffEndDate.of(LocalDateTime.now().minusDays(1));
        Price price = Instancio.create(Price.class);

        // when
        Tariff tariff = Tariff.builder()
            .id(id)
            .product(product)
            .brand(brand)
            .startDate(startDate)
            .endDate(endDate)
            .price(price)
            .build();

        // then
        assertNotNull(tariff);
        assertEquals(id, tariff.getId());
        assertEquals(product, tariff.getProduct());
        assertEquals(brand, tariff.getBrand());
        assertEquals(startDate, tariff.getStartDate());
        assertEquals(endDate, tariff.getEndDate());
        assertEquals(price, tariff.getPrice());
    }

    @Test
    void buildTariffWithSameStartDateAndEndDate() {
        // given
        TariffId id = Instancio.create(TariffId.class);
        Product product = Instancio.create(Product.class);
        Brand brand = Instancio.create(Brand.class);
        LocalDateTime now = LocalDateTime.now();
        TariffStartDate startDate = TariffStartDate.of(now);
        TariffEndDate endDate = TariffEndDate.of(now);
        Price price = Instancio.create(Price.class);

        // when
        Tariff tariff = Tariff.builder()
            .id(id)
            .product(product)
            .brand(brand)
            .startDate(startDate)
            .endDate(endDate)
            .price(price)
            .build();

        // then
        assertNotNull(tariff);
        assertEquals(startDate, tariff.getStartDate());
        assertEquals(endDate, tariff.getEndDate());
    }

    @Test
    void buildTariffWithEndDateBeforeStartDateThrowsException() {
        // given
        TariffId id = Instancio.create(TariffId.class);
        Product product = Instancio.create(Product.class);
        Brand brand = Instancio.create(Brand.class);
        TariffStartDate startDate = TariffStartDate.of(LocalDateTime.now());
        TariffEndDate endDate = TariffEndDate.of(LocalDateTime.now().minusDays(1));
        Price price = Instancio.create(Price.class);

        // when
        Executable sutExecutable = () -> Tariff
            .builder()
            .id(id)
            .product(product)
            .brand(brand)
            .startDate(startDate)
            .endDate(endDate)
            .price(price)
            .build();

        // then
        InvalidTariff exception = assertThrows(InvalidTariff.class, sutExecutable);
        assertEquals(InvalidTariff.endDateEarlierThanStartDate().getMessage(), exception.getMessage());
    }
}