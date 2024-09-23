package com.inditex.tariff_manager.acceptance.shared.parameter_types;

import com.inditex.tariff_manager.acceptance.shared.CucumberParameterType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReactionMessage implements CucumberParameterType {
    // Success
    OK("OK", HttpStatus.OK),
    NOT_FOUND("NOT FOUND", HttpStatus.NOT_FOUND),
    INVALID_REQUEST("INVALID REQUEST", HttpStatus.BAD_REQUEST);

    private final String typeName;
    private final HttpStatus httpStatus;
}
