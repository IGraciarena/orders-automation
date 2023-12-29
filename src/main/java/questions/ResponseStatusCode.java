package questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

/**
 * @author ivan.graciarena
 * @project order-domain-automation
 */
public class ResponseStatusCode implements Question {

    public static Question<Integer> was(){
        return new ResponseStatusCode();
    }
    @Override
    public Integer answeredBy(final Actor actor) {
        return SerenityRest.lastResponse().statusCode();
    }
}
