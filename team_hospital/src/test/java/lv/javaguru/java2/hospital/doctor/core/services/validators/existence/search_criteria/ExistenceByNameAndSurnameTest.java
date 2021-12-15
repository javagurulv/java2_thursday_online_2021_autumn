package lv.javaguru.java2.hospital.doctor.core.services.validators.existence.search_criteria;

import lv.javaguru.java2.hospital.database.doctor_repository.DoctorRepository;
import lv.javaguru.java2.hospital.doctor.core.requests.SearchDoctorsRequest;
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
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class ExistenceByNameAndSurnameTest {

    @Mock
    private DoctorRepository database;
    @InjectMocks
    private ExistenceByNameAndSurname existence;

    @Test
    public void shouldReturnTrue() {
        SearchDoctorsRequest request = new SearchDoctorsRequest("Name", "Surname", "");
        assertTrue(existence.canValidate(request));
    }

    @Test
    public void shouldReturnFalse() {
        SearchDoctorsRequest request = new SearchDoctorsRequest("Name64", "", "Speciality74");
        assertFalse(existence.canValidate(request));
    }

    @Test
    public void shouldReturnDoctorError() {
        SearchDoctorsRequest request = new SearchDoctorsRequest("Name22", "Surname324", "");
        Optional<CoreError> errorList = existence.validateExistence(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.get().getField(), "Doctor");
        assertEquals(errorList.get().getMessage(), "Does not exist!");
    }

    @Test
    public void shouldReturnEmptyList() {
        Doctor doctor = new Doctor("Name22", "Surname324", "Speciality76");

        SearchDoctorsRequest request = new SearchDoctorsRequest("Name22", "Surname324", "");
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor);
        Mockito.when(database.getAllDoctors()).thenReturn(doctors);

        Optional<CoreError> errorList = existence.validateExistence(request);
        assertTrue(errorList.isEmpty());
    }

}