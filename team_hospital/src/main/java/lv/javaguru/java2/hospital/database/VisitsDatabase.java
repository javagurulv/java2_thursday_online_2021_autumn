package lv.javaguru.java2.hospital.database;

import lv.javaguru.java2.hospital.domain.PatientVisit;

public interface VisitsDatabase {
    void recordVisit(PatientVisit patientVisit);
}
