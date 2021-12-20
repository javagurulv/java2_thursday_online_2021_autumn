package lv.javaguru.java2.hospital.prescription.core.services;

import lv.javaguru.java2.hospital.database.doctor_repository.DoctorRepository;
import lv.javaguru.java2.hospital.database.jpa.JpaDoctorRepository;
import lv.javaguru.java2.hospital.database.jpa.JpaPatientRepository;
import lv.javaguru.java2.hospital.database.jpa.JpaPrescriptionRepository;
import lv.javaguru.java2.hospital.database.patient_repository.PatientRepository;
import lv.javaguru.java2.hospital.database.prescription_repository.PrescriptionRepository;
import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.domain.Prescription;
import lv.javaguru.java2.hospital.prescription.core.requests.AddPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.AddPrescriptionResponse;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import lv.javaguru.java2.hospital.prescription.core.services.validators.AddPrescriptionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class AddPrescriptionService {

    @Autowired private JpaPrescriptionRepository database;
    @Autowired private JpaPatientRepository patientRepository;
    @Autowired private JpaDoctorRepository doctorRepository;
    @Autowired private AddPrescriptionValidator validator;

    public AddPrescriptionResponse execute(AddPrescriptionRequest request) {
        List<CoreError> errors = validator.validate(request);
        if(!errors.isEmpty()) {
            return new AddPrescriptionResponse(errors);
        }

        Prescription prescription = new Prescription
                (getDoctor(request), getPatient(request), request.getMedicationName(), Integer.parseInt(request.getQuantity()));
        database.save(prescription);
        return new AddPrescriptionResponse(prescription);
    }

    private Doctor getDoctor(AddPrescriptionRequest request) {
        return doctorRepository.findById(Long.parseLong(request.getDoctorId())).get();
    }

    private Patient getPatient(AddPrescriptionRequest request) {
        if (patientRepository.findById(Long.parseLong(request.getPatientId())).isPresent()) {
            return patientRepository.findById(Long.parseLong(request.getPatientId())).get();
        }
        else return null;
    }
}
