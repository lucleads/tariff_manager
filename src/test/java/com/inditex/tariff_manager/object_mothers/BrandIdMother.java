package com.inditex.tariff_manager.object_mothers;

import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.BrandId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BrandIdMother {

    public static BrandId random() {
        return Instancio.create(BrandId.class);
    }
}
