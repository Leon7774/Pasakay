package main.objects;

public class CarType {
    private int id;
    private String type;
    private int passengerCount;
    private String terrain;

    public CarType(int carTypeID, String type, int passengerCount, String terrain) {
        this.id = carTypeID;
        this.type = type;
        this.passengerCount = passengerCount;
        this.terrain = terrain;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public String getTerrain() {
        return terrain;
    }
}
