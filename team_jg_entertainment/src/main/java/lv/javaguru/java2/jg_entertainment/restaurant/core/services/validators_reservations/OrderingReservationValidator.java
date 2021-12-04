package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_reservations;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.ReservationOrdering;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class OrderingReservationValidator {

    public List<CoreError> validator(ReservationOrdering ordering){
        List<CoreError> errors = new ArrayList<>();
        validatorOrderBy(ordering).ifPresent(errors::add);
        validatorOrderDirection(ordering).ifPresent(errors::add);
        validatorMandatoryOrderBy(ordering).ifPresent(errors::add);
        validatorMandatoryOrderDirection(ordering).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validatorOrderBy(ReservationOrdering ordering) {
        return ordering.getOrderBy() != null
                && !(ordering.getOrderBy().equals("reservationId") || ordering.getOrderBy().equals("userId")
                || ordering.getOrderBy().equals("date"))
                ? Optional.of(new CoreError("orderBy", "might contain 'reservationId' or 'userId' or 'date'"))
                : Optional.empty();
    }

    private Optional<CoreError> validatorOrderDirection(ReservationOrdering ordering) {
        return (ordering.getOrderDirection() != null
                && !(ordering.getOrderDirection().equals("ASCENDING") || ordering.getOrderDirection().equals("DESCENDING")))
                ? Optional.of(new CoreError("orderDirection", "might contain 'ASCENDING' or 'DESCENDING' only!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatorMandatoryOrderBy(ReservationOrdering ordering) {
        return (ordering.getOrderDirection() != null && ordering.getOrderBy() == null)
                ? Optional.of(new CoreError("orderBy", "can not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatorMandatoryOrderDirection(ReservationOrdering ordering) {
        return (ordering.getOrderBy() != null && ordering.getOrderDirection() == null)
                ? Optional.of(new CoreError("orderDirection", "can not be empty!"))
                : Optional.empty();
    }
}
