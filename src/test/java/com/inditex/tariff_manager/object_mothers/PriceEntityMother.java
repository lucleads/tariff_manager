package com.inditex.tariff_manager.object_mothers;

import com.inditex.tariff_manager.shared.persistence.h2.entities.PriceEntity;
import java.time.LocalDateTime;
import org.instancio.Instancio;

public final class PriceEntityMother {

    public static PriceEntity randomValid() {
        PriceEntity priceEntity = Instancio.create(PriceEntity.class);

        priceEntity.setStartDate(LocalDateTime.now().minusDays(2));
        priceEntity.setEndDate(LocalDateTime.now().plusDays(1));

        return priceEntity;
    }
}
