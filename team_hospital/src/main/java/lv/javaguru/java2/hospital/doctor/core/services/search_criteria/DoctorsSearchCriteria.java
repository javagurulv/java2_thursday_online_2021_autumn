package lv.javaguru.java2.hospital.doctor.core.services.search_criteria;

import lv.javaguru.java2.hospital.doctor.core.requests.SearchDoctorsRequest;
import lv.javaguru.java2.hospital.domain.Doctor;

import java.util.List;

public interface DoctorsSearchCriteria {

    boolean canProcess(SearchDoctorsRequest request);

    List<Doctor> process(SearchDoctorsRequest request);
}
