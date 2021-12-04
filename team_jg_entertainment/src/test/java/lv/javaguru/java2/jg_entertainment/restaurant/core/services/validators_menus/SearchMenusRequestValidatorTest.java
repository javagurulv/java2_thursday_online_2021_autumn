package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_menus;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.SearchMenusRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchMenusRequestValidatorTest {

    private final SearchMenusRequestValidator validator =
            new SearchMenusRequestValidator();

    @Test
    public void shouldNotReturnErrorsListWhenAllFieldsAreProvided() {
        SearchMenusRequest request = new SearchMenusRequest("title", "description");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsListWhenTitlesAreProvided() {
        SearchMenusRequest request = new SearchMenusRequest("title", null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsListWhenDescriptionAreProvided() {
        SearchMenusRequest request = new SearchMenusRequest(null, "description");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsListWhenAllAreProvided() {
        SearchMenusRequest request = new SearchMenusRequest(null, null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(0).getField(), "description");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
        assertEquals(errors.get(1).getField(), "author");
        assertEquals(errors.get(1).getMessage(), "Must not be empty!");
    }
}