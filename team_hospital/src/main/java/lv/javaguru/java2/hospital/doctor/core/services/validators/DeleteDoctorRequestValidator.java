package lv.javaguru.java2.hospital.doctor.core.services.validators;

import lv.javaguru.java2.hospital.database.prescription_repository.PrescriptionRepository;
import lv.javaguru.java2.hospital.database.visit_repository.VisitRepository;
import lv.javaguru.java2.hospital.doctor.core.requests.DeleteDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import lv.javaguru.java2.hospital.doctor.core.services.checkers.DoctorLongNumChecker;
import lv.javaguru.java2.hospital.doctor.core.services.validators.existence.DoctorExistenceByIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DeleteDoctorRequestValidator {

    @Autowired private DoctorLongNumChecker longNumChecker;
    @Autowired private DoctorExistenceByIdValidator validator;
    @Autowired private VisitRepository visitRepository;
    @Autowired private PrescriptionRepository prescriptionRepository;

    public List<CoreError> validate(DeleteDoctorRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        validateNumInID(request).ifPresent(errors::add);
        if (errors.isEmpty()) {
            validateDoctorExistence(request).ifPresent(errors::add);
            validateDoctorExistenceInVisits(request).ifPresent(errors::add);
            validateDoctorExistenceInPrescriptions(request).ifPresent(errors::add);
        }

        return errors;
    }

    private Optional<CoreError> validateId(DeleteDoctorRequest request) {
        return (request.getDoctorIdToDelete() == null || request.getDoctorIdToDelete().isEmpty())
                ? Optional.of(new CoreError("id", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateDoctorExistence(DeleteDoctorRequest request) {
        if (request.getDoctorIdToDelete() == null || request.getDoctorIdToDelete().isEmpty()) {
            return Optional.empty();
        }
        return validator.validateExistenceById(request.getDoctorIdToDelete());
    }

    private Optional<CoreError> validateNumInID(DeleteDoctorRequest request) {
        return (request.getDoctorIdToDelete() == null || request.getDoctorIdToDelete().isEmpty())
                ? Optional.empty() : longNumChecker.validate(request.getDoctorIdToDelete(), "ID");
    }

    private Optional<CoreError> validateDoctorExistenceInVisits(DeleteDoctorRequest request){
        return request.getDoctorIdToDelete() == null || request.getDoctorIdToDelete().isEmpty()
                ? Optional.empty() : visitRepository.findPatientForDeleting(Long.valueOf(request.getDoctorIdToDelete())).isEmpty()
                ? Optional.empty() : Optional.of(new CoreError("Doctor", "is in visits list, can`t delete!"));
    }

    private Optional<CoreError> validateDoctorExistenceInPrescriptions(DeleteDoctorRequest request){
        return request.getDoctorIdToDelete() == null || request.getDoctorIdToDelete().isEmpty()
                ? Optional.empty() : prescriptionRepository.findPatientForDeleting(Long.valueOf(request.getDoctorIdToDelete())).isEmpty()
                ? Optional.empty() : Optional.of(new CoreError("Doctor", "is in prescriptions list, can`t delete him!"));
    }
}
