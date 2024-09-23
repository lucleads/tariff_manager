package com.inditex.tariff_manager.integration.tariff_management.infrastructure.adapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.inditex.tariff_manager.integration.IntegrationTest;
import com.inditex.tariff_manager.object_mothers.BrandIdMother;
import com.inditex.tariff_manager.object_mothers.PriceEntityMother;
import com.inditex.tariff_manager.object_mothers.ProductIdMother;
import com.inditex.tariff_manager.shared.persistence.h2.entities.PriceEntity;
import com.inditex.tariff_manager.shared.persistence.h2.repositories.PriceEntityRepository;
import com.inditex.tariff_manager.tariff_management.domain.read_model.Tariff;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.BrandId;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.ProductId;
import com.inditex.tariff_manager.tariff_management.infrastructure.adapters.TariffAdapter;
import com.inditex.tariff_manager.tariff_management.infrastructure.exceptions.TariffNotFound;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor(onConstructor_ = {@Autowired})
class TariffAdapterIT extends IntegrationTest {

    private final TariffAdapter sut;
    private final PriceEntityRepository priceEntityRepository;

    @Test
    void successfulTariffFind() throws TariffNotFound {
        // given
        PriceEntity existingPriceEntity = PriceEntityMother.random();
        priceEntityRepository.save(existingPriceEntity);

        // when
        Tariff actual = sut.find(ProductId.of(existingPriceEntity.getProductId()),
            BrandId.of(existingPriceEntity.getBrandId()), existingPriceEntity.getStartDate());

        // then
        assertNotNull(actual);
        assertEquals(existingPriceEntity.getPriceList(), actual.getId().getValue());
    }

    @Test
    void failsWhenCannotFindTheTariff() {
        // when
        Executable sutExecutable = () -> sut.find(ProductIdMother.random(), BrandIdMother.random(),
            LocalDateTime.now());

        // then
        assertThrows(TariffNotFound.class, sutExecutable);
    }

    @Test
    void retrieveTheHighestPriorityTariff() throws TariffNotFound {
        // given
        PriceEntity existingPriceEntityMinorPriority = PriceEntityMother.randomWithPriority(1);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        PriceEntity existingPriceEntityHighestPriority = objectMapper.convertValue(
            existingPriceEntityMinorPriority, PriceEntity.class
        );
        existingPriceEntityHighestPriority.setPriority(2);
        existingPriceEntityHighestPriority.setPriceList(2);
        priceEntityRepository.save(existingPriceEntityMinorPriority);
        priceEntityRepository.save(existingPriceEntityHighestPriority);

        // when
        Tariff actual = sut.find(ProductId.of(existingPriceEntityMinorPriority.getProductId()),
            BrandId.of(existingPriceEntityMinorPriority.getBrandId()), existingPriceEntityMinorPriority.getStartDate());

        // then
        assertEquals(2, actual.getId().getValue());
    }

    @Test
    void onlyRetrieveTheTariffIfSearchedDateIsBetweenStartAndEndDates() {
        // given
        PriceEntity existingPriceEntity = PriceEntityMother.random();
        existingPriceEntity.setStartDate(LocalDateTime.now().minusDays(1));
        existingPriceEntity.setEndDate(LocalDateTime.now().plusDays(1));
        priceEntityRepository.save(existingPriceEntity);

        // when
        Executable sutFindBeforeDate = () -> sut.find(ProductId.of(existingPriceEntity.getProductId()),
            BrandId.of(existingPriceEntity.getBrandId()), LocalDateTime.now().minusDays(2));
        Executable sutFindAfterDate = () -> sut.find(ProductId.of(existingPriceEntity.getProductId()),
            BrandId.of(existingPriceEntity.getBrandId()), LocalDateTime.now().plusDays(2));

        // then
        assertThrows(TariffNotFound.class, sutFindBeforeDate);
        assertThrows(TariffNotFound.class, sutFindAfterDate);
    }
}