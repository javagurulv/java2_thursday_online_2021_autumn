package lv.javaguru.java2.hospital.doctor.core.services;

import lv.javaguru.java2.hospital.database.doctor_repository.DoctorRepository;
import lv.javaguru.java2.hospital.doctor.core.requests.DoctorOrdering;
import lv.javaguru.java2.hospital.doctor.core.requests.DoctorPaging;
import lv.javaguru.java2.hospital.doctor.core.requests.SearchDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import lv.javaguru.java2.hospital.doctor.core.responses.SearchDoctorsResponse;
import lv.javaguru.java2.hospital.doctor.core.services.validators.SearchDoctorsRequestValidator;
import lv.javaguru.java2.hospital.domain.Doctor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class SearchDoctorsServiceTest {

    @Mock
    private DoctorRepository database;
    @Mock
    private SearchDoctorsRequestValidator validator;
    @InjectMocks
    private SearchDoctorsService service;

    @BeforeEach
    public void setup() {
        ReflectionTestUtils.setField(service, "orderingEnabled", true);
        ReflectionTestUtils.setField(service, "pagingEnabled", true);
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenValidatorFails() {
        SearchDoctorsRequest request = new SearchDoctorsRequest(null, null, null, null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("id", "Must not be empty!"));
        errors.add(new CoreError("name", "Must not be empty!"));
        errors.add(new CoreError("surname", "Must not be empty!"));
        errors.add(new CoreError("speciality", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        SearchDoctorsResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 4);

        Mockito.verify(validator).validate(request);
        Mockito.verify(validator).validate(any());
        Mockito.verifyNoInteractions(database);
    }

    @Test
    public void shouldSearchByName() {
        SearchDoctorsRequest request = new SearchDoctorsRequest(null, "Name", null, null);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Name", "Surname", "Speciality"));
        Mockito.when(database.findByName("Name")).thenReturn(doctors);

        SearchDoctorsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getDoctors().size(), 1);
        assertEquals(response.getDoctors().get(0).getName(), "Name");
        assertEquals(response.getDoctors().get(0).getSurname(), "Surname");
        assertEquals(response.getDoctors().get(0).getSpeciality(), "Speciality");

    }

    @Test
    public void shouldSearchBySurname() {
        SearchDoctorsRequest request = new SearchDoctorsRequest(null, null, "Surname", null);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Name", "Surname", "Speciality"));
        Mockito.when(database.findBySurname("Surname")).thenReturn(doctors);

        SearchDoctorsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getDoctors().size(), 1);
        assertEquals(response.getDoctors().get(0).getName(), "Name");
        assertEquals(response.getDoctors().get(0).getSurname(), "Surname");
        assertEquals(response.getDoctors().get(0).getSpeciality(), "Speciality");

    }

    @Test
    public void shouldSearchByNameAndSurname() {
        SearchDoctorsRequest request = new SearchDoctorsRequest(null, "Name", "Surname", null);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Name", "Surname", "Speciality"));
        Mockito.when(database.findByNameAndSurname("Name", "Surname")).thenReturn(doctors);

        SearchDoctorsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getDoctors().size(), 1);
        assertEquals(response.getDoctors().get(0).getName(), "Name");
        assertEquals(response.getDoctors().get(0).getSurname(), "Surname");
        assertEquals(response.getDoctors().get(0).getSpeciality(), "Speciality");
    }

    @Test
    public void shouldSearchById() {
        SearchDoctorsRequest request = new SearchDoctorsRequest(1L, null, null, null);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Name", "Surname", "Speciality"));
        Mockito.when(database.findById(1L)).thenReturn(doctors);

        SearchDoctorsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getDoctors().size(), 1);
        assertEquals(response.getDoctors().get(0).getName(), "Name");
        assertEquals(response.getDoctors().get(0).getSurname(), "Surname");
        assertEquals(response.getDoctors().get(0).getSpeciality(), "Speciality");

    }

    @Test
    public void shouldSearchBySpeciality() {
        SearchDoctorsRequest request = new SearchDoctorsRequest(null, null, null, "Speciality");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Name", "Surname", "Speciality"));
        Mockito.when(database.findBySpeciality("Speciality")).thenReturn(doctors);

        SearchDoctorsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getDoctors().size(), 1);
        assertEquals(response.getDoctors().get(0).getName(), "Name");
        assertEquals(response.getDoctors().get(0).getSurname(), "Surname");
        assertEquals(response.getDoctors().get(0).getSpeciality(), "Speciality");

    }

    @Test
    public void shouldSearchByNameAndSpeciality() {
        SearchDoctorsRequest request = new SearchDoctorsRequest(null, "Name", null, "Speciality");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Name", "Surname", "Speciality"));
        Mockito.when(database.findByNameAndSpeciality("Name", "Speciality")).thenReturn(doctors);

        SearchDoctorsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getDoctors().size(), 1);
        assertEquals(response.getDoctors().get(0).getName(), "Name");
        assertEquals(response.getDoctors().get(0).getSurname(), "Surname");
        assertEquals(response.getDoctors().get(0).getSpeciality(), "Speciality");
    }

    @Test
    public void shouldSearchBySurnameAndSpeciality() {
        SearchDoctorsRequest request = new SearchDoctorsRequest(null, null, "Surname", "Speciality");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Name", "Surname", "Speciality"));
        Mockito.when(database.findBySurnameAndSpeciality("Surname", "Speciality")).thenReturn(doctors);

        SearchDoctorsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getDoctors().size(), 1);
        assertEquals(response.getDoctors().get(0).getName(), "Name");
        assertEquals(response.getDoctors().get(0).getSurname(), "Surname");
        assertEquals(response.getDoctors().get(0).getSpeciality(), "Speciality");
    }

    @Test
    public void shouldSearchByNameAndSurnameAndSpeciality() {
        SearchDoctorsRequest request = new SearchDoctorsRequest(null, "Name", "Surname", "Speciality");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Name", "Surname", "Speciality"));
        Mockito.when(database.findByNameAndSurnameAndSpeciality("Name", "Surname", "Speciality")).thenReturn(doctors);

        SearchDoctorsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getDoctors().size(), 1);
        assertEquals(response.getDoctors().get(0).getName(), "Name");
        assertEquals(response.getDoctors().get(0).getSurname(), "Surname");
        assertEquals(response.getDoctors().get(0).getSpeciality(), "Speciality");
    }

    @Test
    public void shouldSearchByNameWithOrderingAscending() {
        DoctorOrdering doctorOrdering = new DoctorOrdering("surname", "ASCENDING");
        SearchDoctorsRequest request = new SearchDoctorsRequest(null, "Name", null, null, doctorOrdering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Name", "Surname2", "Speciality"));
        doctors.add(new Doctor("Name", "Surname1", "Speciality"));
        Mockito.when(database.findByName("Name")).thenReturn(doctors);

        SearchDoctorsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getDoctors().size(), 2);
        assertEquals(response.getDoctors().get(0).getSurname(), "Surname1");
        assertEquals(response.getDoctors().get(1).getSurname(), "Surname2");
    }

    @Test
    public void shouldSearchByNameWithOrderingDescending() {
        DoctorOrdering doctorOrdering = new DoctorOrdering("surname", "DESCENDING");
        SearchDoctorsRequest request = new SearchDoctorsRequest(null, "Name", null, null, doctorOrdering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Name", "Surname1", "Speciality"));
        doctors.add(new Doctor("Name", "Surname2", "Speciality"));
        Mockito.when(database.findByName("Name")).thenReturn(doctors);

        SearchDoctorsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getDoctors().size(), 2);
        assertEquals(response.getDoctors().get(0).getSurname(), "Surname2");
        assertEquals(response.getDoctors().get(1).getSurname(), "Surname1");
    }

    @Test
    public void shouldSearchByTitleWithPagingFirstPage() {
        DoctorPaging doctorPaging = new DoctorPaging(1, 1);
        SearchDoctorsRequest request = new SearchDoctorsRequest(null, "Name", null, null, doctorPaging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Name", "Surname1", "Speciality"));
        doctors.add(new Doctor("Name", "Surname2", "Speciality"));
        Mockito.when(database.findByName("Name")).thenReturn(doctors);

        SearchDoctorsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getDoctors().size(), 1);
        assertEquals(response.getDoctors().get(0).getName(), "Name");
        assertEquals(response.getDoctors().get(0).getSurname(), "Surname1");
        assertEquals(response.getDoctors().get(0).getSpeciality(), "Speciality");
    }

    @Test
    public void shouldSearchByTitleWithPagingSecondPage() {
        DoctorPaging doctorPaging = new DoctorPaging(2, 1);
        SearchDoctorsRequest request = new SearchDoctorsRequest(null, "Name", null, null, doctorPaging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Name", "Surname1", "Speciality"));
        doctors.add(new Doctor("Name", "Surname2", "Speciality"));
        Mockito.when(database.findByName("Name")).thenReturn(doctors);

        SearchDoctorsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getDoctors().size(), 1);
        assertEquals(response.getDoctors().get(0).getName(), "Name");
        assertEquals(response.getDoctors().get(0).getSurname(), "Surname2");
    }

}