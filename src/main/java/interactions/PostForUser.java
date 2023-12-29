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
public class PostForUser extends RestInteraction {
    private final String resource;

    public PostForUser(final String resource) {
        this.resource = resource;
    }

    @Step("{0} executes a POST on the resource #resource")
    public <T extends Actor> void performAs(T actor) {
        // if you don't put the then, you only log what goes to the server. we want both.
        rest().log().all().post(CallAnApi.as(actor).resolve(resource))
                .then().log().all();
    }

    public static PostForUser to(String resource) {
        return Tasks.instrumented(PostForUser.class, resource);
    }
}
