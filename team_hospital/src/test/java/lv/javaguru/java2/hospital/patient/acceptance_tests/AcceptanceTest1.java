package lv.javaguru.java2.hospital.patient.acceptance_tests;

import lv.javaguru.java2.hospital.config.HospitalConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.ApplicationContext;
import lv.javaguru.java2.hospital.patient.core.requests.AddPatientRequest;
import lv.javaguru.java2.hospital.patient.core.requests.ShowAllPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.ShowAllPatientsResponse;
import lv.javaguru.java2.hospital.patient.core.services.AddPatientService;
import lv.javaguru.java2.hospital.patient.core.services.ShowAllPatientsService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AcceptanceTest1 {

    private ApplicationContext applicationContext;

    @BeforeEach
    public void setup() {
        applicationContext = new AnnotationConfigApplicationContext(HospitalConfiguration.class);
    }

    @Test
    public void shouldCorrectAddPatient() {
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name", "surname", "1234");
        getAddPatienceService().execute(addPatientRequest1);

        ShowAllPatientsResponse response = getShowAllPatientsService().execute(new ShowAllPatientsRequest());
        assertEquals(response.getPatients().size(), 1);
        assertEquals(response.getPatients().get(0).getName(), "name");
        assertEquals(response.getPatients().get(0).getSurname(), "surname");
        assertEquals(response.getPatients().get(0).getPersonalCode(), "1234");
    }

    private AddPatientService getAddPatienceService() {
        return applicationContext.getBean(AddPatientService.class);
    }

    private ShowAllPatientsService getShowAllPatientsService() {
        return applicationContext.getBean(ShowAllPatientsService.class);
    }
}
