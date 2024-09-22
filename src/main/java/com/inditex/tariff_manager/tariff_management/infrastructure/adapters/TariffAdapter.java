package com.inditex.tariff_manager.tariff_management.infrastructure.adapters;

import com.inditex.tariff_manager.shared.persistence.h2.repositories.PriceEntityRepository;
import com.inditex.tariff_manager.tariff_management.domain.ports.TariffPort;
import com.inditex.tariff_manager.tariff_management.domain.read_model.Tariff;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.BrandId;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.ProductId;
import com.inditex.tariff_manager.tariff_management.infrastructure.exceptions.TariffNotFound;
import com.inditex.tariff_manager.tariff_management.infrastructure.mappers.TariffMapper;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("unused")
public record TariffAdapter(
    PriceEntityRepository repository
) implements TariffPort {

    @Override
    public Tariff find(ProductId productId, BrandId brandId, LocalDateTime date) throws TariffNotFound {

        // In case the criteria applies to more fields, change the approach to use ExampleMatchers instead of the query
        // and filter the results by priority.
        return TariffMapper.toAggregate(
            repository.searchHighestPriorityPriceEntity(productId.getValue(), brandId.getValue(), date)
                .orElseThrow(TariffNotFound::tariffNotFound));
    }
}
