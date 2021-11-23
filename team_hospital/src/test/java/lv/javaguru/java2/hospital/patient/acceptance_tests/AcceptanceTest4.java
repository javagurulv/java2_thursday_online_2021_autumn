package lv.javaguru.java2.hospital.patient.acceptance_tests;

import lv.javaguru.java2.hospital.config.HospitalConfiguration;
import lv.javaguru.java2.hospital.database_cleaner.DatabaseCleaner;
import lv.javaguru.java2.hospital.patient.core.requests.AddPatientRequest;
import lv.javaguru.java2.hospital.patient.core.requests.DeletePatientRequest;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.requests.ShowAllPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.AddPatientResponse;
import lv.javaguru.java2.hospital.patient.core.responses.DeletePatientResponse;
import lv.javaguru.java2.hospital.patient.core.responses.SearchPatientsResponse;
import lv.javaguru.java2.hospital.patient.core.responses.ShowAllPatientsResponse;
import lv.javaguru.java2.hospital.patient.core.services.AddPatientService;
import lv.javaguru.java2.hospital.patient.core.services.DeletePatientService;
import lv.javaguru.java2.hospital.patient.core.services.ShowAllPatientsService;
import lv.javaguru.java2.hospital.patient.core.services.search_patient_service.SearchPatientsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HospitalConfiguration.class})
@Sql({"/Schema.sql"})
public class AcceptanceTest4 {

    @Autowired private DatabaseCleaner databaseCleaner;
    @Autowired private AddPatientService addPatientService;
    @Autowired private SearchPatientsService searchPatientsService;
    @Autowired private DeletePatientService deletePatientService;
    @Autowired private ShowAllPatientsService showAllPatientsService;

    @Before
    public void setup() {
        databaseCleaner.clean();
    }

    @Test
    public void shouldReturnCorrectPatientsListAfterDelete() {
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name1", "surname1", "11223344556");
        AddPatientResponse addPatientResponse = addPatientService.execute(addPatientRequest1);

        AddPatientRequest addPatientRequest2 = new AddPatientRequest("name2", "surname2", "22334455667");
        addPatientService.execute(addPatientRequest2);

        SearchPatientsRequest searchPatientsRequest = new SearchPatientsRequest("name1", "surname1", "11223344556");
        SearchPatientsResponse searchPatientsResponse = searchPatientsService.execute(searchPatientsRequest);

        DeletePatientRequest deletePatientRequest =
                new DeletePatientRequest(searchPatientsResponse.getPatientList().get(0).getId().toString());
        DeletePatientResponse deletePatientResponse = deletePatientService.execute(deletePatientRequest);

        ShowAllPatientsResponse response = showAllPatientsService.execute(new ShowAllPatientsRequest());
        assertEquals(response.getPatients().size(), 1);
        assertEquals(response.getPatients().get(0).getName(), "name2");
        assertEquals(response.getPatients().get(0).getSurname(), "surname2");
        assertEquals(response.getPatients().get(0).getPersonalCode(), "22334455667");
    }
}