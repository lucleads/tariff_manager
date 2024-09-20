package com.inditex.tariff_manager.tariff_management.application.search_tariff;

import java.time.LocalDateTime;

public record SearchTariffQuery(
        int productId,
        int brandId,
        LocalDateTime date
) {
}
