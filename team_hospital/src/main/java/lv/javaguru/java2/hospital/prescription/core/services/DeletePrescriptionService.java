package lv.javaguru.java2.hospital.prescription.core.services;

import lv.javaguru.java2.hospital.database.PrescriptionDatabase;
import lv.javaguru.java2.hospital.prescription.core.requests.DeletePrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import lv.javaguru.java2.hospital.prescription.core.responses.DeletePrescriptionResponse;
import lv.javaguru.java2.hospital.prescription.core.services.validators.DeletePrescriptionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeletePrescriptionService {

    @Autowired private PrescriptionDatabase database;
    @Autowired private DeletePrescriptionValidator validator;

    public DeletePrescriptionResponse execute(DeletePrescriptionRequest request) {
        List<CoreError> errors = validator.validate(request);
        if(!errors.isEmpty()) {
            return new DeletePrescriptionResponse(errors);
        }
        boolean isPrescriptionDeleted = database.deletePrescriptionById(request.getPrescriptionIdToDelete());
        return new DeletePrescriptionResponse(isPrescriptionDeleted);
    }
}
