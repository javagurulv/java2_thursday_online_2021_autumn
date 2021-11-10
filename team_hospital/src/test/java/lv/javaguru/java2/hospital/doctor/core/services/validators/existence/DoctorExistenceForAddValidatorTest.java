package lv.javaguru.java2.hospital.doctor.core.services.validators.existence;

import lv.javaguru.java2.hospital.database.doctor_repository.DoctorRepository;
import lv.javaguru.java2.hospital.doctor.core.requests.AddDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import lv.javaguru.java2.hospital.domain.Doctor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class DoctorExistenceForAddValidatorTest {

    @Mock
    private DoctorRepository database;
    @InjectMocks private DoctorExistenceForAddValidator existence;

    @Test
    public void shouldReturnDoctorsErrorWhenDoctorExists() {
        Doctor doctor = new Doctor("Name", "Surname", "Speciality");
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor);
        AddDoctorRequest request = new AddDoctorRequest("Name", "Surname", "Speciality");
        Mockito.when(database.getAllDoctors()).thenReturn(doctors);
        Optional<CoreError> errors = existence.validateDoctorExistence(request);
        assertEquals(errors.get().getField(), "Doctor");
        assertEquals(errors.get().getMessage(), "Already exists!");
    }

    @Test
    public void shouldReturnEmptyList() {
        AddDoctorRequest request = new AddDoctorRequest("Name", "Surname", "Speciality");
        Mockito.when(database.getAllDoctors()).thenReturn(new ArrayList<>());
        Optional<CoreError> errors = existence.validateDoctorExistence(request);
        assertTrue(errors.isEmpty());
    }
}