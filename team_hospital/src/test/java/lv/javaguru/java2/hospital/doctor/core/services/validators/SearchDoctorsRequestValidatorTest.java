package lv.javaguru.java2.hospital.doctor.core.services.validators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import lv.javaguru.java2.hospital.doctor.core.requests.DoctorOrdering;
import lv.javaguru.java2.hospital.doctor.core.requests.SearchDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;

import lv.javaguru.java2.hospital.doctor.core.services.validators.existence.DoctorExistenceForSearchValidator;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class SearchDoctorsRequestValidatorTest {

    @Mock private SearchDoctorsRequestFieldValidator fieldValidator;
    @Mock private DoctorOrderingValidator doctorOrderingValidator;
    @Mock private DoctorPagingValidator doctorPagingValidator;
    @Mock private DoctorExistenceForSearchValidator doctorExistenceForSearchValidator;
    @InjectMocks private SearchDoctorsRequestValidator validator;

    @Test
    public void shouldReturnOrderingErrors() {
        DoctorOrdering doctorOrdering = new DoctorOrdering("name", "ASC");

        SearchDoctorsRequest request = new SearchDoctorsRequest(
                "name", "surname", "speciality", doctorOrdering
        );

        CoreError error = new CoreError("orderBy", "bla bla bla");

        Mockito.when(doctorOrderingValidator.validate(doctorOrdering))
                .thenReturn(List.of(error));

        List<CoreError> errors = validator.validate(request);

        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0), error);

        Mockito.verify(doctorOrderingValidator).validate(doctorOrdering);
    }

    @Test
    public void shouldNotReturnOrderingErrors() {
        DoctorOrdering doctorOrdering = new DoctorOrdering("name", "ASC");
        SearchDoctorsRequest request = new SearchDoctorsRequest(
                "name",
                "surname",
                "speciality",
                doctorOrdering
        );

        Mockito.when(doctorOrderingValidator.validate(doctorOrdering))
                .thenReturn(new ArrayList<>());

        List<CoreError> errors = validator.validate(request);

        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotCheckOrdering() {
        DoctorOrdering doctorOrdering = null;
        SearchDoctorsRequest request = new SearchDoctorsRequest(
                "name",
                "surname",
                "speciality",
                doctorOrdering
        );

        List<CoreError> errors = validator.validate(request);

        assertEquals(errors.size(), 0);

        Mockito.verifyNoMoreInteractions(doctorOrderingValidator);
    }

}