package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_users;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.DeleteUserRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeleteUserValidatorTest {

    DeleteUserValidator visitorValidator = new DeleteUserValidator();

    @Test
    public void coreErrorsNotEmpty() {
        DeleteUserRequest request = new DeleteUserRequest(32L, "Nika");
        List<CoreError> coreErrorList = visitorValidator.coreErrors(request);
        assertTrue(coreErrorList.isEmpty());
    }

    @Test
    public void coreErrorsHaveNullId() {
        DeleteUserRequest request = new DeleteUserRequest(null, "Nika");
        List<CoreError> coreErrorList = visitorValidator.coreErrors(request);
        assertEquals(coreErrorList.size(), 1);
        assertEquals(coreErrorList.get(0).getField(), "id visitor");
        assertEquals(coreErrorList.get(0).getMessageError(), "Can't be null");
    }

    @Test
    public void coreErrorsHaveEmptyNameVisitor() {
        DeleteUserRequest request = new DeleteUserRequest(3289L, "");
        List<CoreError> coreErrorList = visitorValidator.coreErrors(request);
        assertEquals(coreErrorList.size(), 1);
        assertEquals(coreErrorList.get(0).getField(), "name visitor");
        assertEquals(coreErrorList.get(0).getMessageError(), "Can't be empty");
    }

    @Test
    public void coreErrorsHaveNotCorrectInformationInAllField() {
        DeleteUserRequest request = new DeleteUserRequest(null, "");
        List<CoreError> coreErrorList = visitorValidator.coreErrors(request);
        assertEquals(coreErrorList.size(), 2);
        assertEquals(coreErrorList.get(0).getField(), "id visitor");
        assertEquals(coreErrorList.get(0).getMessageError(), "Can't be null");
        assertEquals(coreErrorList.get(1).getField(), "name visitor");
        assertEquals(coreErrorList.get(1).getMessageError(), "Can't be empty");
    }


}