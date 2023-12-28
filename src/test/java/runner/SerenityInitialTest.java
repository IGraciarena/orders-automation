package runner;

import abilities.InteractWithDatabase;
import database.DatabaseConnectionInfo;
import database.DatabaseType;
import database.entities.AddressEntity;
import database.entities.ClientEntity;
import database.entities.OrderEntity;
import database.entities.OrderItemEntity;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.model.util.EnvironmentVariables;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import tasks.ListOrders;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

/**
 * @author ivan.graciarena
 * @project order-domain-automation
 */
@ExtendWith(SerenityJUnit5Extension.class)
class SerenityInitialTest {
    Actor ivan;

    EnvironmentVariables environmentVariables;

    @BeforeEach
    void setUp() {
        final String theRestApiBaseUrl = environmentVariables
                .optionalProperty("restapi.baseurl")
                .orElseThrow();

        ivan = Actor.named("Ivan the client")
                .whoCan(CallAnApi.at(theRestApiBaseUrl));
    }

    @Test
    void getUsers() {
        ivan.attemptsTo(ListOrders.onPage(1));

        ivan.should(
                seeThatResponse("All orders on page 1 should be returned",
                        response -> response.statusCode(200))
        );
    }

//    @Test
//    @Disabled
//    public void dataBaseConnectionTest() {
//        DatabaseConnectionInfo connectionInfo = DatabaseConnectionInfo
//                .builder()
//                .databaseType(DatabaseType.MYSQL)
//                .url("jdbc:mysql://localhost/order_domain")
//                .username("igraciarena")
//                .password("soma1251")
//                .entityNames(Stream.of(
//                        ClientEntity.class, AddressEntity.class, OrderEntity.class, OrderItemEntity.class)
//                        .map(Class::getName)
//                        .collect(Collectors.toList()))
//                .build();
//
//        ivan.can(InteractWithDatabase.using(connectionInfo));
//
//        EntityManager entityManager = InteractWithDatabase.as(ivan).getManager();
//
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//
//        CriteriaQuery<OrderEntity> criteriaQuery = criteriaBuilder.createQuery(OrderEntity.class);
//
//        Root<OrderEntity> userRoot = criteriaQuery.from(OrderEntity.class);
//
//        OrderEntity queryResult = entityManager.createQuery(criteriaQuery.select(userRoot))
//                .getSingleResult();
//
//        System.out.println(queryResult);
//    }
}
