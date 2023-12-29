package tasks;

import interactions.PostForUser;
import io.restassured.http.ContentType;
import models.request.UserCreateRequest;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * @author ivan.graciarena
 * @project order-domain-automation
 */
public class PostUser implements Task {

    private final UserCreateRequest request;

    public PostUser(final UserCreateRequest request) {
        this.request = request;
    }

    public static Performable withBody(final UserCreateRequest request) {
        return instrumented(PostUser.class, request);
    }

    @Override
    @Step("{0} POST a user with resource #resource")
    public <T extends Actor> void performAs(final T actor) {
        actor.attemptsTo(PostForUser.to("/users")
                .with(requestSpecification -> requestSpecification.contentType(ContentType.JSON).body(request)));
    }
}
