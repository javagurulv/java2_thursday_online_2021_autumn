package lv.javaguru.java2.hospital.prescription.core.responses;

import lv.javaguru.java2.hospital.domain.Prescription;

import java.util.List;

public class SearchPrescriptionResponse extends CoreResponse{

    List<Prescription> prescriptions;

    public SearchPrescriptionResponse(List<CoreError> errors, List<Prescription> prescriptions) {
        super(errors);
        this.prescriptions = prescriptions;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }
}
