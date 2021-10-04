package lv.javaguru.java2.hospital.patient.acceptance_tests;

import lv.javaguru.java2.hospital.dependency_injection.ApplicationContext;
import lv.javaguru.java2.hospital.dependency_injection.DIApplicationContextBuilder;
import lv.javaguru.java2.hospital.patient.core.requests.AddPatientRequest;
import lv.javaguru.java2.hospital.patient.core.requests.EditPatientRequest;
import lv.javaguru.java2.hospital.patient.core.requests.ShowAllPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.AddPatientResponse;
import lv.javaguru.java2.hospital.patient.core.responses.EditPatientResponse;
import lv.javaguru.java2.hospital.patient.core.responses.ShowAllPatientsResponse;
import lv.javaguru.java2.hospital.patient.core.services.AddPatientService;
import lv.javaguru.java2.hospital.patient.core.services.EditPatientService;
import lv.javaguru.java2.hospital.patient.core.services.ShowAllPatientsService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AcceptanceTest3 {

    private static final ApplicationContext applicationContext =
            new DIApplicationContextBuilder().build("lv.javaguru.java2.hospital");

    @Test
    public void shouldReturnCorrectNameAfterEditing() {
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name", "surname", "1234");
        AddPatientResponse addPatientResponse = getAddPatienceService().execute(addPatientRequest1);

        EditPatientRequest editPatientRequest = new EditPatientRequest(addPatientResponse.getPatient().getId(),
                1, "NewName");
        EditPatientResponse editPatientResponse = getEditPatientService().execute(editPatientRequest);

        ShowAllPatientsResponse showAllPatientsResponse = getShowAllPatientsService().execute(new ShowAllPatientsRequest());

        assertTrue(editPatientResponse.isTrueOrNot());
        assertEquals(editPatientResponse.getChanges(), showAllPatientsResponse.getPatients().get(0).getName());
        assertEquals(showAllPatientsResponse.getPatients().get(0).getSurname(), "surname");
        assertEquals(showAllPatientsResponse.getPatients().get(0).getPersonalCode(), "1234");
    }

    @Test
    public void shouldReturnCorrectSurnameAfterEditing() {
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name", "surname", "1234");
        AddPatientResponse addPatientResponse = getAddPatienceService().execute(addPatientRequest1);

        EditPatientRequest editPatientRequest = new EditPatientRequest(addPatientResponse.getPatient().getId(),
                2, "NewSurname");
        EditPatientResponse editPatientResponse = getEditPatientService().execute(editPatientRequest);

        ShowAllPatientsResponse showAllPatientsResponse = getShowAllPatientsService().execute(new ShowAllPatientsRequest());

        assertTrue(editPatientResponse.isTrueOrNot());
        assertEquals(editPatientResponse.getChanges(), showAllPatientsResponse.getPatients().get(0).getSurname());
        assertEquals(showAllPatientsResponse.getPatients().get(0).getName(), "name");
        assertEquals(showAllPatientsResponse.getPatients().get(0).getPersonalCode(), "1234");
    }

    @Test
    public void shouldReturnCorrectPersonalCodeAfterEditing() {
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name", "surname", "1234");
        AddPatientResponse addPatientResponse = getAddPatienceService().execute(addPatientRequest1);

        EditPatientRequest editPatientRequest = new EditPatientRequest(addPatientResponse.getPatient().getId(),
                3, "New1234");
        EditPatientResponse editPatientResponse = getEditPatientService().execute(editPatientRequest);

        ShowAllPatientsResponse showAllPatientsResponse = getShowAllPatientsService().execute(new ShowAllPatientsRequest());

        assertTrue(editPatientResponse.isTrueOrNot());
        assertEquals(editPatientResponse.getChanges(), showAllPatientsResponse.getPatients().get(0).getPersonalCode());
        assertEquals(showAllPatientsResponse.getPatients().get(0).getName(), "name");
        assertEquals(showAllPatientsResponse.getPatients().get(0).getSurname(), "surname");
    }

    private AddPatientService getAddPatienceService() {
        return applicationContext.getBean(AddPatientService.class);
    }

    private EditPatientService getEditPatientService() {
        return applicationContext.getBean(EditPatientService.class);
    }

    private ShowAllPatientsService getShowAllPatientsService() {
        return applicationContext.getBean(ShowAllPatientsService.class);
    }
}
