package lv.javaguru.java2.hospital.visit.core.services.validators;

import lv.javaguru.java2.hospital.database.DoctorDatabase;
import lv.javaguru.java2.hospital.database.PatientDatabase;
import lv.javaguru.java2.hospital.visit.core.requests.AddVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.services.validators.date_validator.DateValidatorExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddVisitValidator {

    @Autowired private PatientDatabase patientDatabase;
    @Autowired private DoctorDatabase doctorDatabase;
    @Autowired private DateValidatorExecution dateValidator;

    public List<CoreError> validate(AddVisitRequest patientVisitRequest) {
        List<CoreError> errors = new ArrayList<>();
        validatePatientsPersonalCode(patientVisitRequest).ifPresent(errors::add);
        validateDoctorsName(patientVisitRequest).ifPresent(errors::add);
        validateDoctorsSurname(patientVisitRequest).ifPresent(errors::add);
        validateDateFieldOnEmptiness(patientVisitRequest).ifPresent(errors::add);
        validatePatientExistence(patientVisitRequest).ifPresent(errors::add);
        validateDoctorExistence(patientVisitRequest).ifPresent(errors::add);
        errors.addAll(validateDate(patientVisitRequest));
        return errors;
    }

    private Optional<CoreError> validatePatientsPersonalCode(AddVisitRequest request) {
        return (request.getPatientsPersonalCode() == null || request.getPatientsPersonalCode().isEmpty())
                ? Optional.of(new CoreError("Patient personal code", "must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validateDoctorsName(AddVisitRequest request) {
        return (request.getDoctorsName() == null || request.getDoctorsName().isEmpty())
                ? Optional.of(new CoreError("Doctor name", "must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validateDoctorsSurname(AddVisitRequest request) {
        return (request.getDoctorsSurname() == null || request.getDoctorsSurname().isEmpty())
                ? Optional.of(new CoreError("Doctor surname", "must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validateDateFieldOnEmptiness(AddVisitRequest request) {
        return (request.getVisitDate() == null || request.getVisitDate().isEmpty())
                ? Optional.of(new CoreError("Visit date", "must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validatePatientExistence(AddVisitRequest request) {
        return request == null ? Optional.empty() :
                patientDatabase.findPatientsByPersonalCode(request.getPatientsPersonalCode()).isEmpty() ?
                        Optional.of(new CoreError("Patient", "does not exist!")) : Optional.empty();
    }

    private Optional<CoreError> validateDoctorExistence(AddVisitRequest request) {
        return request == null ? Optional.empty() :
                doctorDatabase.findByNameAndSurname(request.getDoctorsName(), request.getDoctorsSurname()).isEmpty() ?
                        Optional.of(new CoreError("Doctor", "does not exist!")) : Optional.empty();
    }

    private List<CoreError> validateDate(AddVisitRequest request){
       return request == null ? new ArrayList<>() : dateValidator.validate(request);
    }
}
