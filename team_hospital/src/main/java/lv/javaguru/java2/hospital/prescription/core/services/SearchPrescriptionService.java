package lv.javaguru.java2.hospital.prescription.core.services;

import lv.javaguru.java2.hospital.database.prescription_repository.PrescriptionRepository;
import lv.javaguru.java2.hospital.domain.Prescription;
import lv.javaguru.java2.hospital.prescription.core.requests.PrescriptionPaging;
import lv.javaguru.java2.hospital.prescription.core.requests.SearchPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import lv.javaguru.java2.hospital.prescription.core.responses.SearchPrescriptionResponse;
import lv.javaguru.java2.hospital.prescription.core.services.search_criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchPrescriptionService {

    @Autowired private PrescriptionRepository database;

    public SearchPrescriptionResponse execute(SearchPrescriptionRequest request) {
        List<CoreError> errors;

        List<Prescription> prescriptions = search(request);

        prescriptions = paging(prescriptions, request.getPrescriptionPaging());
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

    private List<Prescription> paging(List<Prescription> prescriptions, PrescriptionPaging paging) {
        if(paging != null ) {
            int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
            return prescriptions.stream()
                    .skip(skip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return prescriptions;
        }
    }
}
