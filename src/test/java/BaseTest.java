import io.restassured.RestAssured;

public class BaseTest {

    protected static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";

    static {
        RestAssured.baseURI = BASE_URL;
    }
}