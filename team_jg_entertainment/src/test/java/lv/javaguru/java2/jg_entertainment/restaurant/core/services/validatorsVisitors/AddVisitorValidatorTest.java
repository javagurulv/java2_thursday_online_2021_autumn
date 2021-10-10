package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validatorsVisitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.AddVisitorRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddVisitorValidatorTest {

    AddVisitorValidator visitorValidator = new AddVisitorValidator();

    @Test
    public void coreErrorsNotEmpty() {
        AddVisitorRequest request = new AddVisitorRequest("Nika", "Plotnikova", 3256489742L);
        List<CoreError> coreErrorList = visitorValidator.coreErrors(request);
        assertTrue(coreErrorList.isEmpty());
    }

    @Test
    public void coreErrorsEmptyName() {
        AddVisitorRequest request = new AddVisitorRequest("", "Plotnikova", 3256489742L);
        List<CoreError> coreErrorList = visitorValidator.coreErrors(request);
        assertEquals(coreErrorList.size(), 1);
        assertEquals(coreErrorList.get(0).getField(), "name visitors");
        assertEquals(coreErrorList.get(0).getMessageError(), "Shouldn't be empty");
    }

    @Test
    public void coreErrorsEmptySurname() {
        AddVisitorRequest request = new AddVisitorRequest("Nika", "", 3256489742L);
        List<CoreError> coreErrorList = visitorValidator.coreErrors(request);
        assertEquals(coreErrorList.size(), 1);
        assertEquals(coreErrorList.get(0).getField(), "surname");
        assertEquals(coreErrorList.get(0).getMessageError(), "Shouldn't be empty");
    }

    @Test
    public void coreErrorsNotCorrectTelephoneNumber() {
        AddVisitorRequest request = new AddVisitorRequest("Nika", "Plotnikova", null);
        List<CoreError> coreErrorList = visitorValidator.coreErrors(request);
        assertEquals(coreErrorList.size(), 1);
        assertEquals(coreErrorList.get(0).getField(), "telephone");
        assertEquals(coreErrorList.get(0).getMessageError(), "not correct, telephone can't be null");
    }

    @Test
    public void coreErrorsAllFieldNotCorrect() {
        AddVisitorRequest request = new AddVisitorRequest("", "", null);
        List<CoreError> coreErrorList = visitorValidator.coreErrors(request);
        assertEquals(coreErrorList.size(), 3);
        assertEquals(coreErrorList.get(0).getField(), "name visitors");
        assertEquals(coreErrorList.get(0).getMessageError(), "Shouldn't be empty");
        assertEquals(coreErrorList.get(1).getField(), "surname");
        assertEquals(coreErrorList.get(1).getMessageError(), "Shouldn't be empty");
        assertEquals(coreErrorList.get(2).getField(), "telephone");
        assertEquals(coreErrorList.get(2).getMessageError(), "not correct, telephone can't be null");
    }
}