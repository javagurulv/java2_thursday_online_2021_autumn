package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_users;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.Ordering;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderingValidatorTest {

    private OrderingValidator validator = new OrderingValidator();

    @Test
    public void shouldReturnErrorWhenPresentEmptyOrderDirection() {
        Ordering ordering = new Ordering("name", null);
        List<CoreError> errors = validator.validator(ordering);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderDirection");
        assertEquals(errors.get(0).getMessageError(), "can not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPresentEmptyOrderBy() {
        Ordering ordering = new Ordering(null, "ASCENDING");
        List<CoreError> errors = validator.validator(ordering);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderBy");
        assertEquals(errors.get(0).getMessageError(), "can not be empty!");
    }

    @Test
    public void shouldErrorWhenNotValidValueInOrderBy() {
        Ordering ordering = new Ordering("notValidValue", "ASCENDING");
        List<CoreError> errors = validator.validator(ordering);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderBy");
        assertEquals(errors.get(0).getMessageError(), "might contain 'name' or 'surname' only!");
    }

    @Test
    public void shouldReturnErrorWhenNotValidValueInOrderDirection() {
        Ordering ordering = new Ordering("name", "notValidValue");
        List<CoreError> errors = validator.validator(ordering);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderDirection");
        assertEquals(errors.get(0).getMessageError(), "might contain 'ASCENDING' or 'DESCENDING' only!");
    }


}