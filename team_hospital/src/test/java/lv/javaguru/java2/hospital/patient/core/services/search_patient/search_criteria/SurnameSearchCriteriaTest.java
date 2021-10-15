package lv.javaguru.java2.hospital.patient.core.services.search_patient.search_criteria;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.services.search_patient_service.search_criteria.SurnameSearchCriteria;
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
class SurnameSearchCriteriaTest {

    @Mock private PatientDatabaseImpl database;
    @InjectMocks private SurnameSearchCriteria searchCriteria;

    @Test
    public void shouldReturnTrue(){
        SearchPatientsRequest request = new SearchPatientsRequest("", "Surname", "");
        assertTrue(searchCriteria.canProcess(request));
    }

    @Test
    public void shouldReturnFalse(){
        SearchPatientsRequest request = new SearchPatientsRequest("Name", "", "987");
        assertFalse(searchCriteria.canProcess(request));
    }

    @Test
    public void shouldReturnCorrectPatient(){
        SearchPatientsRequest request = new SearchPatientsRequest("", "Surname1268", "");
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("Name8745", "Surname1268", "985-65691"));

        Mockito.when(database.findPatientsBySurname(request.getSurname()))
                .thenReturn(patients);
        List<Patient> patients2 = database
                .findPatientsBySurname(request.getSurname());
        assertTrue(searchCriteria.canProcess(request));

        assertEquals(patients2.get(0).getName(), "Name8745");
        assertEquals(patients2.get(0).getSurname(), "Surname1268");
        assertEquals(patients2.get(0).getPersonalCode(), "985-65691");
    }

}