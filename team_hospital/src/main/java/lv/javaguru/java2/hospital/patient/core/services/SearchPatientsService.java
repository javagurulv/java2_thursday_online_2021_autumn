package lv.javaguru.java2.hospital.patient.core.services;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.Ordering;
import lv.javaguru.java2.hospital.patient.core.requests.Paging;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import lv.javaguru.java2.hospital.patient.core.responses.SearchPatientsResponse;
import lv.javaguru.java2.hospital.patient.core.search_criteria.*;
import lv.javaguru.java2.hospital.patient.core.services.validators.SearchPatientsValidator;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class SearchPatientsService {
    private final PatientDatabaseImpl patientDatabase;
    private final SearchPatientsValidator validator;

    public SearchPatientsService(PatientDatabaseImpl patientDatabase, SearchPatientsValidator validator) {
        this.patientDatabase = patientDatabase;
        this.validator = validator;
    }

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

    public List<Patient> returnPaging(List<Patient> patients, Paging paging) {
        if (paging != null) {
            int pageNumber = paging.getPageNumber();
            int pageSize = paging.getPageSize();
            int skip = (pageNumber - 1) * pageSize;
            return patients.stream()
                    .skip(skip)
                    .limit(pageSize)
                    .collect(Collectors.toList());
        } else {
            return patients;
        }
    }

    public List<Patient> returnOrdering(List<Patient> patients, Ordering ordering) {
        if (ordering != null) {
            Comparator<Patient> comparator = ordering.getOrderBy().toUpperCase(Locale.ROOT).equals("NAME")
                    ? Comparator.comparing(Patient::getName)
                    : Comparator.comparing(Patient::getSurname);
            if (ordering.getOrderDirection().equals("DESCENDING")) {
                comparator = comparator.reversed();
            }
            return patients.stream().sorted(comparator).collect(Collectors.toList());
        } else {
            return patients;
        }
    }
}

