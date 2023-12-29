package interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.RestInteraction;

/**
 * @author ivan.graciarena
 * @project order-domain-automation
 */
public class GetForUsers extends RestInteraction {
    private final String resource;

    public GetForUsers(final String resource) {
        this.resource = resource;
    }

    @Step("{0} executes a GET on the resource #resource")
    public <T extends Actor> void performAs(final T actor) {
        rest().log().all().get(CallAnApi.as(actor).resolve(resource))
                .then().log().all();
    }

    public static GetForUsers resource(final String resource) {
        return Tasks.instrumented(GetForUsers.class, resource);
    }
}