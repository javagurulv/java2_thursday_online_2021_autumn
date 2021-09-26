package lv.javaguru.java2.core.validations;


import lv.javaguru.java2.core.requests.Find.Ordering;
import lv.javaguru.java2.core.responce.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SpecialistOrderingValidatorTest {

    private SpecialistOrderingValidator validator = new SpecialistOrderingValidator();

    @Test
    public void shouldReturnErrorWhenOrderDirectionAreEmpty() {
        Ordering ordering = new Ordering("Surname", null);
        List<CoreError> errors = validator.validate(ordering);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderDirection");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenOrderByAreEmpty() {
        Ordering ordering = new Ordering(null, "ASCENDING");
        List<CoreError> errors = validator.validate(ordering);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderBy");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenOrderByContainNotValidValue() {
        Ordering ordering = new Ordering("notValidValue", "ASCENDING");
        List<CoreError> errors = validator.validate(ordering);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderBy");
        assertEquals(errors.get(0).getMessage(), "Must contain 'Name' or 'Surname' or 'Profession' only!");
    }

    @Test
    public void shouldReturnErrorWhenOrderDirectionContainNotValidValue() {
        Ordering ordering = new Ordering("Surname", "notValidValue");
        List<CoreError> errors = validator.validate(ordering);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderDirection");
        assertEquals(errors.get(0).getMessage(), "Must contain 'ASCENDING' or 'DESCENDING' only!");
    }

}