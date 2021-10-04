package lv.javaguru.java2.hospital.patient.core.services;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.ShowAllPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.ShowAllPatientsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ShowAllPatientsServiceTest {

    @Mock
    private PatientDatabaseImpl database;
    @InjectMocks
    private ShowAllPatientsService showAllPatientsService;

    @BeforeEach
    public void init() {
        database = Mockito.mock(PatientDatabaseImpl.class);
        showAllPatientsService = new ShowAllPatientsService();
    }

    @Test
    public void shouldGetPatientsFromDb() {
        List<Patient> patientList = new ArrayList<>();
        patientList.add(new Patient("name", "surname", "personalCode"));
        Mockito.when(database.showAllPatients()).thenReturn(patientList);

        ShowAllPatientsRequest request = new ShowAllPatientsRequest();
        ShowAllPatientsResponse response = showAllPatientsService.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getPatients().size(), 1);
        assertEquals(response.getPatients().get(0).getName(), "name");
        assertEquals(response.getPatients().get(0).getSurname(), "surname");
        assertEquals(response.getPatients().get(0).getPersonalCode(), "personalCode");
    }
}