package runner;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.thucydides.model.util.EnvironmentVariables;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import tasks.ListUsers;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

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
        ivan.attemptsTo(ListUsers.onPage(2));

        ivan.should(
                seeThatResponse("All users on page 1 should be returned",
                        response -> response.statusCode(200))
        );
    }
}
