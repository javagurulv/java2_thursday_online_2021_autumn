package lv.javaguru.java2.hospital.visit.core.services.validators.date_validator;

import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.services.validators.existence.search_criteria.GetVisitDate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class DateIsInFutureValidator implements DateValidator {

    private GetVisitDate getVisitDate;

    public DateIsInFutureValidator(GetVisitDate getVisitDate) {
        this.getVisitDate = getVisitDate;
    }

    @Override
    public Optional<CoreError> validate(String date) {
        Date visitDate = getVisitDate.getVisitDateFromString(date);
        boolean dateIsInFuture = visitDate.after(new Date());
        if (dateIsInFuture) {
            return Optional.empty();
        } else {
            return Optional.of(new CoreError("Date", "is not in the future!"));
        }
    }
}
