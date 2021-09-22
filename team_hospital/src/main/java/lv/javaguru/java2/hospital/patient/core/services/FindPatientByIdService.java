package lv.javaguru.java2.hospital.patient.core.services;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.patient.core.requests.FindPatientByIdRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import lv.javaguru.java2.hospital.patient.core.responses.FindPatientByIDResponse;
import lv.javaguru.java2.hospital.patient.core.services.validators.FindPatientByIDValidator;

import java.util.List;

public class FindPatientByIdService {
    private final PatientDatabaseImpl patientDatabase;
    private final FindPatientByIDValidator validator;

    public FindPatientByIdService(PatientDatabaseImpl patientDatabase, FindPatientByIDValidator validator) {
        this.patientDatabase = patientDatabase;
        this.validator = validator;
    }

    public FindPatientByIDResponse execute(FindPatientByIdRequest request){
        List<CoreError> errors = validator.validate(request);
        if(!errors.isEmpty()){
            return new FindPatientByIDResponse(errors);
        } else {
            return new FindPatientByIDResponse(request.getIdRequest(),
                    patientDatabase.findById(Long.parseLong(request.getIdRequest())));
        }
    }
}
