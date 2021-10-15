package lv.javaguru.java2.hospital.patient.core.services.search_patient.search_criteria;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class PersonalCodeSearchCriteriaTest {

    @Mock private PatientDatabaseImpl database;
    @InjectMocks private PersonalCodeSearchCriteria searchCriteria;

    @Test
    public void shouldReturnTrue(){
        SearchPatientsRequest request = new SearchPatientsRequest("", "", "12354-4688");
        assertTrue(searchCriteria.canProcess(request));
    }

    @Test
    public void shouldReturnFalse(){
        SearchPatientsRequest request = new SearchPatientsRequest("Name", "Surname", "");
        assertFalse(searchCriteria.canProcess(request));
    }

    @Test
    public void shouldReturnCorrectPatient(){
        SearchPatientsRequest request = new SearchPatientsRequest("", "", "12354-4688");
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("name3", "surname3", "12354-4688"));

        Mockito.when(database.findPatientsByPersonalCode
                        (request.getPersonalCode()))
                .thenReturn(patients);
        List<Patient> patients2 = database.findPatientsByPersonalCode
                (request.getPersonalCode());

        assertTrue(searchCriteria.canProcess(request));
        Assertions.assertEquals(patients2.get(0).getName(), "name3");
        Assertions.assertEquals(patients2.get(0).getSurname(), "surname3");
        Assertions.assertEquals(patients2.get(0).getPersonalCode(), "12354-4688");
    }

}