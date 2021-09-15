package lv.javaguru.java2.hospital.patient.responses;

import lv.javaguru.java2.hospital.domain.Patient;

import java.util.List;

public class SearchPatientsResponse extends CoreResponse {
    private List<Patient> patientList;

    public SearchPatientsResponse(List<CoreError> errors, List<Patient> patientList) {
        super(errors);
        this.patientList = patientList;
    }

    public List<Patient> getPatientList() {
        return patientList;
    }
}
