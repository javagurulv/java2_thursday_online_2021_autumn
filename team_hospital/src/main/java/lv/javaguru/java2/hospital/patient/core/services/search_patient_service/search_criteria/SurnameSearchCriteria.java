package lv.javaguru.java2.hospital.patient.core.services.search_patient_service.search_criteria;

import lv.javaguru.java2.hospital.database.jpa.JpaPatientRepository;
import lv.javaguru.java2.hospital.database.patient_repository.PatientRepository;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;

import java.util.List;

public class SurnameSearchCriteria implements PatientsSearchCriteria {
    private final JpaPatientRepository patientRepository;

    public SurnameSearchCriteria(JpaPatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public boolean canProcess(SearchPatientsRequest request) {
        return !request.isNameProvided()
                && request.isSurnameProvided()
                && !request.isPersonalCodeProvided();
    }

    @Override
    public List<Patient> process(SearchPatientsRequest request) {
        return patientRepository.findBySurname(request.getSurname());
    }
}
