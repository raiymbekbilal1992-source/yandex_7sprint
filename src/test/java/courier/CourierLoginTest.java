package courier;

import client.CourierClient;
import io.restassured.response.Response;
import model.Courier;
import model.CourierCredentials;
import org.junit.jupiter.api.Test;
import utils.CourierGenerator;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;

public class CourierLoginTest {

    private final CourierClient courierClient = new CourierClient();

    @Test
    void courierCanLogin() {

        Courier courier = CourierGenerator.getRandomCourier();

        courierClient.create(courier);

        CourierCredentials credentials =
                new CourierCredentials(
                        courier.getLogin(),
                        courier.getPassword()
                );

        Response response = courierClient.login(credentials);

        response.then()
                .statusCode(200)
                .body("id", notNullValue());
    }

    @Test
    void loginWithoutLoginReturnsError() {

        CourierCredentials credentials =
                new CourierCredentials(
                        "",
                        "1234"
                );

        Response response = courierClient.login(credentials);

        response.then()
                .statusCode(400)
                .body("message",
                        equalTo("Недостаточно данных для входа"));
    }

    @Test
    void loginWithoutPasswordReturnsError() {

        CourierCredentials credentials =
                new CourierCredentials(
                        "someLogin",
                        ""
                );

        Response response = courierClient.login(credentials);

        response.then()
                .statusCode(400);
    }

    @Test
    void loginWithWrongPasswordReturnsError() {

        Courier courier = CourierGenerator.getRandomCourier();

        courierClient.create(courier);

        CourierCredentials credentials =
                new CourierCredentials(
                        courier.getLogin(),
                        "wrongPassword"
                );

        Response response = courierClient.login(credentials);

        response.then()
                .statusCode(404);
    }

    @Test
    void loginNonExistentCourierReturnsError() {

        CourierCredentials credentials =
                new CourierCredentials(
                        "notExistingLogin",
                        "notExistingPassword"
                );

        Response response = courierClient.login(credentials);

        response.then()
                .statusCode(404);
    }
}