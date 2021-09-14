package lv.javaguru.java2.hospital.doctor.console_ui;

import lv.javaguru.java2.hospital.doctor.core.requests.SearchDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.SearchDoctorsResponse;
import lv.javaguru.java2.hospital.doctor.core.services.SearchDoctorsService;
import lv.javaguru.java2.hospital.domain.Doctor;

public class SearchDoctorsUIAction implements DoctorUIActions{

    private SearchDoctorsService searchDoctorsService;

    public SearchDoctorsUIAction(SearchDoctorsService searchDoctorsService) {
        this.searchDoctorsService = searchDoctorsService;
    }

    @Override
    public void execute() {
        GetUserInput getUserInput = new GetUserInput();
        String name = getUserInput.getUserStringInput("Enter doctor name: ");
        String surname = getUserInput.getUserStringInput("Enter doctor surname: ");

        SearchDoctorsRequest request = new SearchDoctorsRequest(name, surname);
        SearchDoctorsResponse response = searchDoctorsService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            System.out.println(response.getDoctors());
        }

    }
}
