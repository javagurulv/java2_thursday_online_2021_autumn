package lv.javaguru.java2.hospital.patient.acceptance_tests;

import lv.javaguru.java2.hospital.config.HospitalSpringCoreConfiguration;
import lv.javaguru.java2.hospital.database_cleaner.DatabaseCleaner;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.AddPatientRequest;
import lv.javaguru.java2.hospital.patient.core.requests.FindPatientByIdRequest;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.requests.ShowAllPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.FindPatientByIDResponse;
import lv.javaguru.java2.hospital.patient.core.responses.SearchPatientsResponse;
import lv.javaguru.java2.hospital.patient.core.responses.ShowAllPatientsResponse;
import lv.javaguru.java2.hospital.patient.core.services.AddPatientService;
import lv.javaguru.java2.hospital.patient.core.services.FindPatientByIdService;
import lv.javaguru.java2.hospital.patient.core.services.ShowAllPatientsService;
import lv.javaguru.java2.hospital.patient.core.services.search_patient_service.SearchPatientsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HospitalSpringCoreConfiguration.class})
@Sql({"/Schema.sql"})
public class AcceptanceTest5 {

    @Autowired private DatabaseCleaner databaseCleaner;
    @Autowired private AddPatientService addPatientService;
    @Autowired private SearchPatientsService searchPatientsService;
    @Autowired private FindPatientByIdService findPatientByIdService;
    @Autowired private ShowAllPatientsService showAllPatientsService;

    @Before
    public void setup() {
        databaseCleaner.clean();
    }

    @Test
    public void shouldReturnCorrectFindByIDPatient() {
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name1", "surname1", "11122233344");
        addPatientService.execute(addPatientRequest1);

        AddPatientRequest addPatientRequest2 = new AddPatientRequest("name2", "surname2", "22233344455");
        addPatientService.execute(addPatientRequest2);

        AddPatientRequest addPatientRequest3 = new AddPatientRequest("name3", "surname3", "33344455566");
        addPatientService.execute(addPatientRequest3);

        SearchPatientsRequest searchPatientsRequest = new SearchPatientsRequest("name2", "surname2", "22233344455");
        SearchPatientsResponse searchPatientsResponse = searchPatientsService.execute(searchPatientsRequest);

        FindPatientByIDResponse findPatientByIDResponse = findPatientByIdService.execute(
                new FindPatientByIdRequest(searchPatientsResponse.getPatientList().get(0).getId().toString()));
        ShowAllPatientsResponse showAllPatientsResponse = showAllPatientsService.execute(new ShowAllPatientsRequest());
        List<Patient> patients = new ArrayList<>();
        patients.add(showAllPatientsResponse.getPatients().get(1));
        assertEquals(findPatientByIDResponse.getPatient(), patients);
    }
}
