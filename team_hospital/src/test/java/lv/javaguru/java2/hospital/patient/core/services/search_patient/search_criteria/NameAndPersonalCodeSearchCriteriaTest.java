package lv.javaguru.java2.hospital.patient.core.services.search_patient.search_criteria;

import lv.javaguru.java2.hospital.database.patient_repository.PatientRepository;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.services.search_patient_service.search_criteria.NameAndPersonalCodeSearchCriteria;
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
public class NameAndPersonalCodeSearchCriteriaTest {

    @Mock private PatientRepository database;
    @InjectMocks private NameAndPersonalCodeSearchCriteria searchCriteria;

    @Test
    public void shouldReturnTrue(){
        SearchPatientsRequest request = new SearchPatientsRequest("name3", "", "1111");
        assertTrue(searchCriteria.canProcess(request));
    }

    @Test
    public void shouldReturnFalse(){
        SearchPatientsRequest request = new SearchPatientsRequest("", "surname3", "1111");
        assertFalse(searchCriteria.canProcess(request));
    }

    @Test
    public void shouldReturnCorrectPatient(){
        SearchPatientsRequest request = new SearchPatientsRequest("name3", "", "1111");
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("name3", "surname3", "1111"));

        Mockito.when(database.findByNameAndPersonalCode
                        (request.getName(), request.getPersonalCode()))
                .thenReturn(patients);
        List<Patient> patients2 = database.findByNameAndPersonalCode
                (request.getName(), request.getPersonalCode());

        assertTrue(searchCriteria.canProcess(request));
        Assertions.assertEquals(patients2.get(0).getName(), "name3");
        Assertions.assertEquals(patients2.get(0).getSurname(), "surname3");
        Assertions.assertEquals(patients2.get(0).getPersonalCode(), "1111");
    }
}
