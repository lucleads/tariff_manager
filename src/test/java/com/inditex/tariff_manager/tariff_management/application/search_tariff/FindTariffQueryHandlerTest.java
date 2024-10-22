package com.inditex.tariff_manager.tariff_management.application.search_tariff;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.Price;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.ProductId;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.TariffEndDate;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.TariffId;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.TariffStartDate;
import com.inditex.tariff_manager.tariff_management.infrastructure.adapters.TariffAdapter;
import com.inditex.tariff_manager.tariff_management.infrastructure.exceptions.TariffNotFound;
import java.time.LocalDateTime;
import java.util.Optional;
import org.instancio.Instancio;
import org.instancio.junit.InstancioExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({InstancioExtension.class, MockitoExtension.class})
class FindTariffQueryHandlerTest {

    @Mock
    private PriceEntityRepository repository;

    @InjectMocks
    private TariffAdapter tariffAdapter;

    @Test
    void priceEntityRepositoryIsInvokedAndTheEntityReturnedHasProperlyValues() throws TariffNotFound {
        // given
        PriceEntity priceEntity = PriceEntityMother.random();

        when(repository.searchHighestPriorityPriceEntity(anyInt(), anyInt(), any(LocalDateTime.class)))
            .thenReturn(Optional.of(priceEntity));

        ProductId productId = ProductIdMother.random();
        BrandId brandId = BrandIdMother.random();
        LocalDateTime date = Instancio.create(LocalDateTime.class);

        // when
        Tariff result = tariffAdapter.find(productId, brandId, date);

        // then
        assertEquals(TariffId.of(priceEntity.getPriceList()), result.getId());
        assertEquals(ProductId.of(priceEntity.getProductId()), result.getProduct().id());
        assertEquals(BrandId.of(priceEntity.getBrandId()), result.getBrand().id());
        assertEquals(TariffStartDate.of(priceEntity.getStartDate()), result.getStartDate());
        assertEquals(TariffEndDate.of(priceEntity.getEndDate()), result.getEndDate());
        assertEquals(Price.of(priceEntity.getPrice(), priceEntity.getCurrency()), result.getPrice());

        verify(repository, times(1)).searchHighestPriorityPriceEntity(productId.getValue(), brandId.getValue(), date);
    }
}