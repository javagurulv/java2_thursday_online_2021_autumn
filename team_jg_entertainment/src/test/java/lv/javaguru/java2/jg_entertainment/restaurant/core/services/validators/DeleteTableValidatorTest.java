package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.RemoveTableRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.CoreError;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class DeleteTableValidatorTest {

    RemoveTableValidator removeTableValidator = new RemoveTableValidator();

    @Test
    public void shouldReturnEmptyListError() {
        RemoveTableRequest request = new RemoveTableRequest(32L);
        List<CoreError> errorList = removeTableValidator.coreErrors(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void coreErrorsHaveNullId() {
        RemoveTableRequest request = new RemoveTableRequest(null);
        List<CoreError> errorList = removeTableValidator.coreErrors(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "idTable");
        assertEquals(errorList.get(0).getMessageError(), "Can't be null");
    }
}
