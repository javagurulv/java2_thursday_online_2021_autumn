package lv.javaguru.java2.hospital.visit.core.services.validators;

import lv.javaguru.java2.hospital.database.doctor_repository.DoctorRepository;
import lv.javaguru.java2.hospital.database.patient_repository.PatientRepository;
import lv.javaguru.java2.hospital.database.visit_repository.VisitRepository;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.prescription.core.services.search_criteria.DoctorIdSearchCriteria;
import lv.javaguru.java2.hospital.visit.core.checkers.VisitEnumChecker;
import lv.javaguru.java2.hospital.visit.core.checkers.VisitLongNumChecker;
import lv.javaguru.java2.hospital.visit.core.requests.EditVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.services.validators.date_validator.DateValidatorExecution;
import lv.javaguru.java2.hospital.visit.core.services.validators.existence_validators.VisitExistenceByIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Component
public class EditVisitValidator {

    @Autowired private VisitExistenceByIdValidator validator;
    @Autowired private VisitEnumChecker visitEnumChecker;
    @Autowired private DateValidatorExecution dateValidator;
    @Autowired private VisitLongNumChecker longNumChecker;
    @Autowired private PatientRepository patientRepository;
    @Autowired private DoctorRepository doctorRepository;

    public List<CoreError> validate(EditVisitRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        validateVisitIDParse(request).ifPresent(errors::add);
        validateChanges(request).ifPresent(errors::add);
        validateChangesIDParse(request).ifPresent(errors::add);
        validateEnum(request).ifPresent(errors::add);
        errors.addAll(validateDate(request));
        if(errors.isEmpty()){
            validateVisitExistence(request).ifPresent(errors::add);
            validateDoctorExistence(request).ifPresent(errors::add);
            validatePatientExistence(request).ifPresent(errors::add);
        }
        return errors;
    }

    private Optional<CoreError> validateId(EditVisitRequest request) {
        return (request.getVisitID() == null || request.getVisitID().isEmpty())
                ? Optional.of(new CoreError("ID", "must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateVisitIDParse(EditVisitRequest request){
        return (request.getVisitID() == null || request.getVisitID().isEmpty())
                ? Optional.empty() : longNumChecker.validate(request.getVisitID(), "ID");
    }

    private Optional<CoreError> validateChangesIDParse(EditVisitRequest request){
        return (request.getEditEnums() == null || request.getEditEnums().isEmpty())
                ? Optional.empty() : request.getEditEnums().toUpperCase(Locale.ROOT).equals("DOCTOR_ID")
                || request.getEditEnums().toUpperCase(Locale.ROOT).equals("PATIENT_ID")
                ? longNumChecker.validate(request.getChanges(), "ID in changes") : Optional.empty();
    }

    private Optional<CoreError> validateVisitExistence(EditVisitRequest request)  {
        return (request.getVisitID() == null || request.getVisitID().isEmpty())
                ? Optional.empty() : validator.validateExistenceById(Long.valueOf(request.getVisitID()));
    }

    private Optional<CoreError> validateChanges(EditVisitRequest request) {
        return (request.getChanges() == null || request.getChanges().isEmpty())
                ? Optional.of(new CoreError("Changes", "must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateEnum(EditVisitRequest request){
        return (request.getEditEnums() == null || request.getEditEnums().isEmpty())
                ? Optional.of(new CoreError("Edit option", "must not be empty!"))
                : visitEnumChecker.validateEnum(request.getEditEnums());
    }

    private List<CoreError> validateDate(EditVisitRequest request){
        return request.getChanges() == null || request.getChanges().isEmpty()
                ? new ArrayList<>() : request.getEditEnums().equals("DATE")
                ? dateValidator.validate(request.getChanges()) : new ArrayList<>();
    }

    private Optional<CoreError> validateDoctorExistence(EditVisitRequest request){
        return (request.getEditEnums() == null || request.getEditEnums().isEmpty())
                ? Optional.empty() : !request.getEditEnums().toUpperCase(Locale.ROOT).equals("DOCTOR_ID")
                ? Optional.empty() : doctorRepository.findById(Long.parseLong(request.getChanges())).isEmpty()
                ? Optional.of(new CoreError("Doctor", "does not exist!")) : Optional.empty();
    }

    private Optional<CoreError> validatePatientExistence(EditVisitRequest request){
        return (request.getEditEnums() == null || request.getEditEnums().isEmpty())
                ? Optional.empty() : !request.getEditEnums().toUpperCase(Locale.ROOT).equals("PATIENT_ID")
                ? Optional.empty() : patientRepository.findById(Long.parseLong(request.getChanges())).isEmpty()
                ? Optional.of(new CoreError("Patient", "does not exist!")) : Optional.empty();
    }
}
