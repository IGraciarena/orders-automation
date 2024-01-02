package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import questions.UserContent;
import tasks.GetUsers;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.notNullValue;

/**
 * @author ivan.graciarena
 * @project order-domain-automation
 */
public class GetUsersStepDefinition {

    @Given("Ivan is a client that wants to see his users")
    public void ivan_is_a_client_that_wants_to_see_his_users() {
    }

    @When("he sends the page number {int} for the GET method")
    public void he_sends_the_page_number_for_the_get_method(final Integer page) {
        theActorInTheSpotlight().attemptsTo(GetUsers.fromPage(page));
    }

    @Then("he will expect a successful response with the list of users by Page")
    public void he_will_expect_a_successful_response_with_the_list_of_users_by_page() {
        theActorInTheSpotlight().should(
                seeThatResponse("All users on page 2 should be returned ok",
                response -> response.statusCode(200)));
    }

    @Then("Ivan uses a user that matches one of the responses")
    public void ivan_uses_a_user_that_matches_one_of_the_responses() {
        var user = new UserContent().answeredBy(theActorInTheSpotlight())
                .getData().stream()
                .filter(userOnPage -> userOnPage.getId() == 7)
                .findFirst()
                .orElse(null);

        // in this case we use arrow function because we need to
        // convert the Question into a concrete class. (user in this case).
        theActorInTheSpotlight().should(
                seeThat("User content ", act -> user, notNullValue())
        );
    }
}
