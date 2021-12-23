package lv.javaguru.java2.oddJobs.core.validations;

import lv.javaguru.java2.oddJobs.core.database.domainInterfaces.SpecialistRepository;
import lv.javaguru.java2.oddJobs.core.requests.remove.RemoveSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import lv.javaguru.java2.oddJobs.core.response.add.AddSpecialistResponse;
import lv.javaguru.java2.oddJobs.core.response.remove.RemoveSpecialistResponse;
import lv.javaguru.java2.oddJobs.core.services.add.AddSpecialistService;
import lv.javaguru.java2.oddJobs.core.services.remove.RemoveSpecialistService;
import lv.javaguru.java2.oddJobs.core.validations.remove.RemoveSpecialistValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RemoveSpecialistValidatorTest {

    @Mock
    private RemoveSpecialistValidator validator;
    @InjectMocks
    private RemoveSpecialistService removeSpecialistService;

    @Test
    public void shouldReturnErrorWhenIdIsNull() {
        RemoveSpecialistRequest request = new RemoveSpecialistRequest(null, "Name", "Surname");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Id", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        RemoveSpecialistResponse response = removeSpecialistService.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Id");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenNameIsNull() {
        RemoveSpecialistRequest request = new RemoveSpecialistRequest(1L, null, "Surname");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Name", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        RemoveSpecialistResponse response = removeSpecialistService.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Name");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenSurnameIsNull() {
        RemoveSpecialistRequest request = new RemoveSpecialistRequest(1L, "Name", null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Surname", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        RemoveSpecialistResponse response = removeSpecialistService.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Surname");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldSuccess() {
        RemoveSpecialistRequest request = new RemoveSpecialistRequest(1L, "Name", "Surname");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

}