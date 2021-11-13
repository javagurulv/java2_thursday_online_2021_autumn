package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_tables;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.TableRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.RemoveTableRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.RemoveTableResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators.RemoveTableValidator;
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
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RemoveTableServiceTest {

    @Mock
    private TableRepository database;
    @Mock
    private RemoveTableValidator validator;
    @InjectMocks
    private RemoveTableService service;

    @Test
    public void shouldReturnErrorWhenTableIdNotProvided() {
        RemoveTableRequest request = new RemoveTableRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("idTable", "Can't be null"));
        when(validator.coreErrors(request)).thenReturn(errors);
        RemoveTableResponse response = service.execute(request);
        assertTrue(response.hasError());
        assertEquals(response.getErrorsList().size(), 1);
        assertEquals(response.getErrorsList().get(0).getField(), "idTable");
        assertEquals(response.getErrorsList().get(0).getMessageError(), "Can't be null");
    }

    @Test
    public void shouldDeleteTableWithIdFromDatabase() {
        Mockito.when(validator.coreErrors(any())).thenReturn(new ArrayList<>());
        Mockito.when(database.deleteById(1L)).thenReturn(true);
        RemoveTableRequest request = new RemoveTableRequest(1L);
        RemoveTableResponse response = service.execute(request);
        assertFalse(response.hasError());
        assertTrue(response.isTableRemoved());
    }
}