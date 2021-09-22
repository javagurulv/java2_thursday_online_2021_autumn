package lv.javaguru.java2.hospital.patient.core.services;

import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.patient.core.requests.ShowAllPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.ShowAllPatientsResponse;

import java.util.List;

public class ShowAllPatientsService {
    private final PatientDatabaseImpl database;

    public ShowAllPatientsService(PatientDatabaseImpl database) {
        this.database = database;
    }

    public ShowAllPatientsResponse execute(ShowAllPatientsRequest request) {
        List<Patient> patients = database.showAllPatients();
        return new ShowAllPatientsResponse(patients);
    }
}
