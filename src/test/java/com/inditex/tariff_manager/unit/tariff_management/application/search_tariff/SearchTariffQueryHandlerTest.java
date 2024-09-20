package com.inditex.tariff_manager.unit.tariff_management.application.search_tariff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import java.time.LocalDateTime;
import org.instancio.Instancio;
import org.instancio.junit.InstancioExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({InstancioExtension.class, MockitoExtension.class})
class SearchTariffQueryHandlerTest {

    @Mock
    private PriceEntityRepository repository;

    @InjectMocks
    private TariffAdapter tariffAdapter;

    @Test
    void priceEntityRepositoryIsInvokedAndTheEntityReturnedHasProperlyValues() {
        // given
        PriceEntity priceEntity = Instancio.create(PriceEntity.class);

        when(repository.findHighestPriorityPriceEntity(anyInt(), anyInt(), any(LocalDateTime.class)))
            .thenReturn(priceEntity);

        ProductId productId = Instancio.create(ProductId.class);
        BrandId brandId = Instancio.create(BrandId.class);
        LocalDateTime date = Instancio.create(LocalDateTime.class);

        // when
        Tariff result = tariffAdapter.search(productId, brandId, date);

        // then
        assertEquals(TariffId.of(priceEntity.getPriceList()), result.id());
        assertEquals(ProductId.of(priceEntity.getProductId()), result.product().id());
        assertEquals(BrandId.of(priceEntity.getBrandId()), result.brand().id());
        assertEquals(TariffStartDate.of(priceEntity.getStartDate()), result.startDate());
        assertEquals(TariffEndDate.of(priceEntity.getEndDate()), result.endDate());
        assertEquals(Price.of(priceEntity.getPrice(), priceEntity.getCurrency()), result.price());

        verify(repository, times(1)).findHighestPriorityPriceEntity(productId.getValue(), brandId.getValue(), date);
    }
}