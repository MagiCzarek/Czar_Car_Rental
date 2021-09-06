package Model;

import javax.persistence.*;

@Entity(name = "DelieveryTruck")
public class DelieveryTruck extends Car {

    private int numberOfSeats;

    public DelieveryTruck(int idVehicle, double price, boolean isAvailable, int numberOfDoors, String engineType, int numberOfSeats) throws Exception {
        super(idVehicle, price, isAvailable, numberOfDoors, engineType);
        this.numberOfSeats = numberOfSeats;
    }

    public DelieveryTruck() {
    }

    public DelieveryTruck(int numberOfDoors, String engineType, int numberOfSeats) {
        super(numberOfDoors, engineType);
        this.numberOfSeats = numberOfSeats;
    }

    @Basic
    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}
