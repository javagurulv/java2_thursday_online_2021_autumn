package lv.javaguru.java2.hospital.patient.core.services;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.patient.core.requests.FindPatientByIdRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import lv.javaguru.java2.hospital.patient.core.responses.FindPatientByIDResponse;
import lv.javaguru.java2.hospital.patient.core.services.validators.FindPatientByIDValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindPatientByIdService {

    @Autowired private PatientDatabaseImpl patientDatabase;
    @Autowired private FindPatientByIDValidator validator;

    public FindPatientByIDResponse execute(FindPatientByIdRequest request){
        List<CoreError> errors = validator.validate(request);
        if(!errors.isEmpty()){
            return new FindPatientByIDResponse(errors);
        } else {
            return new FindPatientByIDResponse(request.getIDRequest(),
                    patientDatabase.findById(request.getIDRequest()));
        }
    }
}
