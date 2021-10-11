package lv.javaguru.java2.hospital.doctor.core.services.validators.existence;

import lv.javaguru.java2.hospital.database.DoctorDatabaseImpl;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import lv.javaguru.java2.hospital.domain.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class DoctorExistenceByIdValidator {

    @Autowired
    private DoctorDatabaseImpl database;

    public List<CoreError> validate(Long id) {
        List<CoreError> errors = new ArrayList<>();
        if (isIdProvided(id)) {
            validateExistenceById(id).ifPresent(errors::add);
        }
        return errors;
    }

    private boolean isIdProvided(Long id) {
        return id != null;
    }

    private Optional<CoreError> validateExistenceById(Long id) {
        for (Doctor doctor : database.getDoctorsList()) {
            if (Objects.equals(doctor.getId(), id)) {
                return Optional.empty();
            }
        }
        return Optional.of(new CoreError("Doctor", "Does not exist!"));
    }

}
