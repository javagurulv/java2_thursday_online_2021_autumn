package lv.javaguru.java2.hospital.visit.core.services.validators.date_validator;

import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.services.validators.existence.search_criteria.GetVisitDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DateValidatorExecution {

    @Autowired private DateFormatValidator dateFormatValidator;
    @Autowired private GetVisitDate getVisitDate;

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
                new DateTimeIsInWorkingHoursValidator(getVisitDate),
                new DateIsHourlyVisitValidator(getVisitDate)};

        for (DateValidator d : validators) {
            d.validate(request).ifPresent(errors::add);
        }

        return errors;
    }
}
