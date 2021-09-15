package lv.javaguru.java2.hospital.doctor.console_ui;

import lv.javaguru.java2.hospital.doctor.core.requests.Ordering;
import lv.javaguru.java2.hospital.doctor.core.requests.SearchDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.SearchDoctorsResponse;
import lv.javaguru.java2.hospital.doctor.core.services.SearchDoctorsService;

public class SearchDoctorsUIAction implements DoctorUIActions {

    private SearchDoctorsService searchDoctorsService;

    public SearchDoctorsUIAction(SearchDoctorsService searchDoctorsService) {
        this.searchDoctorsService = searchDoctorsService;
    }

    @Override
    public void execute() {
        GetUserInput getUserInput = new GetUserInput();
        String id = getUserInput.getUserStringInput("Enter doctor id: ");
        String name = getUserInput.getUserStringInput("Enter doctor name: ");
        String surname = getUserInput.getUserStringInput("Enter doctor surname: ");
        String speciality = getUserInput.getUserStringInput("Enter doctor speciality: ");

        String orderBy = getUserInput.getUserStringInput("Enter orderBy (name||surname||speciality): ");
        String orderDirection = getUserInput.getUserStringInput("Enter orderDirection (ASCENDING||DESCENDING): ");
        Ordering ordering = new Ordering(orderBy, orderDirection);

        SearchDoctorsRequest request = new SearchDoctorsRequest(id, name, surname, speciality, ordering);
        SearchDoctorsResponse response = searchDoctorsService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            System.out.println(response.getDoctors());
        }

    }
}
