package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.SearchVisitorsRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchVisitorRequestFieldValidatorTest {

    private final SearchVisitorsRequestFieldValidator validator =
            new SearchVisitorsRequestFieldValidator();

    @Test
    public void shouldNotReturnErrorsListWhenAllFieldsAreProvided() {
        SearchVisitorsRequest request = new SearchVisitorsRequest("name", "surname");
        List<CoreError> errors = validator.validatorSearchField(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorIfSearchFieldIsEmpty() {
        SearchVisitorsRequest request = new SearchVisitorsRequest(null, "");
        List<CoreError> errors = validator.validatorSearchField(request);
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(0).getField(), "name");
        assertEquals(errors.get(0).getMessageError(), "can not be empty!");
        assertEquals(errors.get(1).getField(), "surname");
        assertEquals(errors.get(1).getMessageError(), "can not be empty!");
    }

    @Test
    public void shouldNotReturnErrorIfSurnameIsNotEmpty() {
        SearchVisitorsRequest request = new SearchVisitorsRequest(null, "surname");
        List<CoreError> errors = validator.validatorSearchField(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorIfNameIsNotEmpty() {
        SearchVisitorsRequest request = new SearchVisitorsRequest("name", null);
        List<CoreError> errors = validator.validatorSearchField(request);
        assertEquals(errors.size(), 0);
    }

}