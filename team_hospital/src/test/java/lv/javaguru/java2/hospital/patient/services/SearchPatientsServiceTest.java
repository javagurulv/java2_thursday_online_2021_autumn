package lv.javaguru.java2.hospital.patient.services;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.responses.SearchPatientsResponse;
import lv.javaguru.java2.hospital.patient.services.validators.SearchPatientsValidator;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchPatientsServiceTest {
    private final PatientDatabaseImpl patientDatabase = new PatientDatabaseImpl();
    private final SearchPatientsService searchPatientsService
            = new SearchPatientsService(patientDatabase, new SearchPatientsValidator());

    @Test
    public void findByName() {
        addPatients();
        SearchPatientsRequest request =
                new SearchPatientsRequest("Stanislav", "", "", null, null);
        SearchPatientsResponse response = searchPatientsService.execute(request);
        List<Patient> patients = response.getPatientList();
        assertEquals(patients.size(), 1);
        assertEquals(patients.get(0).getName(), "Stanislav");
        assertEquals(patients.get(0).getSurname(), "Cherkasov");
        assertEquals(patients.get(0).getPersonalCode(), "1234");
    }

    @Test
    public void findBySurname() {
        addPatients();
        SearchPatientsRequest request =
                new SearchPatientsRequest("", "Grigorjev", "", null, null);
        SearchPatientsResponse response = searchPatientsService.execute(request);
        List<Patient> patients = response.getPatientList();
        assertEquals(patients.size(), 1);
        assertEquals(patients.get(0).getName(), "Anton");
        assertEquals(patients.get(0).getSurname(), "Grigorjev");
        assertEquals(patients.get(0).getPersonalCode(), "4321");
    }

    @Test
    public void findByPersonalCode() {
        addPatients();
        SearchPatientsRequest request =
                new SearchPatientsRequest("", "", "2121", null, null);
        SearchPatientsResponse response = searchPatientsService.execute(request);
        List<Patient> patients = response.getPatientList();
        assertEquals(patients.size(), 1);
        assertEquals(patients.get(0).getName(), "Aleksandr");
        assertEquals(patients.get(0).getSurname(), "Kornev");
        assertEquals(patients.get(0).getPersonalCode(), "2121");
    }

    @Test
    public void findByNameAndSurname() {
        addPatients();
        SearchPatientsRequest request =
                new SearchPatientsRequest("Petr", "Ivanov", "", null, null);
        SearchPatientsResponse response = searchPatientsService.execute(request);
        List<Patient> patients = response.getPatientList();
        assertEquals(patients.size(), 1);
        assertEquals(patients.get(0).getName(), "Petr");
        assertEquals(patients.get(0).getSurname(), "Ivanov");
        assertEquals(patients.get(0).getPersonalCode(), "2323");
    }

    @Test
    public void findByNameAndPersonalCode() {
        addPatients();
        SearchPatientsRequest request =
                new SearchPatientsRequest("Aleksandr", "", "2121", null, null);
        SearchPatientsResponse response = searchPatientsService.execute(request);
        List<Patient> patients = response.getPatientList();
        assertEquals(patients.size(), 1);
        assertEquals(patients.get(0).getName(), "Aleksandr");
        assertEquals(patients.get(0).getSurname(), "Kornev");
        assertEquals(patients.get(0).getPersonalCode(), "2121");
    }

    private void addPatients() {
        patientDatabase.add(new Patient("Valerij", "Petrov", "1212"));
        patientDatabase.add(new Patient("Aleksandr", "Kornev", "2121"));
        patientDatabase.add(new Patient("Stanislav", "Cherkasov", "1234"));
        patientDatabase.add(new Patient("Anton", "Grigorjev", "4321"));
        patientDatabase.add(new Patient("Petr", "Ivanov", "2323"));
    }
}