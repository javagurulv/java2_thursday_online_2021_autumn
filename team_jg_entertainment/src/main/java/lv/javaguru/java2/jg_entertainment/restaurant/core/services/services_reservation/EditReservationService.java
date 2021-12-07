package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_reservation;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.reservation_repository.ReservationRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.EditReservationEnum;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.EditReservationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.EditReservationResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_reservations.EditReservationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Component
@Transactional
public class EditReservationService {

    @Autowired private ReservationRepository databaseReservation;
    @Autowired private EditReservationValidator validator;

    public EditReservationResponse execute(EditReservationRequest request){
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new EditReservationResponse(errors);
        }
        boolean ifEditReservation = databaseReservation.editReservation(Long.valueOf(request.getReservationId()),
                EditReservationEnum.valueOf(request.getEnumEditReservation().toUpperCase(Locale.ROOT)), request.getChanges());
        return new EditReservationResponse(ifEditReservation);
    }
}
