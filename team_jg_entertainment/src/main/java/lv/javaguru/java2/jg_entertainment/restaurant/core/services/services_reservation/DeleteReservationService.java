package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_reservation;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.reservation_repository.ReservationRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.DeleteReservationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.DeleteReservationResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_reservations.DeleteReservationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class DeleteReservationService {

    @Autowired private ReservationRepository repository;
    @Autowired private DeleteReservationValidator validator;

    public DeleteReservationResponse execute(DeleteReservationRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteReservationResponse(errors);
        }
        return new DeleteReservationResponse(repository.removeReservation(request.getId()));
    }
}
