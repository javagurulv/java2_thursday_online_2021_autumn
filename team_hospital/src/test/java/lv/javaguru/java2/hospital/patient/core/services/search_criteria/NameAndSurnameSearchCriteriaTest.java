package lv.javaguru.java2.hospital.patient.core.services.search_criteria;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class NameAndSurnameSearchCriteriaTest {

    private final PatientDatabaseImpl patientDatabase = new PatientDatabaseImpl();
    private final NameAndSurnameSearchCriteria searchCriteria = new NameAndSurnameSearchCriteria(patientDatabase);

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
        patientDatabase.add(new Patient("name2", "surname2", "4321"));
        SearchPatientsRequest request = new SearchPatientsRequest("name2", "surname2", "");
        Patient patient = searchCriteria.process(request).get(0);
        assertEquals(patient.getName(), "name2");
        assertEquals(patient.getSurname(), "surname2");
        assertEquals(patient.getPersonalCode(), "4321");
    }
}
