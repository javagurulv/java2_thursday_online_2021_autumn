package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_users;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.AddUserRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddUserValidatorTest {

    AddUserValidator visitorValidator = new AddUserValidator();

    @Test
    public void coreErrorsEmptyName() {
        AddUserRequest request = new AddUserRequest("", "Plotnikova", "325");
        List<CoreError> coreErrorList = visitorValidator.coreErrors(request);
        assertEquals(coreErrorList.size(), 1);
        assertEquals(coreErrorList.get(0).getField(), "name");
        assertEquals(coreErrorList.get(0).getMessageError(), "Shouldn't be empty");
    }

    @Test
    public void coreErrorsEmptySurname() {
        AddUserRequest request = new AddUserRequest("Nika", "", "325");
        List<CoreError> coreErrorList = visitorValidator.coreErrors(request);
        assertEquals(coreErrorList.size(), 1);
        assertEquals(coreErrorList.get(0).getField(), "surname");
        assertEquals(coreErrorList.get(0).getMessageError(), "Shouldn't be empty");
    }
}