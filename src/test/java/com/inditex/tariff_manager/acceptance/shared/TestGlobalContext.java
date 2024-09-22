package com.inditex.tariff_manager.acceptance.shared;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public final class TestGlobalContext {

    private final CucumberHttpClient httpClient;

    private final RequestData requestData;

    @Getter
    private ResponseEntity<?> response;

    public TestGlobalContext(CucumberHttpClient httpClient) {
        this.httpClient = httpClient;
        this.requestData = new RequestData();
    }

    public void addPathVariable(final String name, final String value) {
        requestData.addPathVariable(name, value);
    }

    public void addQueryParam(final String name, final String value) {
        requestData.addQueryParam(name, value);
    }

    public void setRequestBody(final Object requestBody) {
        requestData.setRequestBody(requestBody);
    }

    public void doRequest(final HttpMethod method, final String uri) {

        this.response = httpClient.doRequest(method, uri, requestData);
    }

}
