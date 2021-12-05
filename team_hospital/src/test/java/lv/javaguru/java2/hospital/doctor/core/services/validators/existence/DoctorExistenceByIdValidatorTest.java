package lv.javaguru.java2.hospital.doctor.core.services.validators.existence;

import lv.javaguru.java2.hospital.database.doctor_repository.DoctorRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class DoctorExistenceByIdValidatorTest {

    @Mock private DoctorRepository database;
    @InjectMocks
    private DoctorExistenceByIdValidator validator;

    @Test public void shouldReturnDoctorsErrorWhenDoctorDoesNotExist() {
        Optional<CoreError> errors = validator.validateExistenceById("1231");
        assertEquals(errors.get().getField(), "Doctor");
        assertEquals(errors.get().getMessage(), "Does not exist!");
    }

    @Test
    public void shouldReturnEmptyList() {
        Doctor doctor = new Doctor("Name145", "Surname9856", "Speciality9084");
        doctor.setId(14L);
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor);
        Mockito.when(database.getAllDoctors()).thenReturn(doctors);
        Optional<CoreError> errors = validator.validateExistenceById(doctor.getId().toString());
        assertTrue(errors.isEmpty());
    }
}