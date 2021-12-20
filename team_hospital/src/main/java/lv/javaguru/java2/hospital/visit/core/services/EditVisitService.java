package lv.javaguru.java2.hospital.visit.core.services;

import lv.javaguru.java2.hospital.database.jpa.JpaVisitRepository;
import lv.javaguru.java2.hospital.visit.core.requests.EditVisitEnum;
import lv.javaguru.java2.hospital.visit.core.requests.EditVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.responses.EditVisitResponse;
import lv.javaguru.java2.hospital.visit.core.services.validators.EditVisitValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Component
@Transactional
public class EditVisitService {

    @Autowired
    private JpaVisitRepository database;
    @Autowired
    private EditVisitValidator validator;

    public EditVisitResponse execute(EditVisitRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new EditVisitResponse(errors);
        } else {
            switch (request.getFieldToChange().toLowerCase(Locale.ROOT)) {
                case "patient_id" -> {
                    database.editVisitPatient(Long.valueOf(request.getVisitID()), request.getChanges());
                    return new EditVisitResponse(true);
                }
                case "doctor_id" -> {
                    database.editVisitDoctor(Long.valueOf(request.getVisitID()), request.getChanges());
                    return new EditVisitResponse(true);
                }
                case "date" -> {
                    database.editVisitDate(Long.valueOf(request.getVisitID()), request.getChanges());
                    return new EditVisitResponse(true);
                }
                case "description" -> {
                    database.editVisitDescription(Long.valueOf(request.getVisitID()), request.getChanges());
                    return new EditVisitResponse(true);
                }
            }
        }
        return new EditVisitResponse(false);
    }
}
