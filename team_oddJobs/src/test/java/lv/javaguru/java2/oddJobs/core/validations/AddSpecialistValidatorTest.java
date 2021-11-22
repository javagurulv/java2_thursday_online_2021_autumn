package lv.javaguru.java2.oddJobs.core.validations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import lv.javaguru.java2.oddJobs.DatabaseCleaner;
import lv.javaguru.java2.oddJobs.config.ApplicationConfiguration;
import lv.javaguru.java2.oddJobs.core.requests.add.AddSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.responce.CoreError;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lv.javaguru.java2.oddJobs.core.responce.add.AddSpecialistResponse;
import lv.javaguru.java2.oddJobs.core.services.add.AddSpecialistService;
import lv.javaguru.java2.oddJobs.core.validations.add.AddSpecialistValidator;
import lv.javaguru.java2.oddJobs.core.validations.find.FindSpecialistsFieldValidator;
import lv.javaguru.java2.oddJobs.database.domainInterfaces.SpecialistRepository;
import lv.javaguru.java2.oddJobs.domain.Specialist;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(MockitoJUnitRunner.class)
public class AddSpecialistValidatorTest {
    @Mock private SpecialistRepository specialistRepository;

    @Mock
    private AddSpecialistValidator validator;
    @InjectMocks
    private AddSpecialistService addSpecialistService;


    @Test
    public void shouldReturnErrorWhenNameIsNull() {
        AddSpecialistRequest request = new AddSpecialistRequest(null, "Surname", "Profession", "personalCode", "city");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Name", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        AddSpecialistResponse response = addSpecialistService.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Name");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
        Mockito.reset(validator);
    }


    @Test
    public void shouldReturnErrorWhenSurnameIsNull() {
        AddSpecialistRequest request = new AddSpecialistRequest("Name", null, "Profession", "personalCode1", "city");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Surname", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        AddSpecialistResponse response = addSpecialistService.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Surname");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
        Mockito.verifyNoInteractions(specialistRepository);
        Mockito.reset(validator);
    }

    @Test
    public void shouldReturnErrorWhenProfessionIsNull() {
        AddSpecialistRequest request = new AddSpecialistRequest("Name", "Surname", null, "personalCode2", "city");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Profession", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        AddSpecialistResponse response = addSpecialistService.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Profession");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
        Mockito.verifyNoInteractions(specialistRepository);
        Mockito.reset(validator);
    }

    @Test
    public void shouldReturnErrorsWhenNameAndSurnameAndProfessionIsNull() {
        AddSpecialistRequest request = new AddSpecialistRequest(null, null, null, "personalCode3", "city");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Name", "Must not be empty!"));
        errors.add(new CoreError("Surname", "Must not be empty!"));
        errors.add(new CoreError("Profession", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        assertEquals(errors.size(), 3);
        Mockito.verifyNoInteractions(specialistRepository);
        Mockito.reset(validator);
    }


    @Test
    public void shouldSuccess() {
        AddSpecialistRequest request = new AddSpecialistRequest("Name", "Surname", "Profession", "personalCode4", "city");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
        Mockito.verifyNoInteractions(specialistRepository);
        Mockito.reset(validator);
    }
}