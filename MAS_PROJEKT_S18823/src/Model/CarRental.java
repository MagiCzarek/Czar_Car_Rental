package Model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "CarRental")
@Table(name = "carRental")
public class CarRental {

    private long id;

    private String name;
    private List<Vehicle> vehicleList = new ArrayList<>();

    private List<Vehicle> availableVehicleList = new ArrayList<>();
    private List<Worker> workersList = new ArrayList<>();

    private List<Rent> rentList = new ArrayList<>();


    public CarRental() {
    }

    public CarRental(String name) {
        this.name = name;

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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "carRental", cascade = CascadeType.ALL)

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }


    public void showVehicleList() {
        for (Vehicle vehicle : vehicleList) {
            System.out.println(vehicle);
        }

    }

    public void showAialableCars() {
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.isIsAvailable() == true)
                System.out.println(vehicle);
        }
    }

    public void changeAvailability(Vehicle vehicle) {

            if(vehicleList.contains(vehicle)) {
                if (vehicle.isIsAvailable() == true) {
                    vehicle.setIsAvailable(false);
                } else
                    vehicle.setIsAvailable(true);
            }

    }

    public void addVehicle(Vehicle vehicle) {
        getVehicleList().add(vehicle);
        vehicle.setCarRental(this);
    }

    public void removeVehicle(Vehicle vehicle) {
        getVehicleList().remove(vehicle);
        vehicle.setCarRental(this);
    }

    public void removeWorker(Worker worker) {
        getWorkersList().add(worker);
        worker.setCarRental(this);
    }

    public void addWorker(Worker worker) {
        getWorkersList().remove(worker);
        worker.setCarRental(this);
    }

    public void addRent(Rent rent) {
        getRentList().add(rent);
        rent.setCarRental(this);
    }

    public void removeRent(Rent rent) {
        getRentList().remove(rent);
        rent.setCarRental(this);
    }

    @OneToMany(mappedBy = "carRental", cascade = CascadeType.ALL)

    public List<Worker> getWorkersList() {
        return workersList;
    }


    public void setWorkersList(List<Worker> workersList) {
        this.workersList = workersList;
    }

    @OneToMany(mappedBy = "carRental",cascade = CascadeType.ALL)

    public List<Rent> getRentList() {
        return rentList;
    }

    public void setRentList(List<Rent> rentList) {
        this.rentList = rentList;
    }
}
