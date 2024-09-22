package com.inditex.tariff_manager.tariff_management.application.find_tariff;

import java.time.LocalDateTime;

public record FindTariffQuery(
    int productId,
    int brandId,
    LocalDateTime date
) {

}
