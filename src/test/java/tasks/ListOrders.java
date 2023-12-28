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
public class ListOrders implements Task {

    private final int page;

    public ListOrders(int page) {
        this.page = page;
    }

    public ListOrders() {
        this.page = 1;
    }

    public static ListOrders onPage(int page) {
        return instrumented(ListOrders.class, page);
    }

    @Override
    @Step("{0} lists orders on page #page")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/orders/1")
                        .with(request -> request.queryParam("page", page))
        );
    }
}