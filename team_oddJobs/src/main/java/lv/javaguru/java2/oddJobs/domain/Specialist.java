package lv.javaguru.java2.oddJobs.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Specialists")
public class Specialist {
    @Id
    @Column(name = "specialistId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long specialistId;


    @Column(name = "specialistName", nullable = false)
    private String specialistName;

    @Column(name = "specialistSurname", nullable = false)
    private String specialistSurname;

    @Column(name = "specialistProfession", nullable = false)
    private String specialistProfession;

    @Column(name = "personalCode", nullable = false)
    private String personalCode;

    @Column(name = "city", nullable = false)
    private String city;


    public Specialist(String specialistName, String specialistSurname, String specialistProfession,String personalCode, String city) {
        this.specialistName = specialistName;
        this.specialistSurname = specialistSurname;
        this.specialistProfession = specialistProfession;
        this.personalCode=personalCode;
        this.city=city;
    }

    public Specialist(Long specialistId) {
        this.specialistId = specialistId;
    }

    public Specialist(String specialistProfession) {
        this.specialistProfession = specialistProfession;
    }

    public Specialist() {

    }

    public Long getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(Long specialistId) {
        this.specialistId = specialistId;
    }

    public String getSpecialistName() {
        return specialistName;
    }

    public void setSpecialistName(String specialistName) {
        this.specialistName = specialistName;
    }

    public String getSpecialistSurname() {
        return specialistSurname;
    }

    public void setSpecialistSurname(String specialistSurname) {
        this.specialistSurname = specialistSurname;
    }

    public String getSpecialistProfession() {
        return specialistProfession;
    }

    public void setSpecialistProfession(String specialistProfession) {
        this.specialistProfession = specialistProfession;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Specialist that = (Specialist) o;
        return Objects.equals(specialistId, that.specialistId) && Objects.equals(specialistName, that.specialistName) && Objects.equals(specialistSurname, that.specialistSurname) && Objects.equals(specialistProfession, that.specialistProfession) && Objects.equals(personalCode, that.personalCode) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(specialistId, specialistName, specialistSurname, specialistProfession, personalCode, city);
    }

    @Override
    public String toString() {
        return "Specialist{" +
                "specialistId=" + specialistId +
                ", specialistName='" + specialistName + '\'' +
                ", specialistSurname='" + specialistSurname + '\'' +
                ", specialistProfession='" + specialistProfession + '\'' +
                ", personalCode='" + personalCode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
