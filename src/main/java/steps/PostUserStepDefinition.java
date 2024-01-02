package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.request.UserCreateRequest;
import questions.ResponseStatusCode;
import tasks.PostUser;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author ivan.graciarena
 * @project order-domain-automation
 */
public class PostUserStepDefinition {

    @Given("Ivan is a client that wants to manage his orders")
    public void ivan_is_a_client_that_wants_to_manage_his_orders() {
    }

    @When("he sends the information require for the POST")
    public void he_sends_the_information_require_for_the_post() {
        theActorInTheSpotlight().attemptsTo(
                PostUser.withBody(
                        new UserCreateRequest(
                                "Ivan",
                                "Software Engineer",
                                "ivanigraciarena@gmail.com",
                                "123456")));
    }

    @Then("he will expect a successful response")
    public void he_will_expect_a_successful_response() {
        theActorInTheSpotlight().should(
                seeThat("Response status code ", ResponseStatusCode.was(), equalTo(201)));
    }
}
