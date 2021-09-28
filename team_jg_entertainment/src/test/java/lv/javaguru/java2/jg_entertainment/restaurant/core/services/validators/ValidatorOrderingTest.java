package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.OrderingTable;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorOrderingTest {

    private ValidatorOrdering validator = new ValidatorOrdering();

    @Test
    public void shouldReturnErrorWhenOrderDirectionNull() {
        OrderingTable ordering = new OrderingTable("title", null);
        List<CoreError> errorList = validator.validator(ordering);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "orderDirection");
        assertEquals(errorList.get(0).getMessageError(), "can not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenOrderByEmpty() {
        OrderingTable ordering = new OrderingTable(null, "ASCENDING");
        List<CoreError> errorList = validator.validator(ordering);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "orderBy");
        assertEquals(errorList.get(0).getMessageError(), "can not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenNotValidValueInOrderBy() {
        OrderingTable ordering = new OrderingTable("notValidValue", "ASCENDING");
        List<CoreError> errorList = validator.validator(ordering);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "orderBy");
        assertEquals(errorList.get(0).getMessageError(), "might contain title table only!");
    }

    @Test
    public void shouldReturnErrorWhenNotValidValueInOrderDirection(){
        OrderingTable ordering = new OrderingTable("title", "notValidValue");
        List<CoreError>errorList = validator.validator(ordering);
        assertEquals(errorList.size(),1);
        assertEquals(errorList.get(0).getField(), "orderDirection");
        assertEquals(errorList.get(0).getMessageError(), "might contain 'ASCENDING' or 'DESCENDING' only!");
    }
}