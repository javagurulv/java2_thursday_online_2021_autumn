package lv.javaguru.java2.hospital.doctor.core.services.validators.existence;

import lv.javaguru.java2.hospital.doctor.core.requests.SearchDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import lv.javaguru.java2.hospital.doctor.core.services.validators.existence.search_criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DoctorExistenceForSearchValidator {

    @Autowired private ExistenceByNameAndSurnameAndSpeciality existenceByNameAndSurnameAndSpeciality;
    @Autowired private ExistenceByNameAndSurname existenceByNameAndSurname;
    @Autowired private ExistenceByNameAndSpeciality existenceByNameAndSpeciality;
    @Autowired private ExistenceBySurnameAndSpeciality existenceBySurnameAndSpeciality;
    @Autowired private ExistenceByName existenceByName;
    @Autowired private ExistenceBySurname existenceBySurname;
    @Autowired private ExistenceBySpeciality existenceBySpeciality;

    public List<CoreError> validate(SearchDoctorsRequest request) {
        List<CoreError> errors = new ArrayList<>();

        DoctorExistenceBySearchCriteria[] doctorExistenceBySearchCriteria
                = getDoctorsExistenceBySearchCriteria();

        for (DoctorExistenceBySearchCriteria processor : doctorExistenceBySearchCriteria) {
            if(processor.canValidate(request)) {
                processor.validateExistence(request).ifPresent(errors::add);
            }
        }

        return errors;
    }

    private DoctorExistenceBySearchCriteria[] getDoctorsExistenceBySearchCriteria() {
        DoctorExistenceBySearchCriteria[] doctorExistenceBySearchCriteria = {
                existenceByNameAndSurnameAndSpeciality,
                existenceByNameAndSurname,
                existenceByNameAndSpeciality,
                existenceBySurnameAndSpeciality,
                existenceByName,
                existenceBySurname,
                existenceBySpeciality};
        return doctorExistenceBySearchCriteria;
    }
}
