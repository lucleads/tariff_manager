package com.inditex.tariff_manager.acceptance.steps.worlds;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.inditex.tariff_manager.entrypoints.tariff_management.dto.TariffDto;
import com.inditex.tariff_manager.object_mothers.PriceEntityMother;
import com.inditex.tariff_manager.object_mothers.TariffDtoMother;
import com.inditex.tariff_manager.shared.persistence.h2.entities.PriceEntity;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.instancio.Instancio;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Getter
@Component
@Scope(SCOPE_SINGLETON)
public final class TariffWorld {

    private final PriceEntity priceEntity = PriceEntityMother.randomValid();
    private final TariffDto tariffResponse = TariffDtoMother.fromPriceEntity(priceEntity);
    private final LocalDateTime searchedTariffDate = Instancio.create(LocalDateTime.class);
    @Setter
    @Getter
    private List<PriceEntity> existingPriceEntities;

    public String stringifyTariffResponse() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return objectMapper.writeValueAsString(tariffResponse);
    }

}
