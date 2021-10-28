package lv.javaguru.java2.hospital.prescription.core.services;

import lv.javaguru.java2.hospital.database.PrescriptionDatabase;
import lv.javaguru.java2.hospital.domain.Prescription;
import lv.javaguru.java2.hospital.prescription.core.requests.ShowAllPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.ShowAllPrescriptionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShowAllPrescriptionService {

    @Autowired private PrescriptionDatabase database;

    public ShowAllPrescriptionResponse execute(ShowAllPrescriptionRequest request) {
        List<Prescription> prescriptions = database.getAllPrescriptions();
        return new ShowAllPrescriptionResponse(prescriptions);
    }
}
