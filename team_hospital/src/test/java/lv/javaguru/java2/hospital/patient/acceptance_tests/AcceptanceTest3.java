package lv.javaguru.java2.hospital.patient.acceptance_tests;

import lv.javaguru.java2.hospital.config.HospitalConfiguration;
import lv.javaguru.java2.hospital.patient.core.requests.AddPatientRequest;
import lv.javaguru.java2.hospital.patient.core.requests.EditPatientRequest;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.AddPatientResponse;
import lv.javaguru.java2.hospital.patient.core.responses.EditPatientResponse;
import lv.javaguru.java2.hospital.patient.core.responses.SearchPatientsResponse;
import lv.javaguru.java2.hospital.patient.core.services.AddPatientService;
import lv.javaguru.java2.hospital.patient.core.services.EditPatientService;
import lv.javaguru.java2.hospital.patient.core.services.search_patient_service.SearchPatientsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AcceptanceTest3 {

    private ApplicationContext applicationContext;

    @BeforeEach
    public void setup() {
        applicationContext = new AnnotationConfigApplicationContext(HospitalConfiguration.class);
    }

    @Test
    public void shouldReturnCorrectNameAfterEditing() {
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name9", "surname", "11223344556");
        AddPatientResponse addPatientResponse = getAddPatienceService().execute(addPatientRequest1);

        EditPatientRequest editPatientRequest = new EditPatientRequest(addPatientResponse.getPatient().getId(),
                "NAME", "NewName");
        EditPatientResponse editPatientResponse = getEditPatientService().execute(editPatientRequest);

		SearchPatientsRequest searchPatientsRequest = new SearchPatientsRequest("NewName", "surname", "");
		SearchPatientsResponse searchPatientsResponse = getSearchPatientsService().execute(searchPatientsRequest);

		assertEquals(searchPatientsResponse.getPatientList().get(0).getName(), "NewName");
		assertEquals(searchPatientsResponse.getPatientList().get(0).getSurname(), "surname");
		assertEquals(searchPatientsResponse.getPatientList().get(0).getPersonalCode(), "11223344556");
    }

    @Test
    public void shouldReturnCorrectSurnameAfterEditing() {
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name10", "surname", "22334455667");
        AddPatientResponse addPatientResponse = getAddPatienceService().execute(addPatientRequest1);

        EditPatientRequest editPatientRequest = new EditPatientRequest(addPatientResponse.getPatient().getId(),
                "SURNAME", "NewSurname");
        EditPatientResponse editPatientResponse = getEditPatientService().execute(editPatientRequest);

		SearchPatientsRequest searchPatientsRequest = new SearchPatientsRequest("name10", "NewSurname", "");
		SearchPatientsResponse searchPatientsResponse = getSearchPatientsService().execute(searchPatientsRequest);

		assertEquals(searchPatientsResponse.getPatientList().get(0).getName(), "name10");
		assertEquals(searchPatientsResponse.getPatientList().get(0).getSurname(), "NewSurname");
		assertEquals(searchPatientsResponse.getPatientList().get(0).getPersonalCode(), "22334455667");
    }

    @Test
    public void shouldReturnCorrectPersonalCodeAfterEditing() {
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name11", "surname", "33445566778");
        AddPatientResponse addPatientResponse = getAddPatienceService().execute(addPatientRequest1);

        EditPatientRequest editPatientRequest = new EditPatientRequest(addPatientResponse.getPatient().getId(),
                "PERSONAL_CODE", "44556677889");
        EditPatientResponse editPatientResponse = getEditPatientService().execute(editPatientRequest);

		SearchPatientsRequest searchPatientsRequest = new SearchPatientsRequest("name11", "surname", "");
		SearchPatientsResponse searchPatientsResponse = getSearchPatientsService().execute(searchPatientsRequest);

		assertEquals(searchPatientsResponse.getPatientList().get(0).getName(), "name11");
		assertEquals(searchPatientsResponse.getPatientList().get(0).getSurname(), "surname");
		assertEquals(searchPatientsResponse.getPatientList().get(0).getPersonalCode(), "44556677889");
    }

    private AddPatientService getAddPatienceService() {
        return applicationContext.getBean(AddPatientService.class);
    }

    private EditPatientService getEditPatientService() {
        return applicationContext.getBean(EditPatientService.class);
    }

	private SearchPatientsService getSearchPatientsService() {
		return applicationContext.getBean(SearchPatientsService.class);
	}

}
