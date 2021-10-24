package lv.javaguru.java2.hospital.doctor.core.services;

import lv.javaguru.java2.hospital.database.DoctorDatabase;
import lv.javaguru.java2.hospital.doctor.core.requests.ShowDoctorVisitsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.ShowDoctorVisitsResponse;
import lv.javaguru.java2.hospital.doctor.core.services.validators.ShowDoctorVisitsRequestValidator;
import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.domain.Visit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class ShowDoctorVisitsServiceTest {

    @Mock private DoctorDatabase database;
    @Mock private ShowDoctorVisitsRequestValidator validator;
    @InjectMocks private ShowDoctorVisitsService service;

    @Test
    public void shouldGetVisitsFromDb() {
        Doctor doctor = new Doctor("DoctorsName", "DoctorsSurname", "Speciality");
        Patient patient = new Patient("PatientsName", "PatientsSurname", "110254-12636");
        Long doctorId = doctor.getId();
        List<Visit> visits = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime date1 = LocalDateTime.parse("22/12/2021 14:00", formatter);
        LocalDateTime date2 = LocalDateTime.parse("12/01/2022 15:00", formatter);
        visits.add(new Visit(doctor, patient, date1));
        Long visitId1 = visits.get(0).getVisitID();
        visits.add(new Visit(doctor, patient, date2));
        Long visitId2 = visits.get(0).getVisitID();
        Mockito.when(database.getDoctorVisits(doctorId)).thenReturn(visits);

        ShowDoctorVisitsRequest request = new ShowDoctorVisitsRequest(doctorId);
        ShowDoctorVisitsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getVisits().size(), 2);
        assertEquals(response.getVisits().get(0).getDoctor(), doctor);
        assertEquals(response.getVisits().get(0).getPatient(), patient);
        assertEquals(response.getVisits().get(0).getVisitDate(), date1);
        assertEquals(response.getVisits().get(0).getVisitID(), visitId1);
        assertEquals(response.getVisits().get(1).getDoctor(), doctor);
        assertEquals(response.getVisits().get(1).getPatient(), patient);
        assertEquals(response.getVisits().get(1).getVisitDate(), date2);
        assertEquals(response.getVisits().get(1).getVisitID(), visitId2);
    }

    @Test
    public void shouldReturnEmptyList() {
        Doctor doctor = new Doctor("DoctorsName", "DoctorsSurname", "Speciality");
        Long doctorId = doctor.getId();
        Mockito.when(database.getDoctorVisits(doctorId)).thenReturn(new ArrayList<>());
        ShowDoctorVisitsRequest request = new ShowDoctorVisitsRequest(doctorId);
        ShowDoctorVisitsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.getVisits().isEmpty());
    }

}