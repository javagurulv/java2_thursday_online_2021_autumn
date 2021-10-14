package lv.javaguru.java2.hospital.patient.core.services;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.PatientOrdering;
import lv.javaguru.java2.hospital.patient.core.requests.PatientPaging;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import lv.javaguru.java2.hospital.patient.core.responses.SearchPatientsResponse;
import lv.javaguru.java2.hospital.patient.core.services.search_patient.SearchPatientsService;
import lv.javaguru.java2.hospital.patient.core.services.validators.SearchPatientsValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class SearchPatientsServiceTest {

    @Mock
    private PatientDatabaseImpl database;
    @Mock
    private SearchPatientsValidator validator;
    @InjectMocks
    private SearchPatientsService service;

    @BeforeEach
    public void setup() {
        ReflectionTestUtils.setField(service, "orderingEnabled", true);
        ReflectionTestUtils.setField(service, "pagingEnabled", true);
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenValidatorFails() {
        SearchPatientsRequest request = new SearchPatientsRequest(null, null, null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Name", "must not be empty!"));
        errors.add(new CoreError("Surname", "must not be empty!"));
        errors.add(new CoreError("Personal code", "must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        SearchPatientsResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 3);

        Mockito.verify(validator).validate(request);
        Mockito.verify(validator).validate(any());
        Mockito.verifyNoInteractions(database);
    }

    @Test
    public void shouldSearchByName() {
        SearchPatientsRequest request = new SearchPatientsRequest("name", null, null);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("name", "surname", "1234"));
        Mockito.when(database.findPatientsByName("name")).thenReturn(patients);

        SearchPatientsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getPatientList().size(), 1);
        assertEquals(response.getPatientList().get(0).getName(), "name");
        assertEquals(response.getPatientList().get(0).getSurname(), "surname");
        assertEquals(response.getPatientList().get(0).getPersonalCode(), "1234");
    }

    @Test
    public void shouldSearchBySurname() {
        SearchPatientsRequest request = new SearchPatientsRequest(null, "surname", null);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("name", "surname", "1234"));
        Mockito.when(database.findPatientsBySurname("surname")).thenReturn(patients);

        SearchPatientsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getPatientList().size(), 1);
        assertEquals(response.getPatientList().get(0).getName(), "name");
        assertEquals(response.getPatientList().get(0).getSurname(), "surname");
        assertEquals(response.getPatientList().get(0).getPersonalCode(), "1234");
    }

    @Test
    public void shouldSearchByPersonalCode() {
        SearchPatientsRequest request = new SearchPatientsRequest(null, null, "1234");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("name", "surname", "1234"));
        Mockito.when(database.findPatientsByPersonalCode("1234")).thenReturn(patients);

        SearchPatientsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getPatientList().size(), 1);
        assertEquals(response.getPatientList().get(0).getName(), "name");
        assertEquals(response.getPatientList().get(0).getSurname(), "surname");
        assertEquals(response.getPatientList().get(0).getPersonalCode(), "1234");
    }

    @Test
    public void shouldSearchByNameAndSurname() {
        SearchPatientsRequest request = new SearchPatientsRequest("name", "surname", null);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("name", "surname", "1234"));
        Mockito.when(database.findPatientsByNameAndSurname("name", "surname")).thenReturn(patients);

        SearchPatientsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getPatientList().size(), 1);
        assertEquals(response.getPatientList().get(0).getName(), "name");
        assertEquals(response.getPatientList().get(0).getSurname(), "surname");
        assertEquals(response.getPatientList().get(0).getPersonalCode(), "1234");
    }

    @Test
    public void shouldSearchByNameAndPersonalCode() {
        SearchPatientsRequest request = new SearchPatientsRequest("name", null, "1234");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("name", "surname", "1234"));
        Mockito.when(database.findPatientsByNameAndPersonalCode("name", "1234")).thenReturn(patients);

        SearchPatientsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getPatientList().size(), 1);
        assertEquals(response.getPatientList().get(0).getName(), "name");
        assertEquals(response.getPatientList().get(0).getSurname(), "surname");
        assertEquals(response.getPatientList().get(0).getPersonalCode(), "1234");
    }

    @Test
    public void shouldSearchBySurnameAndPersonalCode() {
        SearchPatientsRequest request = new SearchPatientsRequest(null, "surname", "1234");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("name", "surname", "1234"));
        Mockito.when(database.findPatientsBySurnameAndPersonalCode("surname", "1234")).thenReturn(patients);

        SearchPatientsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getPatientList().size(), 1);
        assertEquals(response.getPatientList().get(0).getName(), "name");
        assertEquals(response.getPatientList().get(0).getSurname(), "surname");
        assertEquals(response.getPatientList().get(0).getPersonalCode(), "1234");
    }

    @Test
    public void shouldSearchByNameWithOrderingAscending() {
        PatientOrdering patientOrdering = new PatientOrdering("surname", "ascending");
        SearchPatientsRequest request = new SearchPatientsRequest("name", null, null, patientOrdering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("name", "surname2", "1235"));
        patients.add(new Patient("name", "surname1", "1234"));
        Mockito.when(database.findPatientsByName("name")).thenReturn(patients);

        SearchPatientsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getPatientList().size(), 2);
        assertEquals(response.getPatientList().get(0).getSurname(), "surname1");
        assertEquals(response.getPatientList().get(0).getPersonalCode(), "1234");
        assertEquals(response.getPatientList().get(1).getSurname(), "surname2");
        assertEquals(response.getPatientList().get(1).getPersonalCode(), "1235");
    }

    @Test
    public void shouldSearchByNameWithOrderingDescending() {
        PatientOrdering patientOrdering = new PatientOrdering("surname", "DESCENDING");
        SearchPatientsRequest request = new SearchPatientsRequest("name", null, null, patientOrdering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("name", "surname1", "1234"));
        patients.add(new Patient("name", "surname2", "1235"));
        Mockito.when(database.findPatientsByName("name")).thenReturn(patients);

        SearchPatientsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getPatientList().size(), 2);
        assertEquals(response.getPatientList().get(0).getSurname(), "surname2");
        assertEquals(response.getPatientList().get(0).getPersonalCode(), "1235");
        assertEquals(response.getPatientList().get(1).getSurname(), "surname1");
        assertEquals(response.getPatientList().get(1).getPersonalCode(), "1234");
    }

    @Test
    public void shouldSearchByTitleWithPagingFirstPage() {
        PatientPaging patientPaging = new PatientPaging(1, 1);
        SearchPatientsRequest request = new SearchPatientsRequest("name", null, null, patientPaging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("name", "surname1", "1234"));
        patients.add(new Patient("name", "surname2", "1235"));
        Mockito.when(database.findPatientsByName("name")).thenReturn(patients);

        SearchPatientsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getPatientList().size(), 1);
        assertEquals(response.getPatientList().get(0).getName(), "name");
        assertEquals(response.getPatientList().get(0).getSurname(), "surname1");
        assertEquals(response.getPatientList().get(0).getPersonalCode(), "1234");
    }

    @Test
    public void shouldSearchByTitleWithPagingSecondPage() {
        PatientPaging patientPaging = new PatientPaging(2, 1);
        SearchPatientsRequest request = new SearchPatientsRequest("name", null, null, patientPaging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("name", "surname1", "1234"));
        patients.add(new Patient("name", "surname2", "1235"));
        Mockito.when(database.findPatientsByName("name")).thenReturn(patients);

        SearchPatientsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getPatientList().size(), 1);
        assertEquals(response.getPatientList().get(0).getName(), "name");
        assertEquals(response.getPatientList().get(0).getSurname(), "surname2");
        assertEquals(response.getPatientList().get(0).getPersonalCode(), "1235");
    }
}