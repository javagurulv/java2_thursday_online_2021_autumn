package lv.javaguru.java2.hospital.prescription.core.services.validators;

import lv.javaguru.java2.hospital.database.doctor_repository.DoctorRepository;
import lv.javaguru.java2.hospital.database.jpa.JpaDoctorRepository;
import lv.javaguru.java2.hospital.database.jpa.JpaPatientRepository;
import lv.javaguru.java2.hospital.database.patient_repository.PatientRepository;
import lv.javaguru.java2.hospital.prescription.core.requests.AddPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import lv.javaguru.java2.hospital.prescription.core.services.validators.existence.PrescriptionExistenceForAddValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddPrescriptionValidator {

    @Autowired
    private JpaPatientRepository patientRepository;
    @Autowired
    private JpaDoctorRepository doctorRepository;
    @Autowired
    private PrescriptionExistenceForAddValidator existence;

    public List<CoreError> validate(AddPrescriptionRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateDoctorId(request).ifPresent(errors::add);
        validatePatientId(request).ifPresent(errors::add);
        validateMedicationName(request).ifPresent(errors::add);
        validateQuantity(request).ifPresent(errors::add);
        validateDoctorExistence(request).ifPresent(errors::add);
        validatePatientExistence(request).ifPresent(errors::add);
        validatePrescriptionExistence(request, errors);
        return errors;
    }

    private Optional<CoreError> validateDoctorId(AddPrescriptionRequest request) {
        return (request.getDoctorId() == null || request.getDoctorId().isEmpty())
                ? Optional.of(new CoreError("Doctor id", "must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validatePatientId(AddPrescriptionRequest request) {
        return (request.getPatientId() == null || request.getPatientId().isEmpty())
                ? Optional.of(new CoreError("Patient id", "must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validateMedicationName(AddPrescriptionRequest request) {
        return (request.getMedicationName() == null || request.getMedicationName().isEmpty())
                ? Optional.of(new CoreError("Medication name", "must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validateQuantity(AddPrescriptionRequest request) {
        if (request.getQuantity() == null) {
            return Optional.of(new CoreError("Medication quantity", "must not be empty!"));
        } else if (Integer.parseInt(request.getQuantity()) <= 0) {
            return Optional.of(new CoreError("Medication quantity", "must be greater than 0!"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateDoctorExistence(AddPrescriptionRequest request) {
        return request.getDoctorId() == null ? Optional.empty() :
                doctorRepository.findById(Long.parseLong(request.getDoctorId())).isEmpty() ?
                        Optional.of(new CoreError("Doctor", "does not exist!")) : Optional.empty();
    }

    private Optional<CoreError> validatePatientExistence(AddPrescriptionRequest request) {
        return request.getPatientId() == null ? Optional.empty() :
                patientRepository.findById(Long.parseLong(request.getPatientId())).isEmpty() ?
                        Optional.of(new CoreError("Patient", "does not exist!")) : Optional.empty();
    }

    private void validatePrescriptionExistence(AddPrescriptionRequest request, List<CoreError> errors) {
        Optional<CoreError> error = existence.validatePrescriptionExistence(request);
        error.ifPresent(errors::add);
    }
}
