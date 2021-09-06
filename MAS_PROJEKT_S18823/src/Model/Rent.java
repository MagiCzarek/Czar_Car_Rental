package Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Entity(name = "Rent")
@Table(name = "rent")
public class Rent {
    private long id;

    private int idRent;
    private Date dateFrom;
    private Date dateTo;
    private double cost;
    private CarRental carRental;
    private Client client;
    private long rentLength;


    public Rent() {
    }

    public Rent(Date dateFrom, Date dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        long numberOfDays = dateTo.getTime() - dateFrom.getTime();
        long days = TimeUnit.DAYS.convert(numberOfDays, TimeUnit.MILLISECONDS);
        this.rentLength = days;
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
    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dataOd) {
        this.dateFrom = dataOd;
    }

    @Basic
    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dataDo) {
        this.dateTo = dataDo;
    }

    @Basic
    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Basic
    public long getRentLength() {
        return rentLength;
    }

    public void setRentLength(long rentLength) {
        this.rentLength = rentLength;
    }

    @ManyToOne
//    @JoinColumn(name = "fk_carRental")
    public CarRental getCarRental() {
        return carRental;
    }

    public void setCarRental(CarRental carRental) {
        this.carRental = carRental;
    }

    @ManyToOne
//    @JoinColumn(name = "fk_client")
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public long calcPeriodOfRent() {
        long numberOfDays = dateTo.getTime() - dateFrom.getTime();
        long days = TimeUnit.DAYS.convert(numberOfDays, TimeUnit.MILLISECONDS);
        return days;
    }

    public double calcCost(Vehicle vehicle) {

        return vehicle.getPrice() * calcPeriodOfRent();

    }

    public void rent(Vehicle vehicle) {
        if (vehicle.isIsAvailable() == true) {
            vehicle.setIsAvailable(false);
//            wypożyczalnia.getWypozyczenieList().add(this);
//            this.setWypożyczalnia(wypożyczalnia);
//            klient.getWypożyczenieList().add(this);
//            this.setKlient(klient);
        } else {
            System.out.println("nie mozna wypozyczyc");
        }
    }

    public void deleteRent(Vehicle vehicle) {
        if (vehicle.isIsAvailable() == false) {
            vehicle.setIsAvailable(true);
//            carRental.getRentList().remove(this);
//            this.setCarRental(carRental);
//            client.getRentList().remove(this);
//            this.setClient(client);
        }
    }
    @Column(unique = true)
    public int getIdRent() {
        return idRent;
    }

    public void setIdRent(int idRent) {
        this.idRent = idRent;
    }
}


