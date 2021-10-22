package lv.javaguru.java2.hospital.visit.core.services.validators.date_validator;

import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.services.validators.existence.search_criteria.GetVisitDate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class DateIsWorkingDayValidator implements DateValidator {

    private GetVisitDate getVisitDate;

    public DateIsWorkingDayValidator(GetVisitDate getVisitDate) {
        this.getVisitDate = getVisitDate;
    }

    @Override
    public Optional<CoreError> validate(String date) {
        LocalDateTime visitDate = getVisitDate.getVisitDateFromString(date);
        boolean dateIsWorkingDay = visitDate.getDayOfWeek().getValue() >= 1  &&
                visitDate.getDayOfWeek().getValue() <= 5;
        if (dateIsWorkingDay) {
            return Optional.empty();
        } else {
            return Optional.of(new CoreError("Date", "is not working day!"));
        }
    }
}
