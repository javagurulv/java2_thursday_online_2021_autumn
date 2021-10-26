package lv.javaguru.java2.hospital.database;

import lv.javaguru.java2.hospital.domain.Prescription;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PrescriptionDatabaseImpl implements PrescriptionDatabase{

    private Long nextId = 1L;
    private List<Prescription> prescriptions = new ArrayList<>();

    @Override
    public void addPrescription(Prescription prescription) {
        prescription.setId(nextId);
        nextId++;
        prescriptions.add(prescription);
    }
}
