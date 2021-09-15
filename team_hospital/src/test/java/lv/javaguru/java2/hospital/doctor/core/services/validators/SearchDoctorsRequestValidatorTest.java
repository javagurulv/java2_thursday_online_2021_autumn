package lv.javaguru.java2.hospital.doctor.core.services.validators;

import lv.javaguru.java2.hospital.doctor.core.requests.Ordering;
import lv.javaguru.java2.hospital.doctor.core.requests.Paging;
import lv.javaguru.java2.hospital.doctor.core.requests.SearchDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchDoctorsRequestValidatorTest {

    private SearchDoctorsRequestValidator validator = new SearchDoctorsRequestValidator();

    @Test
    public void shouldReturnEmptyList1() {
        SearchDoctorsRequest request = new SearchDoctorsRequest("123", "name", "surname", "speciality");
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnEmptyList2() {
        SearchDoctorsRequest request = new SearchDoctorsRequest("", "name", "surname", "speciality");
        List<CoreError> errorList = validator.validate(request);
        System.out.println(errorList);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnEmptyList3() {
        SearchDoctorsRequest request = new SearchDoctorsRequest("0", "", "surname", "speciality");
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnEmptyList4() {
        SearchDoctorsRequest request = new SearchDoctorsRequest("", "", "", "speciality");
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnEmptyList5() {
        SearchDoctorsRequest request = new SearchDoctorsRequest("", "name", "", "");
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnEmptyList6() {
        SearchDoctorsRequest request = new SearchDoctorsRequest("", "name", "surname", "");
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnEmptyList7() {
        SearchDoctorsRequest request = new SearchDoctorsRequest("", "name", "", "speciality");
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnIdNameSurnameAndSpecialityErrors() {
        SearchDoctorsRequest request = new SearchDoctorsRequest("", "", "", "");
        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 4);
        assertEquals(errorList.get(0).getField(), "id");
        assertEquals(errorList.get(0).getMessage(), "Must not be empty!");
        assertEquals(errorList.get(1).getField(), "name");
        assertEquals(errorList.get(1).getMessage(), "Must not be empty!");
        assertEquals(errorList.get(2).getField(), "surname");
        assertEquals(errorList.get(2).getMessage(), "Must not be empty!");
        assertEquals(errorList.get(3).getField(), "speciality");
        assertEquals(errorList.get(3).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenOrderDirectionAreEmpty() {
        Ordering ordering = new Ordering("name", null);
        SearchDoctorsRequest request = new SearchDoctorsRequest("1", "name", "surname", "speciality", ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderDirection");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenOrderByAreEmpty() {
        Ordering ordering = new Ordering(null, "ASCENDING");
        SearchDoctorsRequest request = new SearchDoctorsRequest("1", "name", "surname", "speciality", ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderBy");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenOrderByContainNotValidValue() {
        Ordering ordering = new Ordering("notValidValue", "ASCENDING");
        SearchDoctorsRequest request = new SearchDoctorsRequest("1", "name", "surname", "speciality", ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderBy");
        assertEquals(errors.get(0).getMessage(), "Must contain 'name', 'surname' or 'speciality' only!");
    }

    @Test
    public void shouldReturnErrorWhenOrderDirectionContainNotValidValue() {
        Ordering ordering = new Ordering("name", "notValidValue");
        SearchDoctorsRequest request = new SearchDoctorsRequest("1", "name", "surname", "speciality", ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderDirection");
        assertEquals(errors.get(0).getMessage(), "Must contain 'ASCENDING' or 'DESCENDING' only!");
    }

    @Test
    public void shouldReturnErrorWhenPageNumberContainNotValidValue() {
        Paging paging = new Paging(0, 1);
        SearchDoctorsRequest request = new SearchDoctorsRequest("1", "name", "surname", "speciality", paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageNumber");
        assertEquals(errors.get(0).getMessage(), "Must be greater then 0!");
    }

    @Test
    public void shouldReturnErrorWhenPageSizeContainNotValidValue() {
        Paging paging = new Paging(1, 0);
        SearchDoctorsRequest request = new SearchDoctorsRequest("1", "name", "surname", "speciality", paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageSize");
        assertEquals(errors.get(0).getMessage(), "Must be greater then 0!");
    }

    @Test
    public void shouldReturnErrorWhenPageNumberAreEmpty() {
        Paging paging = new Paging(null, 1);
        SearchDoctorsRequest request = new SearchDoctorsRequest("1", "name", "surname", "speciality", paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageNumber");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPageSizeAreEmpty() {
        Paging paging = new Paging(1, null);
        SearchDoctorsRequest request = new SearchDoctorsRequest("1", "name", "surname", "speciality", paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageSize");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

}