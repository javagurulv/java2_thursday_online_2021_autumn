package lv.javaguru.java2.hospital.doctor.acceptancetests;

import lv.javaguru.java2.hospital.DatabaseCleaner;
import lv.javaguru.java2.hospital.config.HospitalConfiguration;
import lv.javaguru.java2.hospital.doctor.core.requests.SearchDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.SearchDoctorsResponse;
import lv.javaguru.java2.hospital.doctor.core.services.SearchDoctorsService;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.ApplicationContext;
import lv.javaguru.java2.hospital.doctor.core.requests.AddDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.requests.EditDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.requests.ShowAllDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.AddDoctorResponse;
import lv.javaguru.java2.hospital.doctor.core.responses.EditDoctorResponse;
import lv.javaguru.java2.hospital.doctor.core.responses.ShowAllDoctorsResponse;
import lv.javaguru.java2.hospital.doctor.core.services.AddDoctorService;
import lv.javaguru.java2.hospital.doctor.core.services.EditDoctorService;
import lv.javaguru.java2.hospital.doctor.core.services.ShowAllDoctorsService;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AcceptanceTest4 {

    private ApplicationContext appContext;

    @BeforeEach
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(HospitalConfiguration.class);
        getDatabaseCleaner().clean();
    }

    @Ignore
    public void shouldEditDoctorName() {
        AddDoctorRequest request1 = new AddDoctorRequest("Name", "Surname1", "Speciality1");
		AddDoctorResponse response1 = getAddDoctorService().execute(request1);

        SearchDoctorsRequest request2 = new SearchDoctorsRequest(null, request1.getName(), request1.getSurname(), request1.getSpeciality());
        SearchDoctorsResponse response2 = getSearchDoctorService().execute(request2);
		Long doctorId = response2.getDoctors().get(0).getId();

        EditDoctorRequest request3 = new EditDoctorRequest(doctorId, "NAME", "Name1");
        EditDoctorResponse response3 = getEditDoctorService().execute(request3);

        assertTrue(response3.isDoctorEdited());

        ShowAllDoctorsResponse response4 = getShowAllDoctorsService().execute(new ShowAllDoctorsRequest());

        assertEquals(response4.getDoctors().size(), 1);
        assertEquals(response4.getDoctors().get(0).getName(), "Name1");
        assertEquals(response4.getDoctors().get(0).getSurname(), "Surname1");
        assertEquals(response4.getDoctors().get(0).getSpeciality(), "Speciality1");
    }

    @Ignore
    public void shouldEditDoctorSurname() {
        AddDoctorRequest request1 = new AddDoctorRequest("Name1", "Surname", "Speciality1");
        AddDoctorResponse response1 = getAddDoctorService().execute(request1);

        SearchDoctorsRequest request2 = new SearchDoctorsRequest(null, request1.getName(), request1.getSurname(), request1.getSpeciality());
        SearchDoctorsResponse response2 = getSearchDoctorService().execute(request2);
        Long doctorId = response2.getDoctors().get(0).getId();

		EditDoctorRequest request3 = new EditDoctorRequest(doctorId, "SURNAME", "Surname1");
        EditDoctorResponse response3 = getEditDoctorService().execute(request3);

        assertTrue(response3.isDoctorEdited());

        ShowAllDoctorsResponse response4 = getShowAllDoctorsService().execute(new ShowAllDoctorsRequest());

        assertEquals(response4.getDoctors().size(), 1);
        assertEquals(response4.getDoctors().get(0).getName(), "Name1");
        assertEquals(response4.getDoctors().get(0).getSurname(), "Surname1");
        assertEquals(response4.getDoctors().get(0).getSpeciality(), "Speciality1");
    }

    @Ignore
    public void shouldEditDoctorSpeciality() {
        AddDoctorRequest request1 = new AddDoctorRequest("Name1", "Surname1", "Speciality");
        AddDoctorResponse response1 = getAddDoctorService().execute(request1);

       SearchDoctorsRequest request2 = new SearchDoctorsRequest(null, request1.getName(), request1.getSurname(), request1.getSpeciality());
       SearchDoctorsResponse response2 = getSearchDoctorService().execute(request2);
       Long doctorId = response2.getDoctors().get(0).getId();

	   EditDoctorRequest request3 = new EditDoctorRequest(doctorId, "SPECIALITY", "Speciality1");
        EditDoctorResponse response3 = getEditDoctorService().execute(request3);

       assertTrue(response3.isDoctorEdited());

        ShowAllDoctorsResponse response4 = getShowAllDoctorsService().execute(new ShowAllDoctorsRequest());

        assertEquals(response4.getDoctors().size(), 1);
        assertEquals(response4.getDoctors().get(0).getName(), "Name1");
        assertEquals(response4.getDoctors().get(0).getSurname(), "Surname1");
        assertEquals(response4.getDoctors().get(0).getSpeciality(), "Speciality1");
    }


    private AddDoctorService getAddDoctorService() {
        return appContext.getBean(AddDoctorService.class);
    }

    private SearchDoctorsService getSearchDoctorService() {
        return appContext.getBean(SearchDoctorsService.class);
    }

    private ShowAllDoctorsService getShowAllDoctorsService() {
        return appContext.getBean(ShowAllDoctorsService.class);
    }

    private EditDoctorService getEditDoctorService() {
        return appContext.getBean(EditDoctorService.class);
    }

    private DatabaseCleaner getDatabaseCleaner() {
        return appContext.getBean(DatabaseCleaner.class);
    }
}
