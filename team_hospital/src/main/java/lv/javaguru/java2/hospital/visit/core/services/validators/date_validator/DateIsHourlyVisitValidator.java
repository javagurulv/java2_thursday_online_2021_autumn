package lv.javaguru.java2.hospital.visit.core.services.validators.date_validator;

import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.services.date_converter.GetVisitDate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class DateIsHourlyVisitValidator implements DateValidator {

    private GetVisitDate getVisitDate;

    public DateIsHourlyVisitValidator(GetVisitDate getVisitDate) {
        this.getVisitDate = getVisitDate;
    }

    @Override
    public Optional<CoreError> validate(String date) {
        LocalDateTime visitDate = getVisitDate.getVisitDateFromString(date);
        boolean visitIsHourly = visitDate.getMinute() == 0;
        if (visitIsHourly) {
            return Optional.empty();
        } else {
            return Optional.of(new CoreError("Minutes", "is not hourly visit!"));
        }
    }
}
