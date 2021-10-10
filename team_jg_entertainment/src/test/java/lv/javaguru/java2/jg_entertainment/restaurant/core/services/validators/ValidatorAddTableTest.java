package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.AddTableRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorAddTableTest {

    ValidatorAddTable tableValidator = new ValidatorAddTable();

    @Test
    void coreErrorsNotEmpty() {
        AddTableRequest addTableRequest = new AddTableRequest("Table 1",4,25);
        List<CoreError> coreErrorList = tableValidator.validate(addTableRequest);
        assertTrue(coreErrorList.isEmpty());
    }

    @Test
    void coreErrorEmptyTitleTable() {
        AddTableRequest addTableRequest = new AddTableRequest("",4,25);
        List<CoreError> coreErrorList = tableValidator.validate(addTableRequest);
        assertEquals(coreErrorList.size(),1);
        assertEquals(coreErrorList.get(0).getField(),"table title");
        assertEquals(coreErrorList.get(0).getMessageError(),"Shouldn't be empty");
    }
}