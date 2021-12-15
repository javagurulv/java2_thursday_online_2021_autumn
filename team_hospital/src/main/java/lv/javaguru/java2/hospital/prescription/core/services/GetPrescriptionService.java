package lv.javaguru.java2.hospital.prescription.core.services;

import lv.javaguru.java2.hospital.database.prescription_repository.PrescriptionRepository;
import lv.javaguru.java2.hospital.prescription.core.requests.GetPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import lv.javaguru.java2.hospital.prescription.core.responses.GetPrescriptionResponse;
import lv.javaguru.java2.hospital.prescription.core.services.validators.GetPrescriptionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class GetPrescriptionService {

    @Autowired private PrescriptionRepository prescriptionRepository;
    @Autowired private GetPrescriptionValidator validator;

    public GetPrescriptionResponse execute(GetPrescriptionRequest request) {
        List<CoreError> errors = validator.validate(request);
        if(!errors.isEmpty()) {
            return new GetPrescriptionResponse(errors);
        }
        return prescriptionRepository.getById(request.getId())
                .map(GetPrescriptionResponse::new)
                .orElseGet(() -> {
                    errors.add(new CoreError("id", "Not found!"));
                    return new GetPrescriptionResponse(errors);
                });
    }
}
