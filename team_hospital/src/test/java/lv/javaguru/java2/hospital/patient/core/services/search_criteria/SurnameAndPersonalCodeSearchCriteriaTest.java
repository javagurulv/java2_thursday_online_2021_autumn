package lv.javaguru.java2.hospital.patient.core.services.search_criteria;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

class SurnameAndPersonalCodeSearchCriteriaTest {

    private PatientDatabaseImpl patientDatabase = new PatientDatabaseImpl();
    private SurnameAndPersonalCodeSearchCriteria searchCriteria = new SurnameAndPersonalCodeSearchCriteria(patientDatabase);

    @Test
    public void shouldReturnTrue() {
        SearchPatientsRequest request = new SearchPatientsRequest("", "Surname546", "656-546");
        assertTrue(searchCriteria.canProcess(request));
    }

    @Test
    public void shouldReturnFalse() {
        SearchPatientsRequest request = new SearchPatientsRequest("Name1569", "", "656-546");
        assertFalse(searchCriteria.canProcess(request));
    }

    @Test
    public void shouldReturnCorrectPatient(){
        patientDatabase.add(new Patient("Name1212", "Surname694", "851589-58"));
        SearchPatientsRequest request = new SearchPatientsRequest("", "Surname694", "851589-58");
        Patient patient = searchCriteria.process(request).get(0);
        assertEquals(patient.getName(), "Name1212");
        assertEquals(patient.getSurname(), "Surname694");
        assertEquals(patient.getPersonalCode(), "851589-58");
    }



}