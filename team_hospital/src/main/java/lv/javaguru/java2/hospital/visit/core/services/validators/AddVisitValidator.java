package lv.javaguru.java2.hospital.visit.core.services.validators;

import lv.javaguru.java2.hospital.database.doctor_repository.DoctorRepository;
import lv.javaguru.java2.hospital.database.patient_repository.PatientRepository;
import lv.javaguru.java2.hospital.visit.core.checkers.VisitLongNumChecker;
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

    @Autowired private PatientRepository patientRepository;
    @Autowired private DoctorRepository doctorRepository;
    @Autowired private DateValidatorExecution dateValidator;
    @Autowired private VisitLongNumChecker longNumChecker;

    public List<CoreError> validate(AddVisitRequest patientVisitRequest) {
        List<CoreError> errors = new ArrayList<>();
        validatePatientID(patientVisitRequest).ifPresent(errors::add);
        validatePatientIDParse(patientVisitRequest).ifPresent(errors::add);
        validateDoctorID(patientVisitRequest).ifPresent(errors::add);
        validateDoctorIDParse(patientVisitRequest).ifPresent(errors::add);
        validateDateFieldOnEmptiness(patientVisitRequest).ifPresent(errors::add);
        errors.addAll(validateDate(patientVisitRequest));
        if(errors.isEmpty()){
            validatePatientExistence(patientVisitRequest).ifPresent(errors::add);
            validateDoctorExistence(patientVisitRequest).ifPresent(errors::add);
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
}