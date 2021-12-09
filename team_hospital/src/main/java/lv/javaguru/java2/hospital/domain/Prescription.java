package lv.javaguru.java2.hospital.domain;

import lv.javaguru.java2.hospital.database.doctor_repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "prescriptions")
public class Prescription {

    @Autowired @Transient private DoctorRepository doctorRepository;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name="patient_id", nullable = false)
    private Patient patient;

    @Column(name = "medication_name", nullable = false)
    private String medicationName;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "date", nullable = false)
    private LocalDate date = LocalDate.now();

    @Column(name = "valid_till", nullable = false)
    private LocalDate validTill = date.plusMonths(2);

    public Prescription() {
    }

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

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setValidTill(LocalDate validTill) {
        this.validTill = validTill;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public DoctorRepository getDoctorRepository() {
        return doctorRepository;
    }

    public void setDoctorRepository(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public String getMedicationName() {
        return medicationName;
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
