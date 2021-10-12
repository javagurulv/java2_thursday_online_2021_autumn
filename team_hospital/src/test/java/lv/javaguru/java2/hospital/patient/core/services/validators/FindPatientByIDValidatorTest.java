package lv.javaguru.java2.hospital.patient.core.services.validators;

import lv.javaguru.java2.hospital.patient.core.requests.FindPatientByIdRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import lv.javaguru.java2.hospital.patient.core.services.validators.patient_existence.PatientExistenceByIDValidator;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class FindPatientByIDValidatorTest {

   @Mock private PatientExistenceByIDValidator idValidator;
   @InjectMocks private FindPatientByIDValidator validator;

    @Test
    public void shouldReturnEmptyList(){
        FindPatientByIdRequest request = new FindPatientByIdRequest(1L);
        Mockito.when(idValidator.existenceByID(request.getIDRequest())).thenReturn(Optional.empty());
        List<CoreError> errorsList = validator.validate(request);
        assertTrue(errorsList.isEmpty());
    }

    @Test
    public void shouldReturnIDError(){
        FindPatientByIdRequest request= new FindPatientByIdRequest(null);
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "ID");
        assertEquals(errorList.get(0).getDescription(), "Must not be empty!");
    }
}