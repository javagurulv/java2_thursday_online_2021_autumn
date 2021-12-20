package lv.javaguru.java2.hospital.patient.core.services;

import lv.javaguru.java2.hospital.database.jpa.JpaPatientRepository;
import lv.javaguru.java2.hospital.patient.core.requests.EditPatientEnum;
import lv.javaguru.java2.hospital.patient.core.requests.EditPatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import lv.javaguru.java2.hospital.patient.core.responses.EditPatientResponse;
import lv.javaguru.java2.hospital.patient.core.services.validators.EditPatientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Component
@Transactional
public class EditPatientService {

    @Autowired private JpaPatientRepository database;
    @Autowired private EditPatientValidator validator;

    public EditPatientResponse execute(EditPatientRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new EditPatientResponse(errors);
        } else {
            switch (request.getFieldToChange().toLowerCase(Locale.ROOT)) {
                case "name" -> database.editPatientName(Long.valueOf(request.getPatientID()), request.getChanges());
                case "surname" -> database.editPatientSurname(Long.valueOf(request.getPatientID()), request.getChanges());
                case "personal_code" -> database.editPatientPersonalCode(Long.valueOf(request.getPatientID()), request.getChanges());
            }
            return new EditPatientResponse(
                    Long.parseLong(request.getPatientID()),
                    EditPatientEnum.valueOf(request.getFieldToChange().toUpperCase(Locale.ROOT)),
                    request.getChanges()
                    );
        }
    }
}
