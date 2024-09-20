package com.inditex.tariff_manager.tariff_management.domain.read_model;

import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.Price;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.ProductId;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Product {
    private final ProductId id;
    private final Price recommendedPvp;

    @Builder(toBuilder = true)
    public Product(
            ProductId id,
            Price recommendedPvp
    ) {
        this.id = id;
        this.recommendedPvp = recommendedPvp;
    }
}
