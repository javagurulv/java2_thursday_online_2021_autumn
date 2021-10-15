package lv.javaguru.java2.hospital.patient.core.services.search_patient.search_criteria;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
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
public class NameAndSurnameSearchCriteriaTest {

    @Mock private PatientDatabaseImpl database;
    @InjectMocks private NameAndSurnameSearchCriteria searchCriteria;

    @Test
    public void shouldReturnTrue(){
        SearchPatientsRequest request = new SearchPatientsRequest("name2", "surname2", "");
        assertTrue(searchCriteria.canProcess(request));
    }

    @Test
    public void shouldReturnFalse(){
        SearchPatientsRequest request = new SearchPatientsRequest("", "surname2", "4321");
        assertFalse(searchCriteria.canProcess(request));
    }

    @Test
    public void shouldReturnCorrectPatient(){
        SearchPatientsRequest request = new SearchPatientsRequest("name2", "surname2", "");
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("name2", "surname2", "4321"));

        Mockito.when(database.findPatientsByNameAndSurname
                        (request.getName(), request.getSurname()))
                .thenReturn(patients);
        List<Patient> patients2 = database.findPatientsByNameAndSurname
                (request.getName(), request.getSurname());

        assertTrue(searchCriteria.canProcess(request));
        Assertions.assertEquals(patients2.get(0).getName(), "name2");
        Assertions.assertEquals(patients2.get(0).getSurname(), "surname2");
        Assertions.assertEquals(patients2.get(0).getPersonalCode(), "4321");
    }
}
