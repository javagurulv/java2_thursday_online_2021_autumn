package lv.javaguru.java2.hospital.patient.core.services.search_patient_service;
import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.services.search_patient_service.search_criteria.*;

import java.util.List;

public class PatientSearch {

    private PatientDatabaseImpl database;

    public PatientSearch(PatientDatabaseImpl database) {
        this.database = database;
    }

    public List<Patient> execute(SearchPatientsRequest request) {
        PatientsSearchCriteria[] patientsSearchCriteria = getPatientsSearchCriteria();

        List<Patient> patients = null;

        for (PatientsSearchCriteria processor : patientsSearchCriteria) {
            if (processor.canProcess(request)) {
                patients = processor.process(request);
                break;
            }
        }
        return patients;
    }

    private PatientsSearchCriteria[] getPatientsSearchCriteria() {
        return new PatientsSearchCriteria[]{
                new NameSearchCriteria(database),
                new SurnameSearchCriteria(database),
                new PersonalCodeSearchCriteria(database),
                new NameAndSurnameSearchCriteria(database),
                new NameAndPersonalCodeSearchCriteria(database),
                new SurnameAndPersonalCodeSearchCriteria(database),
                new NameSurnamePersonalCodeSearchCriteria(database)};
    }
}

