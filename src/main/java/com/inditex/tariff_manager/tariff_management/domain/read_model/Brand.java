package com.inditex.tariff_manager.tariff_management.domain.read_model;

import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.BrandId;
import lombok.Builder;

@Builder(toBuilder = true)
public record Brand(BrandId id) {

}
