package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_reservation;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.reservation_repository.ReservationRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.UpdateReservationEnum;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.UpdateReservationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.UpdateReservationResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_reservations.UpdateReservationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Component
@Transactional
public class UpdateReservationService {

    @Autowired private ReservationRepository databaseReservation;
    @Autowired private UpdateReservationValidator validator;

    public UpdateReservationResponse execute(UpdateReservationRequest request){
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateReservationResponse(errors);
              //  } else {
            //          switch (request.getFieldToChange().toLowerCase(Locale.ROOT)) {
             //              case "visitor_id" -> {

        }
        boolean ifEditReservation = databaseReservation.editReservation(Long.valueOf(request.getReservationId()),
                UpdateReservationEnum.valueOf(request.getEnumEditReservation().toUpperCase(Locale.ROOT)), request.getChanges());
        return new UpdateReservationResponse(ifEditReservation);
    }
}
