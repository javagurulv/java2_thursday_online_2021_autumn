package lv.javaguru.java2.hospital.visit.core.services;


import lv.javaguru.java2.hospital.database.VisitDatabaseImpl;
import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.ShowAllVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.ShowAllVisitResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class ShowAllVisitServiceTest {

    @Mock
    VisitDatabaseImpl database;
    @InjectMocks
    ShowAllVisitService service;

    @Test
    public void shouldGetBooksFromDb() {
        List<Visit> visits = new ArrayList<>();
        Doctor doctor = new Doctor("Name", "Surname", "Speciality");
        Patient patient = new Patient("Name", "Surname", "120593-15634");
        Date date = new Date(2021, 12, 21, 15, 00);
        visits.add(new Visit(doctor, patient, date));
        Mockito.when(database.showAllVisits()).thenReturn(visits);
        ShowAllVisitRequest request = new ShowAllVisitRequest();
        ShowAllVisitResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getPatientVisits().size(), 1);
        assertEquals(response.getPatientVisits().get(0).getPatient(), patient);
        assertEquals(response.getPatientVisits().get(0).getDoctor(), doctor);
        assertEquals(response.getPatientVisits().get(0).getVisitDate(), date);
    }


}