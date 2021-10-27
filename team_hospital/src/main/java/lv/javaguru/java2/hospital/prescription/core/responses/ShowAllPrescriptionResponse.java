package lv.javaguru.java2.hospital.prescription.core.responses;

import lv.javaguru.java2.hospital.domain.Prescription;

import java.util.List;

public class ShowAllPrescriptionResponse extends CoreResponse{

    private List<Prescription> prescriptions;

    public ShowAllPrescriptionResponse(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }
}
