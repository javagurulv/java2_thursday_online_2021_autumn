package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.AddVisitorRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddVisitorValidatorTest {

    AddVisitorValidator visitorValidator = new AddVisitorValidator();

    @Test
    public void coreErrorsEmptyName() {
        AddVisitorRequest request = new AddVisitorRequest("", "Plotnikova", "325");
        List<CoreError> coreErrorList = visitorValidator.coreErrors(request);
        assertEquals(coreErrorList.size(), 1);
        assertEquals(coreErrorList.get(0).getField(), "name visitors");
        assertEquals(coreErrorList.get(0).getMessageError(), "Shouldn't be empty");
    }

    @Test
    public void coreErrorsEmptySurname() {
        AddVisitorRequest request = new AddVisitorRequest("Nika", "", "325");
        List<CoreError> coreErrorList = visitorValidator.coreErrors(request);
        assertEquals(coreErrorList.size(), 1);
        assertEquals(coreErrorList.get(0).getField(), "surname");
        assertEquals(coreErrorList.get(0).getMessageError(), "Shouldn't be empty");
    }
}