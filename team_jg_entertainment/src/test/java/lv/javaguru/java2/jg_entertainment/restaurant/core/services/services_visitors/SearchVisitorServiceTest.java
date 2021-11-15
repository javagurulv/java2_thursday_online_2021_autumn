package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.user_repository.VisitorsRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.Ordering;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.Paging;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.SearchVisitorsRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.SearchVisitorsResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_visitors.SearchVisitorsRequestValidator;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Visitor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class SearchVisitorServiceTest {

    @Mock private VisitorsRepository database;
    @Mock private SearchVisitorsRequestValidator validator;
    @InjectMocks private SearchVisitorsService serviceSearch;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidatorFails() {
        SearchVisitorsRequest request = new SearchVisitorsRequest(null, null);
        List<CoreError> errorList = new ArrayList<>();
        errorList.add(new CoreError("name", "can not be empty!"));
        errorList.add(new CoreError("surname", "can not be empty!"));
        Mockito.when(validator.validator(request)).thenReturn(errorList);
        SearchVisitorsResponse searchVisitorsResponse = serviceSearch.execute(request);
        assertTrue(searchVisitorsResponse.hasError());
        assertEquals(searchVisitorsResponse.getErrorsList().size(), 2);
        Mockito.verify(validator).validator(request);
        Mockito.verify(validator).validator(any());
        Mockito.verifyNoInteractions(database);
    }

    @Test
    public void shouldSearchByName() {
        SearchVisitorsRequest request = new SearchVisitorsRequest("name", null);
        Mockito.when(validator.validator(request)).thenReturn(new ArrayList<>());
        List<Visitor> visitors = new ArrayList<>();
        visitors.add(new Visitor("name", "surname"));
        Mockito.when(database.findByNameVisitor("name")).thenReturn(visitors);

        SearchVisitorsResponse searchVisitorsResponse = serviceSearch.execute(request);
        assertFalse(searchVisitorsResponse.hasError());
        assertEquals(searchVisitorsResponse.getVisitors().size(), 1);
        assertEquals(searchVisitorsResponse.getVisitors().get(0).getClientName(), "name");
        assertEquals(searchVisitorsResponse.getVisitors().get(0).getSurname(), "surname");
    }

    @Test
    public void shouldSearchBySurname() {
        SearchVisitorsRequest request = new SearchVisitorsRequest(null, "surname");
        Mockito.when(validator.validator(request)).thenReturn(new ArrayList<>());
        List<Visitor> visitors = new ArrayList<>();
        visitors.add(new Visitor("name", "surname"));
        Mockito.when(database.findBySurnameVisitor("surname")).thenReturn(visitors);
        SearchVisitorsResponse searchVisitorsResponse = serviceSearch.execute(request);
        assertFalse(searchVisitorsResponse.hasError());
        assertEquals(searchVisitorsResponse.getVisitors().size(), 1);
        assertEquals(searchVisitorsResponse.getVisitors().get(0).getClientName(), "name");
        assertEquals(searchVisitorsResponse.getVisitors().get(0).getSurname(), "surname");
    }

    @Test
    public void shouldSearchByNameAndSurname() {
        SearchVisitorsRequest request = new SearchVisitorsRequest("name", "surname");
        Mockito.when(validator.validator(request)).thenReturn(new ArrayList<>());
        List<Visitor> visitors = new ArrayList<>();
        visitors.add(new Visitor("name", "surname"));
        Mockito.when(database.findByNameAndSurname("name", "surname")).thenReturn(visitors);
        SearchVisitorsResponse searchVisitorsResponse = serviceSearch.execute(request);
        assertFalse(searchVisitorsResponse.hasError());
        assertEquals(searchVisitorsResponse.getVisitors().size(), 1);
        assertEquals(searchVisitorsResponse.getVisitors().get(0).getClientName(), "name");
        assertEquals(searchVisitorsResponse.getVisitors().get(0).getSurname(), "surname");
    }

    @Test
    public void shouldSearchByNameWithOrderingAscending() {
        Ordering ordering = new Ordering("surname", "ASCENDING");
        SearchVisitorsRequest request = new SearchVisitorsRequest("name", null, ordering);
        Mockito.when(validator.validator(request)).thenReturn(new ArrayList<>());
        List<Visitor> visitors = new ArrayList<>();
        visitors.add(new Visitor("name", "surname1"));
        visitors.add(new Visitor("name", "surname2"));
        Mockito.when(database.findByNameVisitor("name")).thenReturn(visitors);

        SearchVisitorsResponse searchVisitorsResponse = serviceSearch.execute(request);
        assertFalse(searchVisitorsResponse.hasError());
        assertEquals(searchVisitorsResponse.getVisitors().size(), 2);
        assertEquals(searchVisitorsResponse.getVisitors().get(0).getSurname(), "surname1");
        assertEquals(searchVisitorsResponse.getVisitors().get(1).getSurname(), "surname2");
    }

    @Test
    public void shouldSearchByNameWithOrderingDescending() {
        Ordering ordering = new Ordering("surname", "DESCENDING");
        SearchVisitorsRequest request = new SearchVisitorsRequest("name", null, ordering);
        Mockito.when(validator.validator(request)).thenReturn(new ArrayList<>());
        List<Visitor> visitors = new ArrayList<>();
        visitors.add(new Visitor("name", "surname1"));
        visitors.add(new Visitor("name", "surname2"));
        Mockito.when(database.findByNameVisitor("name")).thenReturn(visitors);

        SearchVisitorsResponse searchVisitorsResponse = serviceSearch.execute(request);
        assertFalse(searchVisitorsResponse.hasError());
        assertEquals(searchVisitorsResponse.getVisitors().size(), 2);
        assertEquals(searchVisitorsResponse.getVisitors().get(0).getSurname(), "surname2");
        assertEquals(searchVisitorsResponse.getVisitors().get(1).getSurname(), "surname1");
    }

    @Test
    public void shouldSearchByNameWithPagingFirstPage() {
        Paging paging = new Paging(1, 1);
        SearchVisitorsRequest request = new SearchVisitorsRequest("name", null, null, paging);
        Mockito.when(validator.validator(request)).thenReturn(new ArrayList<>());
        List<Visitor> visitors = new ArrayList<>();
        visitors.add(new Visitor("name", "surname1"));
        visitors.add(new Visitor("name", "surname2"));
        Mockito.when(database.findByNameVisitor("name")).thenReturn(visitors);

        SearchVisitorsResponse searchVisitorsResponse = serviceSearch.execute(request);
        assertFalse(searchVisitorsResponse.hasError());
        assertEquals(searchVisitorsResponse.getVisitors().size(), 1);
        assertEquals(searchVisitorsResponse.getVisitors().get(0).getClientName(), "name");
        assertEquals(searchVisitorsResponse.getVisitors().get(0).getSurname(), "surname1");
    }

    @Test
    public void shouldSearchByNameWithPagingSecondPage() {
        Paging paging = new Paging(2, 1);
        SearchVisitorsRequest request = new SearchVisitorsRequest("name", null, null, paging);
        Mockito.when(validator.validator(request)).thenReturn(new ArrayList<>());
        List<Visitor> visitors = new ArrayList<>();
        visitors.add(new Visitor("name", "surname1"));
        visitors.add(new Visitor("name", "surname2"));
        Mockito.when(database.findByNameVisitor("name")).thenReturn(visitors);

        SearchVisitorsResponse searchVisitorsResponse = serviceSearch.execute(request);
        assertFalse(searchVisitorsResponse.hasError());
        assertEquals(searchVisitorsResponse.getVisitors().size(), 1);
        assertEquals(searchVisitorsResponse.getVisitors().get(0).getClientName(), "name");
        assertEquals(searchVisitorsResponse.getVisitors().get(0).getSurname(), "surname2");
    }
}