package lv.javaguru.java2.hospital.visit.acceptance_tests;

import lv.javaguru.java2.hospital.config.HospitalConfiguration;
import lv.javaguru.java2.hospital.database_cleaner.DatabaseCleaner;
import lv.javaguru.java2.hospital.doctor.core.requests.AddDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.AddDoctorResponse;
import lv.javaguru.java2.hospital.doctor.core.services.AddDoctorService;
import lv.javaguru.java2.hospital.patient.core.requests.AddPatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.AddPatientResponse;
import lv.javaguru.java2.hospital.patient.core.services.AddPatientService;
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
import lv.javaguru.java2.hospital.visit.core.services.ShowAllVisitService;
import lv.javaguru.java2.hospital.visit.core.services.search_visit_service.SearchVisitService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HospitalConfiguration.class})
@Sql({"/Schema.sql"})
public class AcceptanceTest2 {

    @Autowired private DatabaseCleaner databaseCleaner;
    @Autowired private AddPatientService addPatientService;
    @Autowired private AddDoctorService addDoctorService;
    @Autowired private AddVisitService addVisitService;
    @Autowired private SearchVisitService searchVisitService;
    @Autowired private ShowAllVisitService showAllVisitService;
    @Autowired private DeleteVisitService deleteVisitService;

    @Before
    public void setup() {
        databaseCleaner.clean();
    }

    @Test
    public void shouldReturnTrueIfDeleted(){
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name2", "surname2", "22223333444");
        AddPatientResponse addPatientResponse = addPatientService.execute(addPatientRequest1);
        AddDoctorRequest addDoctorRequest = new AddDoctorRequest("name2", "surname2", "speciality2");
        AddDoctorResponse addDoctorResponse = addDoctorService.execute(addDoctorRequest);

        AddVisitRequest request = new AddVisitRequest(
                addPatientResponse.getPatient().getId().toString(),
                addDoctorResponse.getNewDoctor().getId().toString(),
                "2023-11-13 11:00", "description");
        AddVisitResponse addVisitResponse = addVisitService.execute(request);

        SearchVisitRequest searchVisitRequest = new SearchVisitRequest(
                null, addDoctorResponse.getNewDoctor().getId().toString(),
                addPatientResponse.getPatient().getId().toString(),
                "2023-11-13 11:00");

        SearchVisitResponse searchVisitResponse = searchVisitService.execute(searchVisitRequest);

        DeleteVisitRequest deleteVisitRequest = new DeleteVisitRequest(searchVisitResponse.getVisits().get(0).getVisitID());
        DeleteVisitResponse deleteVisitResponse = deleteVisitService.execute(deleteVisitRequest);

        ShowAllVisitResponse showAllVisitResponse = showAllVisitService.execute(new ShowAllVisitRequest());

        assertTrue(showAllVisitResponse.getPatientVisits().isEmpty());
    }
}
