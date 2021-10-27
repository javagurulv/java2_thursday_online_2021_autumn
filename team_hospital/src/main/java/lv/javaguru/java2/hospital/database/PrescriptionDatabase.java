package lv.javaguru.java2.hospital.database;

import lv.javaguru.java2.hospital.domain.Prescription;
import lv.javaguru.java2.hospital.prescription.core.requests.EditPrescriptionEnum;

import java.util.List;

public interface PrescriptionDatabase {

    void addPrescription(Prescription prescription);

    boolean EditPrescription(Long prescriptionID, EditPrescriptionEnum prescriptionEnum, String changes);

    List<Prescription> getPrescriptions();

}
