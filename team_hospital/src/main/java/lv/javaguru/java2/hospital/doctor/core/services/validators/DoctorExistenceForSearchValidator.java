package lv.javaguru.java2.hospital.doctor.core.services.validators;

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
public class DoctorExistenceForSearchValidator {

    @Autowired
    private DoctorDatabaseImpl database;

    public List<CoreError> validate(Long id, String name, String surname, String speciality) {
        List<CoreError> errors = validateEachField(id, name, surname, speciality);
        return errors;
    }

    public List<CoreError> validateEachField(Long id, String name, String surname, String speciality) {
        List<CoreError> errors = new ArrayList<>();
        if (isIdProvided(id)) {
            validateExistenceById(id).ifPresent(errors::add);
        } else if (isNameProvided(name) && isSurnameProvided(surname)
                && isSpecialityProvided(speciality)) {
            validateExistenceByNameAndSurnameAndSpeciality(name, surname, speciality)
                    .ifPresent(errors::add);
        } else if (isNameProvided(name) && isSurnameProvided(surname)) {
            validateExistenceByNameAndSurname(name, surname).ifPresent(errors::add);
        } else if (isNameProvided(name) && isSpecialityProvided(speciality)) {
            validateExistenceByNameAndSpeciality(name, speciality).ifPresent(errors::add);
        } else if (isSurnameProvided(surname) && isSpecialityProvided(speciality)) {
            validateExistenceBySurnameAndSpeciality(surname, speciality).ifPresent(errors::add);
        } else if (isNameProvided(name)) {
            validateExistenceByName(name).ifPresent(errors::add);
        } else if (isSurnameProvided(surname)) {
            validateExistenceBySurname(surname).ifPresent(errors::add);
        } else if (isSpecialityProvided(speciality)) {
            validateExistenceBySpeciality(speciality).ifPresent(errors::add);
        }
        return errors;
    }

    private Optional<CoreError> validateExistenceById(Long id) {
        for (Doctor doctor : database.getDoctorsList()) {
            if (Objects.equals(doctor.getId(), id)) {
                Optional.empty();
            }
        }
        return Optional.of(new CoreError("Doctor", "Does not exist!"));
    }

    private Optional<CoreError> validateExistenceByName(String name) {
        for (Doctor doctor : database.getDoctorsList()) {
            if (Objects.equals(doctor.getName(), name)) {
                Optional.empty();
            }
        }
        return Optional.of(new CoreError("Doctor", "Does not exist!"));
    }

    private Optional<CoreError> validateExistenceBySurname(String surname) {
        for (Doctor doctor : database.getDoctorsList()) {
            if (Objects.equals(doctor.getSurname(), surname)) {
                Optional.empty();
            }
        }
        return Optional.of(new CoreError("Doctor", "Does not exist!"));
    }

    private Optional<CoreError> validateExistenceBySpeciality(String speciality) {
        for (Doctor doctor : database.getDoctorsList()) {
            if (Objects.equals(doctor.getSpeciality(), speciality)) {
                Optional.empty();
            }
        }
        return Optional.of(new CoreError("Doctor", "Does not exist!"));
    }

    private Optional<CoreError> validateExistenceByNameAndSurnameAndSpeciality(String name, String surname, String speciality) {
        for (Doctor doctor : database.getDoctorsList()) {
            if (Objects.equals(doctor.getName(), name)
                    && Objects.equals(doctor.getSurname(), surname)
                    && Objects.equals(doctor.getSpeciality(), speciality)) {
                Optional.empty();
            }
        }
        return Optional.of(new CoreError("Doctor", "Does not exist!"));
    }

    private Optional<CoreError> validateExistenceByNameAndSurname(String name, String surname) {
        for (Doctor doctor : database.getDoctorsList()) {
            if (Objects.equals(doctor.getName(), name)
                    && Objects.equals(doctor.getSurname(), surname)) {
                Optional.empty();
            }
        }
        return Optional.of(new CoreError("Doctor", "Does not exist!"));
    }

    private Optional<CoreError> validateExistenceByNameAndSpeciality(String name, String speciality) {
        for (Doctor doctor : database.getDoctorsList()) {
            if (Objects.equals(doctor.getName(), name)
                    && Objects.equals(doctor.getSpeciality(), speciality)) {
                Optional.empty();
            }
        }
        return Optional.of(new CoreError("Doctor", "Does not exist!"));
    }

    private Optional<CoreError> validateExistenceBySurnameAndSpeciality(String surname, String speciality) {
        for (Doctor doctor : database.getDoctorsList()) {
            if (Objects.equals(doctor.getSurname(), surname)
                    && Objects.equals(doctor.getSpeciality(), speciality)) {
                Optional.empty();
            }
        }
        return Optional.of(new CoreError("Doctor", "Does not exist!"));
    }

    private boolean isIdProvided(Long id) {
        return id != null;
    }

    private boolean isNameProvided(String name) {
        return name != null && !name.isEmpty();
    }

    private boolean isSurnameProvided(String surname) {
        return surname != null && !surname.isEmpty();
    }

    private boolean isSpecialityProvided(String speciality) {
        return speciality != null && !speciality.isEmpty();
    }
}
