package com.inditex.tariff_manager.acceptance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.inditex.tariff_manager.acceptance.shared.AcceptanceTest;
import com.inditex.tariff_manager.acceptance.shared.TestGlobalContext;
import com.inditex.tariff_manager.acceptance.shared.parameter_types.ActionButton;
import com.inditex.tariff_manager.acceptance.shared.parameter_types.ReactionMessage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@CucumberContextConfiguration
public class SharedSteps extends AcceptanceTest {

    private final TestGlobalContext testGlobalContext;

    @When("I click the {action} button")
    public void i_click_the_action_button(final ActionButton actionButton) {
        testGlobalContext.doRequest(actionButton.getHttpMethod(), actionButton.getUri());
    }

    @Then("I should see {reaction} status code")
    public void i_should_see_reaction_status_code(final ReactionMessage expectedReaction) {
        assertEquals(expectedReaction.getHttpStatus(), testGlobalContext.getResponse().getStatusCode());
    }
}
