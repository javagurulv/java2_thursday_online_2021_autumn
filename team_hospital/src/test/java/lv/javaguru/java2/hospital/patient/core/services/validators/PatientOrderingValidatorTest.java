package lv.javaguru.java2.hospital.patient.core.services.validators;

import lv.javaguru.java2.hospital.patient.core.requests.PatientOrdering;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatientOrderingValidatorTest {

    private PatientOrderingValidator validator = new PatientOrderingValidator();

    @Test
    public void shouldReturnErrorWhenOrderDirectionISEmpty() {
        PatientOrdering patientOrdering = new PatientOrdering("Name", null);
        SearchPatientsRequest request = new SearchPatientsRequest("name", "surname",
                "1212", patientOrdering);
        List<CoreError> errors = validator.validate(request.getOrdering());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderDirection");
        assertEquals(errors.get(0).getDescription(), "must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenOrderByISEmpty() {
        PatientOrdering patientOrdering = new PatientOrdering(null, "Ascending");
        SearchPatientsRequest request = new SearchPatientsRequest("name", "surname",
                "1212", patientOrdering);
        List<CoreError> errors = validator.validate(request.getOrdering());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderBy");
        assertEquals(errors.get(0).getDescription(), "must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenOrderByContainNotValidValue() {
        PatientOrdering patientOrdering = new PatientOrdering("notValidValue", "ASCENDING");
        SearchPatientsRequest request =
                new SearchPatientsRequest("Name", "Surname", "1212", patientOrdering);
        List<CoreError> errors = validator.validate(request.getOrdering());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderBy");
        assertEquals(errors.get(0).getDescription(), "must contain 'NAME' or 'SURNAME' only!");
    }

    @Test
    public void shouldReturnErrorWhenOrderDirectionContainNotValidValue() {
        PatientOrdering patientOrdering = new PatientOrdering("name", "notValidValue");
        SearchPatientsRequest request =
                new SearchPatientsRequest("Name", "Surname", "1212", patientOrdering);
        List<CoreError> errors = validator.validate(request.getOrdering());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Order Direction");
        assertEquals(errors.get(0).getDescription(), "must contain 'ASCENDING' or 'DESCENDING' only!");
    }
}