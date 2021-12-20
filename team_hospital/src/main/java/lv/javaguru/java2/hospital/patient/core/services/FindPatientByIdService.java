package lv.javaguru.java2.hospital.patient.core.services;

import lv.javaguru.java2.hospital.database.jpa.JpaPatientRepository;
import lv.javaguru.java2.hospital.database.patient_repository.PatientRepository;
import lv.javaguru.java2.hospital.patient.core.requests.FindPatientByIdRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import lv.javaguru.java2.hospital.patient.core.responses.FindPatientByIDResponse;
import lv.javaguru.java2.hospital.patient.core.services.validators.FindPatientByIDValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class FindPatientByIdService {

    @Autowired private JpaPatientRepository patientRepository;
    @Autowired private FindPatientByIDValidator validator;

    public FindPatientByIDResponse execute(FindPatientByIdRequest request){
        List<CoreError> errors = validator.validate(request);
        if(!errors.isEmpty()){
            return new FindPatientByIDResponse(errors);
        } else {
            return new FindPatientByIDResponse(Long.parseLong(request.getIDRequest()),
                    patientRepository.findById(Long.valueOf(request.getIDRequest())));
        }
    }
}
