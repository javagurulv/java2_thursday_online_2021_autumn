package lv.javaguru.java2.hospital.database;

import lv.javaguru.java2.hospital.domain.PatientVisit;

import java.util.List;

public interface VisitsDatabase {

    void recordVisit(PatientVisit patientVisit);

    boolean DeleteVisit(Long id);

    List<PatientVisit> showAllPatientVisits();

    boolean editVisit(Long visitId, int userInput, String changes);
}
