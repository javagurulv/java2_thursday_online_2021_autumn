package lv.javaguru.java2.hospital.prescription.core.services;

import lv.javaguru.java2.hospital.database.jpa.JpaPrescriptionRepository;
import lv.javaguru.java2.hospital.database.prescription_repository.PrescriptionRepository;
import lv.javaguru.java2.hospital.domain.Prescription;
import lv.javaguru.java2.hospital.prescription.core.requests.ShowAllPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.ShowAllPrescriptionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class ShowAllPrescriptionService {

    @Autowired private JpaPrescriptionRepository database;

    public ShowAllPrescriptionResponse execute(ShowAllPrescriptionRequest request) {
        List<Prescription> prescriptions = database.findAll();
        return new ShowAllPrescriptionResponse(prescriptions);
    }
}
