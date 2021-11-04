package lv.javaguru.java2.hospital.visit.core.services.search_visit_service;

import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.VisitOrdering;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class VisitOrderingExecute {

    public List<Visit> execute(List<Visit> visits, VisitOrdering visitOrdering, boolean orderingEnabled) {
        if (orderingEnabled && visitOrdering != null) {
            Comparator<Visit> comparator = null;
            if (visitOrdering.getOrderBy().toUpperCase(Locale.ROOT).equals("DATE")) {
                comparator = Comparator.comparing(Visit::getVisitDate);
            } else {
                comparator = Comparator.comparing(Visit::getVisitID);
            }
            if (visitOrdering.getOrderDirection().toUpperCase(Locale.ROOT).equals("DESCENDING")) {
                comparator = comparator.reversed();
            }
            return visits.stream().sorted(comparator).collect(Collectors.toList());
        } else {
            return visits;
        }
    }
}

