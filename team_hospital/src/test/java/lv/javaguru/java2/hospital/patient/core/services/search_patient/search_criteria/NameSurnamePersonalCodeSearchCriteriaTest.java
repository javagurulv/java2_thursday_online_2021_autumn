package lv.javaguru.java2.hospital.patient.core.services.search_patient.search_criteria;


import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.services.search_patient_service.search_criteria.NameSurnamePersonalCodeSearchCriteria;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class NameSurnamePersonalCodeSearchCriteriaTest {

    @Mock private PatientDatabaseImpl database;
    @InjectMocks private NameSurnamePersonalCodeSearchCriteria searchCriteria;

    @Test
    public void shouldReturnTrue(){
        SearchPatientsRequest request = new SearchPatientsRequest("name", "surname", "1234");
        assertTrue(searchCriteria.canProcess(request));
    }

    @Test
    public void shouldReturnFalse(){
        SearchPatientsRequest request = new SearchPatientsRequest("name", "surname", "");
        assertFalse(searchCriteria.canProcess(request));
    }

    @Test
    public void shouldReturnCorrectPatient(){
        SearchPatientsRequest request = new SearchPatientsRequest("name", "surname", "1234");
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("name", "surname", "1234"));

        Mockito.when(database.findPatientByNameSurnamePersonalCode
                (request.getName(), request.getSurname(), request.getPersonalCode()))
                .thenReturn(patients);
        List<Patient> patients2 = database
                .findPatientByNameSurnamePersonalCode(request.getName(), request.getSurname(), request.getPersonalCode());

        assertTrue(searchCriteria.canProcess(request));
        assertEquals(patients2.get(0).getName(), "name");
        assertEquals(patients2.get(0).getSurname(), "surname");
        assertEquals(patients2.get(0).getPersonalCode(), "1234");
    }
}