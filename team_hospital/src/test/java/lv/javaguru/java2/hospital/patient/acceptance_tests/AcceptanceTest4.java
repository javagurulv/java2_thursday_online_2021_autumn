package lv.javaguru.java2.hospital.patient.acceptance_tests;

import lv.javaguru.java2.hospital.database_cleaner.DatabaseCleaner;
import lv.javaguru.java2.hospital.config.HospitalConfiguration;
import lv.javaguru.java2.hospital.patient.core.requests.AddPatientRequest;
import lv.javaguru.java2.hospital.patient.core.requests.DeletePatientRequest;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.requests.ShowAllPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.AddPatientResponse;
import lv.javaguru.java2.hospital.patient.core.responses.DeletePatientResponse;
import lv.javaguru.java2.hospital.patient.core.responses.SearchPatientsResponse;
import lv.javaguru.java2.hospital.patient.core.responses.ShowAllPatientsResponse;
import lv.javaguru.java2.hospital.patient.core.services.AddPatientService;
import lv.javaguru.java2.hospital.patient.core.services.DeletePatientService;
import lv.javaguru.java2.hospital.patient.core.services.ShowAllPatientsService;
import lv.javaguru.java2.hospital.patient.core.services.search_patient_service.SearchPatientsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AcceptanceTest4 {

    private ApplicationContext applicationContext;

    @BeforeEach
    public void setup() {
        applicationContext = new AnnotationConfigApplicationContext(HospitalConfiguration.class);
        getDatabaseCleaner().clean();
    }

    @Test
    public void shouldReturnCorrectPatientsListAfterDelete() {
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name1", "surname1", "11223344556");
        AddPatientResponse addPatientResponse = getAddPatienceService().execute(addPatientRequest1);

        AddPatientRequest addPatientRequest2 = new AddPatientRequest("name2", "surname2", "22334455667");
        getAddPatienceService().execute(addPatientRequest2);

        SearchPatientsRequest searchPatientsRequest = new SearchPatientsRequest("name1", "surname1", "11223344556");
        SearchPatientsResponse searchPatientsResponse = getSearchPatientsService().execute(searchPatientsRequest);

        DeletePatientRequest deletePatientRequest = new DeletePatientRequest(searchPatientsResponse.getPatientList().get(0).getId());
        DeletePatientResponse deletePatientResponse = getDeletePatientService().execute(deletePatientRequest);

        ShowAllPatientsResponse response = getShowAllPatientsService().execute(new ShowAllPatientsRequest());
        assertEquals(response.getPatients().size(), 1);
        assertEquals(response.getPatients().get(0).getName(), "name2");
        assertEquals(response.getPatients().get(0).getSurname(), "surname2");
        assertEquals(response.getPatients().get(0).getPersonalCode(), "22334455667");
    }


    private AddPatientService getAddPatienceService() {
        return applicationContext.getBean(AddPatientService.class);
    }

    private DeletePatientService getDeletePatientService() {
        return applicationContext.getBean(DeletePatientService.class);
    }

    private ShowAllPatientsService getShowAllPatientsService() {
        return applicationContext.getBean(ShowAllPatientsService.class);
    }

    private SearchPatientsService getSearchPatientsService() {
        return applicationContext.getBean(SearchPatientsService.class);
    }

    private DatabaseCleaner getDatabaseCleaner() {
        return applicationContext.getBean(DatabaseCleaner.class);
    }
}