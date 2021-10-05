package lv.javaguru.java2.hospital.visits.core.services.validators;

import lv.javaguru.java2.hospital.visits.core.request.EditVisitRequest;
import lv.javaguru.java2.hospital.visits.core.responses.CoreError;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EditVisitValidatorTest {

    private EditVisitValidator validator = new EditVisitValidator();

    @Test
    public void shouldReturnEmptyList() {
        EditVisitRequest request = new EditVisitRequest(1L, 1, "changes");
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnIdError() {
        EditVisitRequest request = new EditVisitRequest(null, 1, "changes");
        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "id");
        assertEquals(errorList.get(0).getDescription(), "Must not be empty!");
    }

    @Test
    public void shouldReturnChangesError() {
        EditVisitRequest request = new EditVisitRequest(1L, 1, "");
        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "changes");
        assertEquals(errorList.get(0).getDescription(), "Must not be empty!");
    }

    @Test
    public void shouldReturnIdAndChangesErrors() {
        EditVisitRequest request = new EditVisitRequest(null, 1, "");
        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 2);
        assertEquals(errorList.get(0).getField(), "id");
        assertEquals(errorList.get(0).getDescription(), "Must not be empty!");
        assertEquals(errorList.get(1).getField(), "changes");
        assertEquals(errorList.get(1).getDescription(), "Must not be empty!");
    }

}