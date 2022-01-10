package lv.javaguru.java2.hospital.visit.core.services.search_visit_service;

import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.requests.VisitPaging;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VisitPagingExecute {

    public List<Visit> execute(List<Visit> visits, SearchVisitRequest request, boolean pagingEnabled) {
        if (pagingEnabled && request.getPageNumber() != null && !request.getPageNumber().isEmpty()
                && request.getPageSize() != null && !request.getPageSize().isEmpty()) {
            int skip = (Integer.parseInt(request.getPageNumber()) - 1) * Integer.parseInt(request.getPageSize());
            return visits.stream()
                    .skip(skip)
                    .limit(Long.parseLong(request.getPageSize()))
                    .collect(Collectors.toList());
        } else {
            return visits;
        }
    }
}
