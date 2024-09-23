package com.inditex.tariff_manager.object_mothers;

import com.inditex.tariff_manager.tariff_management.domain.read_model.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductMother {

    public static Product random() {

        return Product.builder().id(ProductIdMother.random()).build();
    }
}
