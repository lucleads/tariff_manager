package com.inditex.tariff_manager.acceptance.shared.parameter_types;

import io.cucumber.java.ParameterType;
import java.util.Arrays;

public class ActionButtonParameterType {

    private static final String ADVICE_HANDLE_EXCEPTION_TEMPLATE = "No %s found with value: [ %s ]";

    @ParameterType(name = "action", value = ".*")
    public ActionButton action(String value) {

        return Arrays.stream(ActionButton.values())
            .filter(button -> button.getTypeName().equals(value))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(
                ADVICE_HANDLE_EXCEPTION_TEMPLATE.formatted(ActionButton.class.getSimpleName(), value)));
    }

}
