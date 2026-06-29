package utils;

import model.Courier;

public class CourierGenerator {

    public static Courier getRandomCourier() {

        String login = "courier" + System.currentTimeMillis();

        return new Courier(
                login,
                "password123",
                "Ivan"
        );
    }
}