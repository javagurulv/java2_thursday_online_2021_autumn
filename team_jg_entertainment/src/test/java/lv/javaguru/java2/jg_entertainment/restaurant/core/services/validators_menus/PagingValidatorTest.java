package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_menus;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.PagingMenu;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.SearchMenusRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PagingValidatorTest {
    private final SearchMenusRequestValidator validator = new SearchMenusRequestValidator();

    @Test
    public void shouldReturnErrorWhenPageNumberAreEmpty() {
        PagingMenu pagingMenu = new PagingMenu(null, 1);
        SearchMenusRequest request = new SearchMenusRequest("title", "description", pagingMenu);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageNumber");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPageSizeAreEmpty() {
        PagingMenu pagingMenu = new PagingMenu(1, null);
        SearchMenusRequest request = new SearchMenusRequest("title", "description", pagingMenu);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageSize");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPageNumberContainNotValidValue() {
        PagingMenu pagingMenu = new PagingMenu(0, 1);
        SearchMenusRequest request = new SearchMenusRequest("title", "description", pagingMenu);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageNumber");
        assertEquals(errors.get(0).getMessage(), "Must be greater then 0!");
    }
    @Test
    public void shouldReturnErrorWhenPageSizeContainNotValidValue() {
        PagingMenu pagingMenu = new PagingMenu(1, 0);
        SearchMenusRequest request = new SearchMenusRequest("title", "description", pagingMenu);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageSize");
        assertEquals(errors.get(0).getMessage(), "Must be greater then 0!");
    }
}
