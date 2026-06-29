package courier;

import client.CourierClient;
import io.restassured.response.Response;
import model.Courier;
import model.CourierCredentials;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import utils.CourierGenerator;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class CourierCreateTest {

    private final CourierClient courierClient = new CourierClient();

    private Courier courier;

    @AfterEach
    void tearDown() {

        if (courier != null) {
            try {
                int courierId = courierClient.getCourierId(
                        new CourierCredentials(
                                courier.getLogin(),
                                courier.getPassword()
                        )
                );

                courierClient.delete(courierId);

            } catch (Exception ignored) {
            }
        }
    }

    @Test
    void courierCanBeCreated() {

        courier = CourierGenerator.getRandomCourier();

        Response response = courierClient.create(courier);

        response.then()
                .statusCode(201)
                .body("ok", equalTo(true));
    }

    @Test
    void cannotCreateDuplicateCourier() {

        courier = CourierGenerator.getRandomCourier();

        courierClient.create(courier);

        Response response = courierClient.create(courier);

        response.then()
                .statusCode(409)
                .body("message",
                        containsString("Этот логин уже используется"));
    }

    @Test
    void cannotCreateCourierWithoutLogin() {

        Courier courier = new Courier(
                null,
                "1234",
                "Ivan"
        );

        Response response = courierClient.create(courier);

        response.then()
                .statusCode(400)
                .body("message",
                        equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    void cannotCreateCourierWithoutPassword() {

        Courier courier = new Courier(
                "login123",
                null,
                "Ivan"
        );

        Response response = courierClient.create(courier);

        response.then()
                .statusCode(400)
                .body("message",
                        equalTo("Недостаточно данных для создания учетной записи"));
    }
}