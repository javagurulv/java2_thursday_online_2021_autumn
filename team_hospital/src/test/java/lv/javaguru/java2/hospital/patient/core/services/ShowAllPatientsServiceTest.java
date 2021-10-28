package lv.javaguru.java2.hospital.patient.core.services;

import lv.javaguru.java2.hospital.database.PatientDatabase;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.ShowAllPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.ShowAllPatientsResponse;
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
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class ShowAllPatientsServiceTest {

    @Mock private PatientDatabase database;
    @InjectMocks private ShowAllPatientsService showAllPatientsService;

    @Test
    public void shouldGetPatientsFromDb() {
        List<Patient> patientList = new ArrayList<>();
        patientList.add(new Patient("name", "surname", "personalCode"));
        Mockito.when(database.getAllPatients()).thenReturn(patientList);

        ShowAllPatientsRequest request = new ShowAllPatientsRequest();
        ShowAllPatientsResponse response = showAllPatientsService.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getPatients().size(), 1);
        assertEquals(response.getPatients().get(0).getName(), "name");
        assertEquals(response.getPatients().get(0).getSurname(), "surname");
        assertEquals(response.getPatients().get(0).getPersonalCode(), "personalCode");
    }
}