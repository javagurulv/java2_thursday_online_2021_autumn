package lv.javaguru.java2.hospital.patient.core.services.validators;

import lv.javaguru.java2.hospital.database.jpa.JpaPrescriptionRepository;
import lv.javaguru.java2.hospital.database.jpa.JpaVisitRepository;
import lv.javaguru.java2.hospital.database.prescription_repository.PrescriptionRepository;
import lv.javaguru.java2.hospital.database.visit_repository.VisitRepository;
import lv.javaguru.java2.hospital.patient.core.requests.DeletePatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import lv.javaguru.java2.hospital.patient.core.services.checkers.PatientLongNumChecker;
import lv.javaguru.java2.hospital.patient.core.services.validators.patient_existence.PatientExistenceByIDValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DeletePatientValidator {

    @Autowired private PatientLongNumChecker longNumChecker;
    @Autowired private PatientExistenceByIDValidator validator;
    @Autowired private JpaVisitRepository visitRepository;
    @Autowired private JpaPrescriptionRepository prescriptionRepository;

    public List<CoreError> validate(DeletePatientRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateID(request).ifPresent(errors::add);
        validateNumInID(request).ifPresent(errors::add);
        if(errors.isEmpty()){
            validatePatientExistence(request).ifPresent(errors::add);
            validatePatientExistenceInVisits(request).ifPresent(errors::add);
            validatePatientExistenceInPrescriptions(request).ifPresent(errors::add);
        }

        return errors;
    }

    private Optional<CoreError> validateID(DeletePatientRequest request) {
        return (request.getIdRequest() == null || request.getIdRequest().isEmpty())
                ? Optional.of(new CoreError("ID", "must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validateNumInID(DeletePatientRequest request) {
        return (request.getIdRequest() == null || request.getIdRequest().isEmpty())
                ? Optional.empty() : longNumChecker.validate(request.getIdRequest(), "ID");
    }

    private Optional<CoreError> validatePatientExistence(DeletePatientRequest request) {
        return request.getIdRequest() == null || request.getIdRequest().isEmpty()
        ? Optional.empty() : validator.existenceByID(request.getIdRequest());
    }

    private Optional<CoreError> validatePatientExistenceInVisits(DeletePatientRequest request){
        return request.getIdRequest() == null || request.getIdRequest().isEmpty()
                ? Optional.empty() : visitRepository.findByPatientId(Long.valueOf(request.getIdRequest())).isEmpty()
                ? Optional.empty() : Optional.of(new CoreError("Patient", "is in visits list, can`t delete him!"));
    }

    private Optional<CoreError> validatePatientExistenceInPrescriptions(DeletePatientRequest request){
        return request.getIdRequest() == null || request.getIdRequest().isEmpty()
                ? Optional.empty() : prescriptionRepository.findByPatientId(Long.valueOf(request.getIdRequest())).isEmpty()
                ? Optional.empty() : Optional.of(new CoreError("Patient", "is in prescriptions list, can`t delete him!"));
    }
}

