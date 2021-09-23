package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validatorsMenus;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.AddMenuRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.CoreError;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddMenuValidatorTest {
    private AddMenuValidator menuValidator = new AddMenuValidator();

    @Test
    public void shouldReturnEmptyList() {
        AddMenuRequest request = new AddMenuRequest("title", "description", 1);
        List<CoreError> errorList = menuValidator.validate(request);
        assertTrue(errorList.isEmpty());

    }
}