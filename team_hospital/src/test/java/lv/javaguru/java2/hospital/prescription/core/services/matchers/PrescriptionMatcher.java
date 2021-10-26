package lv.javaguru.java2.hospital.prescription.core.services.matchers;

import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.domain.Prescription;
import org.mockito.ArgumentMatcher;

import java.time.LocalDate;

public class PrescriptionMatcher implements ArgumentMatcher<Prescription> {

    private Doctor doctor;
    private Patient patient;
    private String medicationName;
    private Integer quantity;

    public PrescriptionMatcher(Doctor doctor, Patient patient, String medicationName, Integer quantity) {
        this.doctor = doctor;
        this.patient = patient;
        this.medicationName = medicationName;
        this.quantity = quantity;
    }

    @Override
    public boolean matches(Prescription prescription) {
        return prescription.getDoctor().equals(doctor)
        && prescription.getPatient().equals(patient)
                && prescription.getMedication().equals(medicationName)
                && prescription.getQuantity() == quantity;
    }
}
