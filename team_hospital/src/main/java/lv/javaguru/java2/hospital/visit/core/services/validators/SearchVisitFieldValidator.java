package lv.javaguru.java2.hospital.visit.core.services.validators;

import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SearchVisitFieldValidator {

    public List<CoreError> validate(SearchVisitRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateVisitID(request).ifPresent(errors::add);
        validateDoctorID(request).ifPresent(errors::add);
        validatePatientID(request).ifPresent(errors::add);
        validateVisitDate(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateVisitID(SearchVisitRequest request){
        return request.getVisitId() == null || request.getVisitId().isEmpty()
                ? Optional.of(new CoreError("visitId", "Must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validateDoctorID(SearchVisitRequest request){
        return request.getDoctorId() == null || request.getDoctorId().isEmpty()
                ? Optional.of(new CoreError("doctorId", "Must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validatePatientID(SearchVisitRequest request){
        return request.getPatientId() == null || request.getPatientId().isEmpty()
                ? Optional.of(new CoreError("patientId", "Must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validateVisitDate(SearchVisitRequest request){
        return request.getVisitDate() == null || request.getVisitDate().isEmpty()
                ? Optional.of(new CoreError("visitDate", "Must not be empty!")) : Optional.empty();
    }
}
