package com.inditex.tariff_manager.tariff_management.domain.read_model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.inditex.tariff_manager.object_mothers.BrandMother;
import com.inditex.tariff_manager.object_mothers.PriceMother;
import com.inditex.tariff_manager.object_mothers.ProductMother;
import com.inditex.tariff_manager.object_mothers.TariffIdMother;
import com.inditex.tariff_manager.tariff_management.domain.exceptions.InvalidTariff;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.Price;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.TariffEndDate;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.TariffId;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.TariffStartDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class TariffTest {

    @Test
    void buildTariffWithValidDates() {
        // given
        TariffId id = TariffIdMother.random();
        Product product = ProductMother.random();
        Brand brand = BrandMother.random();
        TariffStartDate startDate = TariffStartDate.of(LocalDateTime.now());
        TariffEndDate endDate = TariffEndDate.of(LocalDateTime.now().plusDays(1));
        Price price = PriceMother.random();

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
        TariffId id = TariffIdMother.random();
        Product product = ProductMother.random();
        Brand brand = BrandMother.random();
        LocalDateTime now = LocalDateTime.now();
        TariffStartDate startDate = TariffStartDate.of(now);
        TariffEndDate endDate = TariffEndDate.of(now);
        Price price = PriceMother.random();

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
        TariffId id = TariffIdMother.random();
        Product product = ProductMother.random();
        Brand brand = BrandMother.random();
        TariffStartDate startDate = TariffStartDate.of(LocalDateTime.now());
        TariffEndDate endDate = TariffEndDate.of(LocalDateTime.now().minusDays(1));
        Price price = PriceMother.random();

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