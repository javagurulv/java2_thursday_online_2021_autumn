package lv.javaguru.java2.hospital.prescription.core.services.validators;

import lv.javaguru.java2.hospital.prescription.core.requests.PrescriptionPaging;
import lv.javaguru.java2.hospital.prescription.core.requests.SearchPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class SearchPrescriptionValidatorTest {

    @Mock private SearchPrescriptionFieldValidator fieldValidator;
    @Mock private PrescriptionPagingValidator prescriptionPagingValidator;
    @InjectMocks private SearchPrescriptionValidator validator;

    @Test
    public void shouldNotReturnFieldErrors() {
        SearchPrescriptionRequest request = new SearchPrescriptionRequest(null, 656L, null);
        Mockito.when(fieldValidator.validate(request)).thenReturn(new ArrayList<>());
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnFieldErrors() {
        SearchPrescriptionRequest request = new SearchPrescriptionRequest(null, null, null);
        List<CoreError> list = new ArrayList<>();
        list.add(new CoreError("prescriptionId", "Must not be empty!"));
        list.add(new CoreError("doctorId", "Must not be empty!"));
        list.add(new CoreError("patientId", "Must not be empty!"));
        Mockito.when(fieldValidator.validate(request)).thenReturn(list);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 3);
        assertEquals(errors.get(0).getField(), "prescriptionId");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
        assertEquals(errors.get(1).getField(), "doctorId");
        assertEquals(errors.get(1).getMessage(), "Must not be empty!");
        assertEquals(errors.get(2).getField(), "patientId");
        assertEquals(errors.get(2).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldNotReturnPagingErrors() {
        PrescriptionPaging paging = new PrescriptionPaging(1, 1);
        SearchPrescriptionRequest request = new SearchPrescriptionRequest(13L, null, null, paging);
        Mockito.when(prescriptionPagingValidator.validate(paging)).thenReturn(new ArrayList<>());
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnPagingError() {
        PrescriptionPaging paging = new PrescriptionPaging(1, 0);
        SearchPrescriptionRequest request = new SearchPrescriptionRequest(13L, null, null, paging);
        List<CoreError> pagingErrors = new ArrayList<>();
        pagingErrors.add(new CoreError("pageSize", "Must be greater then 0!"));
        Mockito.when(prescriptionPagingValidator.validate(paging)).thenReturn(new ArrayList<>());
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

}