package lv.javaguru.java2.hospital.prescription.core.services;

import lv.javaguru.java2.hospital.database.DoctorDatabase;
import lv.javaguru.java2.hospital.database.PatientDatabase;
import lv.javaguru.java2.hospital.database.PrescriptionDatabase;
import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.domain.Prescription;
import lv.javaguru.java2.hospital.prescription.core.requests.AddPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.AddPrescriptionResponse;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import lv.javaguru.java2.hospital.prescription.core.services.validators.AddPrescriptionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddPrescriptionService {

    @Autowired private PrescriptionDatabase database;
    @Autowired private PatientDatabase patientDatabase;
    @Autowired private DoctorDatabase doctorDatabase;
    @Autowired private AddPrescriptionValidator validator;

    public AddPrescriptionResponse execute(AddPrescriptionRequest request) {
        List<CoreError> errors = validator.validate(request);
        if(!errors.isEmpty()) {
            return new AddPrescriptionResponse(errors);
        }

        Prescription prescription = new Prescription
                (getDoctor(request), getPatient(request), request.getMedicationName(), request.getQuantity());
        database.addPrescription(prescription);
        return new AddPrescriptionResponse(prescription);
    }

    private Doctor getDoctor(AddPrescriptionRequest request) {
        return doctorDatabase.findById(request.getDoctorId()).get(0);
    }

    private Patient getPatient(AddPrescriptionRequest request) {
        if (patientDatabase.findById(request.getPatientId()).isPresent()) {
            return patientDatabase.findById(request.getPatientId()).get();
        }
        else return null;
    }
}
