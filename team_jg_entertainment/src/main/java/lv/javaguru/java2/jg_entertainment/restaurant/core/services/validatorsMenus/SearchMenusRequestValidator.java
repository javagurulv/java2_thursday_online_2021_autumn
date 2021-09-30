package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validatorsMenus;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.OrderingMenu;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.PagingMenu;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.SearchMenusRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchMenusRequestValidator {
    public List<CoreError> validate(SearchMenusRequest request) {
        List<CoreError> errors = new ArrayList<>();
      errors.addAll(validateSearchFields(request));;
      if(request.getOrderingMenu() != null){
          validateOrderBy(request.getOrderingMenu()).ifPresent(errors::add);
          validateOrderDirection(request.getOrderingMenu()).ifPresent(errors::add);
          validateMandatoryOrderBy(request.getOrderingMenu()).ifPresent(errors::add);
          validateMandatoryOrderDirection(request.getOrderingMenu()).ifPresent(errors::add);
        }
        if(request.getPagingMenu() != null){
            validatePageNumber(request.getPagingMenu()).ifPresent(errors::add);
            validatePageSize(request.getPagingMenu()).ifPresent(errors::add);
            validateMandatoryPageNumber(request.getPagingMenu()).ifPresent(errors::add);
            validateMandatoryPageSize(request.getPagingMenu()).ifPresent(errors::add);
        }
        return errors;
    }

    private List<CoreError> validateSearchFields(SearchMenusRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (isEmpty(request.getTitle()) && isEmpty(request.getDescription())) {
            errors.add(new CoreError("description", "Must not be empty!"));
            errors.add(new CoreError("author", "Must not be empty!"));
        }
        return errors;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private Optional<CoreError> validateOrderBy(OrderingMenu orderingMenu) {
        return (orderingMenu.getOrderBy() != null
                && !(orderingMenu.getOrderBy().equals("description") || orderingMenu.getOrderBy().equals("title")))
                ? Optional.of(new CoreError("orderBy", "Must contain 'description' or 'title' only!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateOrderDirection(OrderingMenu orderingMenu) {
        return (orderingMenu.getOrderDirection() != null
                && !(orderingMenu.getOrderDirection().equals("ASCENDING") || orderingMenu.getOrderDirection().equals("DESCENDING")))
                ? Optional.of(new CoreError("orderDirection", "Must contain 'ASCENDING' or 'DESCENDING' only!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryOrderBy(OrderingMenu orderingMenu) {
        return (orderingMenu.getOrderDirection() != null && orderingMenu.getOrderBy() == null)
                ? Optional.of(new CoreError("orderBy", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryOrderDirection(OrderingMenu orderingMenu) {
        return (orderingMenu.getOrderBy() != null && orderingMenu.getOrderDirection() == null)
                ? Optional.of(new CoreError("orderDirection", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePageNumber(PagingMenu pagingMenu) {
        return (pagingMenu.getPageNumber() != null
                && pagingMenu.getPageNumber() <= 0)
                ? Optional.of(new CoreError("pageNumber", "Must be greater then 0!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePageSize(PagingMenu pagingMenu) {
        return (pagingMenu.getPageSize() != null
                && pagingMenu.getPageSize() <= 0)
                ? Optional.of(new CoreError("pageSize", "Must be greater then 0!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryPageNumber(PagingMenu pagingMenu) {
        return (pagingMenu.getPageNumber() == null && pagingMenu.getPageSize() != null)
                ? Optional.of(new CoreError("pageNumber", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryPageSize(PagingMenu pagingMenu) {
        return (pagingMenu.getPageSize() == null && pagingMenu.getPageNumber() != null)
                ? Optional.of(new CoreError("pageSize", "Must not be empty!"))
                : Optional.empty();
    }


}
