package Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;

import java.util.List;

@Entity(name = "Data")
@Table(name = "data")
public class Data {
    @Id
    private long id;
    private String brand;
    private String model;
    private int yearOfProduction;
    private double engineCapacity;
    private List<Vehicle> vehicleList = new ArrayList<>();

    /**
     * Required by Hibernate.
     */

    public Data() {
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    @Basic
    public String getBrand() {
        return brand;
    }

    public void setBrand(String marka) {
        this.brand = marka;
    }

    @Basic
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    @Basic
    public double getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public void addVehicle(Vehicle vehicle) {
        getVehicleList().add(vehicle);
        vehicle.setData(this);
    }

    public void removeVehicle(Vehicle vehicle) {
        getVehicleList().remove(vehicle);
        vehicle.setData(this);
    }

    public String showData(Vehicle vehicle) {
        if (vehicleList.contains(vehicle)) {
            return "Dane{" +
                    "marka='" + getBrand() + '\'' +
                    ", model='" + getModel() + '\'' +
                    ", rokProdukcji=" + getYearOfProduction() +
                    ", pojemnoscSilnika=" + getEngineCapacity() +
                    ", pojazdList=" + getVehicleList() +
                    '}';
        } else return "BLADBLADBLAD";
    }

    @OneToMany(mappedBy = "data", cascade = CascadeType.ALL)

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }


    public void showData() {
        System.out.println("Dane{" +
                "id=" + id +
                ", marka='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", rokProdukcji=" + yearOfProduction +
                ", pojemnoscSilnika=" + engineCapacity +
                ", pojazdList=" + vehicleList +
                '}');

    }

    @Override
    public String toString() {
        return " marka='" + brand + '\'' +
                ", model='" + model;
    }
}
