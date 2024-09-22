package com.inditex.tariff_manager.acceptance.shared.parameter_types;

import com.inditex.tariff_manager.acceptance.shared.CucumberParameterType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReactionMessage implements CucumberParameterType {
    // Success
    OK("OK", HttpStatus.OK);

    private final String typeName;
    private final HttpStatus httpStatus;
}
