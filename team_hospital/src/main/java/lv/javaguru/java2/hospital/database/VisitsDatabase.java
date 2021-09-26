package lv.javaguru.java2.hospital.database;

import lv.javaguru.java2.hospital.domain.PatientVisit;

import java.util.List;

public interface VisitsDatabase {
    void recordVisit(PatientVisit patientVisit);
    public boolean DeleteVisit(Long id);
    public List<PatientVisit> getPatientVisits();
}
