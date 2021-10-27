package lv.javaguru.java2.hospital.prescription.core.services.validators;

import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import org.junit.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class PrescriptionEnumCheckerTest {

    private final PrescriptionEnumChecker checker = new PrescriptionEnumChecker();

    @Test
    public void shouldReturnEnumError(){
        String enu = "enum";
        Optional<CoreError> error = checker.validate(enu);

        assertFalse(error.isEmpty());
        assertEquals(error.get().getField(), "User choice");
        assertEquals(error.get().getMessage(), "must be PATIENT, MEDICATION_NAME or QUANTITY!");
    }

    @Test
    public void shouldNotReturnError(){
        String enu = "PATIENT";
        Optional<CoreError> error = checker.validate(enu);
        assertTrue(error.isEmpty());
    }

    @Test
    public void shouldNotReturn2Error(){
        String enu = "MEDICATION_NAME";
        Optional<CoreError> error = checker.validate(enu);
        assertTrue(error.isEmpty());
    }

    @Test
    public void shouldNotReturn3Error(){
        String enu = "QUANTITY";
        Optional<CoreError> error = checker.validate(enu);
        assertTrue(error.isEmpty());
    }
}