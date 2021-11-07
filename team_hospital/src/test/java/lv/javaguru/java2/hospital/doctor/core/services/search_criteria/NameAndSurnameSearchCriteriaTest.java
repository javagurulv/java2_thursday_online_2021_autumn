package lv.javaguru.java2.hospital.doctor.core.services.search_criteria;

import lv.javaguru.java2.hospital.database.doctor_repository.DoctorRepository;
import lv.javaguru.java2.hospital.doctor.core.requests.SearchDoctorsRequest;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class NameAndSurnameSearchCriteriaTest {

    @Mock
    private DoctorRepository database;
    @InjectMocks
    NameAndSurnameSearchCriteria searchCriteria;

    @Test
    public void shouldReturnTrue() {
        SearchDoctorsRequest request = new SearchDoctorsRequest(null, "Name518", "Surname35", "");
        assertTrue(searchCriteria.canProcess(request));
    }

    @Test
    public void shouldReturnFalse() {
        SearchDoctorsRequest request = new SearchDoctorsRequest(null, "", "Surname868543", "");
        assertFalse(searchCriteria.canProcess(request));
    }

    @Test
    public void shouldReturnCorrectDoctor() {
        Doctor doctor1 = new Doctor("Name23", "Surname155", "Speciality899");
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor1);
        Mockito.when(database.findByNameAndSurname("Name23", "Surname155")).thenReturn(doctors);
        SearchDoctorsRequest request = new SearchDoctorsRequest(null, "Name23", "Surname155", "");
        Doctor doctor2 = searchCriteria.process(request).get(0);
        assertEquals(searchCriteria.process(request).size(), 1);
        assertEquals(doctor2.getName(), ("Name23"));
        assertEquals(doctor2.getSurname(), ("Surname155"));
        assertEquals(doctor2.getSpeciality(), ("Speciality899"));
    }
}