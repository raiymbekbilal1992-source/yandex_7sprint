package courier;

import client.CourierClient;
import io.restassured.response.Response;
import model.Courier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import utils.CourierGenerator;

import static org.hamcrest.Matchers.equalTo;

public class CourierCreateTest {

    private final CourierClient courierClient = new CourierClient();

    private Courier courier;
    private int courierId;

    @AfterEach
    void tearDown() {
        if (courierId != 0) {
            courierClient.delete(courierId);
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
}