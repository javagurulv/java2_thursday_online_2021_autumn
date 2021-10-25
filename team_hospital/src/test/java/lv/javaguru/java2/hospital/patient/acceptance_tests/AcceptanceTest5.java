package lv.javaguru.java2.hospital.patient.acceptance_tests;

import lv.javaguru.java2.hospital.config.HospitalConfiguration;
import org.springframework.context.ApplicationContext;
import lv.javaguru.java2.hospital.patient.core.requests.AddPatientRequest;
import lv.javaguru.java2.hospital.patient.core.requests.FindPatientByIdRequest;
import lv.javaguru.java2.hospital.patient.core.requests.ShowAllPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.AddPatientResponse;
import lv.javaguru.java2.hospital.patient.core.responses.FindPatientByIDResponse;
import lv.javaguru.java2.hospital.patient.core.responses.ShowAllPatientsResponse;
import lv.javaguru.java2.hospital.patient.core.services.AddPatientService;
import lv.javaguru.java2.hospital.patient.core.services.FindPatientByIdService;
import lv.javaguru.java2.hospital.patient.core.services.ShowAllPatientsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AcceptanceTest5 {

    private ApplicationContext applicationContext;

    @BeforeEach
    public void setup() {
        applicationContext = new AnnotationConfigApplicationContext(HospitalConfiguration.class);
    }

    @Test
    public void shouldReturnCorrectFindByIDPatient() {
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name1", "surname1", "11122233344");
        getAddPatienceService().execute(addPatientRequest1);

        AddPatientRequest addPatientRequest2 = new AddPatientRequest("name2", "surname2", "22233344455");
        AddPatientResponse addPatientResponse = getAddPatienceService().execute(addPatientRequest2);

        AddPatientRequest addPatientRequest3 = new AddPatientRequest("name3", "surname3", "33344455566");
        getAddPatienceService().execute(addPatientRequest3);

        FindPatientByIDResponse findPatientByIDResponse = getFindPatientByIdService().execute(
                new FindPatientByIdRequest(addPatientResponse.getPatient().getId()));
        ShowAllPatientsResponse showAllPatientsResponse = getShowAllPatientsService().execute(new ShowAllPatientsRequest());
        assertEquals(findPatientByIDResponse.getPatient(), Optional.of(showAllPatientsResponse.getPatients().get(1)));
    }

    private AddPatientService getAddPatienceService() {
        return applicationContext.getBean(AddPatientService.class);
    }

    private FindPatientByIdService getFindPatientByIdService() {
        return applicationContext.getBean(FindPatientByIdService.class);
    }

    private ShowAllPatientsService getShowAllPatientsService() {
        return applicationContext.getBean(ShowAllPatientsService.class);
    }

}
