package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_menus;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.AddMenuRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.CoreError;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class AddMenuValidatorTest {
 /*   private AddMenuValidator menuValidator = new AddMenuValidator();

    @Test
    public void shouldReturnEmptyList() {
        AddMenuRequest request = new AddMenuRequest("title", "description", 1.00);
        List<CoreError> errorList = menuValidator.validate(request);
        assertTrue(errorList.isEmpty());
    }
    @Test
    public void shouldReturnTitleError() {
        AddMenuRequest request = new AddMenuRequest("", "description", 1.00);
        List<CoreError> errorList = menuValidator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "title");
        assertEquals(errorList.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnDescriptionError() {
        AddMenuRequest request = new AddMenuRequest("title", "", 1.00);
        List<CoreError> errorList = menuValidator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "description");
        assertEquals(errorList.get(0).getMessage(), "Must not be empty!");
    }

   @Test
    public void shouldReturnPriceError() {
        AddMenuRequest request = new AddMenuRequest("title", "description", 0.00);
        List<CoreError> errorList = menuValidator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "price");
        assertEquals(errorList.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnTitleAndAuthorErrors() {
        AddMenuRequest request = new AddMenuRequest("", "", 1.00);
        List<CoreError> errorList = menuValidator.validate(request);
        assertEquals(errorList.size(), 2);
        assertEquals(errorList.get(0).getField(), "title");
        assertEquals(errorList.get(0).getMessage(), "Must not be empty!");
        assertEquals(errorList.get(1).getField(), "description");
        assertEquals(errorList.get(1).getMessage(), "Must not be empty!");
    }*/
}