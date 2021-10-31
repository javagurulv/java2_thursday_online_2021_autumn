package lv.javaguru.java2.hospital.visit.matchers;

import lv.javaguru.java2.hospital.domain.Visit;
import org.mockito.ArgumentMatcher;

import java.time.LocalDateTime;

public class VisitMatcher implements ArgumentMatcher<Visit> {

    private Long doctorID;
    private Long patientID;
    private LocalDateTime visitDate;
    private String description;

    public VisitMatcher(Long doctorID, Long patientID, LocalDateTime visitDate) {
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.visitDate = visitDate;
    }

    public VisitMatcher(Long doctorID, Long patientID, LocalDateTime visitDate, String description) {
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.visitDate = visitDate;
        this.description = description;
    }

    @Override
    public boolean matches(Visit visit) {
        if(description == null || description.isEmpty()) {
            return visit.getPatientID().equals(patientID)
                    && visit.getDoctorID().equals(doctorID)
                    && visit.getVisitDate().equals(visitDate);
        } else {
            return visit.getPatientID().equals(patientID)
                    && visit.getDoctorID().equals(doctorID)
                    && visit.getVisitDate().equals(visitDate)
                    && visit.getDescription().equals(description);
        }
    }
}
