package lv.javaguru.java2.hospital.prescription.core.services.validators;

import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import org.junit.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class IDNumCheckerTest {

    private final IDNumChecker checker = new IDNumChecker();

    @Test
    public void shouldIDNumReturnError(){
        String input = "str";
        Optional<CoreError> error = checker.validate(input);

        assertFalse(error.isEmpty());
        assertEquals(error.get().getField(), "ID");
        assertEquals(error.get().getMessage(), "must be a number!");
    }

    @Test
    public void shouldNotReturnError(){
        String input = "8";
        Optional<CoreError> error = checker.validate(input);
        assertTrue(error.isEmpty());
    }
}