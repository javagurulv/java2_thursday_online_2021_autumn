package lv.javaguru.java2.hospital.patient.core.search_criteria;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;

import java.util.List;

public class NameAndSurnameSearchCriteria implements PatientsSearchCriteria {
    private final PatientDatabaseImpl patientDatabase;

    public NameAndSurnameSearchCriteria(PatientDatabaseImpl patientDatabase) {
        this.patientDatabase = patientDatabase;
    }

    @Override
    public boolean canProcess(SearchPatientsRequest request) {
        return request.isNameProvided()
                && request.isSurnameProvided()
                && !request.isPersonalCodeProvided();
    }

    @Override
    public List<Patient> process(SearchPatientsRequest request) {
        return patientDatabase
                .findPatientsByNameAndSurname
                        (request.getName(), request.getSurname());
    }
}
