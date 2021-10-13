package lv.javaguru.java2.hospital;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PatientEnumCheckerTest {

    PatientEnumChecker checker = new PatientEnumChecker();

    @Test
    public void enumShouldNotBeNull(){
        assertNotNull(checker.validateEnum("", "name"));
    }
}