package tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * @author ivan.graciarena
 * @project order-domain-automation
 */
public class ListUsers implements Task {

    private final int page;

    public ListUsers(int page) {
        this.page = page;
    }

    public ListUsers() {
        this.page = 1;
    }

    public static ListUsers onPage(int page) {
        return instrumented(ListUsers.class, page);
    }

    @Override
    @Step("{0} lists users on page #page")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/users")
                        .with(request -> request.queryParam("page", page))
        );
    }
}