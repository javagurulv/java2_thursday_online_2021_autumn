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
class NameAndSpecialitySearchCriteriaTest {

    @Mock private DoctorRepository database;
    @InjectMocks private NameAndSpecialitySearchCriteria searchCriteria;

    @Test
    public void shouldReturnTrue() {
        SearchDoctorsRequest request = new SearchDoctorsRequest("Name51568", "", "Speciality327");
        assertTrue(searchCriteria.canProcess(request));
    }

    @Test
    public void shouldReturnFalse() {
        SearchDoctorsRequest request = new SearchDoctorsRequest("Name654", "Surname868543", "");
        assertFalse(searchCriteria.canProcess(request));
    }

    @Test
    public void shouldReturnCorrectDoctor() {
        Doctor doctor1 = new Doctor("Name244653", "Surname1545435", "Speciality8954739");
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor1);
        Mockito.when(database.findByNameAndSpeciality("Name244653", "Speciality8954739")).thenReturn(doctors);
        SearchDoctorsRequest request = new SearchDoctorsRequest("Name244653", "", "Speciality8954739");
        Doctor doctor2 = searchCriteria.process(request).get(0);
        assertEquals(searchCriteria.process(request).size(), 1);
        assertEquals(doctor2.getName(), ("Name244653"));
        assertEquals(doctor2.getSurname(), ("Surname1545435"));
        assertEquals(doctor2.getSpeciality(), ("Speciality8954739"));
    }
}