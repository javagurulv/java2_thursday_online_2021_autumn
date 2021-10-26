package lv.javaguru.java2.hospital.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Prescription {

    private Long id;
    private Doctor doctor;
    private Patient patient;
    private String medicationName;
    private Integer quantity;
    private LocalDate date = LocalDate.now();
    private LocalDate validTill = date.plusMonths(2);

    public Prescription(Doctor doctor, Patient patient, String medicationName, Integer quantity) {
        this.doctor = doctor;
        this.patient = patient;
        this.medicationName = medicationName;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getMedication() {
        return medicationName;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalDate getValidTill() {
        return validTill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prescription that = (Prescription) o;
        return quantity == that.quantity && Objects.equals(id, that.id) && Objects.equals(doctor, that.doctor) && Objects.equals(patient, that.patient) && Objects.equals(medicationName, that.medicationName) && Objects.equals(date, that.date) && Objects.equals(validTill, that.validTill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, doctor, patient, medicationName, quantity, date, validTill);
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "id=" + id +
                ", doctor=" + doctor +
                ", patient=" + patient +
                ", medication='" + medicationName + '\'' +
                ", quantity=" + quantity +
                ", date=" + date +
                ", validTill=" + validTill +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }
}
