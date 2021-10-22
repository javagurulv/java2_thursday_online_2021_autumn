package lv.javaguru.java2.hospital.visit.core.services.validators.date_validator;

import lv.javaguru.java2.hospital.visit.core.requests.AddVisitRequest;
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

    public List<CoreError> validate(AddVisitRequest request) {
        List<CoreError> errors = new ArrayList<>();

        dateFormatValidator.validateFormat(request.getVisitDate()).ifPresent(errors::add);

        if (errors.isEmpty()) {
            errors = timeValidation(request);
        }

        return errors;
    }

    private List<CoreError> timeValidation(AddVisitRequest request) {
        List<CoreError> errors = new ArrayList<>();
        DateValidator[] validators = {
                new DateIsInFutureValidator(getVisitDate),
                new DateIsWorkingDayValidator(getVisitDate),
                new DateTimeIsInWorkingHoursValidator(getVisitDate)};

        for (DateValidator d : validators) {
            d.validate(request.getVisitDate()).ifPresent(errors::add);
        }

        return errors;
    }
}
