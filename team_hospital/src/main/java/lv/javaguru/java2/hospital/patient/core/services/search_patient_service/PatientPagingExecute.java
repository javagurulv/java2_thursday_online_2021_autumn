package lv.javaguru.java2.hospital.patient.core.services.search_patient_service;

import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PatientPagingExecute {
    public List<Patient> execute(List<Patient> patients, SearchPatientsRequest request, boolean pagingEnabled) {
        if (pagingEnabled && request.getPageNumber() != null && !request.getPageNumber().isEmpty()
        && request.getPageSize() != null && !request.getPageSize().isEmpty()) {
            int pageNumber = Integer.parseInt(request.getPageNumber());
            int pageSize = Integer.parseInt(request.getPageSize());
            int skip = (pageNumber - 1) * pageSize;
            return patients.stream()
                    .skip(skip)
                    .limit(pageSize)
                    .collect(Collectors.toList());
        } else {
            return patients;
        }
    }
}
