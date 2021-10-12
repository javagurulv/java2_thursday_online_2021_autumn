package lv.javaguru.java2.hospital.patient.core.services.validators;

import lv.javaguru.java2.hospital.patient.core.requests.PatientOrdering;
import lv.javaguru.java2.hospital.patient.core.requests.PatientPaging;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import lv.javaguru.java2.hospital.patient.core.services.validators.patient_existence.PatientExistenceForSearch;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class SearchPatientsValidatorTest {

    @Mock private PatientExistenceForSearch existenceSearchValidator;
    @Mock private SearchPatientsRequestFieldValidator fieldValidator;
    @Mock private PatientOrderingValidator patientOrderingValidator;
    @Mock private PatientPagingValidator patientPagingValidator;
    @InjectMocks private SearchPatientsValidator validator;

    @Test
    public void shouldReturnOrderingErrors() {
        PatientOrdering patientOrdering = new PatientOrdering("name", "ASC");

        SearchPatientsRequest request = new SearchPatientsRequest(
                "name",
                "surname",
                "1234",
                patientOrdering);

        CoreError error = new CoreError("orderBy", "bla bla bla");

        Mockito.when(patientOrderingValidator.validate(patientOrdering))
                .thenReturn(List.of(error));

        List<CoreError> errors = validator.validate(request);

        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0), error);

        Mockito.verify(patientOrderingValidator).validate(patientOrdering);
    }

    @Test
    public void shouldNotReturnOrderingErrors() {
        PatientOrdering patientOrdering = new PatientOrdering("name", "ASC");
        SearchPatientsRequest request = new SearchPatientsRequest(
                "name",
                "surname",
                "1234",
                patientOrdering
        );

        Mockito.when(patientOrderingValidator.validate(patientOrdering))
                .thenReturn(new ArrayList<>());

        List<CoreError> errors = validator.validate(request);

        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotCheckOrdering() {
        PatientOrdering patientOrdering = null;
        SearchPatientsRequest request = new SearchPatientsRequest(
                "name",
                "surname",
                "1234",
                patientOrdering
        );

        List<CoreError> errors = validator.validate(request);

        assertEquals(errors.size(), 0);

        Mockito.verifyNoMoreInteractions(patientOrderingValidator);
    }

    @Test
    public void shouldReturnPagingErrors() {
        PatientPaging patientPaging = new PatientPaging(0, 0);

        SearchPatientsRequest request = new SearchPatientsRequest(
                "name",
                "surname",
                "1234",
                patientPaging);

        CoreError error = new CoreError("orderBy", "bla bla bla");

        Mockito.when(patientPagingValidator.validate(patientPaging))
                .thenReturn(List.of(error));

        List<CoreError> errors = validator.validate(request);

        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0), error);

        Mockito.verify(patientPagingValidator).validate(patientPaging);
    }

    @Test
    public void shouldNotReturnPagingErrors() {
        PatientPaging patientPaging = new PatientPaging(1, 1);
        SearchPatientsRequest request = new SearchPatientsRequest(
                "name",
                "surname",
                "1234",
                patientPaging
        );

        Mockito.when(patientPagingValidator.validate(patientPaging))
                .thenReturn(new ArrayList<>());

        List<CoreError> errors = validator.validate(request);

        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotCheckPaging() {
        PatientPaging patientPaging = null;
        SearchPatientsRequest request = new SearchPatientsRequest(
                "name",
                "surname",
                "1234",
                patientPaging
        );

        List<CoreError> errors = validator.validate(request);

        assertEquals(errors.size(), 0);

        Mockito.verifyNoMoreInteractions(patientPagingValidator);
    }
}