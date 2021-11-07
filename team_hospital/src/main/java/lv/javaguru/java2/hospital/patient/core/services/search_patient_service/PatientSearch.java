package lv.javaguru.java2.hospital.patient.core.services.search_patient_service;

import lv.javaguru.java2.hospital.database.patient_repository.PatientRepository;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.services.search_patient_service.search_criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PatientSearch {

    @Autowired private PatientRepository database;

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

