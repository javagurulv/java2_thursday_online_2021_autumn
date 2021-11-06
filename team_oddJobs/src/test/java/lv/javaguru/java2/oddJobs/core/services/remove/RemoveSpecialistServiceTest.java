package lv.javaguru.java2.oddJobs.core.services.remove;

import lv.javaguru.java2.oddJobs.core.requests.remove.RemoveSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.responce.CoreError;
import lv.javaguru.java2.oddJobs.core.responce.remove.RemoveSpecialistResponse;
import lv.javaguru.java2.oddJobs.core.validations.remove.RemoveSpecialistValidator;
import lv.javaguru.java2.oddJobs.database.Database;
import lv.javaguru.java2.oddJobs.database.SpecialistRepository;
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
public class RemoveSpecialistServiceTest {
    @Mock
    private SpecialistRepository specialistRepository;
    @Mock
    private RemoveSpecialistValidator validator;
    @InjectMocks
    private RemoveSpecialistService service;

    @Test
    public void shouldReturnErrorWhenSpecialistIdNotProvided() {
        RemoveSpecialistRequest request = new RemoveSpecialistRequest(null, "Name", "Surname");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("specialistId", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        RemoveSpecialistResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "specialistId");
        assertEquals(response.getErrors().get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldDeleteSpecialistWithIdFromDatabase() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(specialistRepository.removeSpecialist(1L, "Name", "Surname")).thenReturn(true);
        RemoveSpecialistRequest request = new RemoveSpecialistRequest(1L, "Name", "Surname");
        RemoveSpecialistResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isSpecialistRemoved());
    }
}