package lv.javaguru.java2.hospital.doctor.core.services;

import lv.javaguru.java2.hospital.database.doctor_repository.DoctorRepository;
import lv.javaguru.java2.hospital.doctor.core.requests.EditDoctorEnum;
import lv.javaguru.java2.hospital.doctor.core.requests.EditDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import lv.javaguru.java2.hospital.doctor.core.responses.EditDoctorResponse;
import lv.javaguru.java2.hospital.doctor.core.services.validators.EditDoctorRequestValidator;
import lv.javaguru.java2.hospital.domain.Doctor;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class EditDoctorServiceTest {

    @Mock
    private DoctorRepository database;
    @Mock
    private EditDoctorRequestValidator validator;
    @InjectMocks
    private EditDoctorService service;

    @Test
    public void shouldReturnErrorWhenDoctorIdNotProvided() {
        EditDoctorRequest request = new EditDoctorRequest(null, "Name", "NewName");
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
    public void shouldChangeDoctorName() {
        EditDoctorRequest request = new EditDoctorRequest("1", "Name", "NewName");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Name", "Surname", "Speciality"));
        Mockito.when(database.editDoctor("1", EditDoctorEnum.NAME, "NewName")).thenReturn(true);

        EditDoctorResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isDoctorEdited());
    }

    @Test
    public void shouldChangeDoctorSurname() {
        EditDoctorRequest request = new EditDoctorRequest("1", "Surname", "NewSurname");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Name", "Surname", "Speciality"));
        Mockito.when(database.editDoctor("1", EditDoctorEnum.SURNAME, "NewSurname")).thenReturn(true);

        EditDoctorResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isDoctorEdited());
    }

    @Test
    public void shouldChangeDoctorSpeciality() {
        EditDoctorRequest request = new EditDoctorRequest("1", "Speciality", "NewSpeciality");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Name", "Surname", "Speciality"));
        Mockito.when(database.editDoctor("1", EditDoctorEnum.SPECIALITY, "NewSpeciality")).thenReturn(true);

        EditDoctorResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isDoctorEdited());
    }

}