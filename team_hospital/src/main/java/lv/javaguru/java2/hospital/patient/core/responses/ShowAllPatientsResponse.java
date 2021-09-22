package lv.javaguru.java2.hospital.patient.core.responses;

import lv.javaguru.java2.hospital.domain.Patient;

import java.util.ArrayList;
import java.util.List;

public class ShowAllPatientsResponse extends CoreResponse {
    private List<Patient> patients = new ArrayList<>();

    public ShowAllPatientsResponse(List<Patient> patients) {
        this.patients = patients;
    }

    public List<Patient> getPatients() {
        return patients;
    }
}
