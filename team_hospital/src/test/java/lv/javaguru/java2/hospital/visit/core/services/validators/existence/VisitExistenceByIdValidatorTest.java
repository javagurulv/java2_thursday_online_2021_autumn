package lv.javaguru.java2.hospital.visit.core.services.validators.existence;

import lv.javaguru.java2.hospital.database.VisitDatabase;
import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class VisitExistenceByIdValidatorTest {

    @Mock private VisitDatabase database;
    @InjectMocks VisitExistenceByIdValidator validator;

    @Test
    public void shouldReturnDoctorsErrorWhenVisitDoesNotExist() {
        Optional<CoreError> errors = validator.validateExistenceById(1231L);
        assertEquals(errors.get().getField(), "Visit");
        assertEquals(errors.get().getDescription(), "Does not exist!");
    }

    @Test
    public void shouldReturnEmptyList() {
        Doctor doctor = new Doctor("DoctorsName", "DoctorsSurname", "Speciality");
        doctor.setId(1L);
        Patient patient = new Patient("PatientsName", "PatientsSurname", "110254-12636");
        patient.setId(2L);

        List<Visit> visits = new ArrayList<>();
        LocalDateTime date = LocalDateTime.from(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").parse("20-12-2021 14:00"));
        visits.add(new Visit(doctor, patient, date));
        Long visitId = 165L;
        visits.get(0).setVisitID(visitId);

        Mockito.when(database.getAllVisits()).thenReturn(visits);
        Optional<CoreError> errors = validator.validateExistenceById(visitId);
        assertTrue(errors.isEmpty());
    }
}
