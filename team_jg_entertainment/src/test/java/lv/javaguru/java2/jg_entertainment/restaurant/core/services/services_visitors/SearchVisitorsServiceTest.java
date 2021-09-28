package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Visitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.ImplDatabaseVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.Ordering;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.Paging;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.SearchVisitorsRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.ResponseSearchVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validatorsVisitors.SearchVisitorsRequestValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
class SearchVisitorsServiceTest {

    @Mock
    private ImplDatabaseVisitors database;
    @Mock
    private SearchVisitorsRequestValidator validator;
    @InjectMocks
    private ServiceSearchVisitors service;

    @BeforeEach
    public void before() {
        database = Mockito.mock(ImplDatabaseVisitors.class);
        validator = Mockito.mock(SearchVisitorsRequestValidator.class);
        service = new ServiceSearchVisitors(database, validator);
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenValidatorFails() {
        SearchVisitorsRequest requestValidator = new SearchVisitorsRequest(null, null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("name", "can not be empty!"));
        errors.add(new CoreError("surname", "can not be empty!"));
        Mockito.when(validator.validator(requestValidator)).thenReturn(errors);

        ResponseSearchVisitors response = service.execute(requestValidator);
        assertTrue(response.hasError());
        assertEquals(response.getErrorsList().size(), 2);

        Mockito.verify(validator).validator(requestValidator);
        Mockito.verify(validator).validator(any());
        Mockito.verifyNoInteractions(database);
    }

    @Test
    public void shouldSearchByNameVisitor() {
        SearchVisitorsRequest request = new SearchVisitorsRequest("name", null);
        Mockito.when(validator.validator(request)).thenReturn(new ArrayList<>());

        List<Visitors> visitors = new ArrayList<>();
        visitors.add(new Visitors("name", "surname"));
        Mockito.when(database.findByNameVisitor("name")).thenReturn(visitors);

        ResponseSearchVisitors response = service.execute(request);
        assertFalse(response.hasError());
        assertEquals(response.getVisitors().size(), 1);
        assertEquals(response.getVisitors().get(0).getClientName(), "name");
        assertEquals(response.getVisitors().get(0).getSurname(), "surname");
    }

    @Test
    public void shouldSearchBySurnameVisitors() {
        SearchVisitorsRequest request = new SearchVisitorsRequest(null, "surname");
        Mockito.when(validator.validator(request)).thenReturn(new ArrayList<>());

        List<Visitors> visitors = new ArrayList<>();
        visitors.add(new Visitors("name", "surname"));
        Mockito.when(database.findBySurnameVisitor("surname")).thenReturn(visitors);

        ResponseSearchVisitors response = service.execute(request);
        assertFalse(response.hasError());
        assertEquals(response.getVisitors().size(), 1);
        assertEquals(response.getVisitors().get(0).getClientName(), "name");
        assertEquals(response.getVisitors().get(0).getSurname(), "surname");
    }

    @Test
    public void shouldSearchByNameAndSurnameVisitors() {
        SearchVisitorsRequest request = new SearchVisitorsRequest("name", "surname");
        Mockito.when(validator.validator(request)).thenReturn(new ArrayList<>());

        List<Visitors> visitors = new ArrayList<>();
        visitors.add(new Visitors("name", "surname"));
        Mockito.when(database.findByNameAndSurname("name", "surname")).thenReturn(visitors);

        ResponseSearchVisitors response = service.execute(request);
        assertFalse(response.hasError());
        assertEquals(response.getVisitors().size(), 1);
        assertEquals(response.getVisitors().get(0).getClientName(), "name");
        assertEquals(response.getVisitors().get(0).getSurname(), "surname");
    }

    @Test
    public void shouldSearchByNameWithOrderingAscending() {
        Ordering ordering = new Ordering("surname", "ASCENDING");
        SearchVisitorsRequest request = new SearchVisitorsRequest("name", null, ordering);
        Mockito.when(validator.validator(request)).thenReturn(new ArrayList<>());

        List<Visitors> visitors = new ArrayList<>();
        visitors.add(new Visitors("name", "surname2"));
        visitors.add(new Visitors("name", "surname1"));
        Mockito.when(database.findByNameVisitor("name")).thenReturn(visitors);

        ResponseSearchVisitors response = service.execute(request);
        assertFalse(response.hasError());
        assertEquals(response.getVisitors().size(), 2);
        assertEquals(response.getVisitors().get(0).getSurname(), "surname1");
        assertEquals(response.getVisitors().get(1).getSurname(), "surname2");
    }

    @Test
    public void shouldSearchBySurnameWithOrderingAscending() {
        Ordering ordering = new Ordering("name", "Ascending");
        SearchVisitorsRequest request = new SearchVisitorsRequest(null, "surname", ordering);
        Mockito.when(validator.validator(request)).thenReturn(new ArrayList<>());

        List<Visitors> visitors = new ArrayList<>();
        visitors.add(new Visitors("name2", "surname"));
        visitors.add(new Visitors("name1", "surname"));
        Mockito.when(database.findBySurnameVisitor("surname")).thenReturn(visitors);

        ResponseSearchVisitors response = service.execute(request);
        assertFalse(response.hasError());
        assertEquals(response.getVisitors().size(), 2);
        assertEquals(response.getVisitors().get(0).getClientName(), "name1");
        assertEquals(response.getVisitors().get(1).getClientName(), "name2");
    }

    @Test
    public void shouldSearchByNameWithOrderingDescending() {
        Ordering ordering = new Ordering("surname", "DESCENDING");
        SearchVisitorsRequest request = new SearchVisitorsRequest("name", null, ordering);
        Mockito.when(validator.validator(request)).thenReturn(new ArrayList<>());

        List<Visitors> visitors = new ArrayList<>();
        visitors.add(new Visitors("name", "surname1"));
        visitors.add(new Visitors("name", "surname2"));
        Mockito.when(database.findByNameVisitor("name")).thenReturn(visitors);

        ResponseSearchVisitors response = service.execute(request);
        assertFalse(response.hasError());
        assertEquals(response.getVisitors().size(), 2);
        assertEquals(response.getVisitors().get(0).getSurname(), "surname2");
        assertEquals(response.getVisitors().get(1).getSurname(), "surname1");
    }

    @Test
    public void shouldSearchBySurnameWithOrderingDescending() {
        Ordering ordering = new Ordering("name", "DESCENDING");
        SearchVisitorsRequest request = new SearchVisitorsRequest(null, "surname", ordering);
        Mockito.when(validator.validator(request)).thenReturn(new ArrayList<>());

        List<Visitors> visitors = new ArrayList<>();
        visitors.add(new Visitors("name1", "surname"));
        visitors.add(new Visitors("name2", "surname"));
        Mockito.when(database.findBySurnameVisitor("surname")).thenReturn(visitors);

        ResponseSearchVisitors response = service.execute(request);
        assertFalse(response.hasError());
        assertEquals(response.getVisitors().size(), 2);
        assertEquals(response.getVisitors().get(0).getClientName(), "name2");
        assertEquals(response.getVisitors().get(1).getClientName(), "name1");
    }

    @Test
    public void shouldSearchByTitleWithPagingFirstPage() {
        Paging paging = new Paging(1, 1);
        SearchVisitorsRequest request = new SearchVisitorsRequest("name", null, paging);
        Mockito.when(validator.validator(request)).thenReturn(new ArrayList<>());

        List<Visitors> visitors = new ArrayList<>();
        visitors.add(new Visitors("name1", "surname1"));
        visitors.add(new Visitors("name2", "surname2"));
        visitors.add(new Visitors("name3", "surname3"));
        Mockito.when(database.findByNameVisitor("name")).thenReturn(visitors);

        ResponseSearchVisitors response = service.execute(request);
        assertFalse(response.hasError());
        assertEquals(response.getVisitors().size(), 1);
        assertEquals(response.getVisitors().get(0).getClientName(), "name1");
        assertEquals(response.getVisitors().get(0).getSurname(), "surname1");
    }
    @Test
    public void shouldSearchByTitleWithPagingSecondPage() {
        Paging paging = new Paging(2, 1);
        SearchVisitorsRequest request = new SearchVisitorsRequest("name", null, paging);
        Mockito.when(validator.validator(request)).thenReturn(new ArrayList<>());

        List<Visitors> visitors = new ArrayList<>();
        visitors.add(new Visitors("name1", "surname1"));
        visitors.add(new Visitors("name2", "surname2"));
        visitors.add(new Visitors("name3", "surname3"));
        Mockito.when(database.findByNameVisitor("name")).thenReturn(visitors);

        ResponseSearchVisitors response = service.execute(request);
        assertFalse(response.hasError());
        assertEquals(response.getVisitors().size(), 1);
        assertEquals(response.getVisitors().get(0).getClientName(), "name2");
        assertEquals(response.getVisitors().get(0).getSurname(), "surname2");
    }

}