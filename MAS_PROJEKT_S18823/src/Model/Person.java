package Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@MappedSuperclass
public abstract class Person {
    private long id;
    private String name;
    private String secondName;
    private LocalDate dateOfBirth;
    private int age;
    private int PESEL;
    private String email;

    /**
     * Required by Hibernate.
     */
    public Person() {
    }

    public Person(String name, String secondName, LocalDate dateOfBirth, int PESEL, String email) {
        this.name = name;
        this.secondName = secondName;
        this.dateOfBirth = dateOfBirth;
        this.age = calculateAge(dateOfBirth, LocalDate.of(2021, 7, 30));
        this.PESEL = PESEL;
        this.email = email;
    }


    public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
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
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dataUrodzenia) {
        this.dateOfBirth = dataUrodzenia;
    }

    @Basic
    public int getAge() {
        return age;
    }

    public void setAge(int wiek) {
        this.age = wiek;
    }

    @Basic
    public int getPESEL() {
        return PESEL;
    }

    public void setPESEL(int PESEL) {
        this.PESEL = PESEL;
    }

    @Basic
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
