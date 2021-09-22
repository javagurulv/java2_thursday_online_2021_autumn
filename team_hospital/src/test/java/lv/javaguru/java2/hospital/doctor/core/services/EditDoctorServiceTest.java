package lv.javaguru.java2.hospital.doctor.core.services;

import lv.javaguru.java2.hospital.database.DoctorDatabaseImpl;
import lv.javaguru.java2.hospital.doctor.core.requests.EditDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import lv.javaguru.java2.hospital.doctor.core.responses.EditDoctorResponse;
import lv.javaguru.java2.hospital.doctor.core.services.validators.EditDoctorValidator;
import lv.javaguru.java2.hospital.domain.Doctor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EditDoctorServiceTest {

    @Mock
    private DoctorDatabaseImpl database;
    @Mock
    private EditDoctorValidator validator;
    @InjectMocks
    private EditDoctorService service;

    @BeforeEach
    public void init() {
        database = Mockito.mock(DoctorDatabaseImpl.class);
        validator = Mockito.mock(EditDoctorValidator.class);
        service = new EditDoctorService(database, validator);
    }

    @Test
    public void shouldReturnErrorWhenDoctorIdNotProvided() {
        EditDoctorRequest request = new EditDoctorRequest(null, 1, "NewName");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("id", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        EditDoctorResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "id");
        assertEquals(response.getErrors().get(0).getMessage(), "Must not be empty!");
    }


    @Test
    public void shouldReturnFalse() {
        EditDoctorRequest request = new EditDoctorRequest("1", 1, "NewName");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Name", "Surname", "Speciality"));
        Mockito.when(database.editDoctor(2, 1, "NewName")).thenReturn(false);

        EditDoctorResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertFalse(response.isDoctorEdited());
    }

    @Test
    public void shouldChangeDoctorName() {
        EditDoctorRequest request = new EditDoctorRequest("1", 1, "NewName");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Name", "Surname", "Speciality"));
        Mockito.when(database.editDoctor(1, 1, "NewName")).thenReturn(true);

        EditDoctorResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isDoctorEdited());
    }

    @Test
    public void shouldChangeDoctorSurname() {
        EditDoctorRequest request = new EditDoctorRequest("1", 2, "NewSurname");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Name", "Surname", "Speciality"));
        Mockito.when(database.editDoctor(1, 2, "NewSurname")).thenReturn(true);

        EditDoctorResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isDoctorEdited());
    }

    @Test
    public void shouldChangeDoctorSpeciality() {
        EditDoctorRequest request = new EditDoctorRequest("1", 3, "NewSpeciality");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Name", "Surname", "Speciality"));
        Mockito.when(database.editDoctor(1, 3, "NewSpeciality")).thenReturn(true);

        EditDoctorResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isDoctorEdited());
    }

}