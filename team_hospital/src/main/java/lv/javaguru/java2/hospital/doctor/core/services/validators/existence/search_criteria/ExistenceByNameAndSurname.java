package lv.javaguru.java2.hospital.doctor.core.services.validators.existence.search_criteria;

import lv.javaguru.java2.hospital.database.DoctorDatabaseImpl;
import lv.javaguru.java2.hospital.doctor.core.requests.SearchDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import lv.javaguru.java2.hospital.domain.Doctor;

import java.util.Objects;
import java.util.Optional;

public class ExistenceByNameAndSurname implements DoctorExistenceBySearchCriteria{

    private DoctorDatabaseImpl database = new DoctorDatabaseImpl();

    public ExistenceByNameAndSurname(DoctorDatabaseImpl database) {
        this.database = database;
    }

    @Override
    public boolean canValidate(SearchDoctorsRequest request) {
        return request.isNameProvided()
                && request.isSurnameProvided()
                && !request.isIdProvided()
                && !request.isSpecialityProvided();
    }

    @Override
    public Optional<CoreError> validateExistence(SearchDoctorsRequest request) {
        for (Doctor doctor : database.getDoctorsList()) {
            if (Objects.equals(doctor.getName(), request.getName())
                    && Objects.equals(doctor.getSurname(), request.getSurname())) {
                return Optional.empty();
            }
        }
        return Optional.of(new CoreError("Doctor", "Does not exist!"));
    }
}
