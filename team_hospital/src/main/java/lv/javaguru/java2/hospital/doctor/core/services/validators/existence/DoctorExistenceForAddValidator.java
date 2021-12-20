package lv.javaguru.java2.hospital.doctor.core.services.validators.existence;

import lv.javaguru.java2.hospital.database.doctor_repository.DoctorRepository;
import lv.javaguru.java2.hospital.database.jpa.JpaDoctorRepository;
import lv.javaguru.java2.hospital.doctor.core.requests.AddDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import lv.javaguru.java2.hospital.domain.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DoctorExistenceForAddValidator {

    @Autowired
    private JpaDoctorRepository database;

    public Optional<CoreError> validateDoctorExistence(AddDoctorRequest request) {
        for (Doctor doctor : database.findAll()) {
            if (doctor.getName().equals(request.getName())
                    && doctor.getSurname().equals(request.getSurname())
                    && doctor.getSpeciality().equals(request.getSpeciality())) {
                return Optional.of(new CoreError("Doctor", "Already exists!"));
            }
        }
        return Optional.empty();
    }
}
