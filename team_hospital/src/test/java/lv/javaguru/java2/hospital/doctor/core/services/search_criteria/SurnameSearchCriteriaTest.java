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
class SurnameSearchCriteriaTest {

    @Mock
    JpaDoctorRepository database;
    @InjectMocks SurnameSearchCriteria searchCriteria;

    @Test
    public void shouldReturnTrue() {
        SearchDoctorsRequest request = new SearchDoctorsRequest(null, "", "Surname", "");
        assertTrue(searchCriteria.canProcess(request));
    }

    @Test
    public void shouldReturnFalse() {
        SearchDoctorsRequest request = new SearchDoctorsRequest(null, "Name235", "", "");
        assertFalse(searchCriteria.canProcess(request));
    }

    @Test
    public void shouldReturnCorrectDoctor() {
        Doctor doctor1 = new Doctor("Name45", "Surname46", "Speciality9");
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor1);
        Mockito.when(database.findBySurname("Surname46")).thenReturn(doctors);
        SearchDoctorsRequest request = new SearchDoctorsRequest(null, "", "Surname46", "");
        Doctor doctor2 = searchCriteria.process(request).get(0);
        assertEquals(searchCriteria.process(request).size(), 1);
        assertEquals(doctor2.getName(), ("Name45"));
        assertEquals(doctor2.getSurname(), ("Surname46"));
        assertEquals(doctor2.getSpeciality(), ("Speciality9"));
    }

    @Test
    public void shouldReturnCorrectDoctors() {
        Doctor doctor1 = new Doctor("Name125", "Surname139", "Speciality03");
        Doctor doctor2 = new Doctor("Name215", "Surname139", "Speciality70");
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor1);
        doctors.add(doctor2);
        Mockito.when(database.findBySurname("Surname139")).thenReturn(doctors);
        SearchDoctorsRequest request = new SearchDoctorsRequest(null, "", "Surname139", "");
        Doctor doctor3 = searchCriteria.process(request).get(0);
        assertEquals(searchCriteria.process(request).size(), 2);
        assertEquals(doctor3.getName(), ("Name125"));
        assertEquals(doctor3.getSurname(), ("Surname139"));
        assertEquals(doctor3.getSpeciality(), ("Speciality03"));
        Doctor doctor4 = searchCriteria.process(request).get(1);
        assertEquals(doctor4.getName(), ("Name215"));
        assertEquals(doctor4.getSurname(), ("Surname139"));
        assertEquals(doctor4.getSpeciality(), ("Speciality70"));
    }



}