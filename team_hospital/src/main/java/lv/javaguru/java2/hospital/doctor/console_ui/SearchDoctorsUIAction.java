package lv.javaguru.java2.hospital.doctor.console_ui;

import lv.javaguru.java2.hospital.doctor.core.requests.DoctorOrdering;
import lv.javaguru.java2.hospital.doctor.core.requests.DoctorPaging;
import lv.javaguru.java2.hospital.doctor.core.requests.SearchDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.SearchDoctorsResponse;
import lv.javaguru.java2.hospital.doctor.core.services.SearchDoctorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchDoctorsUIAction implements DoctorUIAction {

    @Autowired private SearchDoctorsService searchDoctorsService;

    @Override
    public void execute() {
        GetUserInput getUserInput = new GetUserInput();
        String name = getUserInput.getUserStringInput("Enter doctor name: ");
        String surname = getUserInput.getUserStringInput("Enter doctor surname: ");
        String speciality = getUserInput.getUserStringInput("Enter doctor speciality: ");

        String orderBy = getUserInput.getUserStringInput("Enter orderBy (name||surname||speciality): ");
        String orderDirection = getUserInput.getUserStringInput("Enter orderDirection (ASCENDING||DESCENDING): ");
        DoctorOrdering doctorOrdering = new DoctorOrdering(orderBy, orderDirection);

        Integer pageNumber = getUserInput.getUserNumericInput("Enter pageNumber: ");
        Integer pageSize = getUserInput.getUserNumericInput("Enter pageSize: ");
        DoctorPaging doctorPaging = new DoctorPaging(pageNumber, pageSize);

        SearchDoctorsRequest request = new SearchDoctorsRequest(name, surname, speciality, doctorOrdering, doctorPaging);
        SearchDoctorsResponse response = searchDoctorsService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            System.out.println(response.getDoctors());
        }

    }
}
