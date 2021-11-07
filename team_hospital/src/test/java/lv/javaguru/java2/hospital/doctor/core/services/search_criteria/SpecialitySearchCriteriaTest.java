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
class SpecialitySearchCriteriaTest {

    @Mock
    DoctorRepository database;
    @InjectMocks
    private SpecialitySearchCriteria searchCriteria;

    @Test
    public void shouldReturnTrue() {
        SearchDoctorsRequest request = new SearchDoctorsRequest(null, "", "", "Spec66");
        assertTrue(searchCriteria.canProcess(request));
    }

    @Test
    public void shouldReturnFalse() {
        SearchDoctorsRequest request = new SearchDoctorsRequest(null, "", "Surname88543", "");
        assertFalse(searchCriteria.canProcess(request));
    }

    @Test
    public void shouldReturnCorrectDoctor() {
        Doctor doctor1 = new Doctor("Name26", "Surname1235", "Speciality31");
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor1);
        Mockito.when(database.findBySpeciality("Speciality31")).thenReturn(doctors);
        SearchDoctorsRequest request = new SearchDoctorsRequest(null, "", "", "Speciality31");
        Doctor doctor2 = searchCriteria.process(request).get(0);
        assertEquals(searchCriteria.process(request).size(), 1);
        assertEquals(doctor2.getName(), ("Name26"));
        assertEquals(doctor2.getSurname(), ("Surname1235"));
        assertEquals(doctor2.getSpeciality(), ("Speciality31"));
    }

    @Test
    public void shouldReturnCorrectDoctors() {
        Doctor doctor1 = new Doctor("Name232", "Surname19", "Speciality30");
        Doctor doctor2 = new Doctor("Name782", "Surname1", "Speciality30");
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor1);
        doctors.add(doctor2);
        Mockito.when(database.findBySpeciality("Speciality30")).thenReturn(doctors);
        SearchDoctorsRequest request = new SearchDoctorsRequest(null, "", "", "Speciality30");
        Doctor doctor3 = searchCriteria.process(request).get(0);
        assertEquals(searchCriteria.process(request).size(), 2);
        assertEquals(doctor3.getName(), ("Name232"));
        assertEquals(doctor3.getSurname(), ("Surname19"));
        assertEquals(doctor3.getSpeciality(), ("Speciality30"));
        Doctor doctor4 = searchCriteria.process(request).get(1);
        assertEquals(doctor4.getName(), ("Name782"));
        assertEquals(doctor4.getSurname(), ("Surname1"));
        assertEquals(doctor4.getSpeciality(), ("Speciality30"));
    }
}