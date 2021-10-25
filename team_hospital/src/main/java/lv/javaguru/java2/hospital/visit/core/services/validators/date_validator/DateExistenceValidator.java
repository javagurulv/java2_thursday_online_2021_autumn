package lv.javaguru.java2.hospital.visit.core.services.validators.date_validator;

import lv.javaguru.java2.hospital.database.VisitDatabase;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.services.validators.existence.search_criteria.GetVisitDate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class DateExistenceValidator implements DateValidator {

    private GetVisitDate getVisitDate;
    private VisitDatabase database;

    public DateExistenceValidator(GetVisitDate getVisitDate, VisitDatabase database) {
        this.getVisitDate = getVisitDate;
        this.database = database;
    }

    @Override
    public Optional<CoreError> validate(String date) {
        LocalDateTime dateTime = getVisitDate.getVisitDateFromString(date);
        for (Visit d : database.showAllVisits()) {
            if (d.getVisitDate().isEqual(dateTime)) {
                return Optional.of(new CoreError("Date", "already is busy!"));
            }
        }
        return Optional.empty();
    }
}
