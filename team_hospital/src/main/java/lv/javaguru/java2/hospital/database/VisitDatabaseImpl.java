package lv.javaguru.java2.hospital.database;

import lv.javaguru.java2.hospital.domain.PatientVisit;

import java.util.ArrayList;
import java.util.List;

public class VisitDatabaseImpl implements VisitsDatabase{
    private final List<PatientVisit> patientVisits = new ArrayList<>();

    @Override
    public void recordVisit(PatientVisit patientVisit) {
        patientVisits.add(patientVisit);
    }

    public List<PatientVisit> getPatientVisits() {
        return patientVisits;
    }
}
