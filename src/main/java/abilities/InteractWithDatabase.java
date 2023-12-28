package abilities;

import database.DatabaseConnectionInfo;
import database.manager.JpaEntityManagerFactory;
import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;

import javax.persistence.EntityManager;

/**
 * @author ivan.graciarena
 * @project order-domain-automation
 */
public class InteractWithDatabase implements Ability {

    private EntityManager entityManager;

    public EntityManager getManager() {
        return entityManager;
    }

    public InteractWithDatabase(DatabaseConnectionInfo databaseConnectionInfo) {
        this.entityManager =
                new JpaEntityManagerFactory(databaseConnectionInfo).getEntityManager();
    }

    public static InteractWithDatabase using(DatabaseConnectionInfo databaseConnectionInfo) {
        return new InteractWithDatabase(databaseConnectionInfo);
    }

    public static InteractWithDatabase as(Actor actor) {
        return actor.abilityTo(InteractWithDatabase.class);
    }
}
