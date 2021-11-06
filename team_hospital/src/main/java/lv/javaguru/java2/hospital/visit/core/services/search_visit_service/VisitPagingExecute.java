package lv.javaguru.java2.hospital.visit.core.services.search_visit_service;

import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.VisitPaging;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VisitPagingExecute {

    public List<Visit> execute(List<Visit> visits, VisitPaging visitPaging, boolean pagingEnabled) {
        if (pagingEnabled && visitPaging != null) {
            int skip = (visitPaging.getPageNumber() - 1) * visitPaging.getPageSize();
            return visits.stream()
                    .skip(skip)
                    .limit(visitPaging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return visits;
        }
    }
}
