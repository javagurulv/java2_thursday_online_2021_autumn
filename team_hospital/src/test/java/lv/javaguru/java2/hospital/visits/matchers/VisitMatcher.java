package lv.javaguru.java2.hospital.visits.matchers;

import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.domain.Visit;
import org.mockito.ArgumentMatcher;

import java.util.Date;

public class VisitMatcher implements ArgumentMatcher<Visit> {

    private Doctor doctor;
    private Patient patient;
    private Date visitDate;

    public VisitMatcher(Doctor doctor, Patient patient, Date visitDate) {
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
