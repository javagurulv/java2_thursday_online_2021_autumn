package lv.javaguru.java2.hospital.patient.core.services.search_patient_service;

import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class PatientOrderingExecute {
    public List<Patient> execute(List<Patient> patients, SearchPatientsRequest request, boolean orderingEnabled) {
        if (orderingEnabled && request.getOrderBy() != null && !request.getOrderBy().isEmpty()
        && request.getOrderDirection() != null && !request.getOrderDirection().isEmpty()) {
            Comparator<Patient> comparator = request.getOrderBy().toUpperCase(Locale.ROOT).equals("NAME")
                    ? Comparator.comparing(Patient::getName)
                    : Comparator.comparing(Patient::getSurname);
            if (request.getOrderDirection().toUpperCase(Locale.ROOT).equals("DESCENDING")) {
                comparator = comparator.reversed();
            }
            return patients.stream().sorted(comparator).collect(Collectors.toList());
        } else {
            return patients;
        }
    }
}
