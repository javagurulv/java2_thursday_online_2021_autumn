package lv.javaguru.java2.hospital.prescription.core.services;

import lv.javaguru.java2.hospital.database.PrescriptionDatabase;
import lv.javaguru.java2.hospital.prescription.core.requests.EditPrescriptionEnum;
import lv.javaguru.java2.hospital.prescription.core.requests.EditPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import lv.javaguru.java2.hospital.prescription.core.responses.EditPrescriptionResponse;
import lv.javaguru.java2.hospital.prescription.core.services.validators.EditPrescriptionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EditPrescriptionService {

    private @Autowired PrescriptionDatabase database;
    private @Autowired EditPrescriptionValidator validator;

    public EditPrescriptionResponse execute(EditPrescriptionRequest request){
        List<CoreError> errors = validator.validate(request);

        if(!errors.isEmpty()){
            return new EditPrescriptionResponse(errors);
        }

        return new EditPrescriptionResponse(
                database.EditPrescription(request.getPrescriptionID(),
                EditPrescriptionEnum.valueOf(request.getEditPrescriptionEnum()),
                request.getChanges()));
    }
}
