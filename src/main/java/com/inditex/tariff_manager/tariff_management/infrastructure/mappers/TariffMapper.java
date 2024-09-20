package com.inditex.tariff_manager.tariff_management.infrastructure.mappers;

import com.inditex.tariff_manager.shared.persistence.h2.entities.PriceEntity;
import com.inditex.tariff_manager.tariff_management.domain.read_model.Brand;
import com.inditex.tariff_manager.tariff_management.domain.read_model.Product;
import com.inditex.tariff_manager.tariff_management.domain.read_model.Tariff;
import com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TariffMapper {
    public static Tariff toAggregate(final PriceEntity price) {
        return Tariff.builder()
                .id(
                        TariffId.of(price.getPriceList())
                )
                .product(
                        Product.builder()
                                .id(ProductId.of(price.getProductId()))
                                .build()
                )
                .brand(
                        Brand.builder()
                                .id(BrandId.of(price.getBrandId()))
                                .build()
                )
                .startDate(
                        TariffStartDate.of(price.getStartDate())
                )
                .endDate(
                        TariffEndDate.of(price.getEndDate())
                )
                .price(
                        Price.of(price.getPrice(), price.getCurrency())
                ).build();
    }
}
