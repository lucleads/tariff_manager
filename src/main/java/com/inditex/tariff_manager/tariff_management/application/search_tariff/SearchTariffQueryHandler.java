package com.inditex.tariff_manager.tariff_management.application.search_tariff;

import com.inditex.tariff_manager.tariff_management.domain.ports.TariffPort;
import com.inditex.tariff_manager.tariff_management.domain.read_model.Tariff;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.BrandId;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.ProductId;
import org.springframework.stereotype.Service;

@Service
public record SearchTariffQueryHandler(
    TariffPort tariffPort
) {

    public Tariff searchTariff(final SearchTariffQuery query) {
        return tariffPort.search(ProductId.of(query.productId()), BrandId.of(query.brandId()), query.date());
    }
}
