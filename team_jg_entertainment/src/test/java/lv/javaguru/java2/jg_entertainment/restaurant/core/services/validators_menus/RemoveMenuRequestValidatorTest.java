package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_menus;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.RemoveMenuRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.CoreError;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class RemoveMenuRequestValidatorTest {

    RemoveMenuRequestValidator validator = new RemoveMenuRequestValidator();

    @Test
    public void shouldReturnEmptyListError() {
        RemoveMenuRequest request = new RemoveMenuRequest(32L);
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void coreErrorsHaveNullId() {
        RemoveMenuRequest request = new RemoveMenuRequest(null);
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "number");
        assertEquals(errorList.get(0).getMessage(), "Must not be empty!");
    }
}