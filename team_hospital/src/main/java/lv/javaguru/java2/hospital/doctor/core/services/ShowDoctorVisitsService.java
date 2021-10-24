package lv.javaguru.java2.hospital.doctor.core.services;

import lv.javaguru.java2.hospital.database.DoctorDatabase;
import lv.javaguru.java2.hospital.doctor.core.requests.ShowDoctorVisitsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import lv.javaguru.java2.hospital.doctor.core.responses.ShowDoctorVisitsResponse;
import lv.javaguru.java2.hospital.doctor.core.services.validators.ShowDoctorVisitsRequestValidator;
import lv.javaguru.java2.hospital.domain.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShowDoctorVisitsService {

    @Autowired private DoctorDatabase database;
    @Autowired private ShowDoctorVisitsRequestValidator validator;

    public ShowDoctorVisitsResponse execute(ShowDoctorVisitsRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new ShowDoctorVisitsResponse(null, errors);
        }
        List<Visit> visits = database.getDoctorVisits(request.getDoctorId());
        return new ShowDoctorVisitsResponse(visits, null);
    }
}
