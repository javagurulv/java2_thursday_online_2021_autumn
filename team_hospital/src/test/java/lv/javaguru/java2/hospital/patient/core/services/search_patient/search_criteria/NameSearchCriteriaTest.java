package lv.javaguru.java2.hospital.patient.core.services.search_patient.search_criteria;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import org.junit.Test;

import static org.junit.Assert.*;

public class NameSearchCriteriaTest {

    private final PatientDatabaseImpl patientDatabase = new PatientDatabaseImpl();
    private final NameSearchCriteria searchCriteria = new NameSearchCriteria(patientDatabase);

    @Test
    public void shouldReturnTrue(){
        SearchPatientsRequest request = new SearchPatientsRequest("name", "", "");
        assertTrue(searchCriteria.canProcess(request));
    }

    @Test
    public void shouldReturnFalse(){
        SearchPatientsRequest request = new SearchPatientsRequest("", "surname", "1234");
        assertFalse(searchCriteria.canProcess(request));
    }

    @Test
    public void shouldReturnCorrectPatient(){
        patientDatabase.add(new Patient("name1", "surname1", "1234"));
        SearchPatientsRequest request = new SearchPatientsRequest("name1", "", "");
        Patient patient = searchCriteria.process(request).get(0);
        assertEquals(patient.getName(), "name1");
        assertEquals(patient.getSurname(), "surname1");
        assertEquals(patient.getPersonalCode(), "1234");
    }
}
