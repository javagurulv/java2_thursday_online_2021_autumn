package lv.javaguru.java2.hospital.patient.core.services.checkers;

import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class PersonalCodeCheckerTest {

    private final PersonalCodeChecker checker = new PersonalCodeChecker();

    @Test
    public void shouldReturnPersonalCodeError(){
        String personalCode = "12qwe34";
        Optional<CoreError> error = checker.execute(personalCode);
        assertFalse(error.isEmpty());
        assertEquals(error.get().getField(), "Personal code");
        assertEquals(error.get().getDescription(), "must consist from numbers only!");
    }

    @Test
    public void shouldNotReturnPersonalCodeError(){
        String personalCode = "1234";
        Optional<CoreError> error = checker.execute(personalCode);
        assertTrue(error.isEmpty());
    }
}