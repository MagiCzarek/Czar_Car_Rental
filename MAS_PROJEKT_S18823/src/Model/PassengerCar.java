package Model;

import javax.persistence.*;

@Entity(name = "PassengerCar")
public class PassengerCar extends Car {
    private double capacity;

    public PassengerCar(int idVehicle, double price, boolean isAvailable, int numberofDoors, String typSilnika, double capacity) throws Exception {
        super(idVehicle, price, isAvailable, numberofDoors, typSilnika);
        this.capacity = capacity;
    }

    public PassengerCar(double capacity) {
        this.capacity = capacity;
    }

    public PassengerCar() {
    }

    public PassengerCar(int iloscDrzwi, String typSilnika, double capacity) {
        super(iloscDrzwi, typSilnika);
        this.capacity = capacity;
    }

    @Basic
    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double ladownosc) {
        this.capacity = ladownosc;
    }
}
