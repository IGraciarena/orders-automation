package runner.hook;

import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.model.util.EnvironmentVariables;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

/**
 * @author ivan.graciarena
 * @project order-domain-automation
 */
public class Hook {
    private EnvironmentVariables environmentVariables;

    @Before
    public void configureBaseUrl() {
        OnStage.setTheStage(new OnlineCast());
        theActorCalled("Ivan");
        String theRestApiBaseUrl = environmentVariables.optionalProperty("restapi.baseurl")
                .orElse("https://reqres.in/api");
        theActorInTheSpotlight().whoCan(CallAnApi.at(theRestApiBaseUrl));
    }
}
