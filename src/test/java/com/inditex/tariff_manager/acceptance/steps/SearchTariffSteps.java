package com.inditex.tariff_manager.acceptance.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.inditex.tariff_manager.acceptance.shared.AcceptanceTest;
import com.inditex.tariff_manager.acceptance.shared.TestGlobalContext;
import com.inditex.tariff_manager.acceptance.steps.worlds.TariffWorld;
import com.inditex.tariff_manager.shared.persistence.h2.entities.PriceEntity;
import com.inditex.tariff_manager.shared.persistence.h2.repositories.PriceEntityRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import java.util.Optional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SearchTariffSteps extends AcceptanceTest {

    private final TestGlobalContext testGlobalContext;
    private final TariffWorld tariffWorld;

    private final PriceEntityRepository priceEntityRepository;

    @Given("I fill the search tariff form with valid data")
    public void i_fill_the_search_tariff_form_with_valid_data() {

        testGlobalContext.addQueryParam("product_id", String.valueOf(tariffWorld.getPriceEntity().getProductId()));
        testGlobalContext.addQueryParam("brand_id", String.valueOf(tariffWorld.getPriceEntity().getBrandId()));
        testGlobalContext.addQueryParam("date", tariffWorld.getSearchedTariffDate().toString());
    }

    @Given("I already have a created price")
    public void i_already_have_a_created_price() {
        PriceEntity existingPrice = tariffWorld.getPriceEntity();
        when(priceEntityRepository.searchHighestPriorityPriceEntity(existingPrice.getProductId(),
            existingPrice.getBrandId(), tariffWorld.getSearchedTariffDate()))
            .thenReturn(Optional.of(existingPrice));
    }

    @Then("I should see the tariff information")
    public void i_should_see_the_tariff_information() throws JsonProcessingException {
        assertEquals(tariffWorld.stringifyTariffResponse(), testGlobalContext.getResponse().getBody());
    }
}
