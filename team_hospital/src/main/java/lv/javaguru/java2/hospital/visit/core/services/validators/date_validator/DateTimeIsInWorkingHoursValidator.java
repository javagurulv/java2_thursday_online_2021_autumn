package lv.javaguru.java2.hospital.visit.core.services.validators.date_validator;

import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.services.validators.existence.search_criteria.GetVisitDate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class DateTimeIsInWorkingHoursValidator implements DateValidator {

    private GetVisitDate getVisitDate;

    public DateTimeIsInWorkingHoursValidator(GetVisitDate getVisitDate) {
        this.getVisitDate = getVisitDate;
    }

    @Override
    public Optional<CoreError> validate(String date) {
        Date visitDate = getVisitDate.getVisitDateFromString(date);
        boolean dateIsWorkingDay = visitDate.getHours() >= 9 &&
                visitDate.getHours() <= 17;
        if (dateIsWorkingDay) {
            return Optional.empty();
        } else {
            return Optional.of(new CoreError("Date", "is not working hour!"));
        }
    }
}
