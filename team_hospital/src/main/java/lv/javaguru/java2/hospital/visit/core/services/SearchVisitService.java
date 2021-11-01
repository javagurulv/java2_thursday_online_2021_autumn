package lv.javaguru.java2.hospital.visit.core.services;

import lv.javaguru.java2.hospital.database.VisitDatabase;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.requests.VisitOrdering;
import lv.javaguru.java2.hospital.visit.core.requests.VisitPaging;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.responses.SearchVisitResponse;
import lv.javaguru.java2.hospital.visit.core.services.search_criteria.*;
import lv.javaguru.java2.hospital.visit.core.services.validators.SearchVisitValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchVisitService {

    @Value("${search.ordering.enabled}")
    @Autowired
    private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    @Autowired
    private boolean pagingEnabled;

    @Autowired
    private VisitDatabase visitDatabase;
    @Autowired
    private SearchVisitValidator validator;

    public SearchVisitService(VisitDatabase database, SearchVisitValidator validator) {
        this.visitDatabase = database;
        this.validator = validator;
    }

    public SearchVisitResponse execute(SearchVisitRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SearchVisitResponse(null, errors);
        }

        List<Visit> visits = search(request);
        visits = order(visits, request.getOrdering());
        visits = paging(visits, request.getPaging());

        return new SearchVisitResponse(visits, null);
    }

    private List<Visit> search(SearchVisitRequest request) {
        List<Visit> visits = new ArrayList<>();

        VisitsSearchCriteria[] visitsSearchCriteria = getVisitsSearchCriteria();

        for (VisitsSearchCriteria processor : visitsSearchCriteria) {
            if (processor.canProcess(request)) {
                visits = processor.process(request);
                break;
            }
        }

        return visits;
    }

    private VisitsSearchCriteria[] getVisitsSearchCriteria() {
        return new VisitsSearchCriteria[]{
                new VisitIdSearchCriteria(visitDatabase),
                new DoctorIdAndPatientIdAndDateSearchCriteria(visitDatabase),
                new DoctorIdAndPatientIdSearchCriteria(visitDatabase),
                new DoctorIdAndDateSearchCriteria(visitDatabase),
                new PatientIdAndDateSearchCriteria(visitDatabase),
                new DoctorIdSearchCriteria(visitDatabase),
                new PatientIdSearchCriteria(visitDatabase),
                new DateSearchCriteria(visitDatabase)};
    }


    private List<Visit> order(List<Visit> visits, VisitOrdering visitOrdering) {
        if (orderingEnabled && visitOrdering != null) {
            Comparator<Visit> comparator = null;
            if (visitOrdering.getOrderBy().equals("date")) {
                comparator = Comparator.comparing(Visit::getVisitDate);
            } else {
                comparator = Comparator.comparing(Visit::getVisitID);
            }
            if (visitOrdering.getOrderDirection().equals("DESCENDING")) {
                comparator = comparator.reversed();
            }
            return visits.stream().sorted(comparator).collect(Collectors.toList());
        } else {
            return visits;
        }
    }

    private List<Visit> paging(List<Visit> visits, VisitPaging visitPaging) {
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
