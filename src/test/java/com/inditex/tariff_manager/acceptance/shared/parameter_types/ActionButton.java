package com.inditex.tariff_manager.acceptance.shared.parameter_types;

import static org.springframework.http.HttpMethod.GET;

import com.inditex.tariff_manager.acceptance.shared.CucumberParameterType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpMethod;

@Getter
@AllArgsConstructor
public enum ActionButton implements CucumberParameterType {
    // Tariff
    SEARCH_TARIFF("SEARCH TARIFF", GET, "/tariffs");

    private final String typeName;
    private final HttpMethod httpMethod;
    private final String uri;

}
