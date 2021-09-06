package Model;

import javax.persistence.*;

@Entity(name = "MotorCycle")
public class MotorCycle extends Vehicle {
    private String type;

    public MotorCycle(int idVehicle, double price, boolean isAvailable, String type) throws Exception {
        super(idVehicle, price, isAvailable);
        this.type = type;
    }

    public MotorCycle() {
    }

    public MotorCycle(String type) {
        this.type = type;
    }

    @Basic
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
