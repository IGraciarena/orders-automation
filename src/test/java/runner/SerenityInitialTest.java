package runner;

import facts.NetflixPlan;
import models.request.UserCreateRequest;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.model.util.EnvironmentVariables;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import questions.ResponseStatusCode;
import questions.UserContent;
import tasks.GetUsers;
import tasks.PostUser;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

/**
 * @author ivan.graciarena
 * @project order-domain-automation
 */
@ExtendWith(SerenityJUnit5Extension.class)
class SerenityInitialTest {
    Actor ivan;

    EnvironmentVariables environmentVariables;

    @BeforeEach
    void setUp() {

        final String theRestApiBaseUrl = environmentVariables
                .optionalProperty("restapi.baseurl")
                .orElseThrow();

        ivan = Actor.named("Ivan the client")
                .whoCan(CallAnApi.at(theRestApiBaseUrl));
    }

    @Test
    void getUsers() {
        ivan.attemptsTo(GetUsers.fromPage(2));

        ivan.should(
                seeThatResponse("All users on page 2 should be returned ok",
                        response -> response.statusCode(200))
        );
        // a different way to do it, implementing a question. more readability.
        ivan.should(
                seeThat("Response status code ",
                        ResponseStatusCode.was(), equalTo(200))
        );

        var user = new UserContent().answeredBy(ivan)
                .getData()
                .stream()
                .filter(userOnPage -> userOnPage.getId() == 7)
                .findFirst()
                .orElse(null);

        // in this case we use arrow function because we need to
        // convert the Question into a concrete class. (user in this case).
        ivan.should(
                seeThat("User content ", act -> user, notNullValue())
        );
    }

    @Test
    void postUser() {
        ivan.attemptsTo(PostUser.withBody(new UserCreateRequest(
                "Ivan",
                "Software Engineer",
                "ivanigraciarena@gmail.com",
                "123456")
        ));

        ivan.should(
                seeThat("Response status code ",
                        ResponseStatusCode.was(), equalTo(201))
        );
    }

    @Test
    void actorCouldRememberThings() {
        // this test is going to pass because it has no assertions, is just to remember how to retrieve something that
        // the actor has remembered.
        ivan.has(NetflixPlan.toViewSeries());
    }
}
