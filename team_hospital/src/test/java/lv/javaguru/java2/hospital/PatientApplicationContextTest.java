package lv.javaguru.java2.hospital;

import lv.javaguru.java2.hospital.patient.core.requests.*;
import lv.javaguru.java2.hospital.patient.core.responses.*;
import lv.javaguru.java2.hospital.patient.core.services.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatientApplicationContextTest {

    private final PatientApplicationContext applicationContext = new PatientApplicationContext();

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

    @Test
    public void shouldReturnCorrectPatientsListWithSearch() {
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name1", "surname1", "1234");
        getAddPatienceService().execute(addPatientRequest1);

        AddPatientRequest addPatientRequest2 = new AddPatientRequest("name2", "surname2", "1235");
        getAddPatienceService().execute(addPatientRequest2);

        AddPatientRequest addPatientRequest3 = new AddPatientRequest("name3", "surname3", "1236");
        getAddPatienceService().execute(addPatientRequest3);

        SearchPatientsRequest searchPatientsRequest = new SearchPatientsRequest("", "surname2", "");
        SearchPatientsResponse searchPatientsResponse = getSearchPatientsService().execute(searchPatientsRequest);

        assertEquals(searchPatientsResponse.getPatientList().size(), 1);
        assertEquals(searchPatientsResponse.getPatientList().get(0).getName(), "name2");
        assertEquals(searchPatientsResponse.getPatientList().get(0).getSurname(), "surname2");
        assertEquals(searchPatientsResponse.getPatientList().get(0).getPersonalCode(), "1235");
    }

    @Test
    public void shouldReturnCorrectPatientsList() {
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name", "surname", "1234");
        getAddPatienceService().execute(addPatientRequest1);

        AddPatientRequest addPatientRequest2 = new AddPatientRequest("name", "surname", "1235");
        getAddPatienceService().execute(addPatientRequest2);

        ShowAllPatientsResponse response = getShowAllPatientsService().execute(new ShowAllPatientsRequest());
        assertEquals(response.getPatients().size(), 2);
    }

    /* @Test
    public void shouldReturnCorrectPatientAfterEditing() {
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name", "surname", "1234");
        getAddPatienceService().execute(addPatientRequest1);

        EditPatientRequest editPatientRequest = new EditPatientRequest("1", "2", "NewSurname");
        getEditPatientService().execute(editPatientRequest);

        ShowAllPatientsResponse response = getShowAllPatientsService().execute(new ShowAllPatientsRequest());
        assertEquals(response.getPatients().size(), 1);
        assertEquals(response.getPatients().get(0).getName(), "name");
        assertEquals(response.getPatients().get(0).getSurname(), "NewSurname");
        assertEquals(response.getPatients().get(0).getPersonalCode(), "1234");
    }

    @Test
    public void shouldReturnCorrectFindByIDPatient() {
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name1", "surname1", "1234");
        getAddPatienceService().execute(addPatientRequest1);

        AddPatientRequest addPatientRequest2 = new AddPatientRequest("name2", "surname2", "1235");
        getAddPatienceService().execute(addPatientRequest2);

        AddPatientRequest addPatientRequest3 = new AddPatientRequest("name3", "surname3", "1236");
        getAddPatienceService().execute(addPatientRequest3);

        FindPatientByIDResponse findPatientByIDResponse = getFindPatientByIdService().execute(new FindPatientByIdRequest("2"));
        ShowAllPatientsResponse showAllPatientsResponse = getShowAllPatientsService().execute(new ShowAllPatientsRequest());
        assertEquals(findPatientByIDResponse.getPatient(), Optional.of(showAllPatientsResponse.getPatients().get(1)));
    }

    @Test
    public void shouldReturnCorrectPatientsListAfterDelete() {
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name1", "surname1", "1234");
        getAddPatienceService().execute(addPatientRequest1);

        AddPatientRequest addPatientRequest2 = new AddPatientRequest("name2", "surname2", "1235");
        getAddPatienceService().execute(addPatientRequest2);

        DeletePatientRequest deletePatientRequest = new DeletePatientRequest("1");
        getDeletePatientService().execute(deletePatientRequest);

        ShowAllPatientsResponse response = getShowAllPatientsService().execute(new ShowAllPatientsRequest());
        assertEquals(response.getPatients().size(), 1);
        assertEquals(response.getPatients().get(0).getName(), "name2");
        assertEquals(response.getPatients().get(0).getSurname(), "surname2");
        assertEquals(response.getPatients().get(0).getPersonalCode(), "1235");
    } */


    private AddPatientService getAddPatienceService() {
        return applicationContext.getBean(AddPatientService.class);
    }

    private DeletePatientService getDeletePatientService() {
        return applicationContext.getBean(DeletePatientService.class);
    }

    private EditPatientService getEditPatientService() {
        return applicationContext.getBean(EditPatientService.class);
    }

    private FindPatientByIdService getFindPatientByIdService() {
        return applicationContext.getBean(FindPatientByIdService.class);
    }

    private SearchPatientsService getSearchPatientsService() {
        return applicationContext.getBean(SearchPatientsService.class);
    }

    private ShowAllPatientsService getShowAllPatientsService() {
        return applicationContext.getBean(ShowAllPatientsService.class);
    }
}