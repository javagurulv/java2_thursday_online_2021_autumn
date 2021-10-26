package lv.javaguru.java2.hospital.prescription.core.responses;

import lv.javaguru.java2.hospital.domain.Prescription;

import java.util.List;

public class AddPrescriptionResponse extends CoreResponse {

    private Prescription prescription;

    public AddPrescriptionResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddPrescriptionResponse(Prescription prescription) {
        this.prescription = prescription;
    }

    public Prescription getPrescription() {
        return prescription;
    }
}
