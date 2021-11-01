package lv.javaguru.java2.hospital.doctor.acceptancetests;

import lv.javaguru.java2.hospital.DatabaseCleaner;
import lv.javaguru.java2.hospital.config.HospitalConfiguration;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.ApplicationContext;
import lv.javaguru.java2.hospital.doctor.core.requests.*;
import lv.javaguru.java2.hospital.doctor.core.responses.SearchDoctorsResponse;
import lv.javaguru.java2.hospital.doctor.core.services.AddDoctorService;
import lv.javaguru.java2.hospital.doctor.core.services.SearchDoctorsService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AcceptanceTest2 {

    private ApplicationContext appContext;

    @BeforeEach
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(HospitalConfiguration.class);
        getDatabaseCleaner().clean();
    }

    @Ignore
    public void shouldReturnCorrectDoctorList() {
        AddDoctorRequest request1 = new AddDoctorRequest("Name", "Surname1", "Speciality1");
        getAddDoctorService().execute(request1);

        AddDoctorRequest request2 = new AddDoctorRequest("Name", "Surname2", "Speciality2");
        getAddDoctorService().execute(request2);

        SearchDoctorsRequest request3 = new SearchDoctorsRequest(null, "Name", null, null);
        SearchDoctorsResponse response = getSearchDoctorsService().execute(request3);

        assertEquals(response.getDoctors().size(), 2);
        assertEquals(response.getDoctors().get(0).getName(), "Name");
        assertEquals(response.getDoctors().get(0).getSurname(), "Surname1");
        assertEquals(response.getDoctors().get(0).getSpeciality(), "Speciality1");
        assertEquals(response.getDoctors().get(1).getName(), "Name");
        assertEquals(response.getDoctors().get(1).getSurname(), "Surname2");
        assertEquals(response.getDoctors().get(1).getSpeciality(), "Speciality2");
    }

    @Ignore
    public void searchDoctorsOrderingDescending() {
        AddDoctorRequest request1 = new AddDoctorRequest("Name", "Surname1", "Speciality1");
        getAddDoctorService().execute(request1);

        AddDoctorRequest request2 = new AddDoctorRequest("Name", "Surname2", "Speciality2");
        getAddDoctorService().execute(request2);

        DoctorOrdering doctorOrdering = new DoctorOrdering("surname", "DESCENDING");
        SearchDoctorsRequest request3 = new SearchDoctorsRequest(null, "Name", null, null, doctorOrdering);
        SearchDoctorsResponse response = getSearchDoctorsService().execute(request3);

        assertEquals(response.getDoctors().size(), 2);
        assertEquals(response.getDoctors().get(0).getName(), "Name");
        assertEquals(response.getDoctors().get(0).getSurname(), "Surname2");
        assertEquals(response.getDoctors().get(0).getSpeciality(), "Speciality2");
        assertEquals(response.getDoctors().get(1).getName(), "Name");
        assertEquals(response.getDoctors().get(1).getSurname(), "Surname1");
        assertEquals(response.getDoctors().get(1).getSpeciality(), "Speciality1");
    }

    @Ignore
    public void searchDoctorsOrderingAscending() {
        AddDoctorRequest request1 = new AddDoctorRequest("Name", "Surname1", "Speciality1");
        getAddDoctorService().execute(request1);

        AddDoctorRequest request2 = new AddDoctorRequest("Name", "Surname2", "Speciality2");
        getAddDoctorService().execute(request2);

        DoctorOrdering doctorOrdering = new DoctorOrdering("surname", "ASCENDING");
        SearchDoctorsRequest request3 = new SearchDoctorsRequest(null, "Name", null, null, doctorOrdering);
        SearchDoctorsResponse response = getSearchDoctorsService().execute(request3);

        assertEquals(response.getDoctors().size(), 2);
        assertEquals(response.getDoctors().get(0).getName(), "Name");
        assertEquals(response.getDoctors().get(0).getSurname(), "Surname1");
        assertEquals(response.getDoctors().get(0).getSpeciality(), "Speciality1");
        assertEquals(response.getDoctors().get(1).getName(), "Name");
        assertEquals(response.getDoctors().get(1).getSurname(), "Surname2");
        assertEquals(response.getDoctors().get(1).getSpeciality(), "Speciality2");
    }

    @Ignore
    public void searchDoctorsOrderingPaging() {
        AddDoctorRequest request1 = new AddDoctorRequest("Name", "Surname1", "Speciality1");
        getAddDoctorService().execute(request1);

        AddDoctorRequest request2 = new AddDoctorRequest("Name", "Surname2", "Speciality2");
        getAddDoctorService().execute(request2);

        DoctorOrdering doctorOrdering = new DoctorOrdering("surname", "ASCENDING");
        DoctorPaging doctorPaging = new DoctorPaging(1, 1);
        SearchDoctorsRequest request3 = new SearchDoctorsRequest(null, "Name", null, null, doctorOrdering, doctorPaging);
        SearchDoctorsResponse response = getSearchDoctorsService().execute(request3);

        assertEquals(response.getDoctors().size(), 1);
        assertEquals(response.getDoctors().get(0).getName(), "Name");
        assertEquals(response.getDoctors().get(0).getSurname(), "Surname1");
        assertEquals(response.getDoctors().get(0).getSpeciality(), "Speciality1");
    }

    private AddDoctorService getAddDoctorService() {
        return appContext.getBean(AddDoctorService.class);
    }

    private SearchDoctorsService getSearchDoctorsService() {
        return appContext.getBean(SearchDoctorsService.class);
    }

    private DatabaseCleaner getDatabaseCleaner() {
        return appContext.getBean(DatabaseCleaner.class);
    }
}
