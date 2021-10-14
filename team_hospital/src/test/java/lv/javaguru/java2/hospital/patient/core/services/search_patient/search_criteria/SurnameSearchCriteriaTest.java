package lv.javaguru.java2.hospital.patient.core.services.search_patient.search_criteria;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

class SurnameSearchCriteriaTest {

    private PatientDatabaseImpl patientDatabase = new PatientDatabaseImpl();
    private SurnameSearchCriteria searchCriteria = new SurnameSearchCriteria(patientDatabase);

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
        patientDatabase.add(new Patient("Name8745", "Surname1268", "985-65691"));
        SearchPatientsRequest request = new SearchPatientsRequest("", "Surname1268", "");
        Patient patient = searchCriteria.process(request).get(0);
        assertEquals(patient.getName(), "Name8745");
        assertEquals(patient.getSurname(), "Surname1268");
        assertEquals(patient.getPersonalCode(), "985-65691");
    }

}