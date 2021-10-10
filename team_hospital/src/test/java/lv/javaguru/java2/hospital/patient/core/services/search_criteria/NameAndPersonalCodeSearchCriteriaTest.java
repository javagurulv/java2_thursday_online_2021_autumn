package lv.javaguru.java2.hospital.patient.core.services.search_criteria;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class NameAndPersonalCodeSearchCriteriaTest {

    private final PatientDatabaseImpl patientDatabase = new PatientDatabaseImpl();
    private final NameAndPersonalCodeSearchCriteria searchCriteria = new NameAndPersonalCodeSearchCriteria(patientDatabase);

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
        patientDatabase.add(new Patient("name3", "surname3", "1111"));
        SearchPatientsRequest request = new SearchPatientsRequest("name3", "", "1111");
        Patient patient = searchCriteria.process(request).get(0);
        assertEquals(patient.getName(), "name3");
        assertEquals(patient.getSurname(), "surname3");
        assertEquals(patient.getPersonalCode(), "1111");
    }
}