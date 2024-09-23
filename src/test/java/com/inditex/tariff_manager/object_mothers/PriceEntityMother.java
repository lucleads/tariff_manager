package com.inditex.tariff_manager.object_mothers;

import com.inditex.tariff_manager.shared.persistence.h2.entities.PriceEntity;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PriceEntityMother {

    public static PriceEntity random() {
        PriceEntity priceEntity = Instancio.create(PriceEntity.class);

        priceEntity.setStartDate(LocalDateTime.now().minusDays(2));
        priceEntity.setEndDate(LocalDateTime.now().plusDays(1));

        return priceEntity;
    }
}
