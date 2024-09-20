package com.inditex.tariff_manager.tariff_management.infrastructure.adapters;

import com.inditex.tariff_manager.shared.persistence.h2.repositories.PriceEntityRepository;
import com.inditex.tariff_manager.tariff_management.domain.ports.TariffPort;
import com.inditex.tariff_manager.tariff_management.domain.read_model.Tariff;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.BrandId;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.ProductId;
import com.inditex.tariff_manager.tariff_management.infrastructure.mappers.TariffMapper;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public record TariffAdapter(
    PriceEntityRepository repository
) implements TariffPort {

    @Override
    public Tariff search(ProductId productId, BrandId brandId, LocalDateTime date) {

        // In case the criteria applies to more fields, change the approach to use ExampleMatchers instead of the query
        // and filter the results by priority.
        return TariffMapper.toAggregate(
            repository.findHighestPriorityPriceEntity(productId.getValue(), brandId.getValue(), date)
        );
    }
}
