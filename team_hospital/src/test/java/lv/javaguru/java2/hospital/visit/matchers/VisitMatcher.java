package lv.javaguru.java2.hospital.visit.matchers;

import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.domain.Visit;
import org.mockito.ArgumentMatcher;

import java.time.LocalDateTime;

public class VisitMatcher implements ArgumentMatcher<Visit> {

    private Doctor doctor;
    private Patient patient;
    private LocalDateTime visitDate;

    public VisitMatcher(Doctor doctor, Patient patient, LocalDateTime visitDate) {
        this.doctor = doctor;
        this.patient = patient;
        this.visitDate = visitDate;
    }

    @Override
    public boolean matches(Visit visit) {
        return visit.getPatient().equals(patient)
                && visit.getDoctor().equals(doctor)
                && visit.getVisitDate().equals(visitDate);
    }
}
