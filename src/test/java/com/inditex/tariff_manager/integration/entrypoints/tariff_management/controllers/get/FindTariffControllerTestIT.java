package com.inditex.tariff_manager.integration.entrypoints.tariff_management.controllers.get;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.inditex.tariff_manager.entrypoints.tariff_management.controllers.get.FindTariffController;
import com.inditex.tariff_manager.entrypoints.tariff_management.dto.TariffDto;
import com.inditex.tariff_manager.entrypoints.tariff_management.mappers.TariffToTariffDtoMapper;
import com.inditex.tariff_manager.object_mothers.TariffMother;
import com.inditex.tariff_manager.tariff_management.application.find_tariff.FindTariffQuery;
import com.inditex.tariff_manager.tariff_management.application.find_tariff.FindTariffQueryHandler;
import com.inditex.tariff_manager.tariff_management.domain.read_model.Tariff;
import com.inditex.tariff_manager.tariff_management.infrastructure.exceptions.TariffNotFound;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {FindTariffController.class})
class FindTariffControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FindTariffQueryHandler queryHandler;

    @Test
    void successfulSearchTariff() throws Exception {
        int productId = 1;
        int brandId = 1;
        LocalDateTime date = LocalDateTime.of(2024, 10, 20, 10, 0);

        Tariff expectedTariff = TariffMother.random();

        TariffDto expectedTariffDto = TariffToTariffDtoMapper.toTariffDto(expectedTariff);

        when(queryHandler.findTariff(any(FindTariffQuery.class))).thenReturn(expectedTariff);

        mockMvc.perform(get("/tariffs")
                .param("product_id", String.valueOf(productId))
                .param("brand_id", String.valueOf(brandId))
                .param("date", date.toString())
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.tariff_id").value(expectedTariffDto.getTariffId()))
            .andExpect(jsonPath("$.product_id").value(expectedTariffDto.getProductId()))
            .andExpect(jsonPath("$.brand_id").value(expectedTariffDto.getBrandId()))
            .andExpect(jsonPath("$.price.amount").value(expectedTariffDto.getPrice().getAmount()))
            .andExpect(jsonPath("$.price.currency").value(expectedTariffDto.getPrice().getCurrency()))
            .andExpect(jsonPath("$.tariff_start_date").value(
                expectedTariffDto.getTariffStartDate().toString().substring(0, 26)))
            .andExpect(
                jsonPath("$.tariff_end_date").value(expectedTariffDto.getTariffEndDate().toString().substring(0, 26)));
    }

    @Test
    void notFoundStatusWhenCannotFindTheTariff() throws Exception {
        int productId = 1;
        int brandId = 1;
        LocalDateTime date = LocalDateTime.of(2024, 10, 20, 10, 0);

        when(queryHandler.findTariff(any(FindTariffQuery.class))).thenThrow(TariffNotFound.class);

        mockMvc.perform(get("/tariffs")
                .param("product_id", String.valueOf(productId))
                .param("brand_id", String.valueOf(brandId))
                .param("date", date.toString())
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    void badRequestWhenProductIdIsMissing() throws Exception {
        int brandId = 1;
        LocalDateTime date = LocalDateTime.of(2024, 10, 20, 10, 0);

        mockMvc.perform(get("/tariffs")
                .param("brand_id", String.valueOf(brandId))
                .param("date", date.toString())
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    void badRequestWhenBrandIdIsMissing() throws Exception {
        int productId = 1;
        LocalDateTime date = LocalDateTime.of(2024, 10, 20, 10, 0);

        mockMvc.perform(get("/tariffs")
                .param("product_id", String.valueOf(productId))
                .param("date", date.toString())
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    void badRequestWhenDateIsMissing() throws Exception {
        int productId = 1;
        int brandId = 1;

        mockMvc.perform(get("/tariffs")
                .param("product_id", String.valueOf(productId))
                .param("brand_id", String.valueOf(brandId))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }
}