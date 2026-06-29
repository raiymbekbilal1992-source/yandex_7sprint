package utils;

import model.Courier;

public class CourierGenerator {

    public static Courier getRandomCourier() {

        return new Courier(
                "courier" + System.currentTimeMillis(),
                "1234",
                "Ivan"
        );
    }
}