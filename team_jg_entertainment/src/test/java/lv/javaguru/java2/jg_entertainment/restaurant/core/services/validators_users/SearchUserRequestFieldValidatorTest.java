package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_users;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.SearchUsersRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchUserRequestFieldValidatorTest {

    private final SearchUsersRequestFieldValidator validator =
            new SearchUsersRequestFieldValidator();

    @Test
    public void shouldNotReturnErrorsListWhenAllFieldsAreProvided() {
        SearchUsersRequest request = new SearchUsersRequest("name", "surname");
        List<CoreError> errors = validator.validatorSearchField(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorIfSearchFieldIsEmpty() {
        SearchUsersRequest request = new SearchUsersRequest(null, "");
        List<CoreError> errors = validator.validatorSearchField(request);
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(0).getField(), "name");
        assertEquals(errors.get(0).getMessageError(), "can not be empty!");
        assertEquals(errors.get(1).getField(), "surname");
        assertEquals(errors.get(1).getMessageError(), "can not be empty!");
    }

    @Test
    public void shouldNotReturnErrorIfSurnameIsNotEmpty() {
        SearchUsersRequest request = new SearchUsersRequest(null, "surname");
        List<CoreError> errors = validator.validatorSearchField(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorIfNameIsNotEmpty() {
        SearchUsersRequest request = new SearchUsersRequest("name", null);
        List<CoreError> errors = validator.validatorSearchField(request);
        assertEquals(errors.size(), 0);
    }

}