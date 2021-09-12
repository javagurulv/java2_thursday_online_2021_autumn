package lv.javaguru.java2.core.validations;

import lv.javaguru.java2.core.requests.Find.FindClientsRequest;
import lv.javaguru.java2.core.requests.Find.Ordering;
import lv.javaguru.java2.core.requests.Find.Paging;
import lv.javaguru.java2.core.responce.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FindClientsValidatorTest {

    private FindClientsRequestValidator validator = new FindClientsRequestValidator();

    @Test
    public void shouldReturnEmptyList() {

        FindClientsRequest request = new FindClientsRequest(1L,"NAME","SURNAME");
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnErrorsWhenIdNameSurnameIsNotProvided(){
        FindClientsRequest request = new FindClientsRequest((Long) null,null, null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(),3);
        assertEquals(errors.get(0).getField(),"ID");
        assertEquals(errors.get(0).getMessage(),"Must not be empty!");
        assertEquals(errors.get(1).getField(),"NAME");
        assertEquals(errors.get(1).getMessage(),"Must not be empty!");
        assertEquals(errors.get(2).getField(),"SURNAME");
        assertEquals(errors.get(2).getMessage(),"Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenIdIsNotProvided() {

        FindClientsRequest request = new FindClientsRequest(null,"NAME","SURNAME");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(),1);
        assertEquals(errors.get(0).getField(),"ID");
        assertEquals(errors.get(0).getMessage(),"Must not be empty!");
    }
//
    @Test
    public void shouldReturnErrorWhenNameIsNotProvided() {

        FindClientsRequest request = new FindClientsRequest(1L,null,"SURNAME");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(),1);
        assertEquals(errors.get(0).getField(),"NAME");
        assertEquals(errors.get(0).getMessage(),"Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenSurnameIsNotProvided() {

        FindClientsRequest request = new FindClientsRequest(1L,"NAME",null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(),1);
        assertEquals(errors.get(0).getField(),"SURNAME");
        assertEquals(errors.get(0).getMessage(),"Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenOrderDirectionAreEmpty() {
        Ordering ordering = new Ordering("NAME",null);
        FindClientsRequest request = new FindClientsRequest(1L,"NAME","SURNAME",ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(),1);
        assertEquals(errors.get(0).getField(),"orderDirection");
        assertEquals(errors.get(0).getMessage(),"Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenOrderByAreEmpty() {
        Ordering ordering = new Ordering(null,"ASCENDING");
        FindClientsRequest request = new FindClientsRequest(1L,"NAME","SURNAME",ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(),1);
        assertEquals(errors.get(0).getField(),"orderBy");
        assertEquals(errors.get(0).getMessage(),"Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenOrderByContainNotValidValue() {
        Ordering ordering = new Ordering("notValidValue", "ASCENDING");
        FindClientsRequest request = new FindClientsRequest(1L,"NAME","SURNAME",ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderBy");
        assertEquals(errors.get(0).getMessage(), "Must contain id,name or surname");
    }

    @Test
    public void shouldReturnErrorWhenOrderDirectionContainNotValidValue() {
        Ordering ordering = new Ordering("ID", "notValidValue");
        FindClientsRequest request = new FindClientsRequest(1L,"NAME","SURNAME",ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderDirection");
        assertEquals(errors.get(0).getMessage(), "Must contain 'ASCENDING' or 'DESCENDING' only!");
    }

    @Test
    public void shouldReturnErrorWhenPageNumberContainNotValidValue() {
        Paging paging = new Paging(0, 1);
        FindClientsRequest request = new FindClientsRequest(1L,"NAME","SURNAME",paging);

        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageNumber");
        assertEquals(errors.get(0).getMessage(), "Must be greater then 0!");
    }

    @Test
    public void shouldReturnErrorWhenPageSizeContainNotValidValue() {
        Paging paging = new Paging(1, 0);
        FindClientsRequest request = new FindClientsRequest(1L,"NAME","SURNAME",paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageSize");
        assertEquals(errors.get(0).getMessage(), "Must be greater then 0!");
    }

    @Test
    public void shouldReturnErrorWhenPageNumberAreEmpty() {
        Paging paging = new Paging(null, 1);
        FindClientsRequest request = new FindClientsRequest(1L,"NAME","SURNAME",paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageNumber");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPageSizeAreEmpty() {
        Paging paging = new Paging(1, null);
        FindClientsRequest request = new FindClientsRequest(1L,"NAME","SURNAME",paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageSize");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }



}