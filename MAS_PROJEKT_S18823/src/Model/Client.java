package Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@DiscriminatorValue("Client")
public class Client extends Person {
    private long id;
    private int idClient;
    private DrivingLicense drivingLicense;
    private List<Rent> rentList = new ArrayList<>();

    public Client() {
    }

    public Client(int idClient) throws Exception {

        this.idClient = idClient;

    }

    public Client(String name, String secondName, LocalDate datofBirth, int PESEL, String email, int idClient) throws Exception {
        super(name, secondName, datofBirth, PESEL, email);

        this.idClient = idClient;
    }

    public void addDrivingLicense(DrivingLicense drivingLicenseToAdd) throws Exception {
        if (drivingLicenseToAdd.equals(drivingLicense)) {
            throw new Exception("This prawo jazdy cant be added");
        } else {
            this.drivingLicense = drivingLicenseToAdd;

        }
    }

    public void deleteDrivingLicense() {
        this.drivingLicense = null;
    }

    public boolean hasDrivingLicense() {
        if (drivingLicense == null) {
            return false;
        } else
            return true;
    }

    public void addRent(Rent rent) {
        getRentList().add(rent);
        rent.setClient(this);
    }

    public void removeRent(Rent rent) {
        getRentList().remove(rent);
        rent.setClient(this);
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

    @Column(unique = true)
    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }


    @OneToOne(cascade = CascadeType.ALL, optional = true)
//    @JoinColumn(name = "wypozyczalnia_id", referencedColumnName = "id")
    public DrivingLicense getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(DrivingLicense drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    @OneToMany(cascade = CascadeType.ALL)
    public List<Rent> getRentList() {
        return rentList;
    }

    public void setRentList(List<Rent> rentList) {
        this.rentList = rentList;
    }

    @Override
    public String toString() {
        return "Klient{" +
                "id=" + id +
                ", IdKlient=" + idClient +
                ", prawoJazdy=" + drivingLicense +
                '}';
    }
}

