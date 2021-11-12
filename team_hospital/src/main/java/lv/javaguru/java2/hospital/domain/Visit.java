package lv.javaguru.java2.hospital.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "visits")
public class Visit {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long visitID;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "date", nullable = false)
    private LocalDateTime visitDate;

    @Column(name = "description", nullable = false)
    private String description;

    public Visit() {
    }

    public Visit(Doctor doctor, Patient patient, LocalDateTime visitDate) {
        this.doctor = doctor;
        this.patient = patient;
        this.visitDate = visitDate;
    }

    public Visit(Doctor doctor, Patient patient, LocalDateTime visitDate, String description) {
        this.doctor = doctor;
        this.patient = patient;
        this.visitDate = visitDate;
        this.description = description;
    }

    public Long getVisitID() {
        return visitID;
    }

    public void setVisitID(Long visitID) {
        this.visitID = visitID;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDateTime getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDateTime visitDate) {
        this.visitDate = visitDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "visitID=" + visitID +
                ", doctor=" + doctor +
                ", patient=" + patient +
                ", visitDate=" + visitDate +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return Objects.equals(visitID, visit.visitID) && Objects.equals(doctor, visit.doctor) && Objects.equals(patient, visit.patient) && Objects.equals(visitDate, visit.visitDate) && Objects.equals(description, visit.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(visitID, doctor, patient, visitDate, description);
    }
}