package lv.javaguru.java2.hospital.doctor.core.services.validators.existence.search_criteria;

import lv.javaguru.java2.hospital.database.doctor_repository.DoctorRepository;
import lv.javaguru.java2.hospital.doctor.core.requests.SearchDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import lv.javaguru.java2.hospital.domain.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class ExistenceBySpeciality implements DoctorExistenceBySearchCriteria {

    @Autowired
    private DoctorRepository database;

    @Override
    public boolean canValidate(SearchDoctorsRequest request) {
        return request.isSpecialityProvided()
                && !request.isNameProvided()
                && !request.isIdProvided()
                && !request.isSurnameProvided();
    }

    @Override
    public Optional<CoreError> validateExistence(SearchDoctorsRequest request) {
        for (Doctor doctor : database.getAllDoctors()) {
            if (Objects.equals(doctor.getSpeciality(), request.getSpeciality())) {
                return Optional.empty();
            }
        }
        return Optional.of(new CoreError("Doctor", "Does not exist!"));
    }
}
