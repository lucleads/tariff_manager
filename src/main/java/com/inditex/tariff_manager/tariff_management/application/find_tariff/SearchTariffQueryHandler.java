package com.inditex.tariff_manager.tariff_management.application.find_tariff;

import com.inditex.tariff_manager.tariff_management.domain.ports.TariffPort;
import com.inditex.tariff_manager.tariff_management.domain.read_model.Tariff;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.BrandId;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.ProductId;
import com.inditex.tariff_manager.tariff_management.infrastructure.exceptions.TariffNotFound;
import org.springframework.stereotype.Service;

@Service
public record SearchTariffQueryHandler(
    TariffPort tariffPort
) {

    public Tariff findTariff(final FindTariffQuery query) throws TariffNotFound {
        return tariffPort.find(ProductId.of(query.productId()), BrandId.of(query.brandId()), query.date());
    }
}
