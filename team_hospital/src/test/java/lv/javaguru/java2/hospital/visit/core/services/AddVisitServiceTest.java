package lv.javaguru.java2.hospital.visit.core.services;

import lv.javaguru.java2.hospital.database.DoctorDatabaseImpl;
import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.database.VisitDatabaseImpl;
import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.visit.core.requests.AddVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.AddVisitResponse;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.services.validators.AddVisitValidator;
import lv.javaguru.java2.hospital.visit.matchers.VisitMatcher;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class AddVisitServiceTest {

    @Mock private PatientDatabaseImpl patientDatabase;
    @Mock private DoctorDatabaseImpl doctorDatabase;
    @Mock private AddVisitValidator validator;
    @Mock private VisitDatabaseImpl visitDatabase;
    @InjectMocks private AddVisitService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {
        AddVisitRequest request = new AddVisitRequest("", "doctorsName", "doctorsSurname", "21/12/2021 15:00");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Patient personal code", "must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddVisitResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Patient personal code");
        assertEquals(response.getErrors().get(0).getDescription(), "must not be empty!");

        Mockito.verifyNoMoreInteractions(visitDatabase);
    }

    @Test
    public void shouldAddVisitToDatabase() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Doctor doctor = new Doctor("DoctorsName", "DoctorsSurname", "Speciality");
        Patient patient = new Patient("PatientsName", "PatientsSurname", "120254-12636");
        List<Patient> patients = new ArrayList<>();
        patients.add(patient);
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor);
        Mockito.when(patientDatabase.findPatientsByPersonalCode(patient.getPersonalCode()))
                .thenReturn((patients));
        Mockito.when(doctorDatabase.findByNameAndSurname(doctor.getName(), doctor.getSurname()))
                .thenReturn((doctors));

        AddVisitRequest request = new AddVisitRequest(patient.getPersonalCode(), doctor.getName(), doctor.getSurname(),
                "21/12/2021 15:00");
        AddVisitResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        Mockito.verify(visitDatabase).recordVisit(argThat(
                new VisitMatcher(response.getPatientVisit().getDoctor(),
                        response.getPatientVisit().getPatient(),
                        response.getPatientVisit().getVisitDate())));
    }
}