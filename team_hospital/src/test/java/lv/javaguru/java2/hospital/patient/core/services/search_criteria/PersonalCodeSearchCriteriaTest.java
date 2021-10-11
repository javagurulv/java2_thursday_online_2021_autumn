package lv.javaguru.java2.hospital.patient.core.services.search_criteria;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

class PersonalCodeSearchCriteriaTest {

    private PatientDatabaseImpl patientDatabase = new PatientDatabaseImpl();
    private PersonalCodeSearchCriteria searchCriteria = new PersonalCodeSearchCriteria(patientDatabase);

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
        patientDatabase.add(new Patient("Name123", "Surname123", "12357-65691"));
        SearchPatientsRequest request = new SearchPatientsRequest("", "", "12357-65691");
        Patient patient = searchCriteria.process(request).get(0);
        assertEquals(patient.getName(), "Name123");
        assertEquals(patient.getSurname(), "Surname123");
        assertEquals(patient.getPersonalCode(), "12357-65691");
    }

}