package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.DeleteVisitorRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeleteVisitorValidatorTest {

    DeleteVisitorValidator visitorValidator = new DeleteVisitorValidator();

    @Test
    public void coreErrorsNotEmpty() {
        DeleteVisitorRequest request = new DeleteVisitorRequest(32L, "Nika");
        List<CoreError> coreErrorList = visitorValidator.coreErrors(request);
        assertTrue(coreErrorList.isEmpty());
    }

    @Test
    public void coreErrorsHaveNullId() {
        DeleteVisitorRequest request = new DeleteVisitorRequest(null, "Nika");
        List<CoreError> coreErrorList = visitorValidator.coreErrors(request);
        assertEquals(coreErrorList.size(), 1);
        assertEquals(coreErrorList.get(0).getField(), "id visitor");
        assertEquals(coreErrorList.get(0).getMessageError(), "Can't be null");
    }

    @Test
    public void coreErrorsHaveEmptyNameVisitor() {
        DeleteVisitorRequest request = new DeleteVisitorRequest(3289L, "");
        List<CoreError> coreErrorList = visitorValidator.coreErrors(request);
        assertEquals(coreErrorList.size(), 1);
        assertEquals(coreErrorList.get(0).getField(), "name visitor");
        assertEquals(coreErrorList.get(0).getMessageError(), "Can't be empty");
    }

    @Test
    public void coreErrorsHaveNotCorrectInformationInAllField() {
        DeleteVisitorRequest request = new DeleteVisitorRequest(null, "");
        List<CoreError> coreErrorList = visitorValidator.coreErrors(request);
        assertEquals(coreErrorList.size(), 2);
        assertEquals(coreErrorList.get(0).getField(), "id visitor");
        assertEquals(coreErrorList.get(0).getMessageError(), "Can't be null");
        assertEquals(coreErrorList.get(1).getField(), "name visitor");
        assertEquals(coreErrorList.get(1).getMessageError(), "Can't be empty");
    }


}