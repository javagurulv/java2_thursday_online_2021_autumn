package lv.javaguru.java2.hospital.patient.services.validators;

import lv.javaguru.java2.hospital.patient.requests.AddPatientRequest;
import lv.javaguru.java2.hospital.patient.responses.CoreError;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddPatientValidatorTest {

    private final AddPatientValidator validator = new AddPatientValidator();

    @Test
    public void shouldReturnEmptyList(){
        AddPatientRequest request =
                new AddPatientRequest("Name", "Surname", "1234");
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnNameError(){
        AddPatientRequest request =
                new AddPatientRequest("", "Surname", "1234");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Name");
        assertEquals(errors.get(0).getDescription(), "Must not be empty!");
    }

    @Test
    public void shouldReturnSurnameError(){
        AddPatientRequest request =
                new AddPatientRequest("Name", "", "1234");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Surname");
        assertEquals(errors.get(0).getDescription(), "Must not be empty!");
    }

    @Test
    public void shouldReturnPersonalCodeError(){
        AddPatientRequest request =
                new AddPatientRequest("Name", "Surname", "");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Personal code");
        assertEquals(errors.get(0).getDescription(), "Must not be empty!");
    }

    @Test
    public void shouldReturnAll3Errors(){
        AddPatientRequest request =
                new AddPatientRequest("", "", "");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 3);
        assertEquals(errors.get(0).getField(), "Name");
        assertEquals(errors.get(0).getDescription(), "Must not be empty!");
        assertEquals(errors.get(1).getField(), "Surname");
        assertEquals(errors.get(1).getDescription(), "Must not be empty!");
        assertEquals(errors.get(2).getField(), "Personal code");
        assertEquals(errors.get(2).getDescription(), "Must not be empty!");
    }
}