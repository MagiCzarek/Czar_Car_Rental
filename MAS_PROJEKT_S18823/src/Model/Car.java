package Model;

import javax.persistence.*;

@Entity(name = "Car")
public class Car extends Vehicle {

    private long id;
    private int numberOfDoors;
    private String engineType;

    public Car(int idVehicle, double price, boolean isAvailable, int numberOfDoors, String engineType) throws Exception {
        super(idVehicle, price, isAvailable);
        this.numberOfDoors = numberOfDoors;
        this.engineType = engineType;
    }

    public Car() {
    }

    public Car(int numberOfDoors, String engineType) {
        this.numberOfDoors = numberOfDoors;
        this.engineType = engineType;
    }
    @Basic
    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }
    @Basic
    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }
}
