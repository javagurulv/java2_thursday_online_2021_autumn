package lv.javaguru.java2.hospital.prescription.core.responses;

import java.util.List;

public class DeletePrescriptionResponse extends CoreResponse{

    private boolean prescriptionDeleted;

    public DeletePrescriptionResponse(boolean prescriptionDeleted) {
        this.prescriptionDeleted = prescriptionDeleted;
    }

    public DeletePrescriptionResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isPrescriptionDeleted() {
        return prescriptionDeleted;
    }
}
