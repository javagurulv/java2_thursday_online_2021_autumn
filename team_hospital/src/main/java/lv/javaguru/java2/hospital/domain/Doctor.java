package lv.javaguru.java2.hospital.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "speciality", nullable = false)
    private String speciality;

    public Doctor() {
    }

    public Doctor(String name, String surname, String speciality) {
        this.name = name;
        this.surname = surname;
        this.speciality = speciality;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(id, doctor.id) && Objects.equals(name, doctor.name) && Objects.equals(surname, doctor.surname) && Objects.equals(speciality, doctor.speciality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, speciality);
    }

    @Override
    public String toString() {
        return "Doctor:" +
                " id = " + id +
                ", name = " + name  +
                ", surname= " + surname +
                ", speciality = " + speciality + ".";
    }
}
