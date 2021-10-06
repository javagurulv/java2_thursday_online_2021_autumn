package lv.javaguru.java2.hospital.visit.core.services;

import lv.javaguru.java2.hospital.database.VisitDatabaseImpl;
import lv.javaguru.java2.hospital.visit.core.requests.EditVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.responses.EditVisitResponse;
import lv.javaguru.java2.hospital.visit.core.services.validators.EditVisitValidator;
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

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class EditVisitServiceTest {

    @Mock private VisitDatabaseImpl database;
    @Mock private EditVisitValidator validator;
    @InjectMocks private EditVisitService service;

    @Test
    public void shouldReturnErrorWhenVisitIdNotProvided() {
        EditVisitRequest request = new EditVisitRequest(null, 1, "NewDoctorId");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("id", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        EditVisitResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "id");
        assertEquals(response.getErrors().get(0).getDescription(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenChangesNotProvided() {
        EditVisitRequest request = new EditVisitRequest(1L, 1, "");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("changes", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        EditVisitResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "changes");
        assertEquals(response.getErrors().get(0).getDescription(), "Must not be empty!");
    }
/*
    @Test
    public void shouldChangeDoctor() {


        EditVisitRequest request = new EditVisitRequest(1L, 1, "2");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Visit> visits = new ArrayList<>();
        Doctor doctor = new Doctor("Name", "Surname", "Speciality");
        Patient patient = new Patient("Name", "Surname", "120593-15634");
        Date date = new Date(2021, 12, 21, 15, 00);
        visits.add(new Visit(doctor, patient, date));

       Long doctorsIdToChange = visits.get(0).getDoctor().getId();
       EditVisitResponse response = service.execute(request);
       assertFalse(response.hasErrors());
       assertTrue(response.isVisitEdited());

    }
*/
}