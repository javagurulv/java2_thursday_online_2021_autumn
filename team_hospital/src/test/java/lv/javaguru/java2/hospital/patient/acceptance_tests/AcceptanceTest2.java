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

        SearchPatientsRequest searchPatientsRequest = new SearchPatientsRequest("name2", "surname2", "");
        SearchPatientsResponse searchPatientsResponse = getSearchPatientsService().execute(searchPatientsRequest);

        assertEquals(searchPatientsResponse.getPatientList().size(), 1);
        assertEquals(searchPatientsResponse.getPatientList().get(0).getName(), "name2");
        assertEquals(searchPatientsResponse.getPatientList().get(0).getSurname(), "surname2");
        assertEquals(searchPatientsResponse.getPatientList().get(0).getPersonalCode(), "1235");
    }

    @Test
    public void shouldReturnCorrectPatientsListWithSearchDescending() {
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name4", "surname1", "9876");
        getAddPatienceService().execute(addPatientRequest1);

        AddPatientRequest addPatientRequest2 = new AddPatientRequest("name4", "surname2", "9876");
        getAddPatienceService().execute(addPatientRequest2);

        AddPatientRequest addPatientRequest3 = new AddPatientRequest("name4", "surname3", "9876");
        getAddPatienceService().execute(addPatientRequest3);

        Ordering ordering = new Ordering("surname", "descending");

        SearchPatientsRequest searchPatientsRequest =
                new SearchPatientsRequest("name4", "", "", ordering);
        SearchPatientsResponse searchPatientsResponse = getSearchPatientsService().execute(searchPatientsRequest);

        assertEquals(searchPatientsResponse.getPatientList().size(), 3);
        assertEquals(searchPatientsResponse.getPatientList().get(0).getName(), "name4");
        assertEquals(searchPatientsResponse.getPatientList().get(0).getSurname(), "surname3");
        assertEquals(searchPatientsResponse.getPatientList().get(0).getPersonalCode(), "9876");
        assertEquals(searchPatientsResponse.getPatientList().get(1).getName(), "name4");
        assertEquals(searchPatientsResponse.getPatientList().get(1).getSurname(), "surname2");
        assertEquals(searchPatientsResponse.getPatientList().get(1).getPersonalCode(), "9876");
        assertEquals(searchPatientsResponse.getPatientList().get(2).getName(), "name4");
        assertEquals(searchPatientsResponse.getPatientList().get(2).getSurname(), "surname1");
        assertEquals(searchPatientsResponse.getPatientList().get(2).getPersonalCode(), "9876");
    }

    @Test
    public void shouldReturnCorrectPatientsListWithSearchAscending() {
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name5", "surname1", "4444");
        getAddPatienceService().execute(addPatientRequest1);

        AddPatientRequest addPatientRequest2 = new AddPatientRequest("name5", "surname2", "4445");
        getAddPatienceService().execute(addPatientRequest2);

        AddPatientRequest addPatientRequest3 = new AddPatientRequest("name5", "surname3", "4446");
        getAddPatienceService().execute(addPatientRequest3);

        Ordering ordering = new Ordering("surname", "ascending");

        SearchPatientsRequest searchPatientsRequest =
                new SearchPatientsRequest("name5", "", "", ordering);
        SearchPatientsResponse searchPatientsResponse = getSearchPatientsService().execute(searchPatientsRequest);

        assertEquals(searchPatientsResponse.getPatientList().size(), 3);
        assertEquals(searchPatientsResponse.getPatientList().get(0).getName(), "name5");
        assertEquals(searchPatientsResponse.getPatientList().get(0).getSurname(), "surname1");
        assertEquals(searchPatientsResponse.getPatientList().get(0).getPersonalCode(), "4444");
        assertEquals(searchPatientsResponse.getPatientList().get(1).getName(), "name5");
        assertEquals(searchPatientsResponse.getPatientList().get(1).getSurname(), "surname2");
        assertEquals(searchPatientsResponse.getPatientList().get(1).getPersonalCode(), "4445");
        assertEquals(searchPatientsResponse.getPatientList().get(2).getName(), "name5");
        assertEquals(searchPatientsResponse.getPatientList().get(2).getSurname(), "surname3");
        assertEquals(searchPatientsResponse.getPatientList().get(2).getPersonalCode(), "4446");
    }

    @Test
    public void shouldReturnCorrectPatientsListWithSearchAscendingAndPaging() {
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name6", "surname1", "5554");
        getAddPatienceService().execute(addPatientRequest1);

        AddPatientRequest addPatientRequest2 = new AddPatientRequest("name6", "surname2", "5555");
        getAddPatienceService().execute(addPatientRequest2);

        AddPatientRequest addPatientRequest3 = new AddPatientRequest("name6", "surname3", "5556");
        getAddPatienceService().execute(addPatientRequest3);

        Ordering ordering = new Ordering("surname", "ascending");
        Paging paging = new Paging(1, 1);

        SearchPatientsRequest searchPatientsRequest =
                new SearchPatientsRequest("name6", "", "", ordering, paging);
        SearchPatientsResponse searchPatientsResponse = getSearchPatientsService().execute(searchPatientsRequest);

        assertEquals(searchPatientsResponse.getPatientList().size(), 1);
        assertEquals(searchPatientsResponse.getPatientList().get(0).getName(), "name6");
        assertEquals(searchPatientsResponse.getPatientList().get(0).getSurname(), "surname1");
        assertEquals(searchPatientsResponse.getPatientList().get(0).getPersonalCode(), "5554");
    }

    @Test
    public void shouldReturnCorrectPatientsListWithSearchAscendingAndPaging2() {
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name7", "surname1", "7774");
        getAddPatienceService().execute(addPatientRequest1);

        AddPatientRequest addPatientRequest2 = new AddPatientRequest("name7", "surname2", "7775");
        getAddPatienceService().execute(addPatientRequest2);

        AddPatientRequest addPatientRequest3 = new AddPatientRequest("name7", "surname3", "7776");
        getAddPatienceService().execute(addPatientRequest3);

        Ordering ordering = new Ordering("surname", "ascending");
        Paging paging = new Paging(3, 1);

        SearchPatientsRequest searchPatientsRequest =
                new SearchPatientsRequest("name7", "", "", ordering, paging);
        SearchPatientsResponse searchPatientsResponse = getSearchPatientsService().execute(searchPatientsRequest);

        assertEquals(searchPatientsResponse.getPatientList().size(), 1);
        assertEquals(searchPatientsResponse.getPatientList().get(0).getName(), "name7");
        assertEquals(searchPatientsResponse.getPatientList().get(0).getSurname(), "surname3");
        assertEquals(searchPatientsResponse.getPatientList().get(0).getPersonalCode(), "7776");
    }

    private AddPatientService getAddPatienceService() {
        return applicationContext.getBean(AddPatientService.class);
    }

    private SearchPatientsService getSearchPatientsService() {
        return applicationContext.getBean(SearchPatientsService.class);
    }
}
