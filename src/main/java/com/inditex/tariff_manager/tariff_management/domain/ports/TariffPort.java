package com.inditex.tariff_manager.tariff_management.domain.ports;

import com.inditex.tariff_manager.tariff_management.domain.read_model.Tariff;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.BrandId;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.ProductId;

import java.time.LocalDateTime;

public interface TariffPort {
    Tariff search(final ProductId productId, final BrandId brandId, LocalDateTime date);
}
