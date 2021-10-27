package lv.javaguru.java2.hospital.visit.core.services.validators.existence;

import lv.javaguru.java2.hospital.database.VisitDatabase;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class VisitExistenceByIdValidator {

    @Autowired
    private VisitDatabase database;

    public Optional<CoreError> validateExistenceById(Long id) {
        for (Visit visit : database.showAllVisits()) {
            if (visit.getVisitID().equals(id)) {
                return Optional.empty();
            }
        }
        return Optional.of(new CoreError("Visit", "Does not exist!"));
    }


}
