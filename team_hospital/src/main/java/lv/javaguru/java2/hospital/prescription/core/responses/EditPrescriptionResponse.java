package lv.javaguru.java2.hospital.prescription.core.responses;

import java.util.List;

public class EditPrescriptionResponse extends CoreResponse {
    private boolean prescriptionEdited;

    public EditPrescriptionResponse(List<CoreError> errors) {
        super(errors);
    }

    public EditPrescriptionResponse(boolean prescriptionEdited) {
        this.prescriptionEdited = prescriptionEdited;
    }

    public boolean isPrescriptionEdited() {
        return prescriptionEdited;
    }
}
