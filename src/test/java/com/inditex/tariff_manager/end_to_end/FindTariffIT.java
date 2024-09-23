package com.inditex.tariff_manager.end_to_end;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.inditex.tariff_manager.config.Profiles;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import org.junit.jupiter.api.Test;
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
class FindTariffIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void successfulGetTariff1() throws IOException {
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/tariffs")
            .queryParam("product_id", 35455)
            .queryParam("brand_id", 1)
            .queryParam("date", "2020-06-14T10:00:00Z")
            .build()
            .toUri();
        ResponseEntity<String> response = this.restTemplate.getForEntity(uri, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode expectedTariff = objectMapper.readTree(readExpectedTariffResponse("tariff1"));
        JsonNode actualTariff = objectMapper.readTree(response.getBody());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals(expectedTariff, actualTariff);
    }

    @Test
    void successfulGetTariff2() throws IOException {
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/tariffs")
            .queryParam("product_id", 35455)
            .queryParam("brand_id", 1)
            .queryParam("date", "2020-06-14T16:00:00Z")
            .build()
            .toUri();
        ResponseEntity<String> response = this.restTemplate.getForEntity(uri, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode expectedTariff = objectMapper.readTree(readExpectedTariffResponse("tariff2"));
        JsonNode actualTariff = objectMapper.readTree(response.getBody());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals(expectedTariff, actualTariff);
    }

    @Test
    void successfulGetTariff1_2() throws IOException {
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/tariffs")
            .queryParam("product_id", 35455)
            .queryParam("brand_id", 1)
            .queryParam("date", "2020-06-14T21:00:00Z")
            .build()
            .toUri();
        ResponseEntity<String> response = this.restTemplate.getForEntity(uri, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode expectedTariff = objectMapper.readTree(readExpectedTariffResponse("tariff1"));
        JsonNode actualTariff = objectMapper.readTree(response.getBody());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals(expectedTariff, actualTariff);
    }

    @Test
    void successfulGetTariff3() throws IOException {
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/tariffs")
            .queryParam("product_id", 35455)
            .queryParam("brand_id", 1)
            .queryParam("date", "2020-06-15T10:00:00Z")
            .build()
            .toUri();
        ResponseEntity<String> response = this.restTemplate.getForEntity(uri, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode expectedTariff = objectMapper.readTree(readExpectedTariffResponse("tariff3"));
        JsonNode actualTariff = objectMapper.readTree(response.getBody());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals(expectedTariff, actualTariff);
    }

    @Test
    void successfulGetTariff4() throws IOException {
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/tariffs")
            .queryParam("product_id", 35455)
            .queryParam("brand_id", 1)
            .queryParam("date", "2020-06-16T21:00:00Z")
            .build()
            .toUri();
        ResponseEntity<String> response = this.restTemplate.getForEntity(uri, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode expectedTariff = objectMapper.readTree(readExpectedTariffResponse("tariff4"));
        JsonNode actualTariff = objectMapper.readTree(response.getBody());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals(expectedTariff, actualTariff);
    }

    private String readExpectedTariffResponse(String tariff) throws IOException {
        ObjectMapper om = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        File file = ResourceUtils.getFile("classpath:end_to_end/expected_responses/" + tariff + ".json");
        JsonNode json = om.readTree(file);

        return om.writeValueAsString(json);
    }

}
