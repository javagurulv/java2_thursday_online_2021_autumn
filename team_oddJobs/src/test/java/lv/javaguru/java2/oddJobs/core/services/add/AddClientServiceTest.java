package lv.javaguru.java2.oddJobs.core.services.add;

import lv.javaguru.java2.oddJobs.core.requests.add.AddClientRequest;
import lv.javaguru.java2.oddJobs.core.responce.CoreError;
import lv.javaguru.java2.oddJobs.core.responce.add.AddClientResponse;
import lv.javaguru.java2.oddJobs.core.validations.AddClientValidator;
import lv.javaguru.java2.oddJobs.database.Database;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AddClientServiceTest {

    @Mock
    private Database database;
    @Mock
    private AddClientValidator validator;
    @InjectMocks
    private AddClientService addClientService;


    @Test
    public void shouldReturnErrorsWhenNameValidationFails() {

        //given
        AddClientRequest request = new AddClientRequest(null, "Surname");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Name", "Must not be empty!"));
        when(validator.validate(request)).thenReturn(errors);

        //when
        AddClientResponse response = addClientService.execute(request);
        //then
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Name");
        assertEquals(response.getErrors().get(0).getMessage(), "Must not be empty!");

        verifyNoInteractions(database);

    }    @Test
    public void shouldReturnErrorsWhenSurnameValidationFails() {

        //given
        AddClientRequest request = new AddClientRequest("Name", null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Surname", "Must not be empty!"));
        when(validator.validate(request)).thenReturn(errors);

        //when
        AddClientResponse response = addClientService.execute(request);
        //then
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Surname");
        assertEquals(response.getErrors().get(0).getMessage(), "Must not be empty!");

        verifyNoInteractions(database);

    }    public void shouldNotInvokeDatabaseWhenValidationFails() {

        //given
        AddClientRequest request = new AddClientRequest("Name", null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Surname", "Must not be empty!"));
        when(validator.validate(request)).thenReturn(errors);

        //when
        AddClientResponse response = addClientService.execute(request);
        //then
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Surname");
        assertEquals(response.getErrors().get(0).getMessage(), "Must not be empty!");

        verifyNoInteractions(database);

    }



    @Test
    public void shouldAddClientToDatabase() {
        //given
        AddClientRequest request = new AddClientRequest("Name", "Surname");
        List<CoreError> errors = new ArrayList<>();

        when(validator.validate(request)).thenReturn(errors);

        //when
        AddClientResponse response = addClientService.execute(request);

        //then
        assertFalse(response.hasErrors());
        verify(database).addClient(
                argThat(new ClientMatcher("Name", "Surname")));
    }


}