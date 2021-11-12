package lv.javaguru.java2.hospital.prescription.core.services;

import lv.javaguru.java2.hospital.database.prescription_repository.PrescriptionRepository;
import lv.javaguru.java2.hospital.prescription.core.requests.EditPrescriptionEnum;
import lv.javaguru.java2.hospital.prescription.core.requests.EditPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import lv.javaguru.java2.hospital.prescription.core.responses.EditPrescriptionResponse;
import lv.javaguru.java2.hospital.prescription.core.services.validators.EditPrescriptionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class EditPrescriptionService {

    private @Autowired
    PrescriptionRepository database;
    private @Autowired EditPrescriptionValidator validator;

    public EditPrescriptionResponse execute(EditPrescriptionRequest request){
        List<CoreError> errors = validator.validate(request);

        if(!errors.isEmpty()){
            return new EditPrescriptionResponse(errors);
        }

        return new EditPrescriptionResponse(
                database.editPrescription(Long.valueOf(request.getPrescriptionID()),
                EditPrescriptionEnum.valueOf(request.getEditPrescriptionEnum()),
                request.getChanges()));
    }
}
