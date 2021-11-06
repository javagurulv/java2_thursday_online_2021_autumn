package lv.javaguru.java2.hospital.prescription.core.services.validators;

import lv.javaguru.java2.hospital.prescription.core.requests.SearchPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchPrescriptionFieldValidatorTest {

    private SearchPrescriptionFieldValidator validator = new SearchPrescriptionFieldValidator();

    @Test
    public void shouldNotReturnErrorsWhenPrescriptionIdIsProvided() {
        SearchPrescriptionRequest request = new SearchPrescriptionRequest(12L, null, null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenDoctorIdIsProvided() {
        SearchPrescriptionRequest request = new SearchPrescriptionRequest(null, 15L, null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenPatientIdIsProvided() {
        SearchPrescriptionRequest request = new SearchPrescriptionRequest(null, null, 87L);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenPrescriptionAndDoctorIdIsProvided() {
        SearchPrescriptionRequest request = new SearchPrescriptionRequest(12L, 97L, null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenPrescriptionAndPatientIdIsProvided() {
        SearchPrescriptionRequest request = new SearchPrescriptionRequest(12L, null, 54L);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenDoctorAndPatientIdIsProvided() {
        SearchPrescriptionRequest request = new SearchPrescriptionRequest(null, 64L, 54L);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorWhenSearchFieldsAreEmpty() {
        SearchPrescriptionRequest request = new SearchPrescriptionRequest(null, null, null);
        List<CoreError> errors = validator.validate(request);
        System.out.println(errors);
        assertEquals(errors.size(), 3);
        assertEquals(errors.get(0).getField(), "prescriptionId");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
        assertEquals(errors.get(1).getField(), "doctorId");
        assertEquals(errors.get(1).getMessage(), "Must not be empty!");
        assertEquals(errors.get(2).getField(), "patientId");
        assertEquals(errors.get(2).getMessage(), "Must not be empty!");
    }

}