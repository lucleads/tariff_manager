package com.inditex.tariff_manager.object_mothers;

import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.TariffId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TariffIdMother {

    public static TariffId random() {
        return Instancio.create(TariffId.class);
    }
}
