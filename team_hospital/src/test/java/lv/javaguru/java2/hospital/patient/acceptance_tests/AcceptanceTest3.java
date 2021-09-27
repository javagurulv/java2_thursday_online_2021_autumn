package lv.javaguru.java2.hospital.patient.acceptance_tests;

import lv.javaguru.java2.hospital.PatientApplicationContext;
import lv.javaguru.java2.hospital.patient.core.services.AddPatientService;
import lv.javaguru.java2.hospital.patient.core.services.EditPatientService;
import lv.javaguru.java2.hospital.patient.core.services.ShowAllPatientsService;

public class AcceptanceTest3 {

    private final PatientApplicationContext applicationContext = new PatientApplicationContext();

   /* @Test
    public void shouldReturnCorrectSurnameAfterEditing() {
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name", "surname", "1234");
        getAddPatienceService().execute(addPatientRequest1);

        EditPatientRequest editPatientRequest = new EditPatientRequest(1L, 2, "NewSurname");
        EditPatientResponse editPatientResponse = getEditPatientService().execute(editPatientRequest);

        ShowAllPatientsResponse showAllPatientsResponse = getShowAllPatientsService().execute(new ShowAllPatientsRequest());

        assertTrue(editPatientResponse.isTrueOrNot());
        assertEquals(editPatientResponse.getChanges(), showAllPatientsResponse.getPatients().get(0).getSurname());
    } */

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
