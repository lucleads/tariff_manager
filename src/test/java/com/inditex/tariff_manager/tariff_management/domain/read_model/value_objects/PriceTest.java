package com.inditex.tariff_manager.tariff_management.domain.read_model.value_objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.inditex.tariff_manager.tariff_management.domain.exceptions.InvalidPrice;
import org.junit.jupiter.api.Test;

class PriceTest {

    @Test
    void successfullyCreatePriceWithValidValue() {
        // given
        double validAmount = 100.0;
        String validCurrency = "USD";

        // when
        Price price = Price.of(validAmount, validCurrency);

        // then
        assertThat(price.getAmount()).isEqualTo(validAmount);
        assertThat(price.getCurrency()).isEqualTo(validCurrency);
    }

    @Test
    void failsWhenCreatingANegativePrice() {
        // given
        double invalidAmount = -1.0;
        String currency = "USD";

        // then
        assertThrows(InvalidPrice.class, () -> Price.of(invalidAmount, currency));
    }

    @Test
    void shouldAllowZeroAmount() {
        // given
        double zeroAmount = 0.0;
        String currency = "USD";

        // when
        Price price = Price.of(zeroAmount, currency);

        // then
        assertThat(price.getAmount()).isEqualTo(zeroAmount);
        assertThat(price.getCurrency()).isEqualTo(currency);
    }

}