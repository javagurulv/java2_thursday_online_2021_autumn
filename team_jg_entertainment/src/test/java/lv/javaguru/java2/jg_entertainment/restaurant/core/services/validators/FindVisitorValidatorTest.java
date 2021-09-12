package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.RequestFindVisitorInformation;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FindVisitorValidatorTest {
    ValidatorFindVisitor visitorValidator = new ValidatorFindVisitor();

    @Test
    void coreErrorsNotEmpty() {
        RequestFindVisitorInformation request = new RequestFindVisitorInformation(2L, "Olga");
        List<CoreError> coreErrorList = visitorValidator.coreErrors(request);
        assertTrue(coreErrorList.isEmpty());
    }

    @Test
    void coreErrorsHaveEmptyName() {
        RequestFindVisitorInformation request = new RequestFindVisitorInformation(1L, "");
        List<CoreError> coreErrorList = visitorValidator.coreErrors(request);
        assertEquals(coreErrorList.size(), 1);
        assertEquals(coreErrorList.get(0).getField(), "name visitors");
        assertEquals(coreErrorList.get(0).getMessageError(), "couldn't be empty");
    }

    @Test
    void coreErrorsHaveNullTelephoneNumber() {
        RequestFindVisitorInformation request = new RequestFindVisitorInformation(null, "Olga");
        List<CoreError> coreErrorList = visitorValidator.coreErrors(request);
        assertEquals(coreErrorList.size(), 1);
        assertEquals(coreErrorList.get(0).getField(), "id visitors");
        assertEquals(coreErrorList.get(0).getMessageError(), "couldn't be null");
    }

    @Test
    void coreErrorsIfPresentEmptyFieldAndNullNumber() {
        RequestFindVisitorInformation request = new RequestFindVisitorInformation(null, "");
        List<CoreError> coreErrorList = visitorValidator.coreErrors(request);
        assertEquals(coreErrorList.size(), 2);
        assertEquals(coreErrorList.get(0).getField(), "name visitors");
        assertEquals(coreErrorList.get(0).getMessageError(), "couldn't be empty");
        assertEquals(coreErrorList.get(1).getField(), "id visitors");
        assertEquals(coreErrorList.get(1).getMessageError(), "couldn't be null");
    }
}