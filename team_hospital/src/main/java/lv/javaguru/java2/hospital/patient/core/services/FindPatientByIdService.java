package lv.javaguru.java2.hospital.patient.core.services;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.dependency_injection.DIComponent;
import lv.javaguru.java2.hospital.dependency_injection.DIDependency;
import lv.javaguru.java2.hospital.patient.core.requests.FindPatientByIdRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import lv.javaguru.java2.hospital.patient.core.responses.FindPatientByIDResponse;
import lv.javaguru.java2.hospital.patient.core.services.validators.FindPatientByIDValidator;

import java.util.List;

@DIComponent
public class FindPatientByIdService {

    @DIDependency private PatientDatabaseImpl patientDatabase;
    @DIDependency private FindPatientByIDValidator validator;

    public FindPatientByIDResponse execute(FindPatientByIdRequest request){
        List<CoreError> errors = validator.validate(request);
        if(!errors.isEmpty()){
            return new FindPatientByIDResponse(errors);
        } else {
            return new FindPatientByIDResponse(request.getIdRequest(),
                    patientDatabase.findById(request.getIdRequest()));
        }
    }
}
