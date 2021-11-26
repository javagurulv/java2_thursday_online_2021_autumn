package lv.javaguru.java2.hospital.patient.acceptance_tests;

import lv.javaguru.java2.hospital.config.HospitalSpringCoreConfiguration;
import lv.javaguru.java2.hospital.database_cleaner.DatabaseCleaner;
import lv.javaguru.java2.hospital.patient.core.requests.AddPatientRequest;
import lv.javaguru.java2.hospital.patient.core.requests.EditPatientRequest;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.AddPatientResponse;
import lv.javaguru.java2.hospital.patient.core.responses.EditPatientResponse;
import lv.javaguru.java2.hospital.patient.core.responses.SearchPatientsResponse;
import lv.javaguru.java2.hospital.patient.core.services.AddPatientService;
import lv.javaguru.java2.hospital.patient.core.services.EditPatientService;
import lv.javaguru.java2.hospital.patient.core.services.search_patient_service.SearchPatientsService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HospitalSpringCoreConfiguration.class})
@Sql({"/Schema.sql"})
public class AcceptanceTest3 {

    @Autowired private DatabaseCleaner databaseCleaner;
    @Autowired private AddPatientService addPatientService;
    @Autowired private SearchPatientsService searchPatientsService;
    @Autowired private EditPatientService editPatientService;

    @BeforeEach
    public void setup() {
        databaseCleaner.clean();
    }

    @Test
    public void shouldReturnCorrectNameAfterEditing() {
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name9", "surname", "11223344556");
        AddPatientResponse addPatientResponse = addPatientService.execute(addPatientRequest1);

        SearchPatientsRequest searchPatientsRequest1 = new SearchPatientsRequest("name9", "surname", "11223344556");
        SearchPatientsResponse searchPatientsResponse1 = searchPatientsService.execute(searchPatientsRequest1);

        EditPatientRequest editPatientRequest = new EditPatientRequest(searchPatientsResponse1.getPatientList().get(0).getId().toString(),
                "NAME", "NewName");
        EditPatientResponse editPatientResponse = editPatientService.execute(editPatientRequest);

		SearchPatientsRequest searchPatientsRequest2 = new SearchPatientsRequest("NewName", "surname", "");
		SearchPatientsResponse searchPatientsResponse2 = searchPatientsService.execute(searchPatientsRequest2);

		assertEquals(searchPatientsResponse2.getPatientList().get(0).getName(), "NewName");
		assertEquals(searchPatientsResponse2.getPatientList().get(0).getSurname(), "surname");
		assertEquals(searchPatientsResponse2.getPatientList().get(0).getPersonalCode(), "11223344556");
    }

    @Test
    public void shouldReturnCorrectSurnameAfterEditing() {
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name10", "surname", "22334455667");
        AddPatientResponse addPatientResponse = addPatientService.execute(addPatientRequest1);

        SearchPatientsRequest searchPatientsRequest1 = new SearchPatientsRequest("name10", "surname", "22334455667");
        SearchPatientsResponse searchPatientsResponse1 = searchPatientsService.execute(searchPatientsRequest1);

        EditPatientRequest editPatientRequest = new EditPatientRequest(searchPatientsResponse1.getPatientList().get(0).getId().toString(),
                "SURNAME", "NewSurname");
        EditPatientResponse editPatientResponse = editPatientService.execute(editPatientRequest);

		SearchPatientsRequest searchPatientsRequest2 = new SearchPatientsRequest("name10", "NewSurname", "");
		SearchPatientsResponse searchPatientsResponse2 = searchPatientsService.execute(searchPatientsRequest2);

		assertEquals(searchPatientsResponse2.getPatientList().get(0).getName(), "name10");
		assertEquals(searchPatientsResponse2.getPatientList().get(0).getSurname(), "NewSurname");
		assertEquals(searchPatientsResponse2.getPatientList().get(0).getPersonalCode(), "22334455667");
    }

    @Test
    public void shouldReturnCorrectPersonalCodeAfterEditing() {
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name11", "surname", "33445566778");
        AddPatientResponse addPatientResponse = addPatientService.execute(addPatientRequest1);

        EditPatientRequest editPatientRequest = new EditPatientRequest(addPatientResponse.getPatient().getId().toString(),
                "PERSONAL_CODE", "44556677889");
        EditPatientResponse editPatientResponse = editPatientService.execute(editPatientRequest);

		SearchPatientsRequest searchPatientsRequest2 = new SearchPatientsRequest("name11", "surname", "");
		SearchPatientsResponse searchPatientsResponse2 = searchPatientsService.execute(searchPatientsRequest2);

		assertEquals(searchPatientsResponse2.getPatientList().get(0).getName(), "name11");
		assertEquals(searchPatientsResponse2.getPatientList().get(0).getSurname(), "surname");
		assertEquals(searchPatientsResponse2.getPatientList().get(0).getPersonalCode(), "44556677889");
    }
}
