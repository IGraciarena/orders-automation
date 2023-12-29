package facts;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.facts.Fact;
import net.serenitybdd.screenplay.rest.interactions.Get;

/**
 * @author ivan.graciarena
 * @project order-domain-automation
 */
public class NetflixPlan implements Fact {
    // this class is used if you need something to be remembered by the actor.
    private String plansInfo;

    public static NetflixPlan toViewSeries() {
        return new NetflixPlan();
    }

    @Override
    public void setup(final Actor actor) {
        actor.attemptsTo(Get.resource("/plans"));
        var plans = SerenityRest.lastResponse().path("data");

        // the actor is going to have this in future test.
        actor.remember("plans", plans);

        plansInfo = plans.toString();
    }

    public String toString(){
        return "Plans are: " + plansInfo;
    }
}
