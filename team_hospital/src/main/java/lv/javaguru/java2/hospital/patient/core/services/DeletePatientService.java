package lv.javaguru.java2.hospital.patient.core.services;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.dependency_injection.DIComponent;
import lv.javaguru.java2.hospital.dependency_injection.DIDependency;
import lv.javaguru.java2.hospital.patient.core.requests.DeletePatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import lv.javaguru.java2.hospital.patient.core.responses.DeletePatientResponse;
import lv.javaguru.java2.hospital.patient.core.services.validators.DeletePatientValidator;

import java.util.List;

@DIComponent
public class DeletePatientService {

    @DIDependency private PatientDatabaseImpl database;
    @DIDependency private DeletePatientValidator validator;

    public DeletePatientResponse execute(DeletePatientRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeletePatientResponse(errors);
        }
        boolean removedOrNot = database.deleteById(request.getIdRequest());
        return new DeletePatientResponse(request.getIdRequest(), removedOrNot);
    }
}
