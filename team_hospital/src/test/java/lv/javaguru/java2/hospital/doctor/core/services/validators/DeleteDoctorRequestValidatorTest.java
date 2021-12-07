package lv.javaguru.java2.hospital.doctor.core.services.validators;

import lv.javaguru.java2.hospital.database.prescription_repository.PrescriptionRepository;
import lv.javaguru.java2.hospital.database.visit_repository.VisitRepository;
import lv.javaguru.java2.hospital.doctor.core.requests.DeleteDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import lv.javaguru.java2.hospital.doctor.core.services.checkers.DoctorLongNumChecker;
import lv.javaguru.java2.hospital.doctor.core.services.validators.existence.DoctorExistenceByIdValidator;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class DeleteDoctorRequestValidatorTest {

    @Mock private DoctorExistenceByIdValidator existence;
    @Mock private DoctorLongNumChecker longNumChecker;
    @Mock private VisitRepository visitRepository;
    @Mock private PrescriptionRepository prescriptionRepository;
    @InjectMocks private DeleteDoctorRequestValidator validator;

    @Test
    public void shouldReturnEmptyList() {
        DeleteDoctorRequest request = new DeleteDoctorRequest("123");
        Mockito.when(existence.validateExistenceById(request.getDoctorIdToDelete()))
                .thenReturn(Optional.empty());
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnIdError() {
        DeleteDoctorRequest request = new DeleteDoctorRequest(null);
        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "id");
        assertEquals(errorList.get(0).getMessage(), "Must not be empty!");
    }
}