package lv.javaguru.java2.hospital.doctor.core.services.validators.existence;

import lv.javaguru.java2.hospital.database.doctor_repository.DoctorRepository;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import lv.javaguru.java2.hospital.domain.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class DoctorExistenceByIdValidator {

    @Autowired
    private DoctorRepository database;

    public Optional<CoreError> validateExistenceById(String id) {
        for (Doctor doctor : database.getAllDoctors()) {
            if (doctor.getId().equals(Long.parseLong(id))) {
                return Optional.empty();
            }
        }
        return Optional.of(new CoreError("Doctor", "Does not exist!"));
    }

}
