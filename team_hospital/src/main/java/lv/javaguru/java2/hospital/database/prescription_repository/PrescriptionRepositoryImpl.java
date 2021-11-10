package lv.javaguru.java2.hospital.database.prescription_repository;

import lv.javaguru.java2.hospital.database.doctor_repository.DoctorRepository;
import lv.javaguru.java2.hospital.database.patient_repository.PatientRepository;
import lv.javaguru.java2.hospital.domain.Prescription;
import lv.javaguru.java2.hospital.prescription.core.requests.EditPrescriptionEnum;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

//@Component
public class PrescriptionRepositoryImpl implements PrescriptionRepository {

    private Long nextId = 1L;
    private List<Prescription> prescriptions = new ArrayList<>();
    @Autowired private PatientRepository patientRepository;
    @Autowired private DoctorRepository doctorRepository;

    @Override
    public void addPrescription(Prescription prescription) {
        prescription.setId(nextId);
        nextId++;
        prescriptions.add(prescription);
    }

    @Override
    public boolean editPrescription(Long prescriptionID, EditPrescriptionEnum prescriptionEnum, String changes) {
        Optional<Prescription> prescriptionToEdit = prescriptions.stream()
                .filter(prescription -> Objects.equals(prescription.getId(), prescriptionID))
                .findFirst();
        if (prescriptionToEdit.isPresent()) {
            Prescription prescription = prescriptionToEdit.get();
            if (prescriptionEnum.equals(EditPrescriptionEnum.PATIENT)) {
                prescription.setPatient(patientRepository.findById(Long.parseLong(changes)).get(0));
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
    public List<Prescription> getAllPrescriptions() {
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

    @Override
    public List<Prescription> findByPrescriptionId(Long prescriptionId) {
        return prescriptions.stream()
                .filter(prescription -> prescription.getId().equals(prescriptionId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Prescription> findByDoctorId(Long doctorId) {
        return prescriptions.stream()
                .filter(prescription -> prescription.getDoctor().getId().equals(doctorId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Prescription> findByPatientId(Long patientId) {
        return prescriptions.stream()
                .filter(prescription -> prescription.getPatient().getId().equals(patientId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Prescription> findByDoctorAndPatientId(Long doctorId, Long patientId) {
        return prescriptions.stream()
                .filter(prescription -> prescription.getDoctor().getId().equals(doctorId))
                .filter(prescription -> prescription.getPatient().getId().equals(patientId))
                .collect(Collectors.toList());
    }

}
