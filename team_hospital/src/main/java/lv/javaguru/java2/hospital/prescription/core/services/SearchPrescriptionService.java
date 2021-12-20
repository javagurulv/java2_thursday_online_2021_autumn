package lv.javaguru.java2.hospital.prescription.core.services;

import lv.javaguru.java2.hospital.database.jpa.JpaPrescriptionRepository;
import lv.javaguru.java2.hospital.database.prescription_repository.PrescriptionRepository;
import lv.javaguru.java2.hospital.domain.Prescription;
import lv.javaguru.java2.hospital.prescription.core.requests.PrescriptionOrdering;
import lv.javaguru.java2.hospital.prescription.core.requests.PrescriptionPaging;
import lv.javaguru.java2.hospital.prescription.core.requests.SearchPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import lv.javaguru.java2.hospital.prescription.core.responses.SearchPrescriptionResponse;
import lv.javaguru.java2.hospital.prescription.core.services.search_criteria.*;
import lv.javaguru.java2.hospital.prescription.core.services.validators.SearchPrescriptionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
@Transactional
public class SearchPrescriptionService {

    @Autowired private JpaPrescriptionRepository database;
    @Autowired private SearchPrescriptionValidator validator;

    public SearchPrescriptionResponse execute(SearchPrescriptionRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SearchPrescriptionResponse(errors, null);
        }

        List<Prescription> prescriptions = search(request);
        prescriptions = order(prescriptions, request.getPrescriptionOrdering());
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
    
    private List<Prescription> order(List<Prescription> prescriptions, PrescriptionOrdering ordering) {
        if (ordering != null) {
            Comparator<Prescription> comparator = null;
            if (ordering.getOrderBy().toUpperCase(Locale.ROOT).equals("DATE")) {
                comparator = Comparator.comparing(Prescription::getDate);
            }
            if (ordering.getOrderDirection().toUpperCase(Locale.ROOT).equals("DESCENDING")) {
                comparator = comparator.reversed();
            }
            return prescriptions.stream().sorted(comparator).collect(Collectors.toList());
        } else {
            return prescriptions;
        }
    }
}
