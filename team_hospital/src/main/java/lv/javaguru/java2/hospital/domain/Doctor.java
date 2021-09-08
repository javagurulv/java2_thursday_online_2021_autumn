package lv.javaguru.java2.hospital.domain;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Doctor {
    private static final AtomicInteger count = new AtomicInteger(0);
    private long id;
    private String name;
    private String surname;
    private String speciality;

    public Doctor(String name, String surname, String speciality) {
        this.name = name;
        this.surname = surname;
        this.speciality = speciality;
        this.id = count.incrementAndGet();
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
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
        return id == doctor.id && Objects.equals(name, doctor.name) && Objects.equals(surname, doctor.surname) && Objects.equals(speciality, doctor.speciality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, speciality);
    }

    @Override
    public String toString() {
        return "doctor_actions.Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", speciality='" + speciality + '\'' +
                '}';
    }
}
