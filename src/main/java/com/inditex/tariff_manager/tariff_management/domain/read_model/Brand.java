package com.inditex.tariff_manager.tariff_management.domain.read_model;

import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.BrandId;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Brand {
    private final BrandId id;

    @Builder(toBuilder = true)
    public Brand(
            BrandId id
    ) {
        this.id = id;
    }
}
