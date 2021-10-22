package lv.javaguru.java2.hospital.doctor.core.services.validators;

import lv.javaguru.java2.hospital.doctor.core.requests.EditDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import lv.javaguru.java2.hospital.doctor.core.services.validators.existence.DoctorExistenceByIdValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class EditDoctorRequestValidatorTest {

    @Mock
    private DoctorExistenceByIdValidator existence;
    @InjectMocks private EditDoctorRequestValidator validator;

    @Test
    public void shouldReturnEmptyListName() {
        EditDoctorRequest request = new EditDoctorRequest(123L, "NAME", "changes");
        Mockito.when(existence.validateExistenceById(123L)).thenReturn(Optional.empty());
        List<CoreError> errorList = validator.validate(request);;
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnEditOptionError1() {
        EditDoctorRequest request = new EditDoctorRequest(123L, "blabla", "changes");
        Mockito.when(existence.validateExistenceById(123L)).thenReturn(Optional.empty());
        List<CoreError> errorList = validator.validate(request);
        System.out.println(errorList);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "edit option");
        assertEquals(errorList.get(0).getMessage(), "Must contain 'NAME', 'SURNAME' or 'SPECIALITY' only!");
    }

    @Test
    public void shouldReturnEditOptionError2() {
        EditDoctorRequest request = new EditDoctorRequest(123L, "", "changes");
        Mockito.when(existence.validateExistenceById(123L)).thenReturn(Optional.empty());
        List<CoreError> errorList = validator.validate(request);
        System.out.println(errorList);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "edit option");
        assertEquals(errorList.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnEmptyListSurname() {
        EditDoctorRequest request = new EditDoctorRequest(123L, "SURNAME", "changes");
        Mockito.when(existence.validateExistenceById(123L)).thenReturn(Optional.empty());
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnEmptyListSpeciality() {
        EditDoctorRequest request = new EditDoctorRequest(123L, "SPECIALITY", "changes");
        Mockito.when(existence.validateExistenceById(123L)).thenReturn(Optional.empty());
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnIdError() {
        EditDoctorRequest request = new EditDoctorRequest(null, "NAME", "changes");
        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "id");
        assertEquals(errorList.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnChangesError() {
        EditDoctorRequest request = new EditDoctorRequest(123L, "NAME", "");
        Mockito.when(existence.validateExistenceById(123L)).thenReturn(Optional.empty());
        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "changes");
        assertEquals(errorList.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnIdAndChangesErrors() {
        EditDoctorRequest request = new EditDoctorRequest(null, "NAME", "");
        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 2);
        assertEquals(errorList.get(0).getField(), "id");
        assertEquals(errorList.get(0).getMessage(), "Must not be empty!");
        assertEquals(errorList.get(1).getField(), "changes");
        assertEquals(errorList.get(1).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnDoctorError() {
        EditDoctorRequest request = new EditDoctorRequest(54L, "NAME", "changes");
        Mockito.when(existence.validateExistenceById(54L)).thenReturn
                (Optional.of(new CoreError("Doctor", "Does not exist!")));
        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Doctor");
        assertEquals(errorList.get(0).getMessage(), "Does not exist!");
    }
}