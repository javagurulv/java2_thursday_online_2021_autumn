package lv.javaguru.java2.hospital.doctor.core.services.validators.existence.search_criteria;

import lv.javaguru.java2.hospital.database.doctor_repository.DoctorRepository;
import lv.javaguru.java2.hospital.database.jpa.JpaDoctorRepository;
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
class ExistenceByNameAndSurnameAndSpecialityTest {

    @Mock
    private JpaDoctorRepository database;
    @InjectMocks
    private ExistenceByNameAndSurnameAndSpeciality existence;

    @Test
    public void shouldReturnTrue() {
        SearchDoctorsRequest request = new SearchDoctorsRequest(null, "Name", "Surname", "Speciality");
        assertTrue(existence.canValidate(request));
    }

    @Test
    public void shouldReturnFalse() {
        SearchDoctorsRequest request = new SearchDoctorsRequest(null, "Name64", "Surname68543", "");
        assertFalse(existence.canValidate(request));
    }

    @Test
    public void shouldReturnDoctorError() {
        SearchDoctorsRequest request = new SearchDoctorsRequest
                (null, "Name156", "Surname75", "Speciality826");
        Optional<CoreError> errorList = existence.validateExistence(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.get().getField(), "Doctor");
        assertEquals(errorList.get().getMessage(), "Does not exist!");
    }

    @Test
    public void shouldReturnEmptyList() {
        Doctor doctor = new Doctor("Name156", "Surname75", "Speciality826");

        SearchDoctorsRequest request = new SearchDoctorsRequest
                (null, "Name156", "Surname75", "Speciality826");
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor);
        Mockito.when(database.findAll()).thenReturn(doctors);

        Optional<CoreError> errorList = existence.validateExistence(request);
        assertTrue(errorList.isEmpty());
    }

}