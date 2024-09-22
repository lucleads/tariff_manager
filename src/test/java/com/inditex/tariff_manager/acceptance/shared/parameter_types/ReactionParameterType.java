package com.inditex.tariff_manager.acceptance.shared.parameter_types;

import io.cucumber.java.ParameterType;
import java.util.Arrays;

public class ReactionParameterType {

    private static final String ADVICE_HANDLE_EXCEPTION_TEMPLATE = "No %s found with value: [ %s ]";

    @ParameterType(name = "reaction", value = ".*")
    public ReactionMessage reaction(String value) {

        return Arrays.stream(ReactionMessage.values())
            .filter(button -> button.getTypeName().equals(value))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(
                ADVICE_HANDLE_EXCEPTION_TEMPLATE.formatted(ReactionMessage.class.getSimpleName(), value)));
    }

}
