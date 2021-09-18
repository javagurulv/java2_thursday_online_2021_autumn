package lv.javaguru.java2.hospital.visits.services.validators;

import lv.javaguru.java2.hospital.visits.request.PatientVisitRequest;
import lv.javaguru.java2.hospital.visits.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientVisitValidator {

    public List<CoreError> validate(PatientVisitRequest patientVisitRequest) {
        List<CoreError> errors = new ArrayList<>();
        validatePatientsPersonalCode(patientVisitRequest).ifPresent(errors::add);
        validateDoctorsName(patientVisitRequest).ifPresent(errors::add);
        validateDoctorsSurname(patientVisitRequest).ifPresent(errors::add);
        validateVisitDate(patientVisitRequest).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validatePatientsPersonalCode(PatientVisitRequest request) {
        return (request.getPatientsPersonalCode() == null || request.getPatientsPersonalCode().isEmpty())
                ? Optional.of(new CoreError("Patients name", "must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validateDoctorsName(PatientVisitRequest request) {
        return (request.getDoctorsName() == null || request.getDoctorsName().isEmpty())
                ? Optional.of(new CoreError("Doctor`s name", "must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validateDoctorsSurname(PatientVisitRequest request) {
        return (request.getDoctorsSurname() == null || request.getDoctorsSurname().isEmpty())
                ? Optional.of(new CoreError("Doctor`s surname", "must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validateVisitDate(PatientVisitRequest request) {
        return (request.getVisitDate() == null || request.getVisitDate().isEmpty())
                ? Optional.of(new CoreError("Visit date", "must not be empty!")) : Optional.empty();
    }
}
