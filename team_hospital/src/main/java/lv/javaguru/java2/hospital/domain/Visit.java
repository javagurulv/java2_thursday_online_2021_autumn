package lv.javaguru.java2.hospital.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class Visit {
    private Doctor doctor;
    private Patient patient;
    private LocalDateTime visitDate;
    private Long visitID;
    private String description;

    public Visit(Doctor doctor, Patient patient, LocalDateTime visitDate) {
        this.doctor = doctor;
        this.patient = patient;
        this.visitDate = visitDate;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public Long getVisitID() {
        return visitID;
    }

    @Override
    public String toString() {
        return "PatientVisit{" +
                "doctor=" + doctor +
                ", patient=" + patient +
                ", visitDate=" + visitDate +
                ", visitID=" + visitID +
                '}';
    }

    public void setVisitID(Long visitID) {
        this.visitID = visitID;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return Objects.equals(doctor, visit.doctor)
                && Objects.equals(patient, visit.patient)
                && Objects.equals(visitDate, visit.visitDate)
                && Objects.equals(visitID, visit.visitID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctor, patient, visitDate, visitID);
    }
}
