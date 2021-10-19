package lv.javaguru.java2.hospital.visit.core.services.validators.date_validator;

import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.services.validators.existence.search_criteria.GetVisitDate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class DateIsWorkingDayValidator implements DateValidator {

    private GetVisitDate getVisitDate;

    public DateIsWorkingDayValidator(GetVisitDate getVisitDate) {
        this.getVisitDate = getVisitDate;
    }

    @Override
    public Optional<CoreError> validate(String date) {
        Date visitDate = getVisitDate.getVisitDateFromString(date);
        boolean dateIsWorkingDay = visitDate.getDay() > 0 &&
                visitDate.getDay() <= 5;
        if (dateIsWorkingDay) {
            return Optional.empty();
        } else {
            return Optional.of(new CoreError("Date", "is not working day!"));
        }
    }
}
