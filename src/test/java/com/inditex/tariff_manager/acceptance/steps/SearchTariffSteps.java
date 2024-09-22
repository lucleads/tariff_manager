package com.inditex.tariff_manager.acceptance.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.inditex.tariff_manager.acceptance.shared.AcceptanceTest;
import com.inditex.tariff_manager.acceptance.shared.TestGlobalContext;
import com.inditex.tariff_manager.acceptance.steps.worlds.TariffWorld;
import com.inditex.tariff_manager.shared.persistence.h2.entities.PriceEntity;
import com.inditex.tariff_manager.shared.persistence.h2.repositories.PriceEntityRepository;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    @Given("I have prices for {int} {int} {string} with information")
    public void i_have_prices_with_information(Integer productId, Integer brandId, String date,
        DataTable dataTable) {
        List<Map<String, String>> priceData = dataTable.asMaps(String.class, String.class);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");

        List<PriceEntity> priceEntities = new ArrayList<>();
        for (Map<String, String> row : priceData) {
            PriceEntity priceEntity = new PriceEntity(
                Integer.parseInt(row.get("priceList")),
                Integer.parseInt(row.get("brandId")),
                LocalDateTime.parse(row.get("startDate"), formatter),
                LocalDateTime.parse(row.get("endDate"), formatter),
                Integer.parseInt(row.get("productId")),
                Integer.parseInt(row.get("priority")),
                Double.parseDouble(row.get("price")),
                row.get("curr")
            );
            priceEntities.add(priceEntity);
            when(priceEntityRepository.searchHighestPriorityPriceEntity(productId,
                brandId, LocalDateTime.parse(date, formatter)))
                .thenReturn(Optional.of(priceEntity));
        }

        tariffWorld.setExistingPriceEntities(priceEntities);
    }

    @Given("I fill the search tariff form with the info {string} {string} {string}")
    public void i_fill_the_search_tariff_form_with_the_info(String brandId, String productId, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");

        testGlobalContext.addQueryParam("brand_id", brandId);
        testGlobalContext.addQueryParam("product_id", productId);
        testGlobalContext.addQueryParam("date", LocalDateTime.parse(date, formatter).toString());
    }

    @Then("I should see the tariff information")
    public void i_should_see_the_tariff_information() throws JsonProcessingException {
        assertEquals(tariffWorld.stringifyTariffResponse(), testGlobalContext.getResponse().getBody());
    }

    @Then("I should see the tariff information for the tariff {int}")
    public void i_should_see_the_tariff_information_for_the_tariff_expected_tariff(Integer expectedTariff) {
        PriceEntity expectedPriceEntity = tariffWorld.getExistingPriceEntities().stream()
            .filter(priceEntity -> priceEntity.getPriceList() == expectedTariff).findFirst().get();
        assertEquals(tariffWorld.stringifyTariffResponse(), testGlobalContext.getResponse().getBody());
    }
}
