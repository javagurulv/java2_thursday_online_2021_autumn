package lv.javaguru.java2.hospital.visit.core.services.validators;

import lv.javaguru.java2.hospital.visit.core.checkers.VisitEnumChecker;
import lv.javaguru.java2.hospital.visit.core.checkers.VisitLongNumChecker;
import lv.javaguru.java2.hospital.visit.core.requests.EditVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.services.validators.date_validator.DateValidatorExecution;
import lv.javaguru.java2.hospital.visit.core.services.validators.existence_validators.VisitExistenceByIdValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class EditVisitValidatorTest {

    @Mock private DateValidatorExecution dateValidator;
    @Mock private VisitEnumChecker checker;
    @Mock private VisitExistenceByIdValidator existence;
    @Mock private VisitLongNumChecker longNumChecker;
    @InjectMocks private EditVisitValidator validator;

    @Test
    public void shouldReturnEmptyList() {
        EditVisitRequest request = new EditVisitRequest("1", "DOCTOR_ID", "changes");
        Mockito.when(longNumChecker.validate(request.getVisitID(), "ID")).thenReturn(Optional.empty());
        Mockito.when(checker.validateEnum(request.getEditEnums())).thenReturn(Optional.empty());
        Mockito.when(existence.validateExistenceById(1L)).thenReturn(Optional.empty());
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnIDError() {
        EditVisitRequest request = new EditVisitRequest(null, "DOCTOR_ID", "changes");
        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "ID");
        assertEquals(errorList.get(0).getDescription(), "must not be empty!");
    }

    @Test
    public void shouldReturnIDParseError() {
        EditVisitRequest request = new EditVisitRequest("qwe", "DOCTOR_ID", "changes");
        Mockito.when(longNumChecker.validate(request.getVisitID(), "ID"))
                .thenReturn(Optional.of(new CoreError("ID in changes", "must be a number!")));
        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "ID in changes");
        assertEquals(errorList.get(0).getDescription(), "must be a number!");
    }

    @Test
    public void shouldReturnChangesError() {
        EditVisitRequest request = new EditVisitRequest("1", "DOCTOR_ID", "");
        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Changes");
        assertEquals(errorList.get(0).getDescription(), "must not be empty!");
    }

    @Test
    public void shouldReturnIdAndChangesErrors() {
        EditVisitRequest request = new EditVisitRequest(null, "DOCTOR_ID", "");
        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 2);
        assertEquals(errorList.get(0).getField(), "ID");
        assertEquals(errorList.get(0).getDescription(), "must not be empty!");
        assertEquals(errorList.get(1).getField(), "Changes");
        assertEquals(errorList.get(1).getDescription(), "must not be empty!");
    }

    @Test
    public void shouldReturnVisitError() {
        Mockito.when(existence.validateExistenceById(12L)).thenReturn
                (Optional.of(new CoreError("Visit", "does not exist!")));
        EditVisitRequest request = new EditVisitRequest("12", "DOCTOR_ID", "changes");
        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Visit");
        assertEquals(errorList.get(0).getDescription(), "does not exist!");
    }

    @Test
    public void shouldReturnEnumErrorIsEmpty() {
        EditVisitRequest request = new EditVisitRequest("11", "", "changes");
        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Edit option");
        assertEquals(errorList.get(0).getDescription(), "must not be empty!");
    }

    @Test
    public void shouldReturnEnumErrorInvalidInput() {
        EditVisitRequest request = new EditVisitRequest("11", "ENUM", "changes");
        Mockito.when(checker.validateEnum(request.getEditEnums())).thenReturn(Optional.of(
                new CoreError("Edit option", "must be DOCTOR_ID, PATIENT_ID, DATE OR DESCRIPTION!")
        ));
        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Edit option");
        assertEquals(errorList.get(0).getDescription(), "must be DOCTOR_ID, PATIENT_ID, DATE OR DESCRIPTION!");
    }

    @Test
    public void shouldReturnDateInputErrorError() {
        EditVisitRequest request = new EditVisitRequest("11", "DATE", "changes");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Date", "input is incorrect!"));

        Mockito.when(checker.validateEnum(request.getEditEnums())).thenReturn(Optional.empty());
        Mockito.when(dateValidator.validate(request.getChanges())).thenReturn(errors);

        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Date");
        assertEquals(errorList.get(0).getDescription(), "input is incorrect!");
    }

    @Test
    public void shouldReturnDateTimeIsNotInTheFutureError() {
        EditVisitRequest request = new EditVisitRequest("11", "DATE", "13-10-2021 10:00");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Date", "is not in the future!"));

        Mockito.when(checker.validateEnum(request.getEditEnums())).thenReturn(Optional.empty());
        Mockito.when(dateValidator.validate(request.getChanges())).thenReturn(errors);

        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Date");
        assertEquals(errorList.get(0).getDescription(), "is not in the future!");
    }

    @Test
    public void shouldReturnDateTimeIsNotWorkingDayError() {
        EditVisitRequest request = new EditVisitRequest("11", "DATE", "2-12-2023 10:00");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Date", "is not working day!"));

        Mockito.when(checker.validateEnum(request.getEditEnums())).thenReturn(Optional.empty());
        Mockito.when(dateValidator.validate(request.getChanges())).thenReturn(errors);

        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Date");
        assertEquals(errorList.get(0).getDescription(), "is not working day!");
    }

    @Test
    public void shouldReturnDateTimeIsNotWorkingHoursError() {
        EditVisitRequest request = new EditVisitRequest("11", "DATE", "1-12-2023 06:00");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Date", "is not working hour!"));

        Mockito.when(checker.validateEnum(request.getEditEnums())).thenReturn(Optional.empty());
        Mockito.when(dateValidator.validate(request.getChanges())).thenReturn(errors);

        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Date");
        assertEquals(errorList.get(0).getDescription(), "is not working hour!");
    }

    @Test
    public void shouldReturn3DateTimeError() {
        EditVisitRequest request = new EditVisitRequest("11", "DATE", "2-10-2021 06:00");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Date", "is not in the future!"));
        errors.add(new CoreError("Date", "is not working day!"));
        errors.add(new CoreError("Date", "is not working hour!"));

        Mockito.when(checker.validateEnum(request.getEditEnums())).thenReturn(Optional.empty());
        Mockito.when(dateValidator.validate(request.getChanges())).thenReturn(errors);

        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 3);
        assertEquals(errorList.get(0).getField(), "Date");
        assertEquals(errorList.get(0).getDescription(), "is not in the future!");
        assertEquals(errorList.get(1).getField(), "Date");
        assertEquals(errorList.get(1).getDescription(), "is not working day!");
        assertEquals(errorList.get(2).getField(), "Date");
        assertEquals(errorList.get(2).getDescription(), "is not working hour!");
    }
}