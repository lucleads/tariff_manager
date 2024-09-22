package com.inditex.tariff_manager.acceptance.shared;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.springframework.test.annotation.DirtiesContext;

@Suite
@SuiteDisplayName("Acceptance test suite")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.inditex.tariff_manager.acceptance.steps, com.inditex.tariff_manager.acceptance.shared.parameter_types")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@ExcludeTags("skip")
public final class AcceptanceTestSuite {

}