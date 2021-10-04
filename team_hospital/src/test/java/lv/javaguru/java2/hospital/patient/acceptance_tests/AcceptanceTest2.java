package lv.javaguru.java2.hospital.patient.acceptance_tests;

import lv.javaguru.java2.hospital.dependency_injection.ApplicationContext;
import lv.javaguru.java2.hospital.dependency_injection.DIApplicationContextBuilder;
import lv.javaguru.java2.hospital.patient.core.requests.AddPatientRequest;
import lv.javaguru.java2.hospital.patient.core.requests.Ordering;
import lv.javaguru.java2.hospital.patient.core.requests.Paging;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.SearchPatientsResponse;
import lv.javaguru.java2.hospital.patient.core.services.AddPatientService;
import lv.javaguru.java2.hospital.patient.core.services.SearchPatientsService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AcceptanceTest2 {

    private static final ApplicationContext applicationContext =
            new DIApplicationContextBuilder().build("lv.javaguru.java2.hospital");

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
    public void shouldReturnCorrectPatientsListWithSearchDescending() {
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name", "surname1", "1234");
        getAddPatienceService().execute(addPatientRequest1);

        AddPatientRequest addPatientRequest2 = new AddPatientRequest("name", "surname2", "1235");
        getAddPatienceService().execute(addPatientRequest2);

        AddPatientRequest addPatientRequest3 = new AddPatientRequest("name", "surname3", "1236");
        getAddPatienceService().execute(addPatientRequest3);

        Ordering ordering = new Ordering("surname", "descending");

        SearchPatientsRequest searchPatientsRequest =
                new SearchPatientsRequest("name", "", "", ordering);
        SearchPatientsResponse searchPatientsResponse = getSearchPatientsService().execute(searchPatientsRequest);

        assertEquals(searchPatientsResponse.getPatientList().size(), 3);
        assertEquals(searchPatientsResponse.getPatientList().get(0).getName(), "name");
        assertEquals(searchPatientsResponse.getPatientList().get(0).getSurname(), "surname3");
        assertEquals(searchPatientsResponse.getPatientList().get(0).getPersonalCode(), "1236");
        assertEquals(searchPatientsResponse.getPatientList().get(1).getName(), "name");
        assertEquals(searchPatientsResponse.getPatientList().get(1).getSurname(), "surname2");
        assertEquals(searchPatientsResponse.getPatientList().get(1).getPersonalCode(), "1235");
        assertEquals(searchPatientsResponse.getPatientList().get(2).getName(), "name");
        assertEquals(searchPatientsResponse.getPatientList().get(2).getSurname(), "surname1");
        assertEquals(searchPatientsResponse.getPatientList().get(2).getPersonalCode(), "1234");
    }

    @Test
    public void shouldReturnCorrectPatientsListWithSearchAscending() {
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name", "surname1", "1234");
        getAddPatienceService().execute(addPatientRequest1);

        AddPatientRequest addPatientRequest2 = new AddPatientRequest("name", "surname2", "1235");
        getAddPatienceService().execute(addPatientRequest2);

        AddPatientRequest addPatientRequest3 = new AddPatientRequest("name", "surname3", "1236");
        getAddPatienceService().execute(addPatientRequest3);

        Ordering ordering = new Ordering("surname", "ascending");

        SearchPatientsRequest searchPatientsRequest =
                new SearchPatientsRequest("name", "", "", ordering);
        SearchPatientsResponse searchPatientsResponse = getSearchPatientsService().execute(searchPatientsRequest);

        assertEquals(searchPatientsResponse.getPatientList().size(), 3);
        assertEquals(searchPatientsResponse.getPatientList().get(0).getName(), "name");
        assertEquals(searchPatientsResponse.getPatientList().get(0).getSurname(), "surname1");
        assertEquals(searchPatientsResponse.getPatientList().get(0).getPersonalCode(), "1234");
        assertEquals(searchPatientsResponse.getPatientList().get(1).getName(), "name");
        assertEquals(searchPatientsResponse.getPatientList().get(1).getSurname(), "surname2");
        assertEquals(searchPatientsResponse.getPatientList().get(1).getPersonalCode(), "1235");
        assertEquals(searchPatientsResponse.getPatientList().get(2).getName(), "name");
        assertEquals(searchPatientsResponse.getPatientList().get(2).getSurname(), "surname3");
        assertEquals(searchPatientsResponse.getPatientList().get(2).getPersonalCode(), "1236");
    }

    @Test
    public void shouldReturnCorrectPatientsListWithSearchAscendingAndPaging() {
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name", "surname1", "1234");
        getAddPatienceService().execute(addPatientRequest1);

        AddPatientRequest addPatientRequest2 = new AddPatientRequest("name", "surname2", "1235");
        getAddPatienceService().execute(addPatientRequest2);

        AddPatientRequest addPatientRequest3 = new AddPatientRequest("name", "surname3", "1236");
        getAddPatienceService().execute(addPatientRequest3);

        Ordering ordering = new Ordering("surname", "ascending");
        Paging paging = new Paging(1, 1);

        SearchPatientsRequest searchPatientsRequest =
                new SearchPatientsRequest("name", "", "", ordering, paging);
        SearchPatientsResponse searchPatientsResponse = getSearchPatientsService().execute(searchPatientsRequest);

        assertEquals(searchPatientsResponse.getPatientList().size(), 1);
        assertEquals(searchPatientsResponse.getPatientList().get(0).getName(), "name");
        assertEquals(searchPatientsResponse.getPatientList().get(0).getSurname(), "surname1");
        assertEquals(searchPatientsResponse.getPatientList().get(0).getPersonalCode(), "1234");
    }

    @Test
    public void shouldReturnCorrectPatientsListWithSearchAscendingAndPaging2() {
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name", "surname1", "1234");
        getAddPatienceService().execute(addPatientRequest1);

        AddPatientRequest addPatientRequest2 = new AddPatientRequest("name", "surname2", "1235");
        getAddPatienceService().execute(addPatientRequest2);

        AddPatientRequest addPatientRequest3 = new AddPatientRequest("name", "surname3", "1236");
        getAddPatienceService().execute(addPatientRequest3);

        Ordering ordering = new Ordering("surname", "ascending");
        Paging paging = new Paging(3, 1);

        SearchPatientsRequest searchPatientsRequest =
                new SearchPatientsRequest("name", "", "", ordering, paging);
        SearchPatientsResponse searchPatientsResponse = getSearchPatientsService().execute(searchPatientsRequest);

        assertEquals(searchPatientsResponse.getPatientList().size(), 1);
        assertEquals(searchPatientsResponse.getPatientList().get(0).getName(), "name");
        assertEquals(searchPatientsResponse.getPatientList().get(0).getSurname(), "surname3");
        assertEquals(searchPatientsResponse.getPatientList().get(0).getPersonalCode(), "1236");
    }

    private AddPatientService getAddPatienceService() {
        return applicationContext.getBean(AddPatientService.class);
    }

    private SearchPatientsService getSearchPatientsService() {
        return applicationContext.getBean(SearchPatientsService.class);
    }
}
