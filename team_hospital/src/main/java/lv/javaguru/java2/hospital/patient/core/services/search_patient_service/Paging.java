package lv.javaguru.java2.hospital.patient.core.services.search_patient_service;

import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.PatientPaging;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Paging {
    public List<Patient> execute(List<Patient> patients, PatientPaging patientPaging, boolean pagingEnabled) {
        if (pagingEnabled && patientPaging != null) {
            int pageNumber = patientPaging.getPageNumber();
            int pageSize = patientPaging.getPageSize();
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
