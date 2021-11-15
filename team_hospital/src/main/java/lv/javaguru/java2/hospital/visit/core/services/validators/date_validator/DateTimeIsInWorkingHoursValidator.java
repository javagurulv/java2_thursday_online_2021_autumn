package lv.javaguru.java2.hospital.visit.core.services.validators.date_validator;

import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.services.date_converter.GetVisitDate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class DateTimeIsInWorkingHoursValidator implements DateValidator {

    private GetVisitDate getVisitDate;

    public DateTimeIsInWorkingHoursValidator(GetVisitDate getVisitDate) {
        this.getVisitDate = getVisitDate;
    }

    @Override
    public Optional<CoreError> validate(String date) {
        LocalDateTime visitDate = getVisitDate.getVisitDateFromString(date);
        boolean dateIsWorkingDay = visitDate.getHour() >= 9 &&
                visitDate.getHour() <= 17;
        if (dateIsWorkingDay) {
            return Optional.empty();
        } else {
            return Optional.of(new CoreError("Time in the date", "is not working hour!"));
        }
    }
}
