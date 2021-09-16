package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.Ordering;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.SearchVisitorsRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchVisitorsRequestValidatorTest {

    private SearchVisitorsRequestValidator validator = new SearchVisitorsRequestValidator();

    @Test
    public void shouldReturnEmpty() {
        SearchVisitorsRequest request = new SearchVisitorsRequest("Alina", "Plotnikova");
        List<CoreError> errors = validator.validator(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnEmptyName() {
        SearchVisitorsRequest request = new SearchVisitorsRequest(null, "Plotnikova");
        List<CoreError> errors = validator.validator(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void emptySurname() {
        SearchVisitorsRequest request = new SearchVisitorsRequest("Lina", null);
        List<CoreError> errors = validator.validator(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void whenEmptyNameAndSurname() {
        SearchVisitorsRequest request = new SearchVisitorsRequest("", "");
        List<CoreError> errors = validator.validator(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(0).getField(), "name");
        assertEquals(errors.get(0).getMessageError(), "can not be empty!");
        assertEquals(errors.get(1).getField(), "surname");
        assertEquals(errors.get(1).getMessageError(), "can not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenEmptyOrderDirection() {
        Ordering ordering = new Ordering("name", null);
        SearchVisitorsRequest request = new SearchVisitorsRequest("name", "surname", ordering);
        List<CoreError> errors = validator.validator(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderDirection");
        assertEquals(errors.get(0).getMessageError(), "can not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenEmptyOrderBy() {
        Ordering ordering = new Ordering(null, "ASCENDING");
        SearchVisitorsRequest request = new SearchVisitorsRequest("name", "surname", ordering);
        List<CoreError> errors = validator.validator(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderBy");
        assertEquals(errors.get(0).getMessageError(), "can not be empty!");
    }

    @Test
    public void shouldErrorWhenNotValidValueInOrderBy() {
        Ordering ordering = new Ordering("notValidValue", "ASCENDING");
        SearchVisitorsRequest request = new SearchVisitorsRequest("name", "surname", ordering);
        List<CoreError> errors = validator.validator(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderBy");
        assertEquals(errors.get(0).getMessageError(), "might contain 'name' or 'surname' only!");
    }

    @Test
    public void shouldReturnErrorWhenNotValidValueInOrderDirection() {
        Ordering ordering = new Ordering("name", "notValidValue");
        SearchVisitorsRequest request = new SearchVisitorsRequest("name", "surname", ordering);
        List<CoreError> errors = validator.validator(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderDirection");
        assertEquals(errors.get(0).getMessageError(), "might contain 'ASCENDING' or 'DESCENDING' only!");
    }
}