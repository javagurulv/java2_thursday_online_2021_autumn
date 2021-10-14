package lv.javaguru.java2.hospital.visit.core.services.search_criteria;


import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;

import java.util.List;

public interface VisitsSearchCriteria {

    boolean canProcess(SearchVisitRequest request);

    List<Visit> process(SearchVisitRequest request);
}
