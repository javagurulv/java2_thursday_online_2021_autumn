package lv.javaguru.java2.hospital.patient.acceptance_tests;

import lv.javaguru.java2.hospital.config.HospitalSpringCoreConfiguration;
import lv.javaguru.java2.hospital.database_cleaner.DatabaseCleaner;
import lv.javaguru.java2.hospital.patient.core.requests.AddPatientRequest;
import lv.javaguru.java2.hospital.patient.core.requests.PatientOrdering;
import lv.javaguru.java2.hospital.patient.core.requests.PatientPaging;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.SearchPatientsResponse;
import lv.javaguru.java2.hospital.patient.core.services.AddPatientService;
import lv.javaguru.java2.hospital.patient.core.services.search_patient_service.SearchPatientsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HospitalSpringCoreConfiguration.class})
@Sql({"/Schema.sql"})
public class AcceptanceTest2 {

    @Autowired private DatabaseCleaner databaseCleaner;
    @Autowired private AddPatientService addPatientService;
    @Autowired private SearchPatientsService searchPatientsService;

    @Before
    public void setup() {
        databaseCleaner.clean();
    }

    @Test
    public void shouldReturnCorrectPatientsListWithSearch() {
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name1", "surname1", "11111111111");
        addPatientService.execute(addPatientRequest1);

        AddPatientRequest addPatientRequest2 = new AddPatientRequest("name2", "surname2", "22222222222");
        addPatientService.execute(addPatientRequest2);

        AddPatientRequest addPatientRequest3 = new AddPatientRequest("name3", "surname3", "33333333333");
        addPatientService.execute(addPatientRequest3);

        SearchPatientsRequest searchPatientsRequest = new SearchPatientsRequest("name2", "surname2", "");
        SearchPatientsResponse searchPatientsResponse = searchPatientsService.execute(searchPatientsRequest);

        assertEquals(searchPatientsResponse.getPatientList().size(), 1);
        assertEquals(searchPatientsResponse.getPatientList().get(0).getName(), "name2");
        assertEquals(searchPatientsResponse.getPatientList().get(0).getSurname(), "surname2");
        assertEquals(searchPatientsResponse.getPatientList().get(0).getPersonalCode(), "22222222222");
    }

    @Test
    public void shouldReturnCorrectPatientsListWithSearchDescending() {
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name4", "surname1", "44444444444");
        addPatientService.execute(addPatientRequest1);

        AddPatientRequest addPatientRequest2 = new AddPatientRequest("name4", "surname2", "55555555555");
        addPatientService.execute(addPatientRequest2);

        AddPatientRequest addPatientRequest3 = new AddPatientRequest("name4", "surname3", "66666666666");
        addPatientService.execute(addPatientRequest3);

        PatientOrdering patientOrdering = new PatientOrdering("surname", "descending");

        SearchPatientsRequest searchPatientsRequest =
                new SearchPatientsRequest("name4", "", "", "surname", "descending", "", "");
        SearchPatientsResponse searchPatientsResponse = searchPatientsService.execute(searchPatientsRequest);

        assertEquals(searchPatientsResponse.getPatientList().size(), 3);
        assertEquals(searchPatientsResponse.getPatientList().get(0).getName(), "name4");
        assertEquals(searchPatientsResponse.getPatientList().get(0).getSurname(), "surname3");
        assertEquals(searchPatientsResponse.getPatientList().get(0).getPersonalCode(), "66666666666");
        assertEquals(searchPatientsResponse.getPatientList().get(1).getName(), "name4");
        assertEquals(searchPatientsResponse.getPatientList().get(1).getSurname(), "surname2");
        assertEquals(searchPatientsResponse.getPatientList().get(1).getPersonalCode(), "55555555555");
        assertEquals(searchPatientsResponse.getPatientList().get(2).getName(), "name4");
        assertEquals(searchPatientsResponse.getPatientList().get(2).getSurname(), "surname1");
        assertEquals(searchPatientsResponse.getPatientList().get(2).getPersonalCode(), "44444444444");
    }

    @Test
    public void shouldReturnCorrectPatientsListWithSearchAscending() {
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name5", "surname1", "77777777777");
        addPatientService.execute(addPatientRequest1);

        AddPatientRequest addPatientRequest2 = new AddPatientRequest("name5", "surname2", "88888888888");
        addPatientService.execute(addPatientRequest2);

        AddPatientRequest addPatientRequest3 = new AddPatientRequest("name5", "surname3", "99999999999");
        addPatientService.execute(addPatientRequest3);

        PatientOrdering patientOrdering = new PatientOrdering("surname", "ascending");

        SearchPatientsRequest searchPatientsRequest =
                new SearchPatientsRequest("name5", "", "", patientOrdering);
        SearchPatientsResponse searchPatientsResponse = searchPatientsService.execute(searchPatientsRequest);

        assertEquals(searchPatientsResponse.getPatientList().size(), 3);
        assertEquals(searchPatientsResponse.getPatientList().get(0).getName(), "name5");
        assertEquals(searchPatientsResponse.getPatientList().get(0).getSurname(), "surname1");
        assertEquals(searchPatientsResponse.getPatientList().get(0).getPersonalCode(), "77777777777");
        assertEquals(searchPatientsResponse.getPatientList().get(1).getName(), "name5");
        assertEquals(searchPatientsResponse.getPatientList().get(1).getSurname(), "surname2");
        assertEquals(searchPatientsResponse.getPatientList().get(1).getPersonalCode(), "88888888888");
        assertEquals(searchPatientsResponse.getPatientList().get(2).getName(), "name5");
        assertEquals(searchPatientsResponse.getPatientList().get(2).getSurname(), "surname3");
        assertEquals(searchPatientsResponse.getPatientList().get(2).getPersonalCode(), "99999999999");
    }

    @Test
    public void shouldReturnCorrectPatientsListWithSearchAscendingAndPaging() {
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name6", "surname1", "12345098765");
        addPatientService.execute(addPatientRequest1);

        AddPatientRequest addPatientRequest2 = new AddPatientRequest("name6", "surname2", "123456098765");
        addPatientService.execute(addPatientRequest2);

        AddPatientRequest addPatientRequest3 = new AddPatientRequest("name6", "surname3", "123456709876");
        addPatientService.execute(addPatientRequest3);

        PatientOrdering patientOrdering = new PatientOrdering("surname", "ascending");
        PatientPaging patientPaging = new PatientPaging(1, 1);

        SearchPatientsRequest searchPatientsRequest =
                new SearchPatientsRequest("name6", "", "", patientOrdering, patientPaging);
        SearchPatientsResponse searchPatientsResponse = searchPatientsService.execute(searchPatientsRequest);

        assertEquals(searchPatientsResponse.getPatientList().size(), 1);
        assertEquals(searchPatientsResponse.getPatientList().get(0).getName(), "name6");
        assertEquals(searchPatientsResponse.getPatientList().get(0).getSurname(), "surname1");
        assertEquals(searchPatientsResponse.getPatientList().get(0).getPersonalCode(), "12345098765");
    }

    @Test
    public void shouldReturnCorrectPatientsListWithSearchAscendingAndPaging2() {
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name7", "surname1", "23456789012");
        addPatientService.execute(addPatientRequest1);

        AddPatientRequest addPatientRequest2 = new AddPatientRequest("name7", "surname2", "34567890123");
        addPatientService.execute(addPatientRequest2);

        AddPatientRequest addPatientRequest3 = new AddPatientRequest("name7", "surname3", "45678901234");
        addPatientService.execute(addPatientRequest3);

        SearchPatientsRequest searchPatientsRequest =
                new SearchPatientsRequest("name7", "", "", "surname", "ascending",
                        "3","1");
        SearchPatientsResponse searchPatientsResponse = searchPatientsService.execute(searchPatientsRequest);

        assertEquals(searchPatientsResponse.getPatientList().size(), 1);
        assertEquals(searchPatientsResponse.getPatientList().get(0).getName(), "name7");
        assertEquals(searchPatientsResponse.getPatientList().get(0).getSurname(), "surname3");
        assertEquals(searchPatientsResponse.getPatientList().get(0).getPersonalCode(), "45678901234");
    }
}
