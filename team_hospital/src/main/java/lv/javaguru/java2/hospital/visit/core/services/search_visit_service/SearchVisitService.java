package lv.javaguru.java2.hospital.visit.core.services.search_visit_service;

import lv.javaguru.java2.hospital.database.visit_repository.VisitRepository;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.responses.SearchVisitResponse;
import lv.javaguru.java2.hospital.visit.core.services.validators.SearchVisitValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class SearchVisitService {

    @Value("${search.ordering.enabled}")
    @Autowired private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    @Autowired private boolean pagingEnabled;

    @Autowired private VisitRepository visitRepository;
    @Autowired private SearchVisitValidator validator;
    @Autowired private VisitSearchExecute search;
    @Autowired private VisitOrderingExecute ordering;
    @Autowired private VisitPagingExecute paging;


    public SearchVisitResponse execute(SearchVisitRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SearchVisitResponse(null, errors);
        }

        List<Visit> visits = search.execute(request);
        visits = ordering.execute(visits, request.getVisitOrdering(), orderingEnabled);
        visits = paging.execute(visits, request.getVisitPaging(), pagingEnabled);

        return new SearchVisitResponse(visits, null);
    }
}