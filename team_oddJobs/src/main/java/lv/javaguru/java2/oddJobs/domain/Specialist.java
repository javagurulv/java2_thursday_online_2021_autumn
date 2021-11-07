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


    public Specialist(String specialistName, String specialistSurname, String specialistProfession) {
        this.specialistName = specialistName;
        this.specialistSurname = specialistSurname;
        this.specialistProfession = specialistProfession;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Specialist that = (Specialist) o;
        return Objects.equals(specialistId, that.specialistId) && Objects.equals(specialistName, that.specialistName) && Objects.equals(specialistSurname, that.specialistSurname) && Objects.equals(specialistProfession, that.specialistProfession);
    }

    @Override
    public int hashCode() {
        return Objects.hash(specialistId, specialistName, specialistSurname, specialistProfession);
    }

    @Override
    public String toString() {
        return "Specialist{" +
                "id=" + specialistId +
                ", name='" + specialistName + '\'' +
                ", surname='" + specialistSurname + '\'' +
                ", profession='" + specialistProfession + '\'' +
                '}';
    }

}
