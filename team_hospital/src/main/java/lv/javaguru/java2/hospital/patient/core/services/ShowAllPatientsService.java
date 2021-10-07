package lv.javaguru.java2.hospital.patient.core.services;

import lv.javaguru.java2.hospital.dependency_injection.DIComponent;
import lv.javaguru.java2.hospital.dependency_injection.DIDependency;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.patient.core.requests.ShowAllPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.ShowAllPatientsResponse;

import java.util.List;

@DIComponent
public class ShowAllPatientsService {

   @DIDependency private PatientDatabaseImpl database;

    public ShowAllPatientsResponse execute(ShowAllPatientsRequest request) {
        List<Patient> patients = database.showAllPatients();
        return new ShowAllPatientsResponse(patients);
    }
}
