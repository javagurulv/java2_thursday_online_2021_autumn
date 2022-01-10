package lv.javaguru.java2.hospital.patient.core.services.search_patient_service;

import lv.javaguru.java2.hospital.database.jpa.JpaPatientRepository;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import lv.javaguru.java2.hospital.patient.core.responses.SearchPatientsResponse;
import lv.javaguru.java2.hospital.patient.core.services.validators.SearchPatientsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;


@Component
@Transactional
public class  SearchPatientsService {

    @Value("${search.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    private boolean pagingEnabled;

    @Autowired private JpaPatientRepository patientRepository;
    @Autowired private SearchPatientsValidator validator;
    @Autowired private PatientSearch search;
    @Autowired private PatientOrderingExecute patientOrderingExecute;
    @Autowired private PatientPagingExecute patientPagingExecute;

    public SearchPatientsResponse execute(SearchPatientsRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SearchPatientsResponse(errors, null);
        }

        List<Patient> patients = search.execute(request);

        patients = patientOrderingExecute.execute(patients, request, orderingEnabled);

        patients = patientPagingExecute.execute(patients, request, pagingEnabled);

        return new SearchPatientsResponse(null, patients);
    }
}

