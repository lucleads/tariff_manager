package com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects;

import lombok.Value;

@Value(staticConstructor = "of")
public class ProductId {
    int value;
}
