package lv.javaguru.java2.hospital.visit.acceptance_tests;

import lv.javaguru.java2.hospital.config.HospitalConfiguration;
import lv.javaguru.java2.hospital.database_cleaner.DatabaseCleaner;
import lv.javaguru.java2.hospital.doctor.core.requests.AddDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.requests.SearchDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.AddDoctorResponse;
import lv.javaguru.java2.hospital.doctor.core.responses.SearchDoctorsResponse;
import lv.javaguru.java2.hospital.doctor.core.services.AddDoctorService;
import lv.javaguru.java2.hospital.doctor.core.services.SearchDoctorsService;
import lv.javaguru.java2.hospital.patient.core.requests.AddPatientRequest;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.AddPatientResponse;
import lv.javaguru.java2.hospital.patient.core.responses.SearchPatientsResponse;
import lv.javaguru.java2.hospital.patient.core.services.AddPatientService;
import lv.javaguru.java2.hospital.patient.core.services.search_patient_service.SearchPatientsService;
import lv.javaguru.java2.hospital.visit.core.requests.AddVisitRequest;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.requests.ShowAllVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.AddVisitResponse;
import lv.javaguru.java2.hospital.visit.core.responses.SearchVisitResponse;
import lv.javaguru.java2.hospital.visit.core.responses.ShowAllVisitResponse;
import lv.javaguru.java2.hospital.visit.core.services.AddVisitService;
import lv.javaguru.java2.hospital.visit.core.services.ShowAllVisitService;
import lv.javaguru.java2.hospital.visit.core.services.search_visit_service.SearchVisitService;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AcceptanceTest1 {

    private ApplicationContext applicationContext;

    @BeforeEach
    public void setup() {
        applicationContext = new AnnotationConfigApplicationContext(HospitalConfiguration.class);
        getDatabaseCleaner().clean();
    }

    @Ignore
    public void shouldCorrectAddVisit() {
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name", "surname", "11112222333");
        AddPatientResponse addPatientResponse = getAddPatienceService().execute(addPatientRequest1);
        AddDoctorRequest addDoctorRequest = new AddDoctorRequest("name", "surname", "speciality");
        AddDoctorResponse addDoctorResponse = getAddDoctorService().execute(addDoctorRequest);

        SearchPatientsResponse searchPatientsResponse = getSearchPatientsService()
                .execute(new SearchPatientsRequest("name", "surname", "11112222333"));
        SearchDoctorsResponse searchDoctorsResponse = getSearchDoctorsService()
                .execute(new SearchDoctorsRequest(null, "name", "surname", "speciality"));

        AddVisitRequest request = new AddVisitRequest(
                searchPatientsResponse.getPatientList().get(0).getId().toString(),
                searchDoctorsResponse.getDoctors().get(0).getId().toString(),
                "2022-12-12 12:00", "description");
        AddVisitResponse addVisitResponse = getAddVisitService().execute(request);

        SearchVisitRequest searchVisitRequest = new SearchVisitRequest(
                null, null,
                null,
                "2022-12-12 12:00");

        SearchVisitResponse searchVisitResponse = getSearchVisitService().execute(searchVisitRequest);
        ShowAllVisitResponse showAllVisitResponse = getShowAllVisitService().execute(new ShowAllVisitRequest());

        assertEquals(searchVisitResponse.getVisits().get(0), showAllVisitResponse.getPatientVisits().get(0));
    }

    private AddVisitService getAddVisitService() {
        return applicationContext.getBean(AddVisitService.class);
    }

    private SearchPatientsService getSearchPatientsService() {
        return applicationContext.getBean(SearchPatientsService.class);
    }

    private SearchDoctorsService getSearchDoctorsService(){
        return applicationContext.getBean(SearchDoctorsService.class);
    }

    private SearchVisitService getSearchVisitService(){
        return applicationContext.getBean(SearchVisitService.class);
    }

    private ShowAllVisitService getShowAllVisitService() {
        return applicationContext.getBean(ShowAllVisitService.class);
    }

    private AddPatientService getAddPatienceService() {
        return applicationContext.getBean(AddPatientService.class);
    }

    private AddDoctorService getAddDoctorService() {
        return applicationContext.getBean(AddDoctorService.class);
    }

    private DatabaseCleaner getDatabaseCleaner() {
        return applicationContext.getBean(DatabaseCleaner.class);
    }

}
