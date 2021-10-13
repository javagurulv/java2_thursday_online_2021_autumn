package lv.javaguru.java2.hospital;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PatientEnumCheckerTest {

    PatientEnumChecker checker = new PatientEnumChecker();

    @Test
    public void enumShouldNotBeNullWithInputName(){
        assertNotNull(checker.validateEnum("name"));
    }

    @Test
    public void enumShouldNotBeNullWithInputSurname(){
        assertNotNull(checker.validateEnum("surname"));
    }

    @Test
    public void enumShouldNotBeNullWithInputPersonalCode(){
        assertNotNull(checker.validateEnum("personal_code"));
    }

    @Test
    public void enumShouldBeNull(){
        assertNull(checker.validateEnum("input"));
    }
}
