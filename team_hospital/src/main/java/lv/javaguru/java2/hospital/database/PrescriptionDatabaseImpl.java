package lv.javaguru.java2.hospital.database;

import lv.javaguru.java2.hospital.domain.Prescription;
import lv.javaguru.java2.hospital.prescription.core.requests.EditPrescriptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class PrescriptionDatabaseImpl implements PrescriptionDatabase {

    private Long nextId = 1L;
    private List<Prescription> prescriptions = new ArrayList<>();
    @Autowired private PatientDatabase patientDatabase;

    @Override
    public void addPrescription(Prescription prescription) {
        prescription.setId(nextId);
        nextId++;
        prescriptions.add(prescription);
    }

    @Override
    public boolean EditPrescription(Long prescriptionID, EditPrescriptionEnum prescriptionEnum, String changes) {
        Optional<Prescription> prescriptionToEdit = prescriptions.stream()
                .filter(prescription -> Objects.equals(prescription.getId(), prescriptionID))
                .findFirst();
        if (prescriptionToEdit.isPresent()) {
            Prescription prescription = prescriptionToEdit.get();
            if (prescriptionEnum.equals(EditPrescriptionEnum.PATIENT)) {
                prescription.setPatient(patientDatabase.findById(Long.parseLong(changes)).get());
                return true;
            } else if (prescriptionEnum.equals(EditPrescriptionEnum.QUANTITY)) {
                prescription.setQuantity(Integer.parseInt(changes));
                return true;
            } else if (prescriptionEnum.equals(EditPrescriptionEnum.MEDICATION_NAME)) {
                prescription.setMedicationName(changes);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    @Override
    public boolean deletePrescriptionById(Long id) {
        boolean isPrescriptionDeleted = false;
        Optional<Prescription> prescriptionToDeleteOpt = prescriptions.stream()
                .filter(prescription -> prescription.getId() == id)
                .findFirst();
        if(prescriptionToDeleteOpt.isPresent()) {
            Prescription prescriptionToDelete = prescriptionToDeleteOpt.get();
            isPrescriptionDeleted = prescriptions.remove(prescriptionToDelete);
        }
        return isPrescriptionDeleted;
    }

}
