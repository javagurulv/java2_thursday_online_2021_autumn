package lv.javaguru.java2.hospital.domain;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class PatientVisit {
    private Doctor doctor;
    private Patient patient;
    private Date visitDate;
    private Long visitID;
    private static final AtomicLong count = new AtomicLong(0);

    public PatientVisit(Doctor doctor, Patient patient, Date visitDate) {
        this.doctor = doctor;
        this.patient = patient;
        this.visitDate = visitDate;
        this.visitID = count.incrementAndGet();
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public Date getVisitDate() {
        return visitDate;
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

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }
}
