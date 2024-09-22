package com.inditex.tariff_manager.acceptance.shared;

import java.util.Collections;
import java.util.HashMap;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.LinkedMultiValueMap;

@Getter
public final class RequestData {

    private final LinkedMultiValueMap<String, String> queryParams;
    private final HashMap<String, String> pathVariables;
    @Setter
    private Object requestBody;

    public RequestData() {
        this.queryParams = new LinkedMultiValueMap<>();
        this.pathVariables = new HashMap<>();
    }

    public void addQueryParam(final String name, final String value) {
        queryParams.put(name, Collections.singletonList(value));
    }

    public void addPathVariable(final String name, final String value) {
        pathVariables.put(name, value);
    }

}
