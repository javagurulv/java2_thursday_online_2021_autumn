package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.user_repository.VisitorsRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.Ordering;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.Paging;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.SearchVisitorsRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.SearchVisitorsResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Visitor;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_visitors.SearchVisitorsRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class SearchVisitorsService {

    @Value("${search.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    private boolean pagingEnabled;

    @Autowired private VisitorsRepository database;
    @Autowired private SearchVisitorsRequestValidator validator;

    public SearchVisitorsResponse execute(SearchVisitorsRequest request) {

        List<CoreError> errors = validator.validator(request);
        if (!errors.isEmpty()) {
            return new SearchVisitorsResponse(null, errors);
        }
        List<Visitor> visitorList = search(request);
        visitorList = order(visitorList, request.getOrdering());
        visitorList = paging(visitorList, request.getPaging());

        return new SearchVisitorsResponse(visitorList, null);
    }

    private List<Visitor> order(List<Visitor> visitors, Ordering ordering) {

        if (ordering != null) {
            Comparator<Visitor> comparator = ordering.getOrderBy().equals("name")
                    ? Comparator.comparing(Visitor::getClientName)
                    : Comparator.comparing(Visitor::getSurname);
            if (ordering.getOrderDirection().equals("DESCENDING")) {
                comparator = comparator.reversed();
            }
            return visitors.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
        } else {
            return visitors;
        }
    }

    private List<Visitor> search(SearchVisitorsRequest request) {

        List<Visitor> visitors = new ArrayList<>();
        if (request.isNameProvided() && !request.isSurnameProvided()) {
            visitors = database.findByNameVisitor(request.getNameVisitors());
        }
        if (!request.isNameProvided() && request.isSurnameProvided()) {
            visitors = database.findBySurnameVisitor(request.getSurnameVisitors());
        }
        if (request.isNameProvided() && request.isSurnameProvided()) {
            visitors = database.findByNameAndSurname(request.getNameVisitors(), request.getSurnameVisitors());
        }
        if (request.isIDProvided() && !request.isNameProvided() && !request.isSurnameProvided()) {
            visitors = database.findClientById(request.getIdVisitors());
        }
        if (request.isNameProvided() && request.isTelephoneNumberProvided()) {
            visitors = database.findVisitorsByNameAndTelephoneNumber(request.getNameVisitors(), request.getTelephoneNumber());
        }
        return visitors;
    }

    //paging
    private List<Visitor> paging(List<Visitor> visitors, Paging paging) {

        if (paging != null) {
            int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
            return visitors.stream()
                    .skip(skip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return visitors;
        }
    }
}
