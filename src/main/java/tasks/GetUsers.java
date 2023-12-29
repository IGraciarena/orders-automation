package tasks;

import interactions.GetForUsers;
import io.restassured.http.ContentType;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

/**
 * @author ivan.graciarena
 * @project order-domain-automation
 */
public class GetUsers implements Task {

    private final int page;

    public GetUsers(final int page) {
        this.page = page;
    }

    public static Performable fromPage(final int page) {
        return Tasks.instrumented(GetUsers.class, page);
    }

    @Override
    @Step("{0} lists users on page #page")
    public <T extends Actor> void performAs(final T actor) {
        actor.attemptsTo(
                GetForUsers.resource("/users")
                        .with(request -> request.contentType(ContentType.JSON))
        );
    }
}