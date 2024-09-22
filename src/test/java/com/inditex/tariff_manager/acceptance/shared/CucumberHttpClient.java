package com.inditex.tariff_manager.acceptance.shared;

import java.time.Duration;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public final class CucumberHttpClient {

    // Set requests timeout to 5 minutes, so we can debug without reaching that time
    private static final Duration TIMEOUT = Duration.ofMinutes(5);

    // Must match server.port value in application.yaml
    private static final String API_ROOT_URI = "http://localhost:9999";

    private final RestTemplate restTemplate;

    @Autowired
    public CucumberHttpClient(RestTemplateBuilder restTemplateBuilder) {

        this.restTemplate = restTemplateBuilder
            .rootUri(API_ROOT_URI)
            .setConnectTimeout(TIMEOUT)
            .setReadTimeout(TIMEOUT)
            .build();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);

        return builder.requestFactory(() -> factory).build();
    }

    public ResponseEntity<?> doRequest(final HttpMethod method, final String uri, final RequestData requestData) {

        var url = buildUrl(uri, requestData);
        var httpRequest = new HttpEntity<>(requestData.getRequestBody());

        try {
            return restTemplate.exchange(url, method, httpRequest, String.class);
        } catch (HttpStatusCodeException e) {
            return ResponseEntity.status(e.getStatusCode())
                .headers(e.getResponseHeaders())
                .body(e.getResponseBodyAsString());
        } catch (ResourceAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    private String buildUrl(final String uri, final RequestData requestData) {

        return UriComponentsBuilder.fromUriString(uri)
            .queryParams(requestData.getQueryParams())
            .buildAndExpand(requestData.getPathVariables())
            .toUriString();
    }

}
