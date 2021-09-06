package Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "Vehicle")
@Table(name = "vehicle")
@Inheritance(strategy = InheritanceType.JOINED)
public class Vehicle {
    @Id
    private long id;
    @Column(unique = true)
    private int idVehicle;
    private double price;
    private boolean isAvailable;
    private static List<Integer> IdList = new ArrayList<>();
    private Data data;
    private CarRental carRental;


    /**
     * Required by Hibernate.
     */
    public Vehicle() {
    }

    public Vehicle(int idVehicle, double price, boolean isAvailable) throws Exception {
        this.idVehicle = idVehicle;
        this.price = price;
        this.isAvailable = isAvailable;
        if (IdList.contains(idVehicle)) {
            throw new Exception("Element must be unique");

        } else {
            IdList.add(idVehicle);
            this.idVehicle = idVehicle;
        }

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

    @ManyToOne
    public Data getData() {
        return data;
    }

    public void setData(Data dane) {
        this.data = dane;
    }


    public int getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(int idVehicle) throws Exception {
//        if(IdList.contains(idPojazd)){
//            throw new Exception("Element must be unique");
//
//        }else{
//            IdList.add(idPojazd);
        this.idVehicle = idVehicle;
//        }
    }


    @Basic
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Basic
    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @ManyToOne
//    @JoinColumn(name = "fk_carRental")
    public CarRental getCarRental() {
        return carRental;
    }

    public void setCarRental(CarRental carRental) {
        this.carRental = carRental;
    }

    @Override
    public String toString() {
        return "Pojazd{" +
                "IdPojazd=" + idVehicle +
                ", cena=" + price +
                ", dostepnosc=" + isAvailable +
                '}';
    }


}
