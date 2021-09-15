package lv.javaguru.java2.hospital.patient.search_criteria;

import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.requests.SearchPatientsRequest;

import java.util.List;

public interface PatientsSearchCriteria {
    boolean canProcess(SearchPatientsRequest request);

    List<Patient> process(SearchPatientsRequest request);
}
