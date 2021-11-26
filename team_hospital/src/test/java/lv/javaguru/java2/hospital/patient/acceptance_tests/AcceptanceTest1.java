package lv.javaguru.java2.hospital.patient.acceptance_tests;

import lv.javaguru.java2.hospital.config.HospitalSpringCoreConfiguration;
import lv.javaguru.java2.hospital.database_cleaner.DatabaseCleaner;
import lv.javaguru.java2.hospital.patient.core.requests.AddPatientRequest;
import lv.javaguru.java2.hospital.patient.core.requests.ShowAllPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.ShowAllPatientsResponse;
import lv.javaguru.java2.hospital.patient.core.services.AddPatientService;
import lv.javaguru.java2.hospital.patient.core.services.ShowAllPatientsService;
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
public class AcceptanceTest1 {

    @Autowired private DatabaseCleaner databaseCleaner;
    @Autowired private AddPatientService addPatientService;
    @Autowired private ShowAllPatientsService showAllPatientsService;

    @Before
    public void setup() {
        databaseCleaner.clean();
    }

    @Test
    public void shouldCorrectAddPatient() {
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name", "surname", "01234567890");
        addPatientService.execute(addPatientRequest1);

        ShowAllPatientsResponse response = showAllPatientsService.execute(new ShowAllPatientsRequest());
        assertEquals(response.getPatients().size(), 1);
        assertEquals(response.getPatients().get(0).getName(), "name");
        assertEquals(response.getPatients().get(0).getSurname(), "surname");
        assertEquals(response.getPatients().get(0).getPersonalCode(), "01234567890");
    }
}
