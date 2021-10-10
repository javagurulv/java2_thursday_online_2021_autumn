package lv.javaguru.java2.hospital.doctor.core.requests;

public class EditDoctorRequest {

    private Long doctorId;
    private EditOption editOption;
    private String changes;

    public EditDoctorRequest(Long doctorId, EditOption editOption, String changes) {
        this.doctorId = doctorId;
        this.editOption = editOption;
        this.changes = changes;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public EditOption getEditOption() {
        return editOption;
    }

    public String getChanges() {
        return changes;
    }
}
