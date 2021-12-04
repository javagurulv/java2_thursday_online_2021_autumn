package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_menus;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.OrderingMenu;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.menus.SearchMenusRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.menus.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderingValidatorTest {

    private final SearchMenusRequestValidator validator = new SearchMenusRequestValidator();

    @Test
    public void shouldReturnErrorWhenPresentEmptyOrderDirection() {
        OrderingMenu menu = new OrderingMenu("title", null);
        SearchMenusRequest request = new SearchMenusRequest("title", "description", menu);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderDirection");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPresentEmptyOrderBy() {
        OrderingMenu orderingMenu = new OrderingMenu(null, "ASCENDING");
        SearchMenusRequest request = new SearchMenusRequest("title", "description", orderingMenu);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderBy");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldErrorWhenNotValidValueInOrderBy() {
        OrderingMenu orderingMenu = new OrderingMenu("null", "ASCENDING");
        SearchMenusRequest request = new SearchMenusRequest("title", "description", orderingMenu);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderBy");
        assertEquals(errors.get(0).getMessage(), "Must contain 'description' or 'title' only!");
    }

    @Test
    public void shouldReturnErrorWhenNotValidValueInOrderDirection() {
        OrderingMenu orderingMenu = new OrderingMenu("title", "notValidValue");
        SearchMenusRequest request = new SearchMenusRequest("title", "description", orderingMenu);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderDirection");
        assertEquals(errors.get(0).getMessage(), "Must contain 'ASCENDING' or 'DESCENDING' only!");
    }
}
