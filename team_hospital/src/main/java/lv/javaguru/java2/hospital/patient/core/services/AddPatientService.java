package lv.javaguru.java2.hospital.patient.core.services;

import lv.javaguru.java2.hospital.database.patient_repository.PatientRepository;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.AddPatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.AddPatientResponse;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import lv.javaguru.java2.hospital.patient.core.services.validators.AddPatientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddPatientService {

    @Autowired private PatientRepository database;
    @Autowired private AddPatientValidator validator;

    public AddPatientResponse execute(AddPatientRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddPatientResponse(errors);
        }
        Patient patient = new Patient(request.getName(), request.getSurname(), request.getPersonalCode());
        database.add(patient);
        return new AddPatientResponse(patient);
    }
}
