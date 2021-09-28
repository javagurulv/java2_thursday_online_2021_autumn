package lv.javaguru.java2.hospital.doctor.acceptancetests;

import lv.javaguru.java2.hospital.DoctorApplicationContext;
import lv.javaguru.java2.hospital.doctor.core.requests.*;
import lv.javaguru.java2.hospital.doctor.core.responses.SearchDoctorsResponse;
import lv.javaguru.java2.hospital.doctor.core.services.AddDoctorService;
import lv.javaguru.java2.hospital.doctor.core.services.SearchDoctorsService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AcceptanceTest2 {

    private DoctorApplicationContext appContest = new DoctorApplicationContext();

    @Test
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

    @Test
    public void searchDoctorsOrderingDescending() {
        AddDoctorRequest request1 = new AddDoctorRequest("Name", "Surname1", "Speciality1");
        getAddDoctorService().execute(request1);

        AddDoctorRequest request2 = new AddDoctorRequest("Name", "Surname2", "Speciality2");
        getAddDoctorService().execute(request2);

        Ordering ordering = new Ordering("surname", "DESCENDING");
        SearchDoctorsRequest request3 = new SearchDoctorsRequest(null, "Name", null, null, ordering);
        SearchDoctorsResponse response = getSearchDoctorsService().execute(request3);

        assertEquals(response.getDoctors().size(), 2);
        assertEquals(response.getDoctors().get(0).getName(), "Name");
        assertEquals(response.getDoctors().get(0).getSurname(), "Surname2");
        assertEquals(response.getDoctors().get(0).getSpeciality(), "Speciality2");
        assertEquals(response.getDoctors().get(1).getName(), "Name");
        assertEquals(response.getDoctors().get(1).getSurname(), "Surname1");
        assertEquals(response.getDoctors().get(1).getSpeciality(), "Speciality1");
    }

    @Test
    public void searchDoctorsOrderingAscending() {
        AddDoctorRequest request1 = new AddDoctorRequest("Name", "Surname1", "Speciality1");
        getAddDoctorService().execute(request1);

        AddDoctorRequest request2 = new AddDoctorRequest("Name", "Surname2", "Speciality2");
        getAddDoctorService().execute(request2);

        Ordering ordering = new Ordering("surname", "ASCENDING");
        SearchDoctorsRequest request3 = new SearchDoctorsRequest(null, "Name", null, null, ordering);
        SearchDoctorsResponse response = getSearchDoctorsService().execute(request3);

        assertEquals(response.getDoctors().size(), 2);
        assertEquals(response.getDoctors().get(0).getName(), "Name");
        assertEquals(response.getDoctors().get(0).getSurname(), "Surname1");
        assertEquals(response.getDoctors().get(0).getSpeciality(), "Speciality1");
        assertEquals(response.getDoctors().get(1).getName(), "Name");
        assertEquals(response.getDoctors().get(1).getSurname(), "Surname2");
        assertEquals(response.getDoctors().get(1).getSpeciality(), "Speciality2");
    }

    @Test
    public void searchDoctorsOrderingPaging() {
        AddDoctorRequest request1 = new AddDoctorRequest("Name", "Surname1", "Speciality1");
        getAddDoctorService().execute(request1);

        AddDoctorRequest request2 = new AddDoctorRequest("Name", "Surname2", "Speciality2");
        getAddDoctorService().execute(request2);

        Ordering ordering = new Ordering("surname", "ASCENDING");
        Paging paging = new Paging(1, 1);
        SearchDoctorsRequest request3 = new SearchDoctorsRequest(null, "Name", null, null, ordering, paging);
        SearchDoctorsResponse response = getSearchDoctorsService().execute(request3);

        assertEquals(response.getDoctors().size(), 1);
        assertEquals(response.getDoctors().get(0).getName(), "Name");
        assertEquals(response.getDoctors().get(0).getSurname(), "Surname1");
        assertEquals(response.getDoctors().get(0).getSpeciality(), "Speciality1");
    }

    private AddDoctorService getAddDoctorService() {
        return appContest.getBean(AddDoctorService.class);
    }

    private SearchDoctorsService getSearchDoctorsService() {
        return appContest.getBean(SearchDoctorsService.class);
    }
}
