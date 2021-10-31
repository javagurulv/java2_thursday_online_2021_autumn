package lv.javaguru.java2.hospital.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Visit {
    private Long visitID;
    private Long doctorID;
    private Long patientID;
    private LocalDateTime visitDate;
    private String description;

    public Visit() {
    }

    public Visit(Long doctorID, Long patientID, LocalDateTime visitDate) {
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.visitDate = visitDate;
    }

    public Visit(Long doctorID, Long patientID, LocalDateTime visitDate, String description) {
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.visitDate = visitDate;
        this.description = description;
    }

    public Long getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(Long doctorID) {
        this.doctorID = doctorID;
    }

    public Long getPatientID() {
        return patientID;
    }

    public void setPatientID(Long patientID) {
        this.patientID = patientID;
    }

    public LocalDateTime getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDateTime visitDate) {
        this.visitDate = visitDate;
    }

    public Long getVisitID() {
        return visitID;
    }

    public void setVisitID(Long visitID) {
        this.visitID = visitID;
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
        return Objects.equals(doctorID, visit.doctorID) && Objects.equals(patientID, visit.patientID) && Objects.equals(visitDate, visit.visitDate) && Objects.equals(visitID, visit.visitID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorID, patientID, visitDate, visitID);
    }
}
