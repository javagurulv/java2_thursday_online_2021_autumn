package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.AddVisitorRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.ResponseAddVisitor;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validatorsVisitors.ValidatorAddVisitor;
import lv.javaguru.java2.jg_entertainment.restaurant.matchers_visitors.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ServiceAddAllVisitorsTest {

    @Mock
    private DatabaseVisitors databaseVisitors;
    @Mock
    private ValidatorAddVisitor validator;
    @InjectMocks
    private ServiceAddAllVisitors service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {
        AddVisitorRequest notValidRequest = new AddVisitorRequest(null, "surname", 252525L);
        when(validator.coreErrors(notValidRequest)).thenReturn(List.of(new CoreError("name", "Shouldn't be empty")));
        ResponseAddVisitor response = service.execute(notValidRequest);
        assertTrue(response.hasError());
    }

    @Test
    public void shouldReturnResponseWithErrorsReceivedFromValidator() {
        AddVisitorRequest notValidRequest = new AddVisitorRequest(null, "surname", 252525L);
        when(validator.coreErrors(notValidRequest)).thenReturn(List.of(new CoreError("name", "Shouldn't be empty")));
        ResponseAddVisitor responseAddVisitor = service.execute(notValidRequest);
        assertEquals(responseAddVisitor.getErrorsList().size(), 1);
        assertEquals(responseAddVisitor.getErrorsList().get(0).getField(), "name");
        assertEquals(responseAddVisitor.getErrorsList().get(0).getMessageError(), "Shouldn't be empty");
    }

    @Test
    public void shouldNotInvokeDatabaseWhenRequestValidationFails() {
        AddVisitorRequest notValidRequest = new AddVisitorRequest(null, "surname", 252525L);
        when(validator.coreErrors(notValidRequest)).thenReturn(List.of(new CoreError("name", "Shouldn't be empty")));
        service.execute(notValidRequest);
        verifyNoInteractions(databaseVisitors);
    }

    @Test
    public void shouldAddVisitorToDatabaseWhenRequestIsValid() {
        AddVisitorRequest validRequest = new AddVisitorRequest("name", "surname", 252525L);
        when(validator.coreErrors(validRequest)).thenReturn(List.of());
        service.execute(validRequest);
        verify(databaseVisitors).saveClientToRestaurantList(argThat(new Matchers("name", "surname", 252525L)));
    }

    @Test
    public void shouldReturnResponseWithoutErrorsWhenRequestIsValid() {
        AddVisitorRequest validRequest = new AddVisitorRequest("name", "surname", 252525L);
        when((validator.coreErrors(validRequest))).thenReturn(List.of());
        ResponseAddVisitor response = service.execute(validRequest);
        assertFalse(response.hasError());
    }

    @Test
    public void shouldReturnResponseWithVisitorWhenRequestIsValid() {
        AddVisitorRequest validRequest = new AddVisitorRequest("name", "surname", 252525L);
        when(validator.coreErrors(validRequest)).thenReturn(List.of());
        ResponseAddVisitor response = service.execute(validRequest);
        assertNotNull(response.getNewVisitor());
        assertEquals(response.getNewVisitor().getClientName(), validRequest.getName());
        assertEquals(response.getNewVisitor().getSurname(), validRequest.getSurname());
    }
}