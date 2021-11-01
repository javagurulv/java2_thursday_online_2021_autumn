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
import lv.javaguru.java2.hospital.visit.core.requests.EditVisitRequest;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.AddVisitResponse;
import lv.javaguru.java2.hospital.visit.core.responses.EditVisitResponse;
import lv.javaguru.java2.hospital.visit.core.responses.SearchVisitResponse;
import lv.javaguru.java2.hospital.visit.core.services.AddVisitService;
import lv.javaguru.java2.hospital.visit.core.services.EditVisitService;
import lv.javaguru.java2.hospital.visit.core.services.SearchVisitService;
import lv.javaguru.java2.hospital.visit.core.services.ShowAllVisitService;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class AcceptanceTest3 {
    private ApplicationContext applicationContext;

    @BeforeEach
    public void setup() {
        applicationContext = new AnnotationConfigApplicationContext(HospitalConfiguration.class);
        getDatabaseCleaner().clean();
    }

    @Ignore
    public void shouldCorrectAddVisit() {
        AddPatientRequest addPatientRequest = new AddPatientRequest("name3", "surname3", "55556666777");
        AddPatientResponse addPatientResponse = getAddPatienceService().execute(addPatientRequest);
        AddDoctorRequest addDoctorRequest = new AddDoctorRequest("name3", "surname3", "speciality3");
        AddDoctorResponse addDoctorResponse = getAddDoctorService().execute(addDoctorRequest);
        AddDoctorRequest addDoctorRequest2 = new AddDoctorRequest("name4", "surname4", "speciality4");
        AddDoctorResponse addDoctorResponse2 = getAddDoctorService().execute(addDoctorRequest2);

        SearchPatientsResponse searchPatientsResponse = getSearchPatientsService()
                .execute(new SearchPatientsRequest("name3", "surname3", "55556666777"));
        SearchDoctorsResponse searchDoctorsResponse1 = getSearchDoctorsService()
                .execute(new SearchDoctorsRequest(null, "name3", "surname3", "speciality3"));
        SearchDoctorsResponse searchDoctorsResponse2 = getSearchDoctorsService()
                .execute(new SearchDoctorsRequest(null, "name4", "surname4", "speciality4"));

        AddVisitRequest request = new AddVisitRequest(
                searchPatientsResponse.getPatientList().get(0).getId().toString(),
                searchDoctorsResponse1.getDoctors().get(0).getId().toString(),
                "10/10/2023 10:00");
        AddVisitResponse addVisitResponse = getAddVisitService().execute(request);

        SearchVisitRequest searchVisitRequest1 = new SearchVisitRequest(
                null,searchDoctorsResponse1.getDoctors().get(0).getId(),
                searchPatientsResponse.getPatientList().get(0).getId(),
                "10/10/2023 10:00");
        SearchVisitResponse searchVisitResponse1 = getSearchVisitService().execute(searchVisitRequest1);

        EditVisitRequest editVisitRequest = new EditVisitRequest(
                searchVisitResponse1.getVisits().get(0).getVisitID(), "DOCTOR_ID",
                searchDoctorsResponse2.getDoctors().get(0).getId().toString());

        EditVisitResponse editVisitResponse = getEditVisitService().execute(editVisitRequest);

        SearchVisitRequest searchVisitRequest2 = new SearchVisitRequest(
                null,searchDoctorsResponse2.getDoctors().get(0).getId(),
                searchPatientsResponse.getPatientList().get(0).getId(),
                "10/10/2023 10:00");

        SearchVisitResponse searchVisitResponse2 = getSearchVisitService().execute(searchVisitRequest2);

        assertFalse(searchVisitResponse2.getVisits().isEmpty());
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

    private EditVisitService getEditVisitService(){
        return applicationContext.getBean(EditVisitService.class);
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
