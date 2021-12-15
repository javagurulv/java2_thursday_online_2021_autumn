package lv.javaguru.java2.hospital.doctor.core.services.validators;

import lv.javaguru.java2.hospital.doctor.core.requests.SearchDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchDoctorsRequestFieldValidatorTest {

    private SearchDoctorsRequestFieldValidator validator = new SearchDoctorsRequestFieldValidator();

    @Test
    public void shouldNotReturnErrorsWhenNameIsProvided() {
        SearchDoctorsRequest request = new SearchDoctorsRequest("Name", null, null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenSurnameIsProvided() {
        SearchDoctorsRequest request = new SearchDoctorsRequest(null, "Surname", null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenSpecialityIsProvided() {
        SearchDoctorsRequest request = new SearchDoctorsRequest(null, null, "Speciality");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenIdAndNameAreProvided() {
        SearchDoctorsRequest request = new SearchDoctorsRequest("Name", null, null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenIdAndSurnameAreProvided() {
        SearchDoctorsRequest request = new SearchDoctorsRequest(null, "Surname", null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenIdAndSpecialityAreProvided() {
        SearchDoctorsRequest request = new SearchDoctorsRequest(null, null, "Speciality");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenNameAndSurnameAreProvided() {
        SearchDoctorsRequest request = new SearchDoctorsRequest("Name", "Surname", null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenNameAndSpecialityAreProvided() {
        SearchDoctorsRequest request = new SearchDoctorsRequest("Name", null, "Speciality");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenSurnameAndSpecialityAreProvided() {
        SearchDoctorsRequest request = new SearchDoctorsRequest(null, "Surname", "Speciality");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenIdAndNameAndSurnameAreProvided() {
        SearchDoctorsRequest request = new SearchDoctorsRequest("Name", "Surname", null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenIdAndNameAndSpecialityAreProvided() {
        SearchDoctorsRequest request = new SearchDoctorsRequest("Name", null, "Speciality");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenIdAndSurnameAndSpecialityAreProvided() {
        SearchDoctorsRequest request = new SearchDoctorsRequest(null, "Surname", "Speciality");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenNameAndSurnameAndSpecialityAreProvided() {
        SearchDoctorsRequest request = new SearchDoctorsRequest( "Name", "Surname", "Speciality");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorWhenSearchFieldsAreEmpty() {
        SearchDoctorsRequest request = new SearchDoctorsRequest(null, null, null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 4);
        assertEquals(errors.get(0).getField(), "id");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
        assertEquals(errors.get(1).getField(), "name");
        assertEquals(errors.get(1).getMessage(), "Must not be empty!");
        assertEquals(errors.get(2).getField(), "surname");
        assertEquals(errors.get(2).getMessage(), "Must not be empty!");
        assertEquals(errors.get(3).getField(), "speciality");
        assertEquals(errors.get(3).getMessage(), "Must not be empty!");
    }

}


