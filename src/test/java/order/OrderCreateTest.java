package order;

import client.OrderClient;
import io.restassured.response.Response;
import model.Order;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.notNullValue;

public class OrderCreateTest {

    private final OrderClient orderClient = new OrderClient();

    @Test
    void createOrderWithBlackColor() {
        createOrder(new String[]{"BLACK"});
    }

    @Test
    void createOrderWithGreyColor() {
        createOrder(new String[]{"GREY"});
    }

    @Test
    void createOrderWithTwoColors() {
        createOrder(new String[]{"BLACK", "GREY"});
    }

    @Test
    void createOrderWithoutColor() {
        createOrder(new String[]{});
    }

    private void createOrder(String[] colors) {

        Order order = new Order(
                "Naruto",
                "Uchiha",
                "Konoha, 142 apt.",
                4,
                "+7 800 355 35 35",
                5,
                "2026-06-30",
                "Saske, come back to Konoha",
                colors
        );

        Response response = orderClient.create(order);

        response.then()
                .statusCode(201)
                .body("track", notNullValue());
    }
}
