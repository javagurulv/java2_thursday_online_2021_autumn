package lv.javaguru.java2.hospital.visit.core.services.validators;

import lv.javaguru.java2.hospital.database.doctor_repository.DoctorRepository;
import lv.javaguru.java2.hospital.database.patient_repository.PatientRepository;
import lv.javaguru.java2.hospital.visit.core.checkers.VisitLongNumChecker;
import lv.javaguru.java2.hospital.visit.core.requests.AddVisitRequest;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.services.search_visit_service.VisitSearchExecute;
import lv.javaguru.java2.hospital.visit.core.services.validators.date_validator.DateValidatorExecution;
import lv.javaguru.java2.hospital.visit.core.services.validators.existence_validators.VisitExistenceForAdding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddVisitValidator {

    @Autowired private PatientRepository patientRepository;
    @Autowired private DoctorRepository doctorRepository;
    @Autowired private DateValidatorExecution dateValidator;
    @Autowired private VisitLongNumChecker longNumChecker;
    @Autowired private VisitExistenceForAdding visitExistenceForAdding;

    public List<CoreError> validate(AddVisitRequest visitRequest) {
        List<CoreError> errors = new ArrayList<>();
        validatePatientID(visitRequest).ifPresent(errors::add);
        validatePatientIDParse(visitRequest).ifPresent(errors::add);
        validateDoctorID(visitRequest).ifPresent(errors::add);
        validateDoctorIDParse(visitRequest).ifPresent(errors::add);
        validateDateFieldOnEmptiness(visitRequest).ifPresent(errors::add);
        errors.addAll(validateDate(visitRequest));
        if(errors.isEmpty()){
            validatePatientExistence(visitRequest).ifPresent(errors::add);
            validateDoctorExistence(visitRequest).ifPresent(errors::add);
            validateVisitForExistence(visitRequest).ifPresent(errors::add);
        }
        return errors;
    }

    private Optional<CoreError> validatePatientID(AddVisitRequest request) {
        return (request.getPatientID() == null || request.getPatientID().isEmpty())
                ? Optional.of(new CoreError("Patient ID", "must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validatePatientIDParse(AddVisitRequest request){
        return (request.getPatientID() == null || request.getPatientID().isEmpty())
                ? Optional.empty() : longNumChecker.validate(request.getPatientID(), "Patient ID");
    }

    private Optional<CoreError> validateDoctorID(AddVisitRequest request) {
        return (request.getDoctorsID() == null || request.getDoctorsID().isEmpty())
                ? Optional.of(new CoreError("Doctor ID", "must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validateDoctorIDParse(AddVisitRequest request){
        return (request.getDoctorsID() == null || request.getDoctorsID().isEmpty())
                ? Optional.empty() : longNumChecker.validate(request.getDoctorsID(), "Doctor ID");
    }

    private Optional<CoreError> validateDateFieldOnEmptiness(AddVisitRequest request) {
        return (request.getVisitDate() == null || request.getVisitDate().isEmpty())
                ? Optional.of(new CoreError("Visit date", "must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validatePatientExistence(AddVisitRequest request) {
        return request.getPatientID() == null || request.getPatientID().isEmpty()
                ? Optional.empty() : patientRepository.findById(Long.valueOf(request.getPatientID())).isEmpty()
                ? Optional.of(new CoreError("Patient", "does not exist!")) : Optional.empty();
    }

    private Optional<CoreError> validateDoctorExistence(AddVisitRequest request) {
        return request.getDoctorsID() == null || request.getDoctorsID().isEmpty()
                ? Optional.empty() : doctorRepository.findById(Long.valueOf(request.getDoctorsID())).isEmpty()
                ? Optional.of(new CoreError("Doctor", "does not exist!")) : Optional.empty();
    }

    private List<CoreError> validateDate(AddVisitRequest request){
       return request.getVisitDate() == null || request.getVisitDate().isEmpty()
               ? new ArrayList<>() : dateValidator.validate(request.getVisitDate());
    }

    private Optional<CoreError> validateVisitForExistence(AddVisitRequest request){
        return request.getVisitDate() == null || request.getVisitDate().isEmpty()
                ? Optional.empty() : visitExistenceForAdding.validateExistenceForAdding(request);
    }
}