package lv.javaguru.java2.hospital.prescription.core.services.search_criteria;

import lv.javaguru.java2.hospital.domain.Prescription;
import lv.javaguru.java2.hospital.prescription.core.requests.SearchPrescriptionRequest;

import java.util.List;

public interface PrescriptionSearchCriteria {

    boolean canProcess(SearchPrescriptionRequest request);

    List<Prescription> process(SearchPrescriptionRequest request);
}
