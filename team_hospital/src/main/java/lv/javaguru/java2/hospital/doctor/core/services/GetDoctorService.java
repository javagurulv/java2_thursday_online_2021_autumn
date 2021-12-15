package lv.javaguru.java2.hospital.doctor.core.services;

import lv.javaguru.java2.hospital.database.doctor_repository.DoctorRepository;
import lv.javaguru.java2.hospital.doctor.core.requests.GetDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import lv.javaguru.java2.hospital.doctor.core.responses.GetDoctorResponse;
import lv.javaguru.java2.hospital.doctor.core.services.validators.GetDoctorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class GetDoctorService {

    @Autowired private DoctorRepository doctorRepository;
    @Autowired private GetDoctorValidator validator;

    public GetDoctorResponse execute(GetDoctorRequest request) {
        List<CoreError> errors = validator.validate(request);
        if(!errors.isEmpty()) {
            return new GetDoctorResponse(errors);
        }
        return doctorRepository.getById(request.getId())
                .map(GetDoctorResponse::new)
                .orElseGet(() -> {
                    errors.add(new CoreError("id", "Not found!"));
                    return new GetDoctorResponse(errors);
                });
    }
}
