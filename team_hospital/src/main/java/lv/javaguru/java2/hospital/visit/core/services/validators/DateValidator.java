package lv.javaguru.java2.hospital.visit.core.services.validators;

import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.services.validators.existence.search_criteria.GetVisitDate;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Component
public class DateValidator {

    private GetVisitDate getVisitDate = new GetVisitDate();

    public Optional<CoreError> validate(String date) {
        if (validateFormat(date).isPresent()) {
            return validateFormat(date);
        }
        if (validateThatDateIsInFuture(date).isPresent()) {
            return validateThatDateIsInFuture(date);
        }
        if (validateThatDateIsWorkingDay(date).isPresent()) {
            return validateThatDateIsWorkingDay(date);
        }
        if (validateThatTimeIsInWorkingHours(date).isPresent()) {
            return validateThatTimeIsInWorkingHours(date);
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateFormat(String date) {
        try {
            new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date);
            return Optional.empty();
        } catch (ParseException e) {
            return Optional.of(new CoreError("Date", "input is incorrect!"));
        }
    }

    private Optional<CoreError> validateThatDateIsInFuture(String date) {
        Date visitDate = getVisitDate.getVisitDateFromString(date);
        boolean dateIsInFuture = visitDate.after(new Date());
        if (dateIsInFuture) {
            return Optional.empty();
        } else {
            return Optional.of(new CoreError("Date", "is not in the future!"));
        }
    }

    private Optional<CoreError> validateThatDateIsWorkingDay(String date) {
        Date visitDate = getVisitDate.getVisitDateFromString(date);
        boolean dateIsWorkingDay = visitDate.getDay() >= 1 &&
                visitDate.getDay() <= 5;
        if (dateIsWorkingDay) {
            return Optional.empty();
        } else {
            return Optional.of(new CoreError("Date", "is not working day!"));
        }
    }

    private Optional<CoreError> validateThatTimeIsInWorkingHours(String date) {
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
