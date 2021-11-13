package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.TableRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.AddTableRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.AddTableResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators.AddTableValidator;
import lv.javaguru.java2.jg_entertainment.restaurant.matchers_tables.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AddTableServiceTest {

    @Mock private TableRepository database;
    @Mock private AddTableValidator validator;
    @InjectMocks private AddTableService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {
        AddTableRequest notValidRequest = new AddTableRequest(null, 1, 0);
        when(validator.validate(notValidRequest)).thenReturn(List.of(new CoreError("table title", "Shouldn't be empty")));
        AddTableResponse response = service.execute(notValidRequest);
        assertTrue(response.hasError());
    }

    @Test
    public void shouldReturnResponseWithErrorsReceivedFromValidator() {
        AddTableRequest notValidRequest = new AddTableRequest(null, 1, 1);
        when(validator.validate(notValidRequest)).thenReturn(List.of(new CoreError("table title", "Shouldn't be empty")));
        AddTableResponse response = service.execute(notValidRequest);
        assertEquals(response.getErrorsList().size(), 1);
        assertEquals(response.getErrorsList().get(0).getField(), "table title");
        assertEquals(response.getErrorsList().get(0).getMessageError(), "Shouldn't be empty");
    }

    @Test
    public void shouldNotInvokeDatabaseWhenRequestValidationFails() {
        AddTableRequest notValidRequest = new AddTableRequest(null, 1, 0);
        when(validator.validate(notValidRequest)).thenReturn(List.of(new CoreError("table title", "Shouldn't be empty")));
        service.execute(notValidRequest);
        verifyNoInteractions(database);
    }

    @Test
    public void shouldAddTableToDatabaseWhenRequestIsValid() {
        AddTableRequest validRequest = new AddTableRequest("title", 1, 1);
        when(validator.validate(validRequest)).thenReturn(List.of());
        service.execute(validRequest);
        verify(database).save(argThat(new Matchers("title", 1, 1)));
    }

    @Test
    public void shouldReturnResponseWithoutErrorsWhenRequestIsValid() {
        AddTableRequest validRequest = new AddTableRequest("title", 1, 0);
        when(validator.validate(validRequest)).thenReturn(List.of());
        AddTableResponse response = service.execute(validRequest);
        assertFalse(response.hasError());
    }

    @Test
    public void shouldReturnResponseWithTableWhenRequestIsValid() {
        AddTableRequest validRequest = new AddTableRequest("title", 1, 1);
        when(validator.validate(validRequest)).thenReturn(List.of());
        AddTableResponse response = service.execute(validRequest);
        assertNotNull(response.getNewTable());
        assertEquals(response.getNewTable().getTitle(), validRequest.getTitle());
        assertEquals(response.getNewTable().getTableCapacity(), validRequest.getTableCapacity());

    }
}