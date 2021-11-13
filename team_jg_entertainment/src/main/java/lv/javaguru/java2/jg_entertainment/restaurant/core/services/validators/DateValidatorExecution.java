package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.reservation_repository.ReservationRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_reservations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DateValidatorExecution {
    @Autowired private ReservationRepository database;
    @Autowired private DateFormatValidator dateFormatValidator;
    @Autowired private GetReservationDate getVisitDate;

    public List<CoreError> validate(String request) {
        List<CoreError> errors = new ArrayList<>();

        dateFormatValidator.validateFormat(request).ifPresent(errors::add);

        if (errors.isEmpty()) {
            errors = timeValidation(request);
        }

        return errors;
    }

    private List<CoreError> timeValidation(String request) {
        List<CoreError> errors = new ArrayList<>();
        DateValidator[] validators = {
                new DateIsInFutureValidator(getVisitDate),
                new DateIsWorkingDayValidator(getVisitDate),
                new DateTimeValidator(getVisitDate),
                new DateExistenceValidator(getVisitDate, database)};

        for (DateValidator d : validators) {
            d.validate(request).ifPresent(errors::add);
        }

        return errors;
    }
}
