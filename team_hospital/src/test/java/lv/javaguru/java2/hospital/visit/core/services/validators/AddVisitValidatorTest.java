package lv.javaguru.java2.hospital.visit.core.services.validators;

import lv.javaguru.java2.hospital.database.DoctorDatabaseImpl;
import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.visit.core.requests.AddVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class AddVisitValidatorTest {

    @Mock PatientDatabaseImpl patientDatabase;
    @Mock DoctorDatabaseImpl doctorDatabase;
    @InjectMocks AddVisitValidator addVisitValidator;

    @Test
    public void shouldReturnPersonalCodeError(){
        AddVisitRequest addVisitRequest = new AddVisitRequest(
                "",
                "name",
                "surname",
                "12/12/21 12:00");
        List<CoreError> errorList = addVisitValidator.validate(addVisitRequest);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.get(0).getField(), "Patient personal code");
        assertEquals(errorList.get(0).getDescription(), "must not be empty!");
    }

    @Test
    public void shouldDoctorNameError(){
        AddVisitRequest addVisitRequest = new AddVisitRequest(
                "1234",
                "",
                "surname",
                "12/12/21 12:00");
        List<CoreError> errorList = addVisitValidator.validate(addVisitRequest);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.get(0).getField(), "Doctor name");
        assertEquals(errorList.get(0).getDescription(), "must not be empty!");
    }

    @Test
    public void shouldReturnDoctorSurnameError(){
        AddVisitRequest addVisitRequest = new AddVisitRequest(
                "1234",
                "name",
                "",
                "12/12/21 12:00");
        List<CoreError> errorList = addVisitValidator.validate(addVisitRequest);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.get(0).getField(), "Doctor surname");
        assertEquals(errorList.get(0).getDescription(), "must not be empty!");
    }

    @Test
    public void shouldReturnDateError(){
        AddVisitRequest addVisitRequest = new AddVisitRequest(
                "1234",
                "name",
                "surname",
                "");
        List<CoreError> errorList = addVisitValidator.validate(addVisitRequest);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.get(0).getField(), "Visit date");
        assertEquals(errorList.get(0).getDescription(), "must not be empty!");
    }

    @Test
    public void shouldReturnPatientAndDoctorDoesNotExistError(){
        AddVisitRequest addVisitRequest = new AddVisitRequest(
                "0000",
                "name",
                "surname",
                "12/12/2021 12:00");
        List<CoreError> errorList = addVisitValidator.validate(addVisitRequest);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.get(0).getField(), "Patient");
        assertEquals(errorList.get(0).getDescription(), "does not exist!");
        assertEquals(errorList.get(1).getField(), "Doctor");
        assertEquals(errorList.get(1).getDescription(), "does not exist!");
    }
}