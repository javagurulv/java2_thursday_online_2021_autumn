package lv.javaguru.java2.oddJobs.core.services.find;

import lv.javaguru.java2.oddJobs.database.domainInterfaces.SpecialistRepository;
import lv.javaguru.java2.oddJobs.domain.Specialist;
import lv.javaguru.java2.oddJobs.core.requests.find.FindSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.requests.find.Ordering;
import lv.javaguru.java2.oddJobs.core.requests.find.Paging;
import lv.javaguru.java2.oddJobs.core.responce.CoreError;
import lv.javaguru.java2.oddJobs.core.responce.find.FindSpecialistResponse;
import lv.javaguru.java2.oddJobs.core.validations.find.FindSpecialistValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class FindSpecialistServiceTest {
    @Mock
    private SpecialistRepository specialistRepository;
    @Mock
    private FindSpecialistValidator validator;
    @InjectMocks
    private FindSpecialistService service;

    @Before
    public void setup() {
        ReflectionTestUtils.setField(service, "orderingEnabled", true);
        ReflectionTestUtils.setField(service, "pagingEnabled", true);
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenValidatorFails() {
        FindSpecialistRequest request = new FindSpecialistRequest(null, null, null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Name", "Must not be empty!"));
        errors.add(new CoreError("Surname", "Must not be empty!"));
        errors.add(new CoreError("profession", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        FindSpecialistResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 3);

        Mockito.verify(validator).validate(request);
        Mockito.verify(validator).validate(any());
        Mockito.verifyNoInteractions(specialistRepository);
    }

    @Test
    public void shouldSearchByName() {
        FindSpecialistRequest request = new FindSpecialistRequest("Name", null, null);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Specialist> specialists = new ArrayList<>();
        specialists.add(new Specialist("Name", "Surname", "Profession","personalCode","city"));
        Mockito.when(specialistRepository.findSpecialistByName("Name")).thenReturn(specialists);

        FindSpecialistResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getSpecialists().size(), 1);
        assertEquals(response.getSpecialists().get(0).getSpecialistName(), "Name");
        assertEquals(response.getSpecialists().get(0).getSpecialistSurname(), "Surname");
        assertEquals(response.getSpecialists().get(0).getSpecialistProfession(), "Profession");
    }

    @Test
    public void shouldSearchBySurname() {
        FindSpecialistRequest request = new FindSpecialistRequest(null, "Surname", null);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Specialist> specialists = new ArrayList<>();
        specialists.add(new Specialist("Name", "Surname", "Profession","personalCode","city"));
        Mockito.when(specialistRepository.findSpecialistBySurname("Surname")).thenReturn(specialists);

        FindSpecialistResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getSpecialists().size(), 1);
        assertEquals(response.getSpecialists().get(0).getSpecialistName(), "Name");
        assertEquals(response.getSpecialists().get(0).getSpecialistSurname(), "Surname");
        assertEquals(response.getSpecialists().get(0).getSpecialistProfession(), "Profession");
    }

    @Test
    public void shouldSearchByProfession() {
        FindSpecialistRequest request = new FindSpecialistRequest(null, null, "Profession");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Specialist> specialists = new ArrayList<>();
        specialists.add(new Specialist("Name", "Surname", "Profession","personalCode","city"));
        Mockito.when(specialistRepository.findSpecialistByProfession("Profession")).thenReturn(specialists);

        FindSpecialistResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getSpecialists().size(), 1);
        assertEquals(response.getSpecialists().get(0).getSpecialistName(), "Name");
        assertEquals(response.getSpecialists().get(0).getSpecialistSurname(), "Surname");
        assertEquals(response.getSpecialists().get(0).getSpecialistProfession(), "Profession");
    }

    @Test
    public void shouldSearchByNameAndSurnameAndProfession() {
        FindSpecialistRequest request = new FindSpecialistRequest("Name", "Surname", "Profession");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Specialist> specialists = new ArrayList<>();
        specialists.add(new Specialist("Name", "Surname", "Profession","personalCode","city"));
        Mockito.when(specialistRepository.findSpecialistByNameAndSurnameAndProfession("Name", "Surname", "Profession")).thenReturn(specialists);

        FindSpecialistResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getSpecialists().size(), 1);
        assertEquals(response.getSpecialists().get(0).getSpecialistName(), "Name");
        assertEquals(response.getSpecialists().get(0).getSpecialistSurname(), "Surname");
        assertEquals(response.getSpecialists().get(0).getSpecialistProfession(), "Profession");
    }

    @Test
    public void shouldSearchByNameWithOrderingAscending() {
        Ordering ordering = new Ordering("Surname", "ASCENDING");
        FindSpecialistRequest request = new FindSpecialistRequest("Name", null, null, ordering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Specialist> specialists = new ArrayList<>();
        specialists.add(new Specialist("Name", "Surname2", "Profession","personalCode","city"));
        specialists.add(new Specialist("Name", "Surname1", "Profession","personalCode","city"));
        Mockito.when(specialistRepository.findSpecialistByName("Name")).thenReturn(specialists);

        FindSpecialistResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getSpecialists().size(), 2);
        assertEquals(response.getSpecialists().get(0).getSpecialistSurname(), "Surname1");
        assertEquals(response.getSpecialists().get(1).getSpecialistSurname(), "Surname2");
    }

    @Test
    public void shouldSearchByNameWithOrderingDescending() {
        Ordering ordering = new Ordering("Surname", "DESCENDING");
        FindSpecialistRequest request = new FindSpecialistRequest("Name", null, null, ordering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Specialist> specialists = new ArrayList<>();
        specialists.add(new Specialist("Name", "Surname1", "Profession","personalCode","city"));
        specialists.add(new Specialist("Name", "Surname2", "Profession","personalCode","city"));
        Mockito.when(specialistRepository.findSpecialistByName("Name")).thenReturn(specialists);

        FindSpecialistResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getSpecialists().size(), 2);
        assertEquals(response.getSpecialists().get(0).getSpecialistSurname(), "Surname2");
        assertEquals(response.getSpecialists().get(1).getSpecialistSurname(), "Surname1");
    }

    @Test
    public void shouldSearchByNameWithPagingFirstPage() {
        Paging paging = new Paging(1, 1);
        FindSpecialistRequest request = new FindSpecialistRequest("Name", null, null, paging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Specialist> specialists = new ArrayList<>();
        specialists.add(new Specialist("Name", "Surname1", "Profession","personalCode","city"));
        specialists.add(new Specialist("Name", "Surname2", "Profession","personalCode","city"));
        Mockito.when(specialistRepository.findSpecialistByName("Name")).thenReturn(specialists);

        FindSpecialistResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getSpecialists().size(), 1);
        assertEquals(response.getSpecialists().get(0).getSpecialistName(), "Name");
        assertEquals(response.getSpecialists().get(0).getSpecialistSurname(), "Surname1");
        assertEquals(response.getSpecialists().get(0).getSpecialistProfession(), "Profession");
    }

    @Test
    public void shouldSearchByNameWithPagingSecondPage() {
        Paging paging = new Paging(2, 1);
        FindSpecialistRequest request = new FindSpecialistRequest("Name", null, null, paging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Specialist> specialists = new ArrayList<>();
        specialists.add(new Specialist("Name", "Surname1", "Profession","personalCode","city"));
        specialists.add(new Specialist("Name", "Surname2", "Profession","personalCode","city"));
        Mockito.when(specialistRepository.findSpecialistByName("Name")).thenReturn(specialists);

        FindSpecialistResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getSpecialists().size(), 1);
        assertEquals(response.getSpecialists().get(0).getSpecialistName(), "Name");
        assertEquals(response.getSpecialists().get(0).getSpecialistSurname(), "Surname2");
    }

}