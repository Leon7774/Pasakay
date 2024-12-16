package main.objects;

public class CarType {
    private int id;
    private static String type;
    private static int passengerCount;
    private static String terrain;

    public CarType(int carTypeID, String type, int passengerCount, String terrain) {
        this.id = carTypeID;
        CarType.type = type;
        CarType.passengerCount = passengerCount;
        CarType.terrain = terrain;
    }
}
