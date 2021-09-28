package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators;


import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.PagingTable;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorPagingTest {

    private ValidatorPaging validator = new ValidatorPaging();

    @Test
    public void shouldReturnErrorWhenPageNumberNotValidValue() {
        PagingTable paging = new PagingTable(0, 1);
        List<CoreError> errorList = validator.validatorPaging(paging);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "pageNumber");
        assertEquals(errorList.get(0).getMessageError(), "must be greater than 0!");
    }

    @Test
    public void shouldReturnErrorWhenPageSizeNotValidValue() {
        PagingTable paging = new PagingTable(1, 0);
        List<CoreError> errorList = validator.validatorPaging(paging);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "pageSize");
        assertEquals(errorList.get(0).getMessageError(), "must be greater than 0!");
    }

    @Test
    public void shouldReturnErrorWhenPageNumberEmpty() {
        PagingTable paging = new PagingTable(null, 1);
        List<CoreError> errorList = validator.validatorPaging(paging);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "pageNumber");
        assertEquals(errorList.get(0).getMessageError(), "must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPageSizeEmpty(){
        PagingTable paging = new PagingTable(1, null);
        List<CoreError>errorList = validator.validatorPaging(paging);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "pageSize");
        assertEquals(errorList.get(0).getMessageError(), "must not be empty!");
    }
}