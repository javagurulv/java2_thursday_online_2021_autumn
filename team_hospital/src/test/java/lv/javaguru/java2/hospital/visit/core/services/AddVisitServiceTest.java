package lv.javaguru.java2.hospital.visit.core.services;

import lv.javaguru.java2.hospital.database.DoctorDatabaseImpl;
import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.database.VisitDatabaseImpl;
import lv.javaguru.java2.hospital.visit.core.requests.AddVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.AddVisitResponse;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.services.validators.AddVisitValidator;
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
import static org.mockito.ArgumentMatchers.any;

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
/*
    @Test
    public void shouldAddVisitToDatabase() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        AddDoctorRequest request1 = new AddDoctorRequest("DoctorsName", "DoctorsSurname", "Speciality");
        AddDoctorResponse response1 = new AddDoctorService().execute(request1);
        AddPatientRequest request2 = new AddPatientRequest("PatientsName", "PatientsSurname", "120254-12636");
        AddPatientResponse response2 = new AddPatientService().execute(request2);
        String patientsPersonalCode = response2.getPatient().getPersonalCode();
        String doctorsName = response1.getNewDoctor().getName();
        String doctorsSurname = response1.getNewDoctor().getSurname();
        AddVisitRequest request = new AddVisitRequest(patientsPersonalCode, doctorsName, doctorsSurname, "21/12/2021 15:00");
        AddVisitResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        Mockito.verify(visitDatabase).recordVisit(argThat(new VisitMatcher(response1.getNewDoctor(), response2.getPatient(), new Date(2021, 12, 21, 15, 00))));
    }
*/
}