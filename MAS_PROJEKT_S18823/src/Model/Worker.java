package Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("Worker")
public class Worker extends Person {


    private long id;
    private static double salaryPerHour;
    private static double employmentType;
    private static int overTimeHours;
    private static double monthSalary;
    private CarRental carRental;


    /**
     * Required by Hibernate.
     */
    public Worker() {
    }

    public Worker(double salaryPerHour, double employmentType, int overTimeHours) {
        this.salaryPerHour = salaryPerHour;
        this.employmentType = employmentType;
        this.overTimeHours = overTimeHours;
        this.monthSalary = this.salaryPerHour * employmentType * 160 + overTimeHours * salaryPerHour;
    }

    public Worker(String name, String secondName, LocalDate dateOfBirth, int PESEL, String email, double salaryPerHour, double employmentType, int overTimeHours) {
        super(name, secondName, dateOfBirth, PESEL, email);
        this.salaryPerHour = salaryPerHour;
        this.employmentType = employmentType;
        this.overTimeHours = overTimeHours;
        this.monthSalary = this.salaryPerHour * employmentType * 160 + overTimeHours * salaryPerHour;
    }

    public static void calculateMonthSalary() {

        monthSalary = salaryPerHour * employmentType * 160 + overTimeHours * salaryPerHour;

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
    public double getSalaryPerHour() {
        return salaryPerHour;
    }

    public void setSalaryPerHour(double salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }

    @Basic
    public double getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(double rodzajEtatu) {
        this.employmentType = rodzajEtatu;
    }

    @Basic
    public int getOverTimeHours() {
        return overTimeHours;
    }

    public void setOverTimeHours(int overTimeHours) {
        this.overTimeHours = overTimeHours;
    }

    @Basic
    public double getMonthSalary() {
        return monthSalary;
    }

    public void setMonthSalary(double pensjaMiesięczna) {
        this.monthSalary = pensjaMiesięczna;
    }

    @ManyToOne
//    @JoinColumn(name = "fk_carRental")
    public CarRental getCarRental() {
        return carRental;
    }

    public void setCarRental(CarRental carRental) {
        this.carRental = carRental;
    }
}
