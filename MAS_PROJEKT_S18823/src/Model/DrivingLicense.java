package Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "DrivingLicense")
public class DrivingLicense {
    private long id;
    private String name;
    private String secondName;
    private int idDrivingLicense;
    private String category;
    private Client client;

    public DrivingLicense() {
    }

    private DrivingLicense(String name, String secondName, int idDrivingLicense, String category, Client client) {
        this.name = name;
        this.secondName = secondName;
        this.idDrivingLicense = idDrivingLicense;
        this.category = category;
        this.client = client;
    }

    /**
     * Required by Hibernate.
     */

    public static DrivingLicense createDrivingLicense(String imie, String nazwisko, int idPrawoJazdy, String Kategoria, Client klient) throws Exception {
        if (klient == null) {
            throw new Exception("No composition");
        } else {

            DrivingLicense drivingLicense = new DrivingLicense(imie, nazwisko, idPrawoJazdy, Kategoria, klient);
            klient.addDrivingLicense(drivingLicense);
            return drivingLicense;
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

    @Basic
    public String getName() {
        return name;
    }

    public void setName(String imie) {
        this.name = imie;
    }

    @Basic
    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String nazwisko) {
        this.secondName = nazwisko;
    }

    @Basic
    public int getIdDrivingLicense() {
        return idDrivingLicense;
    }

    public void setIdDrivingLicense(int idPrawoJazdy) {
        this.idDrivingLicense = idPrawoJazdy;
    }

    @Basic
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @OneToOne
    public Client getClient() {
        return client;
    }

    public void setClient(Client klient) {
        this.client = klient;
    }

    @Override
    public String toString() {
        return "DrivingLicense{" +
                "name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", idDrivingLicense=" + idDrivingLicense +
                ", category='" + category + '\'' +
                '}';
    }
}

