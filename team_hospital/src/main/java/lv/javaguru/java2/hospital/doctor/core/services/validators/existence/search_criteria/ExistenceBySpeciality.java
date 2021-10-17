package lv.javaguru.java2.hospital.doctor.core.services.validators.existence.search_criteria;

import lv.javaguru.java2.hospital.database.DoctorDatabase;
import lv.javaguru.java2.hospital.database.DoctorDatabaseImpl;
import lv.javaguru.java2.hospital.doctor.core.requests.SearchDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import lv.javaguru.java2.hospital.domain.Doctor;

import java.util.Objects;
import java.util.Optional;

public class ExistenceBySpeciality implements DoctorExistenceBySearchCriteria {

    private DoctorDatabase database = new DoctorDatabaseImpl();

    public ExistenceBySpeciality(DoctorDatabaseImpl database) {
        this.database = database;
    }

    @Override
    public boolean canValidate(SearchDoctorsRequest request) {
        return request.isSpecialityProvided()
                && !request.isIdProvided()
                && !request.isNameProvided()
                && !request.isSurnameProvided();
    }

    @Override
    public Optional<CoreError> validateExistence(SearchDoctorsRequest request) {
        for (Doctor doctor : database.showAllDoctors()) {
            if (Objects.equals(doctor.getSpeciality(), request.getSpeciality())) {
                return Optional.empty();
            }
        }
        return Optional.of(new CoreError("Doctor", "Does not exist!"));
    }
}
