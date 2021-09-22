package lv.javaguru.java2.hospital.patient.core.services;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.patient.core.requests.AddPatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.AddPatientResponse;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import lv.javaguru.java2.hospital.patient.core.services.validators.AddPatientValidator;
import java.util.List;

public class AddPatientService {
    private final PatientDatabaseImpl database;
    private final AddPatientValidator validator;

    public AddPatientService(PatientDatabaseImpl database, AddPatientValidator validator) {
        this.database = database;
        this.validator = validator;
    }

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
