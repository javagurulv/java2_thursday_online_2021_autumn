package lv.javaguru.java2.hospital.patient.core.services.search_patient_service.search_criteria;

import lv.javaguru.java2.hospital.database.patient_repository.PatientRepository;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;

import java.util.List;

public class NameSurnamePersonalCodeSearchCriteria implements PatientsSearchCriteria{

    private final PatientRepository database;

    public NameSurnamePersonalCodeSearchCriteria(PatientRepository database) {
        this.database = database;
    }

    @Override
    public boolean canProcess(SearchPatientsRequest request) {
        return request.isNameProvided()
                && request.isSurnameProvided()
                && request.isPersonalCodeProvided();
    }

    @Override
    public List<Patient> process(SearchPatientsRequest request) {
        return database.findPatientByNameSurnamePersonalCode(request.getName(), request.getSurname(), request.getPersonalCode());
    }
}
