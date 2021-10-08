package lv.javaguru.java2.hospital.patient.core.services;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.PatientOrdering;
import lv.javaguru.java2.hospital.patient.core.requests.PatientPaging;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import lv.javaguru.java2.hospital.patient.core.responses.SearchPatientsResponse;
import lv.javaguru.java2.hospital.patient.core.search_criteria.*;
import lv.javaguru.java2.hospital.patient.core.services.validators.SearchPatientsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class SearchPatientsService {

    @Value("${search.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    private boolean pagingEnabled;

    @Autowired private PatientDatabaseImpl patientDatabase;
    @Autowired private SearchPatientsValidator validator;

    public SearchPatientsResponse execute(SearchPatientsRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SearchPatientsResponse(errors, null);
        }

        List<Patient> patients = search(request);
        patients = returnOrdering(patients, request.getOrdering());
        patients = returnPaging(patients, request.getPaging());

        return new SearchPatientsResponse(null, patients);
    }

    private List<Patient> search(SearchPatientsRequest request) {
        PatientsSearchCriteria[] patientsSearchCriteria = {
                new NameSearchCriteria(patientDatabase),
                new SurnameSearchCriteria(patientDatabase),
                new PersonalCodeSearchCriteria(patientDatabase),
                new NameAndSurnameSearchCriteria(patientDatabase),
                new NameAndPersonalCodeSearchCriteria(patientDatabase),
                new SurnameAndPersonalCodeSearchCriteria(patientDatabase)};

        List<Patient> patients = null;

        for (PatientsSearchCriteria processor : patientsSearchCriteria) {
            if (processor.canProcess(request)) {
                patients = processor.process(request);
                break;
            }
        }
        return patients;
    }

    public List<Patient> returnPaging(List<Patient> patients, PatientPaging patientPaging) {
        if (pagingEnabled && patientPaging != null) {
            int pageNumber = patientPaging.getPageNumber();
            int pageSize = patientPaging.getPageSize();
            int skip = (pageNumber - 1) * pageSize;
            return patients.stream()
                    .skip(skip)
                    .limit(pageSize)
                    .collect(Collectors.toList());
        } else {
            return patients;
        }
    }

    public List<Patient> returnOrdering(List<Patient> patients, PatientOrdering patientOrdering) {
        if (orderingEnabled && patientOrdering != null) {
            Comparator<Patient> comparator = patientOrdering.getOrderBy().toUpperCase(Locale.ROOT).equals("NAME")
                    ? Comparator.comparing(Patient::getName)
                    : Comparator.comparing(Patient::getSurname);
            if (patientOrdering.getOrderDirection().toUpperCase(Locale.ROOT).equals("DESCENDING")) {
                comparator = comparator.reversed();
            }
            return patients.stream().sorted(comparator).collect(Collectors.toList());
        } else {
            return patients;
        }
    }
}

