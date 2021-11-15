package lv.javaguru.java2.hospital.patient.core.services.checkers;

import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PatientLongNumCheckerTest {

    PatientLongNumChecker checker = new PatientLongNumChecker();

    @Test
    public void shouldReturnError(){
        String ID = "1assda";
        Optional<CoreError> error = checker.validate(ID, "ID");
        assertFalse(error.isEmpty());
        assertEquals(error.get().getField(), "ID");
        assertEquals(error.get().getDescription(), "must be a number!");
    }

    @Test
    public void shouldNotReturnError(){
        String ID = "1";
        Optional<CoreError> error = checker.validate(ID, "ID");
        assertTrue(error.isEmpty());
    }
}