package com.inditex.tariff_manager.object_mothers;

import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.ProductId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductIdMother {

    public static ProductId random() {
        return Instancio.create(ProductId.class);
    }
}
