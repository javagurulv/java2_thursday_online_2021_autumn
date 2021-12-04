package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_reservations;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.ReservationOrdering;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.ReservationPaging;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.SearchReservationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchReservationRequestValidator {

    @Autowired private SearchReservationFieldValidator fieldValidator;
    @Autowired private OrderingReservationValidator orderingReservationValidator;
    @Autowired private PagingReservationValidator pagingReservationValidator;

    public List<CoreError> validator(SearchReservationRequest request) {
        List<CoreError> errors = fieldValidator.validatorField(request);
        validateOrderingIfPresent(request, errors);
        validatePagingIfPresent(request, errors);
        return errors;
    }

    private void validateOrderingIfPresent(SearchReservationRequest request,
                                           List<CoreError> coreErrors) {
        if (request.getOrdering() != null) {
            ReservationOrdering ordering = request.getOrdering();
            coreErrors.addAll(orderingReservationValidator.validator(ordering));
        }
    }

    private void validatePagingIfPresent(SearchReservationRequest request,
                                         List<CoreError> coreErrors) {
        if (request.getOrdering() != null) {
            ReservationPaging paging = request.getPaging();
            coreErrors.addAll(pagingReservationValidator.validator(paging));
        }
    }
}
