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
import lv.javaguru.java2.hospital.visit.core.requests.DeleteVisitRequest;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.requests.ShowAllVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.AddVisitResponse;
import lv.javaguru.java2.hospital.visit.core.responses.DeleteVisitResponse;
import lv.javaguru.java2.hospital.visit.core.responses.SearchVisitResponse;
import lv.javaguru.java2.hospital.visit.core.responses.ShowAllVisitResponse;
import lv.javaguru.java2.hospital.visit.core.services.AddVisitService;
import lv.javaguru.java2.hospital.visit.core.services.DeleteVisitService;
import lv.javaguru.java2.hospital.visit.core.services.SearchVisitService;
import lv.javaguru.java2.hospital.visit.core.services.ShowAllVisitService;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AcceptanceTest2 {

    private ApplicationContext applicationContext;

    @BeforeEach
    public void setup() {
        applicationContext = new AnnotationConfigApplicationContext(HospitalConfiguration.class);
        getDatabaseCleaner().clean();
    }

    @Ignore
    public void shouldReturnTrueIfDeleted(){
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name2", "surname2", "22223333444");
        AddPatientResponse addPatientResponse = getAddPatienceService().execute(addPatientRequest1);
        AddDoctorRequest addDoctorRequest = new AddDoctorRequest("name2", "surname2", "speciality2");
        AddDoctorResponse addDoctorResponse = getAddDoctorService().execute(addDoctorRequest);

        SearchPatientsResponse searchPatientsResponse = getSearchPatientsService()
                .execute(new SearchPatientsRequest("name2", "surname2", "22223333444"));
        SearchDoctorsResponse searchDoctorsResponse = getSearchDoctorsService()
                .execute(new SearchDoctorsRequest(null, "name2", "surname2", "speciality2"));

        AddVisitRequest request = new AddVisitRequest(
                searchPatientsResponse.getPatientList().get(0).getId().toString(),
                searchDoctorsResponse.getDoctors().get(0).getId().toString(),
                "11/11/2021 11:00");
        AddVisitResponse addVisitResponse = getAddVisitService().execute(request);

        SearchVisitRequest searchVisitRequest = new SearchVisitRequest(
                null,searchDoctorsResponse.getDoctors().get(0).getId(),
                searchPatientsResponse.getPatientList().get(0).getId(),
                "11/11/2021 11:00");

        SearchVisitResponse searchVisitResponse = getSearchVisitService().execute(searchVisitRequest);

        DeleteVisitRequest deleteVisitRequest = new DeleteVisitRequest(searchVisitResponse.getVisits().get(0).getVisitID());
        DeleteVisitResponse deleteVisitResponse = getDeleteVisitService().execute(deleteVisitRequest);

        ShowAllVisitResponse showAllVisitResponse = getShowAllVisitService().execute(new ShowAllVisitRequest());

        assertTrue(showAllVisitResponse.getPatientVisits().isEmpty());
    }

    private AddVisitService getAddVisitService() {
        return applicationContext.getBean(AddVisitService.class);
    }

    private AddPatientService getAddPatienceService() {
        return applicationContext.getBean(AddPatientService.class);
    }

    private AddDoctorService getAddDoctorService() {
        return applicationContext.getBean(AddDoctorService.class);
    }

    private DeleteVisitService getDeleteVisitService(){
        return applicationContext.getBean(DeleteVisitService.class);
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

    private DatabaseCleaner getDatabaseCleaner() {
        return applicationContext.getBean(DatabaseCleaner.class);
    }

    private ShowAllVisitService getShowAllVisitService() {
        return applicationContext.getBean(ShowAllVisitService.class);
    }

}
