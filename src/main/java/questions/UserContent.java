package questions;

import models.User;
import models.UserPage;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

/**
 * @author ivan.graciarena
 * @project order-domain-automation
 */
public class UserContent implements Question {
    @Override
    public UserPage answeredBy(final Actor actor) {
        return SerenityRest.lastResponse().as(UserPage.class);
    }
}
