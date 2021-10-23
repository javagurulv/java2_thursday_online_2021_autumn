package lv.javaguru.java2.oddJobs.core.services.add;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import lv.javaguru.java2.oddJobs.core.requests.add.AddSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.responce.add.AddSpecialistResponse;
import lv.javaguru.java2.oddJobs.core.responce.CoreError;
import lv.javaguru.java2.oddJobs.core.validations.add.AddSpecialistValidator;
import lv.javaguru.java2.oddJobs.database.Database;

@RunWith(MockitoJUnitRunner.class)
public class AddSpecialistServiceTest {

    @Mock
    private Database database;
    @Mock
    private AddSpecialistValidator validator;
    @InjectMocks
    private AddSpecialistService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {
        AddSpecialistRequest request = new AddSpecialistRequest(null, "Surname", "Profession");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Name", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddSpecialistResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Name");
        assertEquals(response.getErrors().get(0).getMessage(), "Must not be empty!");

        Mockito.verifyNoInteractions(database);
    }

    @Test
    public void shouldAddSpecialistToDatabase() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        AddSpecialistRequest request = new AddSpecialistRequest("Name", "Surname", "Profession");
        AddSpecialistResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        Mockito.verify(database).addSpecialist(
                argThat(new SpecialistMatcher("Name", "Surname", "Profession")));
    }
}