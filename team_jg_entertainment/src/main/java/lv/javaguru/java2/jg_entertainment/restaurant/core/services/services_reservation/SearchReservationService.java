package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_reservation;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.reservation_repository.ReservationRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.ReservationOrdering;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.ReservationPaging;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.SearchReservationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.SearchReservationResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_reservations.SearchReservationRequestValidator;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_reservations.date.GetReservationDate;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Reservation;
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
public class SearchReservationService {

    @Value("${search.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    private boolean pagingEnabled;

    @Autowired private ReservationRepository database;
    @Autowired private SearchReservationRequestValidator validator;
    @Autowired private GetReservationDate date;

    public SearchReservationResponse execute(SearchReservationRequest request) {

        List<CoreError> errors = validator.validator(request);
        if (!errors.isEmpty()) {
            return new SearchReservationResponse(null, errors);
        }
        List<Reservation> reservations = search(request);
        reservations = order(reservations, request.getOrdering());
        reservations = paging(reservations, request.getPaging());

        return new SearchReservationResponse(reservations, null);
    }

    private List<Reservation> search(SearchReservationRequest request) {

        List<Reservation> reservations = new ArrayList<>();

        if (request.isReservationIdProvided() && !request.isUserIdProvided() && !request.isDateProvided()) {
            reservations = database.findByReservationId(Long.valueOf(request.getReservationId()));
        }
        if (!request.isReservationIdProvided() && request.isUserIdProvided() && !request.isDateProvided()) {
            reservations = database.findByUserId(Long.valueOf(request.getUsedId()));
        }
        if (!request.isReservationIdProvided() && !request.isUserIdProvided() && request.isDateProvided()) {
            reservations = database.findByDate(date.getReservationDateFromString(request.getDate()));
        }
        if (!request.isReservationIdProvided() && request.isUserIdProvided() && request.isDateProvided()) {
            reservations = database.findByUserIdAndDate(Long.valueOf(request.getUsedId()),
                    date.getReservationDateFromString(request.getDate()));
        }
        if (request.isReservationIdProvided() && request.isUserIdProvided() && request.isDateProvided()) {
            reservations = database.findByReservationIdAndDate(Long.valueOf(request.getUsedId()),
                    date.getReservationDateFromString(request.getDate()));
        }
        return reservations;
    }

    private List<Reservation> paging(List<Reservation> reservations, ReservationPaging paging) {
        if (paging != null) {
            int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
            return reservations.stream()
                    .skip(skip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return reservations;
        }
    }

    private List<Reservation> order(List<Reservation> reservations, ReservationOrdering ordering) {
        if (ordering != null) {
            Comparator<Reservation> comparator = ordering.getOrderBy().equals("reservationId")
                    ? Comparator.comparing(Reservation::getIdReservation)
                    : Comparator.comparing(Reservation::getReservationDate);
            if (ordering.getOrderDirection().equals("DESCENDING")) {
                comparator = comparator.reversed();
            }
            return reservations.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
        } else {
            return reservations;
        }
    }
}
