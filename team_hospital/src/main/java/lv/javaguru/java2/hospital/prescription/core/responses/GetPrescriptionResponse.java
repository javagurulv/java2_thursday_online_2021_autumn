package lv.javaguru.java2.hospital.prescription.core.responses;

import lv.javaguru.java2.hospital.domain.Prescription;

import java.util.List;

public class GetPrescriptionResponse extends CoreResponse{

    private Prescription prescription;

    public GetPrescriptionResponse(List<CoreError> errors) {
        super(errors);
    }

    public GetPrescriptionResponse(Prescription prescription) {
        this.prescription = prescription;
    }

    public Prescription getPrescription() {
        return prescription;
    }
}
