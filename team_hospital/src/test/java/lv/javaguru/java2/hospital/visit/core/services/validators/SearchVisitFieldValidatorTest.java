package lv.javaguru.java2.hospital.visit.core.services.validators;

import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchVisitFieldValidatorTest {

    private SearchVisitFieldValidator validator = new SearchVisitFieldValidator();

    @Test
    public void shouldNotReturnErrorsWhenVisitIdIsProvided() {
        SearchVisitRequest request = new SearchVisitRequest(1L, null, null, null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenDoctorIdIsProvided() {
        SearchVisitRequest request = new SearchVisitRequest(null, 1L, null, null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenPatientIdIsProvided() {
        SearchVisitRequest request = new SearchVisitRequest(null, null, 1L, null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenDateIsProvided() {
        SearchVisitRequest request = new SearchVisitRequest(null, null, null, "12/12/21 12:00");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenVisitIdAndDoctorIdAreProvided() {
        SearchVisitRequest request = new SearchVisitRequest(1L, 1L, null, null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenVisitIdAndPatientIdAreProvided() {
        SearchVisitRequest request = new SearchVisitRequest(1L, null, 1L, null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenVisitIdAndDateAreProvided() {
        SearchVisitRequest request = new SearchVisitRequest(1L, null, null, "12/12/21 12:00");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenDoctorIdAndPatientIdAreProvided() {
        SearchVisitRequest request = new SearchVisitRequest(null, 1L, 1L, null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenDoctorIdAndDateAreProvided() {
        SearchVisitRequest request = new SearchVisitRequest(null, 1L, null, "12/12/21 12:00");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenPatientIdAndDateAreProvided() {
        SearchVisitRequest request = new SearchVisitRequest(null, null, 1L, "12/12/21 12:00");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenVisitIdAndDoctorIdAndPatientIdAreProvided() {
        SearchVisitRequest request = new SearchVisitRequest(1L, 1L, 1L, null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenVisitIdAndPatientIdAndDateAreProvided() {
        SearchVisitRequest request = new SearchVisitRequest(1L, null, 1L, "12/12/21 12:00");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenVisitIdAndDoctorIdAndDateAreProvided() {
        SearchVisitRequest request = new SearchVisitRequest(1L, 1L, null, "12/12/21 12:00");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenDoctorIdAndPatientIdAndDateAreProvided() {
        SearchVisitRequest request = new SearchVisitRequest(null, 1L, 1L, "12/12/21 12:00");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorWhenSearchFieldsAreEmpty() {
        SearchVisitRequest request = new SearchVisitRequest(null, null, null, null);
        List<CoreError> errors = validator.validate(request);
        System.out.println(errors);
        assertEquals(errors.size(), 4);
        assertEquals(errors.get(0).getField(), "visitId");
        assertEquals(errors.get(0).getDescription(), "Must not be empty!");
        assertEquals(errors.get(1).getField(), "doctorId");
        assertEquals(errors.get(1).getDescription(), "Must not be empty!");
        assertEquals(errors.get(2).getField(), "patientId");
        assertEquals(errors.get(2).getDescription(), "Must not be empty!");
        assertEquals(errors.get(3).getField(), "visitDate");
        assertEquals(errors.get(3).getDescription(), "Must not be empty!");
    }
}