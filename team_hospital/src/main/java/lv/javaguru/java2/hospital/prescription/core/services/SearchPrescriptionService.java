package lv.javaguru.java2.hospital.prescription.core.services;

import lv.javaguru.java2.hospital.database.PrescriptionDatabase;
import lv.javaguru.java2.hospital.doctor.core.services.search_criteria.DoctorsSearchCriteria;
import lv.javaguru.java2.hospital.domain.Prescription;
import lv.javaguru.java2.hospital.prescription.core.requests.SearchPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import lv.javaguru.java2.hospital.prescription.core.responses.SearchPrescriptionResponse;
import lv.javaguru.java2.hospital.prescription.core.services.search_criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchPrescriptionService {

    @Autowired private PrescriptionDatabase database;

    public SearchPrescriptionResponse execute(SearchPrescriptionRequest request) {
        List<CoreError> errors;

        List<Prescription> prescriptions = search(request);
        return new SearchPrescriptionResponse(null, prescriptions);
    }

    private List<Prescription> search(SearchPrescriptionRequest request) {
        List<Prescription> prescriptions = new ArrayList<>();

        PrescriptionSearchCriteria[] prescriptionSearchCriteria = getPrescriptionSearchCriteria();

        for (PrescriptionSearchCriteria processor : prescriptionSearchCriteria) {
            if (processor.canProcess(request)) {
                prescriptions = processor.process(request);
                break;
            }
        }
        return prescriptions;
    }

    private PrescriptionSearchCriteria[] getPrescriptionSearchCriteria() {
        PrescriptionSearchCriteria[] prescriptionSearchCriteria = {
                new PrescriptionIdSearchCriteria(database),
                new DoctorIdAndPatientIdSearchCriteria(database),
                new DoctorIdSearchCriteria(database),
                new PatientIdSearchCriteria(database)};
        return prescriptionSearchCriteria;
    }
}
