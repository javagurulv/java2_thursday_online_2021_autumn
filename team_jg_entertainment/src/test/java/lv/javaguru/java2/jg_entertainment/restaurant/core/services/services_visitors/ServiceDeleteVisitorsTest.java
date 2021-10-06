package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.RequestDeleteVisitor;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.ResponseDeleteVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validatorsVisitors.ValidatorDeleteVisitor;
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
public class ServiceDeleteVisitorsTest {

    @Mock
    private DatabaseVisitors database;
    @Mock
    private ValidatorDeleteVisitor validator;
    @InjectMocks
    private ServiceDeleteVisitors service;

    @Test
    public void shouldReturnErrorWhenBookIdNotProvided() {
        RequestDeleteVisitor request = new RequestDeleteVisitor(null, "name");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("id visitor", "Can't be null"));
        Mockito.when(validator.coreErrors(request)).thenReturn(errors);
        ResponseDeleteVisitors response = service.execute(request);
        assertTrue(response.hasError());
        assertEquals(response.getErrorsList().size(), 1);
        assertEquals(response.getErrorsList().get(0).getField(), "id visitor");
        assertEquals(response.getErrorsList().get(0).getMessageError(), "Can't be null");
    }

    @Test
    public void shouldReturnErrorWhenBookNameNotProvided() {
        RequestDeleteVisitor request = new RequestDeleteVisitor(1L, "");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("name visitor", "Can't be empty"));
        Mockito.when(validator.coreErrors(request)).thenReturn(errors);
        ResponseDeleteVisitors response = service.execute(request);
        assertTrue(response.hasError());
        assertEquals(response.getErrorsList().size(), 1);
        assertEquals(response.getErrorsList().get(0).getField(), "name visitor");
        assertEquals(response.getErrorsList().get(0).getMessageError(), "Can't be empty");
    }

    @Test
    public void shouldDeleteBookWithIdFromDatabase() {
        Mockito.when(validator.coreErrors(any())).thenReturn(new ArrayList<>());
        Mockito.when(database.deleteClientWithIDAndName(1L, "name")).thenReturn(true);
        RequestDeleteVisitor request = new RequestDeleteVisitor(1L, "name");
        ResponseDeleteVisitors response = service.execute(request);
        assertFalse(response.hasError());
        assertTrue(response.ifIdVisitorDelete());
    }
}