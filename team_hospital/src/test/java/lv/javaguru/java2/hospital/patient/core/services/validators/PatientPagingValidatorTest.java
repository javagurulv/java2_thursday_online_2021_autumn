package lv.javaguru.java2.hospital.patient.core.services.validators;


import lv.javaguru.java2.hospital.patient.core.requests.PatientPaging;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatientPagingValidatorTest {

    private PatientPagingValidator validator = new PatientPagingValidator();

    @Test
    public void shouldReturnErrorWhenPageNumberContainNotValidValue() {
        PatientPaging patientPaging = new PatientPaging(0, 1);
        SearchPatientsRequest request =
                new SearchPatientsRequest("Name", "Surname", "1212", patientPaging);
        List<CoreError> errors = validator.validate(request.getPaging());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageNumber");
        assertEquals(errors.get(0).getDescription(), "must be greater then 0!");
    }

    @Test
    public void shouldReturnErrorWhenPageSizeContainNotValidValue() {
        PatientPaging patientPaging = new PatientPaging(1, 0);
        SearchPatientsRequest request =
                new SearchPatientsRequest("Name", "Surname", "1212", patientPaging);
        List<CoreError> errors = validator.validate(request.getPaging());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageSize");
        assertEquals(errors.get(0).getDescription(), "must be greater then 0!");
    }

    @Test
    public void shouldReturnErrorWhenPageNumberAreEmpty() {
        PatientPaging patientPaging = new PatientPaging(null, 1);
        SearchPatientsRequest request =
                new SearchPatientsRequest("Name", "Surname", "1212", patientPaging);
        List<CoreError> errors = validator.validate(request.getPaging());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageNumber");
        assertEquals(errors.get(0).getDescription(), "must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPageSizeAreEmpty() {
        PatientPaging patientPaging = new PatientPaging(1, null);
        SearchPatientsRequest request =
                new SearchPatientsRequest("Name", "Surname", "1212", patientPaging);
        List<CoreError> errors = validator.validate(request.getPaging());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageSize");
        assertEquals(errors.get(0).getDescription(), "must not be empty!");
    }
}