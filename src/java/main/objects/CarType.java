package main.objects;

public class CarType {
    private static String type;
    private static int passengerCount;
    private static String terrain;

    CarType(String type, int passengerCount, String terrain) {
        CarType.type = type;
        CarType.passengerCount = passengerCount;
        CarType.terrain = terrain;
    }
}
