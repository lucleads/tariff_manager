package com.inditex.tariff_manager.tariff_management.infrastructure.adapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.inditex.tariff_manager.object_mothers.BrandIdMother;
import com.inditex.tariff_manager.object_mothers.PriceEntityMother;
import com.inditex.tariff_manager.object_mothers.ProductIdMother;
import com.inditex.tariff_manager.shared.persistence.h2.entities.PriceEntity;
import com.inditex.tariff_manager.shared.persistence.h2.repositories.PriceEntityRepository;
import com.inditex.tariff_manager.tariff_management.domain.read_model.Tariff;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.BrandId;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.ProductId;
import com.inditex.tariff_manager.tariff_management.infrastructure.exceptions.TariffNotFound;
import com.inditex.tariff_manager.tariff_management.infrastructure.mappers.TariffMapper;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TariffAdapterTest {

    @Mock
    private PriceEntityRepository repository;

    @InjectMocks
    private TariffAdapter tariffAdapter;

    @Test
    void successfulFindATariff() throws TariffNotFound, TariffNotFound {
        // Given
        PriceEntity priceEntity = PriceEntityMother.random();
        Tariff tariff = TariffMapper.toAggregate(priceEntity);
        ProductId productId = ProductIdMother.random();
        BrandId brandId = BrandIdMother.random();
        LocalDateTime date = LocalDateTime.now();

        when(repository.searchHighestPriorityPriceEntity(anyInt(), anyInt(), any(LocalDateTime.class)))
            .thenReturn(Optional.of(priceEntity));

        // When
        Tariff actualTariff = tariffAdapter.find(productId, brandId, date);

        // Then
        assertEquals(tariff, actualTariff);
        verify(repository, times(1)).searchHighestPriorityPriceEntity(productId.getValue(), brandId.getValue(), date);
    }

    @Test
    void failsWhenCannotFindATariff() throws TariffNotFound {
        // Given
        ProductId productId = ProductIdMother.random();
        BrandId brandId = BrandIdMother.random();
        LocalDateTime date = LocalDateTime.now();

        when(repository.searchHighestPriorityPriceEntity(anyInt(), anyInt(), any(LocalDateTime.class))).thenReturn(
            Optional.empty());

        // When
        Executable sutExecutable = () -> tariffAdapter.find(productId, brandId, date);

        // Then
        assertThrows(TariffNotFound.class, sutExecutable);
    }
}