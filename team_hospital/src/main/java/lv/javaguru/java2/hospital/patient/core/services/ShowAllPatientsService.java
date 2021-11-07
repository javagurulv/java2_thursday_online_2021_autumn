package lv.javaguru.java2.hospital.patient.core.services;

import lv.javaguru.java2.hospital.database.patient_repository.PatientRepository;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.ShowAllPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.ShowAllPatientsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShowAllPatientsService {

   @Autowired private PatientRepository database;

    public ShowAllPatientsResponse execute(ShowAllPatientsRequest request) {
        List<Patient> patients = database.getAllPatients();
        return new ShowAllPatientsResponse(patients);
    }
}
