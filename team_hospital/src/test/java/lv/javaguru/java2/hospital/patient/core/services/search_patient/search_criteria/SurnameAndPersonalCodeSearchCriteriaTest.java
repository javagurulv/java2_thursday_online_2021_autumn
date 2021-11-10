package lv.javaguru.java2.hospital.patient.core.services.search_patient.search_criteria;

import lv.javaguru.java2.hospital.database.patient_repository.PatientRepository;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.services.search_patient_service.search_criteria.SurnameAndPersonalCodeSearchCriteria;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class SurnameAndPersonalCodeSearchCriteriaTest {

    @Mock private PatientRepository database;
    @InjectMocks private SurnameAndPersonalCodeSearchCriteria searchCriteria;

    @Test
    public void shouldReturnTrue() {
        SearchPatientsRequest request = new SearchPatientsRequest("", "Surname546", "656-546");
        assertTrue(searchCriteria.canProcess(request));
    }

    @Test
    public void shouldReturnFalse() {
        SearchPatientsRequest request = new SearchPatientsRequest("Name1569", "", "656-546");
        assertFalse(searchCriteria.canProcess(request));
    }

    @Test
    public void shouldReturnCorrectPatient(){
        SearchPatientsRequest request = new SearchPatientsRequest("", "Surname694", "851589-58");
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("Name1212", "Surname694", "851589-58"));

        Mockito.when(database.findPatientsBySurnameAndPersonalCode(request.getSurname(), request.getPersonalCode()))
                .thenReturn(patients);
        List<Patient> patients2 = database
                .findPatientsBySurnameAndPersonalCode(request.getSurname(), request.getPersonalCode());
        assertTrue(searchCriteria.canProcess(request));

        Assertions.assertEquals(patients2.get(0).getName(), "Name1212");
        Assertions.assertEquals(patients2.get(0).getSurname(), "Surname694");
        Assertions.assertEquals(patients2.get(0).getPersonalCode(), "851589-58");
    }
}