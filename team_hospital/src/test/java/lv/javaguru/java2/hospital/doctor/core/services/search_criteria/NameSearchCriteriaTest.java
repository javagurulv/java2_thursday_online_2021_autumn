package lv.javaguru.java2.hospital.doctor.core.services.search_criteria;

import lv.javaguru.java2.hospital.database.doctor_repository.DoctorRepository;
import lv.javaguru.java2.hospital.database.jpa.JpaDoctorRepository;
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
class NameSearchCriteriaTest {

    @Mock private JpaDoctorRepository database;
    @InjectMocks private NameSearchCriteria searchCriteria;

    @Test
    public void shouldReturnTrue() {
        SearchDoctorsRequest request = new SearchDoctorsRequest(null, "Name5", "", "");
        assertTrue(searchCriteria.canProcess(request));
    }

    @Test
    public void shouldReturnFalse() {
        SearchDoctorsRequest request = new SearchDoctorsRequest(null, "", "Surname88543", "");
        assertFalse(searchCriteria.canProcess(request));
    }

    @Test
    public void shouldReturnCorrectDoctor() {
        Doctor doctor1 = new Doctor("Name2", "Surname135", "Speciality389");
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor1);
        Mockito.when(database.findByName("Name2")).thenReturn(doctors);
        SearchDoctorsRequest request = new SearchDoctorsRequest(null, "Name2", "", "");
        Doctor doctor2 = searchCriteria.process(request).get(0);
        assertEquals(searchCriteria.process(request).size(), 1);
        assertEquals(doctor2.getName(), ("Name2"));
        assertEquals(doctor2.getSurname(), ("Surname135"));
        assertEquals(doctor2.getSpeciality(), ("Speciality389"));
    }

    @Test
    public void shouldReturnCorrectDoctors() {
        Doctor doctor1 = new Doctor("Name2", "Surname1359", "Speciality3");
        Doctor doctor2 = new Doctor("Name2", "Surname1245", "Speciality7");
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor1);
        doctors.add(doctor2);
        Mockito.when(database.findByName("Name2")).thenReturn(doctors);
        SearchDoctorsRequest request = new SearchDoctorsRequest(null, "Name2", "", "");
        Doctor doctor3 = searchCriteria.process(request).get(0);
        assertEquals(searchCriteria.process(request).size(), 2);
        assertEquals(doctor3.getName(), ("Name2"));
        assertEquals(doctor3.getSurname(), ("Surname1359"));
        assertEquals(doctor3.getSpeciality(), ("Speciality3"));
        Doctor doctor4 = searchCriteria.process(request).get(1);
        assertEquals(doctor4.getName(), ("Name2"));
        assertEquals(doctor4.getSurname(), ("Surname1245"));
        assertEquals(doctor4.getSpeciality(), ("Speciality7"));
    }
}