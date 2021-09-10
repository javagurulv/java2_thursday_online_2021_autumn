package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.DeleteVisitorRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeleteVisitorValidatorTest {
    DeleteVisitorValidator visitorValidator = new DeleteVisitorValidator();

    @Test
    public void coreErrorsNotEmpty() {
        DeleteVisitorRequest request = new DeleteVisitorRequest(3256489742L, "Nika");
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
        DeleteVisitorRequest request = new DeleteVisitorRequest(326589L, "");
        List<CoreError> coreErrorList = visitorValidator.coreErrors(request);
        assertEquals(coreErrorList.size(), 1);
        assertEquals(coreErrorList.get(0).getField(), "name visitor");
        assertEquals(coreErrorList.get(0).getMessageError(), "Can't be empty");
    }
    @Test
    public void coreErrorsHaveNull() {
        DeleteVisitorRequest request = new DeleteVisitorRequest(null, "Nika");
        List<CoreError> coreErrorList = visitorValidator.coreErrors(request);
        assertEquals(coreErrorList.size(), 1);
        assertEquals(coreErrorList.get(0).getField(), "id visitor");
        assertEquals(coreErrorList.get(0).getMessageError(), "Can't be null");
    }
}