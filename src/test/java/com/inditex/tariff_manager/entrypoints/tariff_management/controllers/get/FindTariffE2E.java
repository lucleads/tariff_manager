package com.inditex.tariff_manager.entrypoints.tariff_management.controllers.get;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.config.Profiles;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.ResourceUtils;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(Profiles.TEST)
class FindTariffE2E {

    public static final String URL = "http://localhost:";
    private static final int PRODUCT_ID = 35455;
    private static final int BRAND_ID = 1;
    private static final String BASE_URL = "/tariffs";
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private static Stream<Arguments> tariffTestCases() {
        return Stream.of(
            Arguments.of("2020-06-14T10:00:00Z", "tariff1"),
            Arguments.of("2020-06-14T16:00:00Z", "tariff2"),
            Arguments.of("2020-06-14T21:00:00Z", "tariff1"),
            Arguments.of("2020-06-15T10:00:00Z", "tariff3"),
            Arguments.of("2020-06-16T21:00:00Z", "tariff4")
        );
    }

    @ParameterizedTest
    @MethodSource("tariffTestCases")
    void testGetTariff(String date, String expectedTariffFileName) throws IOException {
        URI uri = buildUri(date);
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        JsonNode expectedTariff = readExpectedTariffResponse(expectedTariffFileName);
        JsonNode actualTariff = objectMapper.readTree(response.getBody());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals(expectedTariff, actualTariff);
    }

    private URI buildUri(String date) {
        return UriComponentsBuilder.fromHttpUrl(URL + port + BASE_URL)
            .queryParam("product_id", PRODUCT_ID)
            .queryParam("brand_id", BRAND_ID)
            .queryParam("date", date)
            .build()
            .toUri();
    }

    private JsonNode readExpectedTariffResponse(String tariffFileName) throws IOException {
        File file = ResourceUtils.getFile("classpath:end_to_end/expected_responses/" + tariffFileName + ".json");
        return objectMapper.readTree(file);
    }
}

