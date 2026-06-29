package order;

import client.OrderClient;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.notNullValue;

public class OrderListTest {

    private final OrderClient orderClient = new OrderClient();

    @Test
    void getOrdersList() {

        Response response = orderClient.getOrders();

        response.then()
                .statusCode(200)
                .body("orders", notNullValue());
    }
}