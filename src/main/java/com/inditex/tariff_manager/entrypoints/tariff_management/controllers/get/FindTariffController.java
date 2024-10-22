package com.inditex.tariff_manager.entrypoints.tariff_management.controllers.get;

import com.inditex.tariff_manager.entrypoints.tariff_management.dto.TariffDto;
import com.inditex.tariff_manager.entrypoints.tariff_management.mappers.TariffToTariffDtoMapper;
import com.inditex.tariff_manager.entrypoints.tariff_management.spec.TariffsApi;
import com.inditex.tariff_manager.tariff_management.application.find_tariff.FindTariffQuery;
import com.inditex.tariff_manager.tariff_management.application.find_tariff.FindTariffQueryHandler;
import com.inditex.tariff_manager.tariff_management.infrastructure.exceptions.TariffNotFound;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class FindTariffController implements TariffsApi {

    private final FindTariffQueryHandler queryHandler;

    @Override
    public ResponseEntity<TariffDto> find(
        Integer productId,
        Integer brandId,
        LocalDateTime date
    ) throws TariffNotFound {

        return ResponseEntity.ok(
            TariffToTariffDtoMapper.toTariffDto(
                queryHandler.findTariff(
                    new FindTariffQuery(productId, brandId, date)
                )
            )
        );
    }
}
