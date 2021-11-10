package lv.javaguru.java2.hospital.prescription.core.services.search_criteria;

import lv.javaguru.java2.hospital.database.prescription_repository.PrescriptionRepository;
import lv.javaguru.java2.hospital.domain.Prescription;
import lv.javaguru.java2.hospital.prescription.core.requests.SearchPrescriptionRequest;

import java.util.List;

public class PatientIdSearchCriteria implements PrescriptionSearchCriteria{

    private final PrescriptionRepository database;

    public PatientIdSearchCriteria(PrescriptionRepository database) {
        this.database = database;
    }

    @Override
    public boolean canProcess(SearchPrescriptionRequest request) {
        return request.isPatientIdProvided()
                && !request.isDoctorIdProvided()
                && !request.isPrescriptionIdProvided();
    }

    @Override
    public List<Prescription> process(SearchPrescriptionRequest request) {
        return database.findByPatientId(request.getPatientId());
    }
}
