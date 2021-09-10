package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.FindVisitorInformationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FindVisitorValidatorTest {
    FindVisitorValidator visitorValidator = new FindVisitorValidator();

    @Test
    void coreErrorsNotEmpty() {
        FindVisitorInformationRequest request = new FindVisitorInformationRequest("Olga", 23655988L);
        List<CoreError> coreErrorList = visitorValidator.coreErrors(request);
        assertTrue(coreErrorList.isEmpty());
    }
    @Test
    void coreErrorsHaveEmptyName() {
        FindVisitorInformationRequest request = new FindVisitorInformationRequest("", 235896884L);
        List<CoreError>coreErrorList = visitorValidator.coreErrors(request);
        assertEquals(coreErrorList.size(), 1);
        assertEquals(coreErrorList.get(0).getField(), "name visitors");
        assertEquals(coreErrorList.get(0).getMessageError(), "couldn't be empty");
    }

    @Test
    void coreErrorsHaveNullTelephoneNumber() {
        FindVisitorInformationRequest request = new FindVisitorInformationRequest("Olga", null);
        List<CoreError>coreErrorList = visitorValidator.coreErrors(request);
        assertEquals(coreErrorList.size(), 1);
        assertEquals(coreErrorList.get(0).getField(), "id visitors");
        assertEquals(coreErrorList.get(0).getMessageError(), "couldn't be null");
    }
    @Test
    void coreErrorsIfPresentEmptyFildAndNullNumber() {
        FindVisitorInformationRequest request = new FindVisitorInformationRequest("", null);
        List<CoreError>coreErrorList = visitorValidator.coreErrors(request);
        assertEquals(coreErrorList.size(), 2);
        assertEquals(coreErrorList.get(0).getField(), "name visitors");
        assertEquals(coreErrorList.get(0).getMessageError(), "couldn't be empty");
        assertEquals(coreErrorList.get(1).getField(), "id visitors");
        assertEquals(coreErrorList.get(1).getMessageError(), "couldn't be null");
    }
}