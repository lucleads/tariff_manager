package com.inditex.tariff_manager.entrypoints.tariff_management.controllers.get;

import com.inditex.tariff_manager.entrypoints.tariff_management.dto.TariffDto;
import com.inditex.tariff_manager.entrypoints.tariff_management.mappers.TariffToTariffDtoMapper;
import com.inditex.tariff_manager.entrypoints.tariff_management.spec.TariffsApi;
import com.inditex.tariff_manager.tariff_management.application.search_tariff.SearchTariffQuery;
import com.inditex.tariff_manager.tariff_management.application.search_tariff.SearchTariffQueryHandler;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SearchTariffController implements TariffsApi {

    private final SearchTariffQueryHandler queryHandler;

    @Override
    public ResponseEntity<TariffDto> search(
        Integer productId,
        Integer brandId,
        OffsetDateTime date
    ) {
        return ResponseEntity.ok(
            TariffToTariffDtoMapper.toTariffDto(
                queryHandler.searchTariff(
                    new SearchTariffQuery(productId, brandId, date.toLocalDateTime())
                )
            )
        );
    }
}
