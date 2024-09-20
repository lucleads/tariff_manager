package com.inditex.tariff_manager.tariff_management.domain.read_model;

import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.Price;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.ProductId;
import lombok.Builder;

@Builder(toBuilder = true)
public record Product(ProductId id, Price recommendedPvp) {

}
