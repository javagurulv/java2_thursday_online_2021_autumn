package lv.javaguru.java2.hospital.prescription.core.services.validators;

import lv.javaguru.java2.hospital.prescription.core.checkers.PrescriptionLongNumChecker;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import org.junit.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class PrescriptionLongNumCheckerTest {

    private final PrescriptionLongNumChecker checker = new PrescriptionLongNumChecker();

    @Test
    public void shouldIDNumReturnError(){
        String input = "str";
        Optional<CoreError> error = checker.validate(input, "Prescription ID");

        assertFalse(error.isEmpty());
        assertEquals(error.get().getField(), "Prescription ID");
        assertEquals(error.get().getMessage(), "must be a number!");
    }

    @Test
    public void shouldNotReturnError(){
        String input = "8";
        Optional<CoreError> error = checker.validate(input, "Prescription ID");
        assertTrue(error.isEmpty());
    }
}