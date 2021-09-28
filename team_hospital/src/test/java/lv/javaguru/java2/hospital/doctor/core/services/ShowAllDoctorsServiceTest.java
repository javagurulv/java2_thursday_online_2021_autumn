package lv.javaguru.java2.hospital.doctor.core.services;

import lv.javaguru.java2.hospital.database.DoctorDatabaseImpl;
import lv.javaguru.java2.hospital.doctor.core.requests.ShowAllDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.ShowAllDoctorsResponse;
import lv.javaguru.java2.hospital.domain.Doctor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ShowAllDoctorsServiceTest {

    @Mock
    private DoctorDatabaseImpl database;
    @InjectMocks
    private ShowAllDoctorsService service;

    @BeforeEach
    public void init() {
        database = Mockito.mock(DoctorDatabaseImpl.class);
        service = new ShowAllDoctorsService(database);
    }

    @Test
    public void shouldGetBooksFromDb() {
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Name", "Surname", "Speciality"));
        Mockito.when(database.showAllDoctors()).thenReturn(doctors);
        ShowAllDoctorsRequest request = new ShowAllDoctorsRequest();
        ShowAllDoctorsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getDoctors().size(), 1);
        assertEquals(response.getDoctors().get(0).getName(), "Name");
        assertEquals(response.getDoctors().get(0).getSurname(), "Surname");
        assertEquals(response.getDoctors().get(0).getSpeciality(), "Speciality");
    }


}