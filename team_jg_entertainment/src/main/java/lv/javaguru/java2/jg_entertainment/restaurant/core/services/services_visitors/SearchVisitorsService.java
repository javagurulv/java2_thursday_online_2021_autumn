package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.Ordering;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.Paging;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.SearchVisitorsRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.SearchVisitorsResponse;
import lv.javaguru.java2.jg_entertainment.Visitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validatorsVisitors.SearchVisitorsRequestValidator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SearchVisitorsService {

    private DatabaseVisitors database;
    private SearchVisitorsRequestValidator validator;


    public SearchVisitorsService(DatabaseVisitors database,
                                 SearchVisitorsRequestValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public SearchVisitorsResponse execute(SearchVisitorsRequest request) {

        List<CoreError> errors = validator.validator(request);
        if (!errors.isEmpty()) {
            return new SearchVisitorsResponse(null, errors);
        }
        List<Visitors> visitorsList = search(request);
        visitorsList = order(visitorsList, request.getOrdering());
        visitorsList = paging(visitorsList, request.getPaging());

        return new SearchVisitorsResponse(visitorsList, null);
    }

    private List<Visitors> order(List<Visitors> visitors, Ordering ordering) {

        if (ordering != null) {
            Comparator<Visitors> comparator = ordering.getOrderBy().equals("name")
                    ? Comparator.comparing(Visitors::getClientName)
                    : Comparator.comparing(Visitors::getSurname);
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

    private List<Visitors> search(SearchVisitorsRequest request) {

        List<Visitors> visitors = new ArrayList<>();
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
    private List<Visitors> paging(List<Visitors> visitors, Paging paging) {

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
